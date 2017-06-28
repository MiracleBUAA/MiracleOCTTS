package cn.miracle.octts.service;

import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Student;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by hf on 2017/6/27.
 */
@Service
public class TeacherService {

    @Autowired
    private StudentDao studentDao;

    public int importStudentList() {
        jxl.Workbook readwb = null;

        Date currentTime = new Date(System.currentTimeMillis());
        int studentCount = 0;

        try {
            String xlsurl = "C:\\Users\\Tony\\Documents\\student_list.xls";
            InputStream instream = new FileInputStream(xlsurl);
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
                    student.setName(cell.getContents());
                    cell = readsheet.getCell(2, row);
                    student.setGender(cell.getContents().charAt(0));
                    cell = readsheet.getCell(3, row);
                    student.setStudent_class(cell.getContents());
                }
                student.setCreatetime(currentTime);
                student.setUpdatetime(currentTime);
                studentCount += studentDao.insertStudent(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readwb.close();
        }
        return studentCount;
    }
}
