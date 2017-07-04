package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyMemberDao;
import cn.miracle.octts.dao.GroupConfirmMemberDao;
import cn.miracle.octts.dao.InvitationDao;
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

    @Autowired
    private InvitationDao invitationDao;


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

    public List<HashMap<String, Object>> getReceiverList(String sender_id) {
        List<HashMap<String, Object>> receiverList = new ArrayList<HashMap<String, Object>>();

        Iterator<String> receiverIter = invitationDao.findReceiverIdBySenderId(sender_id).iterator();
        while (receiverIter.hasNext()) {
            HashMap<String, Object> receiver = student2Json(findStudentById(receiverIter.next()));
            receiverList.add(receiver);
        }
        return receiverList;
    }

    public List<HashMap<String, Object>> getMemberList(List<String> memberIdList) {
        List<HashMap<String, Object>> memberlist = new ArrayList<HashMap<String, Object>>();

        Iterator<String> memberIdIter = memberIdList.iterator();
        while (memberIdIter.hasNext()) {
            HashMap<String, Object> student = student2Json(findStudentById(memberIdIter.next()));
            memberlist.add(student);
        }
        return memberlist;
    }

    public Integer setGroupScoreById(String student_id, Double group_score) {
        return studentDao.setGroupScoreById(student_id, group_score);
    }

    public Double setPersonalScoreById(Student student) {
        Double group_score = student.getGroup_score();
        Double student_rate = student.getStudent_rate();
        HashMap<Integer, Double> absent_punish = new HashMap<>();
        absent_punish.put(1, 0.5);
        absent_punish.put(2, 1.0);
        absent_punish.put(3, 4.5);
        absent_punish.put(4, 8.0);
        absent_punish.put(5, 11.5);
        absent_punish.put(6, 15.0);
        absent_punish.put(7, 19.5);
        absent_punish.put(8, 24.0);
        absent_punish.put(9, 32.0);
        absent_punish.put(10, 40.0);
        absent_punish.put(11, 45.0);
        absent_punish.put(12, 50.0);
        Double absent_punish_score = absent_punish.get(student.getStudent_absent());
        Double personal_score = group_score * student_rate - absent_punish_score;
        student.setPersonal_score(personal_score);
        studentDao.setPersonalScoreById(student);
        return personal_score;
    }

    public List<HashMap<String, Object>> getMemberRateList(Integer group_id) {
        List<HashMap<String, Object>> memberRateList = new ArrayList<HashMap<String, Object>>();

        Iterator<String> studentIdIter = groupConfirmMemberDao.findStudentIdByGroupId(group_id).iterator();
        while (studentIdIter.hasNext()) {
            Student student = findStudentById(studentIdIter.next());
            HashMap<String, Object> memberRate = new HashMap<String, Object>();
            memberRate.put("student_id", student.getStudent_id());
            memberRate.put("student_name", student.getStudent_name());
            memberRate.put("student_rate", student.getStudent_rate());

            memberRateList.add(memberRate);
        }
        return memberRateList;
    }

    public Integer setStudentAbsentById(String student_id, Integer student_absent) {
        Student student = findStudentById(student_id);
        student.setStudent_absent(student_absent);

        Date currentTime = new Date(System.currentTimeMillis());
        student.setUpdatetime(currentTime);

        return studentDao.updateStudentById(student);
    }

    public Integer updateStudentRateById(Double student_rate, String student_id, String uid) {
        Student student = findStudentById(student_id);
        student.setStudent_rate(student_rate);

        Date currentTime = new Date(System.currentTimeMillis());
        student.setUpdatetime(currentTime);
        student.setUid(uid);

        return studentDao.updateStudentById(student);
    }


}
