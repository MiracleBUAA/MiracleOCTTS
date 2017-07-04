package cn.miracle.octts.util;

import cn.miracle.octts.entity.GroupConfirm;
import cn.miracle.octts.entity.GroupConfirmMember;
import cn.miracle.octts.entity.Homework;
import cn.miracle.octts.entity.Student;
import cn.miracle.octts.service.*;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tony on 2017/7/3.
 */
@Component
public class ExportForm {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    @Autowired
    GroupConfirmService groupConfirmService;

    @Autowired
    GroupConfirmMemberService groupConfirmMemberService;

    @Autowired
    StudentService studentService;

    @Autowired
    HomeworkService homeworkService;

    @Autowired
    ScoreSerivce scoreSerivce;

    /**
     * 生成团队组建报表
     *
     * @return
     * @throws IOException
     * @throws RowsExceededException
     * @throws WriteException
     */
    public String exportGroupList() throws IOException, RowsExceededException, WriteException {
        String path = "file" + FILE_SEPARATOR + "form" + FILE_SEPARATOR + "group_list.xls";

        WritableWorkbook writeBook = Workbook.createWorkbook(new File(path));
        WritableSheet firstSheet = writeBook.createSheet("团队组建报表", 0);
        Label label;

        label = new Label(0, 0, "团队名称");
        firstSheet.addCell(label);
        label = new Label(1, 0, "团队编号");
        firstSheet.addCell(label);
        label = new Label(2, 0, "成员姓名");
        firstSheet.addCell(label);
        label = new Label(3, 0, "成员学号");
        firstSheet.addCell(label);
        label = new Label(4, 0, "团队角色");
        firstSheet.addCell(label);

        List<GroupConfirmMember> memberList = groupConfirmMemberService.findAllGroupConfirmMember();
        if (memberList != null) {
            Iterator<GroupConfirmMember> memberListIter = memberList.iterator();
            int row = 0;
            while (memberListIter.hasNext()) {
                row++;
                GroupConfirmMember member = memberListIter.next();

                label = new Label(0, row, groupConfirmService.findGroupConfirmNameById(member.getGroup_id()).toString());
                firstSheet.addCell(label);

                label = new Label(1, row, member.getGroup_id().toString());
                firstSheet.addCell(label);

                label = new Label(2, row, studentService.findStudentNameById(member.getStudent_id()));
                firstSheet.addCell(label);

                label = new Label(3, row, member.getStudent_id());
                firstSheet.addCell(label);

                label = new Label(4, row, member.getGroup_role().equals(2) ? "团队负责人" : "团队成员");
                firstSheet.addCell(label);
            }
        }

        writeBook.write();
        writeBook.close();
        return path;
    }

    /**
     * 生成团队提交作业情况报表
     *
     * @return
     * @throws IOException
     * @throws RowsExceededException
     * @throws WriteException
     */
    public String exportGroupScoreList(Integer course_id) throws IOException, RowsExceededException, WriteException {
        String path = "file" + FILE_SEPARATOR + "form" + FILE_SEPARATOR + "group_score.xls";

        WritableWorkbook writeBook = Workbook.createWorkbook(new File(path));
        WritableSheet firstSheet = writeBook.createSheet("提交作业情况报表", 0);
        Label label;

        label = new Label(0, 0, "团队名称");
        firstSheet.addCell(label);
        label = new Label(1, 0, "团队编号");
        firstSheet.addCell(label);
        label = new Label(2, 0, "团队成绩");
        firstSheet.addCell(label);

        if (groupConfirmService.findGroupConfirmByCourseId(course_id) != null) {
            Iterator<GroupConfirm> groupListIter = groupConfirmService.findGroupConfirmByCourseId(course_id).iterator();
            int row = 0;

            while (groupListIter.hasNext()) {
                Double sumGroupScore = 0.0;
                row++;
                GroupConfirm group = groupListIter.next();

                label = new Label(0, row, group.getGroup_name());
                firstSheet.addCell(label);
                label = new Label(1, row, group.getGroup_id().toString());
                firstSheet.addCell(label);

                if (homeworkService.findHoweworkByCourseId(course_id) != null) {
                    Iterator<Homework> homeworkIter = homeworkService.findHoweworkByCourseId(course_id).iterator();
                    int col = 2;

                    while (homeworkIter.hasNext()) {
                        col++;
                        Homework homework = homeworkIter.next();

                        label = new Label(col, 0, homework.getHomework_title());
                        firstSheet.addCell(label);

                        Double score = scoreSerivce.findScoreValueByHomeworkIdAndGroupId(homework.getHomework_id(), group.getGroup_id());
                        sumGroupScore += (score == null) ? 0.0 : score;
                        label = new Label(col, row, (score == null) ? "0" : score.toString());
                        firstSheet.addCell(label);
                    }
                }

                label = new Label(2, row, sumGroupScore.toString());
                firstSheet.addCell(label);
            }
        }

        writeBook.write();
        writeBook.close();
        return path;
    }

    /**
     * 生成个人成绩报表
     *
     * @return
     * @throws IOException
     * @throws RowsExceededException
     * @throws WriteException
     */
    public String exportStudentScoreList() throws IOException, RowsExceededException, WriteException {
        String path = "file" + FILE_SEPARATOR + "form" + FILE_SEPARATOR + "student_score.xls";

        WritableWorkbook writeBook = Workbook.createWorkbook(new File(path));
        WritableSheet firstSheet = writeBook.createSheet("个人成绩报表", 0);
        Label label;

        label = new Label(0, 0, "学生学号");
        firstSheet.addCell(label);
        label = new Label(1, 0, "学生姓名");
        firstSheet.addCell(label);
        label = new Label(2, 0, "个人成绩");
        firstSheet.addCell(label);
        label = new Label(3, 0, "团队成绩");
        firstSheet.addCell(label);
        label = new Label(4, 0, "业务系数");
        firstSheet.addCell(label);
        label = new Label(5, 0, "缺勤次数");
        firstSheet.addCell(label);

        if (studentService.findAllStudent() != null) {
            Iterator<Student> stuentIter = studentService.findAllStudent().iterator();
            int row = 0;

            while (stuentIter.hasNext()) {
                row++;
                Student student = stuentIter.next();

                label = new Label(0, row, student.getStudent_id());
                firstSheet.addCell(label);
                label = new Label(1, row, student.getStudent_name());
                firstSheet.addCell(label);
                label = new Label(2, row, student.getPersonal_score().toString());
                firstSheet.addCell(label);
                label = new Label(3, row, student.getGroup_score().toString());
                firstSheet.addCell(label);
                label = new Label(4, row, student.getStudent_rate().toString());
                firstSheet.addCell(label);
                label = new Label(5, row, student.getStudent_absent().toString());
                firstSheet.addCell(label);
            }
        }

        writeBook.write();
        writeBook.close();
        return path;
    }

}
