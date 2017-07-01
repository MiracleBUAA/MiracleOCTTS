package cn.miracle.octts.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.miracle.octts.util.FileUtils;

/**
 * Created by Tony on 2017/7/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUtilsTest {

    @Test
    public void TestGetSingleDownloadFile() throws Exception{
        FileUtils.getSingleDownloadFile("file\\upload_test2333.txt");
    }

}
