package cn.miracle.octts.service;

import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.dao.TeacherDao;
import cn.miracle.octts.entity.Announcement;
import cn.miracle.octts.entity.Student;
import cn.miracle.octts.entity.Teacher;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by hf on 2017/6/27.
 */
@Service
public class TeacherService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    public Teacher findTeacherByIdForLogin(String teacher_id) {
        return teacherDao.findByIdForLogin(teacher_id);
    }

    public Integer importStudentList(String ListUrl, String uid) {
        jxl.Workbook readwb = null;
        int studentCount = 0;
        try {
            InputStream instream = new FileInputStream(ListUrl);
            readwb = Workbook.getWorkbook(instream);
            Sheet readsheet = readwb.getSheet(0);
            int rsColumns = readsheet.getColumns();
            int rsRows = readsheet.getRows();

            for (int row = 1; row < rsRows; row++) {
                Student student = new Student();
                Cell cell;
                for (int col = 0; col < rsColumns; col++) {
                    cell = readsheet.getCell(0, row);
                    student.setStudent_id(cell.getContents());
                    student.setPassword(student.getStudent_id());
                    cell = readsheet.getCell(1, row);
                    student.setStudent_name(cell.getContents());
                    cell = readsheet.getCell(2, row);
                    student.setStudent_gender(cell.getContents());
                    cell = readsheet.getCell(3, row);
                    student.setStudent_class(cell.getContents());
                }
                Date currentTime = new Date(System.currentTimeMillis());
                student.setCreatetime(currentTime);
                student.setUpdatetime(currentTime);
                student.setUid(uid);

                studentCount += studentDao.insertStudent(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (readwb != null) {
                readwb.close();
            }
        }
        return studentCount;
    }

    public String findTeacherNameById(String teacher_id) {
        return teacherDao.findTeacherNameById(teacher_id);
    }

}
