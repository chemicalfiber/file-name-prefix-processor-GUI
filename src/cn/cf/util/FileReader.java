package cn.cf.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class FileReader {
    public static ArrayList<File> readFiles(String folderPath, String prefix){
        File f = new File(folderPath);
        File[] filesInDirectory = f.listFiles();
        // 创建存放文件的列表
        ArrayList<File> fileListArray = new ArrayList<>();
        // 遍历并添加需要的文件到待处理列表
        for (File fs : Objects.requireNonNull(filesInDirectory)) {
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + "（是目录，不支持重命名；\n" + fs.getName() + "Is a directory, doesn't support renaming!）");
            } else if (fs.getName().startsWith(prefix)) {
                System.out.println("找到文件（Found File）：" + fs.getName());
                fileListArray.add(fs);
            }
        }
        return fileListArray;
    }
}
