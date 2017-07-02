package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.HomeworkUpload;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by Tony on 2017/6/29.
 */
public interface HomeworkUploadDao extends BaseMapper<HomeworkUpload> {

    @Insert("Insert into homework_upload(gmt_create,gmt_modified,uid,homework_upload_id," +
            "course_id, group_id, homework_id, homework_url, message, homework_upload_time) " +
            "values(#{createtime},#{updatetime},#{uid},#{homework_upload_id}," +
            "#{course_id},#{group_id}, #{homework_id}, #{homework_url},#{message},#{homework_upload_time})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void InsertHomeworkUpload(HomeworkUpload homeworkUpload);


    @Select("SELECT gmt_create, gmt_modified, uid, homework_upload_id, course_id, homework_id, group_id, file_name, homework_url " +
            "FROM homework_upload " +
            "WHERE homework_id = #{homework_id}")
    @ResultMap("cn.miracle.octts.dao.HomeworkUploadDao.HomeworkUploadDetail")
    List<HomeworkUpload> findHomeworkUploadByHomeworkId(Integer homework_id);
}
