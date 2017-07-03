package cn.miracle.octts.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Tony on 2017/7/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExportFormTest {

    @Autowired
    ExportForm exportForm;

    @Test
    public void testExportGroupList() throws Exception {
        exportForm.exportGroupList();
    }

    @Test
    public void testExportGroupScoreList() throws Exception {
        exportForm.exportGroupScoreList();
    }

    @Test
    public void testExportStudentScoreList() throws Exception {
        exportForm.exportStudentScoreList();
    }

}
