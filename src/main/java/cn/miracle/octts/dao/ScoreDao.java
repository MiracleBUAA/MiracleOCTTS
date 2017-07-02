package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Created by hf on 2017/7/2.
 */
public interface ScoreDao extends BaseMapper<Score> {

    @Select("SELECT gmt_create, gmt_modified, uid, score_id, course_id, homework_id, group_id, grader_id, score, score_message " +
            "FROM score " +
            "WHERE score_id = #{score_id}")
    Score findScoreById (Integer score_id);

    @Select("SELECT gmt_create, gmt_modified, uid, score_id, course_id, homework_id, group_id, grader_id, score, score_message " +
            "FROM score " +
            "WHERE homework_id=#{homework_id} AND group_id=#{group_id}")
    Score findScoreByHomeworkIdandGrooupId(Integer homework_id, Integer group_id);

    @Insert("INSERT INTO score(gmt_create, gmt_modified, uid, score_id, course_id, homework_id, group_id, grader_id, score, score_message) " +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{score_id}, #{course_id}, #{homework_id}, #{group_id}, #{grader_id}, #{score}, #{score_message})")
    void InsertScore(Score score);
}
