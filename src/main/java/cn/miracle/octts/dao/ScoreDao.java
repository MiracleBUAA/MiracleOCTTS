package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Score;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hf on 2017/7/2.
 */
@Mapper
@Component
public interface ScoreDao extends BaseMapper<Score> {

    @Select("SELECT gmt_create, gmt_modified, uid, score_id, course_id, homework_id, group_id, grader_id, score, score_message " +
            "FROM score " +
            "WHERE score_id = #{score_id}")
    @ResultMap("cn.miracle.octts.dao.ScoreDao.ScoreDetail")
    Score findScoreById(Integer score_id);

    @Select("SELECT gmt_create, gmt_modified, uid, score_id, course_id, homework_id, group_id, grader_id, score, score_message " +
            "FROM score " +
            "WHERE homework_id=#{homework_id} AND group_id=#{group_id}")
    @ResultMap("cn.miracle.octts.dao.ScoreDao.ScoreDetail")
    Score findScoreByHomeworkIdAndGroupId(@Param(value = "homework_id") Integer homework_id,
                                          @Param(value = "group_id") Integer group_id);

    @Select("SELECT gmt_create, gmt_modified, uid, score_id, course_id, homework_id, group_id, grader_id, score, score_message " +
            "FROM score " +
            "WHERE group_id=#{group_id}")
    @ResultMap("cn.miracle.octts.dao.ScoreDao.ScoreDetail")
    List<Score> findScoreByGroupId(@Param(value = "group_id") Integer group_id);

    @Insert("INSERT INTO score(gmt_create, gmt_modified, uid, score_id, course_id, homework_id, group_id, grader_id, score, score_message) " +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{score_id}, #{course_id}, #{homework_id}, #{group_id}, #{grader_id}, #{score}, #{score_message})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void InsertScore(Score score);

    @Select("SELECT MAX(score_id) FROM score")
    Integer findMaxId();

    @Update("UPDATE score " +
            "SET gmt_modified=#{updatetime}, uid=#{uid}, course_id=#{course_id}, homework_id=#{homework_id}, group_id=#{group_id}, grader_id=#{grader_id}, score=#{score}, score_message=#{score_message} " +
            "WHERE score_id=#{score_id}")
    void updateScore(Score score);

    @Select("SELECT score FROM score WHERE homework_id = #{homework_id} AND group_id = #{group_id}")
    Double findScoreValueByHomeworkIdAndGroupId(@Param(value = "homework_id") Integer homework_id,
                                                @Param(value = "group_id") Integer group_id);
}
