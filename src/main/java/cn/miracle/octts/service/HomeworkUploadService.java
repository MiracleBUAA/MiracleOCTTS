package cn.miracle.octts.service;

import cn.miracle.octts.dao.HomeworkUploadDao;
import cn.miracle.octts.entity.HomeworkUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
