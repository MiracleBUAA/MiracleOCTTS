package cn.miracle.octts.service;

import cn.miracle.octts.dao.HomeworkUploadDao;
import cn.miracle.octts.entity.HomeworkUpload;
import cn.miracle.octts.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void InsertHomeworkUpload(HomeworkUpload homeworkUpload) {
        Date currentDate = new Date(System.currentTimeMillis());
        homeworkUpload.setCreatetime(currentDate);
        homeworkUpload.setUpdatetime(currentDate);
        homeworkUploadDao.InsertHomeworkUpload(homeworkUpload);
    }

    public HomeworkUpload findHomeworkUploadById(Integer homework_upload_id) {
        return homeworkUploadDao.findHomeworkUploadById(homework_upload_id);
    }

    public List<HashMap<String, Object>> getHomeworkUploadList(Integer homework_id) {
        List<HashMap<String, Object>> homework_upload_list = new ArrayList<HashMap<String, Object>>();

        ArrayList<HomeworkUpload> homework_uploads = new ArrayList<>();

        homework_uploads.addAll(homeworkUploadDao.findHomeworkUploadByHomeworkId(homework_id));
        for(HomeworkUpload homework_upload : homework_uploads) {
            HashMap<String, Object> homework_upload_map = new HashMap<>();
            homework_upload_map.put("homework_upload_id", homework_upload.getHomework_upload_id());
            homework_upload_map.put("group_id", homework_upload.getGroup_id());

            // TODO: 获取团队名
            homework_upload_map.put("group_name", "23333");


            homework_upload_list.add(homework_upload_map);
        }

        return homework_upload_list;
    }
}
