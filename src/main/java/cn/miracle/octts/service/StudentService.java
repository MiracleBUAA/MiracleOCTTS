package cn.miracle.octts.service;

import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hf on 2017/6/26.
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> findAllStudent() {
        return studentDao.findAll();
    }

    public Student findStudentById(Integer id) {
        return studentDao.findById(id);
    }

    public Student findStudentByIdForLogin(Integer student_id) {
        return studentDao.findByIdforLogin(student_id);
    }
}
