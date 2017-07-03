package cn.miracle.octts.util;

import cn.miracle.octts.entity.GroupConfirmMember;
import cn.miracle.octts.service.GroupConfirmMemberService;
import cn.miracle.octts.service.GroupConfirmService;
import cn.miracle.octts.service.HomeworkService;
import cn.miracle.octts.service.StudentService;
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

                Integer gid = member.getGroup_id();

                label = new Label(0, row, groupConfirmService.findGroupConfirmNameById(gid).toString());
                firstSheet.addCell(label);

                label = new Label(1, row, gid.toString());
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
     * 生成团队成绩报表
     *
     * @return
     * @throws IOException
     * @throws RowsExceededException
     * @throws WriteException
     */
    public String exportGroupScoreList() throws IOException, RowsExceededException, WriteException {
        String path = "file" + FILE_SEPARATOR + "form" + FILE_SEPARATOR + "group_score.xls";

        WritableWorkbook writeBook = Workbook.createWorkbook(new File(path));
        WritableSheet firstSheet = writeBook.createSheet("团队成绩报表", 0);
        Label label;

        label = new Label(0, 0, "团队名称");
        firstSheet.addCell(label);
        label = new Label(1, 0, "团队编号");
        firstSheet.addCell(label);
        label = new Label(2, 0, "团队总成绩");
        firstSheet.addCell(label);

        if (homeworkService.findAllHomeworkId() != null) {
            Iterator<Integer> homeworkIdIter = homeworkService.findAllHomeworkId().iterator();
            while (homeworkIdIter.hasNext()) {
                Integer homeworkId = homeworkIdIter.next();


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

        label = new Label(0, 0, "学号");
        firstSheet.addCell(label);
        label = new Label(1, 0, "姓名");
        firstSheet.addCell(label);
        label = new Label(2, 0, "成绩");
        firstSheet.addCell(label);

        writeBook.write();
        writeBook.close();
        return path;
    }

}
