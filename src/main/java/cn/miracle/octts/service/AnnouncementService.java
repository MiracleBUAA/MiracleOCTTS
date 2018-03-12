package cn.miracle.octts.service;

import cn.miracle.octts.dao.AnnouncementDao;
import cn.miracle.octts.entity.Announcement;
import cn.miracle.octts.util.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;


/**
 * Created by Tony on 2017/7/1.
 */
@Service
public class AnnouncementService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AnnouncementDao announcementDao;

    public Announcement findAnnouncementById(Integer announcement_id) {
        return announcementDao.findAnnouncementById(announcement_id);
    }

    public Integer findMaxAnnouncementId() {
        if (announcementDao.findMaxAnnouncementId() != null) {
            return announcementDao.findMaxAnnouncementId() + 1;
        } else {
            return new Integer(1);
        }
    }

    public Integer insertAnnouncement(Announcement announcement, String uid) {
        Date currentTime = new Date(System.currentTimeMillis());
        announcement.setCreatetime(currentTime);
        announcement.setUpdatetime(currentTime);
        announcement.setUid(uid);
        return announcementDao.insertAnnouncement(announcement);
    }

    public Integer updateAnnouncement(Announcement announcement, String uid) {
        Date currentTime = new Date(System.currentTimeMillis());
        announcement.setUpdatetime(currentTime);
        announcement.setUid(uid);
        return announcementDao.updateAnnouncement(announcement);
    }

    public List<Announcement> findAnnouncementByCourseId(Integer course_id) {
        return announcementDao.findAnnouncementByCourseId(course_id);
    }

    public HashMap<String, Object> announcement2Json(Announcement announcement) throws ParseException {
        HashMap<String, Object> data = new HashMap<>();

        data.put("announcement_id", announcement.getAnnouncement_id());
        data.put("teacher_name", teacherService.findTeacherNameById(announcement.getTeacher_id()));
        data.put("announcement_title", announcement.getAnnouncement_title());
        data.put("announcement_message", announcement.getAnnouncement_message());
        data.put("announcement_update_time", DateConvert.datetime2String(announcement.getUpdatetime()));

        return data;
    }

    public List<HashMap<String, Object>> getAnnouncementList(Integer course_id) throws ParseException {
        List<HashMap<String, Object>> announcement_list = new ArrayList<HashMap<String, Object>>();

        List<Announcement> announcement_result = findAnnouncementByCourseId(course_id);
        Iterator<Announcement> announcement_iter = announcement_result.iterator();
        while (announcement_iter.hasNext()) {
            HashMap<String, Object> announcement = announcement2Json(announcement_iter.next());
            announcement_list.add(announcement);
        }
        return announcement_list;
    }
}
