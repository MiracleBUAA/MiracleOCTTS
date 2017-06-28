package cn.miracle.octts.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by hf on 2017/6/27.
 */
public class FileUtils {

    private static final String UPLOAD_SAVE_FOLDER = "C:\\Users\\Tony\\Documents\\";

    public static void saveUploadFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty())
                continue;
            byte[] fileBytes = file.getBytes();
            Path path = Paths.get(UPLOAD_SAVE_FOLDER + file.getOriginalFilename());
            Files.write(path, fileBytes);
        }
    }
}
