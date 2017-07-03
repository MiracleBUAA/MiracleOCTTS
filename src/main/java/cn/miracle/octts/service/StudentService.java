package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyMemberDao;
import cn.miracle.octts.dao.GroupConfirmMemberDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hf on 2017/6/26.
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupApplyMemberDao groupApplyMemberDao;

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

    public String findStudentNameById(String student_id) {
        return studentDao.findStudentNameById(student_id);
    }

    public List<String> findAllStudentId() {
        return studentDao.findAllStudentId();
    }

    public HashMap<String, Object> student2Json(Student student) {
        HashMap<String, Object> data = new HashMap<>();

        data.put("student_id", student.getStudent_id());
        data.put("student_name", student.getStudent_name());
        data.put("student_gender", student.getStudent_gender());
        data.put("student_class", student.getStudent_class());

        return data;
    }


    public List<HashMap<String, Object>> getStudentList() {
        List<HashMap<String, Object>> studentList = new ArrayList<HashMap<String, Object>>();

        List<Student> studentResult = findAllStudent();
        Iterator<Student> studentIter = studentResult.iterator();
        while (studentIter.hasNext()) {
            HashMap<String, Object> student = student2Json(studentIter.next());
            studentList.add(student);
        }
        return studentList;
    }

    public Set<String> getStudentNotInGroupSet(Integer course_id) {
        Set<String> resultSet = new HashSet<String>();

        Set<String> universalSet = new HashSet<String>(findAllStudentId());
        Set<String> groupConfirmSet = new HashSet<String>(groupConfirmMemberDao.findStudentIdByCourseId(course_id));
        Set<String> groupApplySet = new HashSet<String>(groupApplyMemberDao.findStudentIdByCourseId(course_id));

        //求差集
        resultSet.clear();
        resultSet.addAll(universalSet);
        resultSet.removeAll(groupConfirmSet);
        resultSet.removeAll(groupApplySet);

        return resultSet;
    }

    public List<HashMap<String, Object>> getStudentNotInGroup(Integer course_id) {
        List<HashMap<String, Object>> student_list = new ArrayList<HashMap<String, Object>>();

        Iterator<String> studentIdIter = getStudentNotInGroupSet(course_id).iterator();
        while (studentIdIter.hasNext()) {
            HashMap<String, Object> student = student2Json(findStudentById(studentIdIter.next()));
            student_list.add(student);
        }
        return student_list;
    }

}
