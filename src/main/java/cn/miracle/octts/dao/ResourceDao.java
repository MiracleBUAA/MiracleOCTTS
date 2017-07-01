package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Resource;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * Created by hf on 2017/6/28.
 */
public interface ResourceDao extends BaseMapper<Resource> {

    @Select("SELECT resource_id, course_id, teacher_id, resource_title, resource_url, resource_type" +
            "FROM resource " +
            "WHERE resource_id = #{resource_id}")
    @ResultMap("cn.miracle.octts.dao.ResourceDao.ResourceDetail")
    Resource findResourceById(Integer resource_id);

}
