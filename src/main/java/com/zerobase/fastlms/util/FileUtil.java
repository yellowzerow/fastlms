package com.zerobase.fastlms.util;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

public class FileUtil {

    public String[] getNewSaveFile(String baseLocalPath, String baseUrlPath, String originalFilename) {

        LocalDate now = LocalDate.now();

        String[] dirs = {
                String.format("%s/%d", baseLocalPath, now.getYear()),
                String.format("%s/%d/%02d/", baseLocalPath,
                        now.getYear(), now.getMonth().getValue()),
                String.format("%s/%d/%02d/%02d/", baseLocalPath,
                        now.getYear(), now.getMonth().getValue(),
                        now.getDayOfMonth())};

        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath,
                now.getYear(), now.getMonth().getValue(),
                now.getDayOfMonth());

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }

        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }

        return new String[] {newFilename, newUrlFilename};
    }

}
