package ua.com.alevel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FileHelper {
    ColorsMessage color = new ColorsMessage();

    public void createFile(String fileName, File dirPath) throws IOException {
        if (fileName == "" || !fileName.matches("^[\\w,\\s-]+\\.[A-Za-z]{3,4}$")) {
            System.out.println(color.RED + "Incorrect data, please try again");
        } else {
            File file = new File(dirPath.getAbsolutePath() + "/" + fileName);
            file.createNewFile();
            System.out.println(color.BLUE + "File " + fileName + " was created");
        }
    }

    public void createDir(String dirName, File dirPath) {
        if (dirName == "" || !dirName.matches("^[a-z0-9_-]{3,15}$")) {
            System.out.println(color.RED + "Incorrect data, please try again");
        } else {
            File dir = new File(dirPath.getAbsolutePath() + "/" + dirName);
            dir.mkdir();
            System.out.println(color.YELLOW + "Directory " + dirName + " was created");
        }
    }

    public void observeDirectory(File dirPath) {
        System.out.println(color.YELLOW + "Directory => " + dirPath.getAbsolutePath());
        File[] files = dirPath.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                observeDirectory(file);
            } else {
                System.out.println(color.BLUE + "File => " + file.getAbsolutePath());
            }
        }
    }

    public void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {

        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdir();
        }
        for (String files : sourceDirectory.list()) {
            copyDirectoryOrFiles(new File(sourceDirectory, files), new File(destinationDirectory, files));
        }
    }

    public void copyDirectoryOrFiles(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            copyDirectory(source, destination);
        } else {
            copyFile(source, destination);
        }
    }

    public void copyFile(File sourceFile, File destinationFile)
            throws IOException {
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

    public void removeFile(String filePath) {
        File deleteFile = new File(filePath);
        deleteFile.delete();
    }


    public boolean removeDir(File innerPath) {
        File[] allContents = innerPath.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                removeDir(file);
            }
        }
        return innerPath.delete();
    }

    public void findFile(String name, File file) {
        File[] list = file.listFiles();
        if (list != null)
            for (File fil : list) {
                if (fil.isDirectory()) {
                    findFile(name, fil);
                } else if (name.equalsIgnoreCase(fil.getName())) {
                    System.out.println(color.BLUE + "File  " + name + " find in directory " + fil.getAbsolutePath());
                }
            }
    }

    public void findDir(String name, File dir) {
        File[] list = dir.listFiles();
        if (list != null)
            for (File direct : list) {
                if (direct.isDirectory() && direct.getName().equalsIgnoreCase(name)) {
                    System.out.println(color.YELLOW + "Directory " + name + " find in directory " + direct.getAbsolutePath());
                    findDir(name, direct);
                } else if (direct.isDirectory() && !direct.getName().equalsIgnoreCase(name)) {
                    findDir(name, direct);
                }
            }
    }

    public void findText(File dirPath, String text) throws IOException {
        Map<String, String> stringsMap = new HashMap<>();
        File[] list = dirPath.listFiles();
        if (list != null)
            for (File fil : list) {
                if (fil.isDirectory()) {
                    findText(fil, text);
                } else if (fil.isFile()) {
                    stringsMap.put(fil.getAbsolutePath(), stringBuild(fil));
                }
            }
        for (String str : stringsMap.keySet()) {
            if (stringsMap.get(str).contains(text)) {
                System.out.println(color.BLUE + "Find same text in file " + str);
            } else {
                System.out.println(color.RED + "Not there " + new File(str).getName());
            }
        }
    }

    private String stringBuild(File pathFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathFile));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
}
