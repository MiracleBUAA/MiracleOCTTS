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

    public static final String UPLOAD_SAVE_FOLDER = "file\\";

    //public static final String DOWNLOAD_FILES_FOLDER = "C:\\Users\\刘柘林\\Desktop\\download\\";

    public static String saveSingleUploadFile(MultipartFile file) throws IOException {
        byte[] filebytes = file.getBytes();
        String file_url = UPLOAD_SAVE_FOLDER + file.getOriginalFilename();
        Path path = Paths.get(file_url);
        Files.write(path, filebytes);
        return file_url;
    }

    public static void saveUploadFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty())
                continue;
            byte[] fileBytes = file.getBytes();
            Path path = Paths.get(UPLOAD_SAVE_FOLDER + file.getOriginalFilename());
            Files.write(path, fileBytes);
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
}
