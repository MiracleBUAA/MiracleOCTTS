package cn.miracle.octts.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hf on 2017/6/27.
 */
public class FileUtils {

    private static final String FILE_SEPARATOR =  System.getProperty("file.separator");
    private static final String UPLOAD_SAVE_FOLDER = "file";

    public static final String RESOURCE_FOLDER = "resource";

    public static final String HOMEWORK_UPLOAD_FOLDER = "homework_upload";

    public static final String STUDENT_LIST_FOLDER = "student_list";

    //public static final String DOWNLOAD_FILES_FOLDER = "C:\\Users\\刘柘林\\Desktop\\download\\";

    public static String saveSingleUploadFile(MultipartFile file, String file_type) throws IOException {
        byte[] filebytes = file.getBytes();
        String file_url = UPLOAD_SAVE_FOLDER + FILE_SEPARATOR + file_type + FILE_SEPARATOR +file.getOriginalFilename();
//        String file_path = UPLOAD_SAVE_FOLDER + file_url;
        Path path = Paths.get(file_url);
        Files.write(path, filebytes);
        return file_url;
    }

    public static void saveUploadFiles(List<MultipartFile> files, String file_type) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty())
                continue;
            String file_path = saveSingleUploadFile(file, file_type);
        }
    }

    public static List<ByteArrayOutputStream> getDownloadFiles(List<String> fileUrls) throws IOException {
        List<ByteArrayOutputStream> BOSList = new ArrayList<>();
        for (String fileurl : fileUrls) {
            FileInputStream fileInputStream = new FileInputStream(fileurl);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();

            int c = bufferedInputStream.read();
            while (c != -1) {
                byteArrayOutputStream.write(c);
                c = bufferedInputStream.read();
            }
            bufferedInputStream.close();
            BOSList.add(byteArrayOutputStream);
        }
        return BOSList;
    }

    public static ByteArrayOutputStream getSingleDownloadFile (String fileUrl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileUrl);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();

        int c = bufferedInputStream.read();
        while (c != -1) {
            byteArrayOutputStream.write(c);
            c = bufferedInputStream.read();
        }
        bufferedInputStream.close();

        return byteArrayOutputStream;
    }

    public static Boolean deleteSingleFile(String file_url) throws IOException{
        File file_to_be_deleted = new File(file_url);
        return  file_to_be_deleted.delete();
    }
}
