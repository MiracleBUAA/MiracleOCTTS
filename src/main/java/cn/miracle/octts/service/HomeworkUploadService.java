package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupConfirmDao;
import cn.miracle.octts.dao.HomeworkUploadDao;
import cn.miracle.octts.dao.ScoreDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.GroupConfirm;
import cn.miracle.octts.entity.HomeworkUpload;
import cn.miracle.octts.entity.Resource;
import cn.miracle.octts.entity.Score;
import cn.miracle.octts.util.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hf on 2017/6/29.
 */
@Service
public class HomeworkUploadService {

    @Autowired
    private HomeworkUploadDao homeworkUploadDao;

    @Autowired
    private GroupConfirmDao groupConfirmDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ScoreDao scoreDao;

    public void InsertHomeworkUpload(HomeworkUpload homeworkUpload) {
        Date currentDate = new Date(System.currentTimeMillis());
        homeworkUpload.setCreatetime(currentDate);
        homeworkUpload.setUpdatetime(currentDate);
        homeworkUploadDao.InsertHomeworkUpload(homeworkUpload);
    }

    public HomeworkUpload findHomeworkUploadById(Integer homework_upload_id) {
        return homeworkUploadDao.findHomeworkUploadById(homework_upload_id);
    }

    public List<HomeworkUpload> findHomeworkUploadByHomeworkId (Integer homework_id) {
        return homeworkUploadDao.findHomeworkUploadByHomeworkId(homework_id);
    }

    public List<HomeworkUpload> findHomeworkUploadByHomeworkIdAndGroupId (Integer homework_id, Integer group_id) {
        return homeworkUploadDao.findHomeworkUploadByHomeworkIdAndGroupId(homework_id, group_id);
    }

    public List<HashMap<String, Object>> getHomeworkUploadList (ArrayList<HomeworkUpload> homework_uploads ) throws ParseException {
        List<HashMap<String, Object>> homework_upload_list = new ArrayList<HashMap<String, Object>>();

        for(HomeworkUpload homework_upload : homework_uploads) {
            HashMap<String, Object> homework_upload_map = new HashMap<>();
            homework_upload_map.put("homework_upload_id", homework_upload.getHomework_upload_id());
            homework_upload_map.put("homework_upload_time", DateConvert.datetime2String(homework_upload.getCreatetime()));
            homework_upload_map.put("homework_file_name", homework_upload.getFile_name());
            homework_upload_map.put("resubmit_limit", homework_upload.getResubmit());
//            homework_upload_map.put("group_id", homework_upload.getGroup_id());
//            // 获取团队名和团队负责人信息
//            GroupConfirm groupConfirm = groupConfirmDao.findGroupConfirmById(homework_upload.getGroup_id());
//            homework_upload_map.put("group_name", groupConfirm.getGroup_name());
//            homework_upload_map.put("group_owner", studentDao.findStudentNameById(groupConfirm.getGroup_owner_id()));
//            // 获取团队得分
//            Integer homework_id = homework_upload.getHomework_id();
//            Integer group_id = homework_upload.getGroup_id();
//            Score score = scoreDao.findScoreByHomeworkIdAndGroupId(homework_id, group_id);
//            homework_upload_map.put("score", score.getScore());
//            homework_upload_map.put("score_message", score.getScore_message());

            homework_upload_list.add(homework_upload_map);
        }
        return homework_upload_list;
    }
}
