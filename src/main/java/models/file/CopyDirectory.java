package models.file;

import java.io.*;

public class CopyDirectory {
    public static void main(String[] args) {
        copy("resources", "targets");
    }
    public static void copy(String originPath, String targetPath) {
        File originFile = new File(originPath);
        File targetFile = new File(targetPath);
        if (!targetFile.exists()) {
            targetFile.mkdir();
        }


        File[] originFiles = originFile.listFiles();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] bytes = new byte[1024];
        int n;
        for (File file: originFiles) {
            if (file.isFile()) {
                try {
                    fis = new FileInputStream(file);
                    fos = new FileOutputStream(targetPath + "\\" + file.getName());


                    while ((n = fis.read(bytes)) != -1) {
                        fos.write(bytes, 0, n);
                    }

                    fos.flush();
                } catch (FileNotFoundException e) {
                    System.out.println("文件不存在");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            else {
                copy(originPath + "\\" + file.getName(), targetPath + "\\" + file.getName());
            }
        }
    }
}
