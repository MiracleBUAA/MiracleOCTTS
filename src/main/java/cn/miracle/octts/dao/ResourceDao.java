package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Resource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hf on 2017/6/28.
 */
public interface ResourceDao extends BaseMapper<Resource> {

    @Select("SELECT resource_id, course_id, teacher_id, resource_title, resource_url, resource_type " +
            "FROM resource " +
            "WHERE resource_id = #{resource_id}")
    @ResultMap("cn.miracle.octts.dao.ResourceDao.ResourceDetail")
    Resource findResourceById(Integer resource_id);

    @Select("SELECT DISTINCT resource_type FROM resource WHERE course_id=#{course_id}")
    List<String> findResourceType(Integer course_id);

    @Select("SELECT resource_id, resource_title, resource_type, teacher_id, gmt_create " +
            "FROM resource " +
            "WHERE course_id=#{course_id}")
    @ResultMap("cn.miracle.octts.dao.ResourceDao.ResourceDetail")
    List<Resource> findResourceByCourseId(Integer course_id);

    @Insert("INSERT INTO resource(gmt_create, gmt_modified, uid, resource_id, course_id, teacher_id, resource_title, resource_url, resource_type) " +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{resource_id}, #{course_id}, #{teacher_id}, #{resource_title}, #{resource_url}, #{resource_type}) ")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void InsertResource(Resource resource);

    @Select("SELECT max(resource_id) FROM resource ")
    Integer findMAxResourceId();

    @Delete("DELETE FROM resource WHERE resource_id=#{resource_id}")
    void deleteResourceById(Integer resource_id);
}
