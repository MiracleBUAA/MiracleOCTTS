package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Announcement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
 * Created by Tony on 2017/7/1.
 */
public interface AnnouncementDao extends BaseMapper<Announcement> {

    @Select("SELECT gmt_modified, uid, announcement_id, course_id, teacher_id, announcement_title, announcement_message " +
            "FROM announcement " +
            "WHERE announcement_id = #{announcement_id}")
    @ResultMap("cn.miracle.octts.dao.AnnouncementDao.AnnouncementDetail")
    Announcement findAnnouncementById(Integer announcement_id);

    @Select("SELECT max(announcement_id) FROM announcement")
    Integer findMaxAnnouncementId();

    @Select("SELECT gmt_modified, uid, announcement_id, course_id, teacher_id, announcement_title, announcement_message " +
            "FROM announcement " +
            "WHERE course_id = #{course_id}")
    @ResultMap("cn.miracle.octts.dao.AnnouncementDao.AnnouncementDetail")
    List<Announcement> findAnnouncementByCourseId(Integer course_id);

    @Insert("INSERT INTO announcement(gmt_create, gmt_modified, uid, announcement_id, course_id, teacher_id, announcement_title, announcement_message) " +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{announcement_id}, #{course_id}, #{teacher_id}, #{announcement_title}, #{announcement_message})")
    Integer insertAnnouncement(Announcement announcement);

    @Update("UPDATE announcement " +
            "SET gmt_modified = #{updatetime}, " +
            "uid = #{uid}, " +
            "teacher_id = #{teacher_id}, " +
            "announcement_title = #{announcement_title}, " +
            "announcement_message = #{announcement_message} " +
            "WHERE announcement_id = #{announcement_id}")
    Integer updateAnnouncement(Announcement announcement);
}
