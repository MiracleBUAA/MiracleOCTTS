package cn.miracle.octts.service;

import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.dao.GroupConfirmMemberDao;
import cn.miracle.octts.entity.GroupConfirmMember;
import cn.miracle.octts.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hf on 2017/6/26.
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupConfirmMemberDao groupConfirmMemberDao;

    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }

    public Student findStudentById(String id) {
        return studentDao.findStudentById(id);
    }

    public Student findStudentByIdForLogin(String student_id) {
        return studentDao.findByIdforLogin(student_id);
    }

    public HashMap<String, Object> adminStudent2Json(Student student) {
        HashMap<String, Object> data = new HashMap<>();

        data.put("student_id", student.getStudent_id());
        data.put("student_name", student.getStudent_name());
        data.put("student_gender", student.getStudent_gender());
        data.put("student_class", student.getStudent_class());

        return data;
    }

    public String findStudentNameById(String student_id) {
        return studentDao.findStudentNameById(student_id);
    }

}
