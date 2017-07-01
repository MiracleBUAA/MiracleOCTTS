package cn.miracle.octts.service;

import cn.miracle.octts.dao.AnnouncementDao;
import cn.miracle.octts.entity.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Tony on 2017/7/1.
 */
@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementDao announcementDao;

    public Announcement findAnnouncementById(Integer announcement_id) {
        return announcementDao.findAnnouncementById(announcement_id);
    }

    public Integer findMaxAnnouncementId() {
        if (announcementDao.findMaxAnnouncementId() != null)
            return announcementDao.findMaxAnnouncementId() + 1;
        else
            return new Integer(1);
    }

    public Integer insertAnnouncement(Announcement announcement, String uid) {
        Date currentTime = new Date(System.currentTimeMillis());
        announcement.setCreatetime(currentTime);
        announcement.setUpdatetime(currentTime);
        announcement.setUid(uid);
        return announcementDao.insertAnnouncement(announcement);
    }

    public Integer updateAnnouncement(Announcement announcement, String uid){
        Date currentTime = new Date(System.currentTimeMillis());
        announcement.setUpdatetime(currentTime);
        announcement.setUid(uid);
        return announcementDao.updateAnnouncement(announcement);
    }
}
