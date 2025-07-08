package com.webfruits.util;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadUtil {
    public static String saveFile(Part part, String uploadDir) throws IOException {
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String originalFileName = extractFileName(part);
        String extension = "";
        String baseName = "";

        int lastDot = originalFileName.lastIndexOf(".");
        if (lastDot != -1) {
            baseName = originalFileName.substring(0, lastDot);
            extension = originalFileName.substring(lastDot);
        } else {
            baseName = originalFileName;
        }

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String newFileName = baseName + "_" + timestamp + extension;

        String fullPath = uploadDir + File.separator + newFileName;
        part.write(fullPath);

        return newFileName;
    }

    public static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            for (String token : contentDisp.split(";")) {
                if (token.trim().startsWith("filename")) {
                    return token.substring(token.indexOf('=') + 2, token.length() - 1);
                }
            }
        }
        return null;
    }
}
