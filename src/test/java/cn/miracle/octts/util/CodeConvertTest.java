package cn.miracle.octts.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.miracle.octts.util.CodeConvert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Tony on 2017/6/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeConvertTest {

    @Test
    public void testString2Unicode(){
        String str = "6-8人，一个女生";
        String result = CodeConvert.string2Unicode(str);
        assertNotNull(result);
        assertEquals("\\u36\\u2d\\u38\\u4eba\\uff0c\\u4e00\\u4e2a\\u5973\\u751f",result);
    }

    @Test
    public void testUnicode2String(){
        String str = "\\u0036\\u002d\\u0038\\u4eba\\uff0c\\u4e00\\u4e2a\\u5973\\u751f";
        String result = CodeConvert.unicode2String(str);
        assertNotNull(result);
        assertEquals("6-8人，一个女生",result);
    }
}
