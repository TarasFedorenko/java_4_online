package ua.com.alevel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileHelperController {

    FileHelper fileHelper = new FileHelper();
    ColorsMessage color = new ColorsMessage();

    public void run() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(color.PURPLE + "\n         ***MAIN MENU***         ");
        String select;
        menu();
        while (true) {
            try {
                if (!((select = bf.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                option(bf, select);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void menu() {
        System.out.println();
        System.out.println(color.BLUE + "To create file of directory enter" + color.PURPLE + "- 1 ");
        System.out.println(color.BLUE + "To get list of all files and directories enter " + color.PURPLE + "- 2 ");
        System.out.println(color.BLUE + "To replace file or directory enter " + color.PURPLE + "- 3");
        System.out.println(color.BLUE + "To search file or directory enter " + color.PURPLE + "- 4");
        System.out.println(color.BLUE + "To search text in files enter " + color.PURPLE + "- 5");
        System.out.println(color.BLUE + "To remove file or directory enter " + color.PURPLE + "- 6");
        System.out.println(color.BLUE + "To quit from application enter " + color.PURPLE + "-7");
        System.out.println();
    }

    private void option(BufferedReader bf, String select) throws IOException {
        switch (select) {
            case "1":
                createFileOrDir(bf);
                break;
            case "2":
                getAllFileAndDir(bf);
                break;
            case "3":
                replaceFileOrDir(bf);
                break;
            case "4":
                searchFileOrDir(bf);
                break;
            case "5":
                searchTextInAllFiles(bf);
                break;
            case "6":
                removeFileOrDir(bf);
                break;
            case "7":
                quit();
                break;
        }
        menu();
    }

    private void createFileOrDir(BufferedReader bf) throws IOException {
        System.out.println(color.GREEN + "Enter name of directory for use");
        String dirName = bf.readLine();
        File dirExist = new File(dirName);
        String chooseCreate;
        String name;
        if (!dirExist.exists() || !dirExist.isDirectory()) {
            System.out.println(color.RED + "Sorry this directory was not found try again");
            createFileOrDir(bf);
        } else {
            System.out.println(color.GREEN + "What you want to create : \n " +
                    color.GREEN + "if you want to create file enter" + color.PURPLE + " \" F \" \n " +
                    color.GREEN + "if you want create directory enter" + color.PURPLE + " \" D \" ");
            chooseCreate = bf.readLine();
            if (chooseCreate.equalsIgnoreCase("F")) {
                System.out.println(color.GREEN + "Enter file name");
                name = bf.readLine();
                fileHelper.createFile(name, dirExist);
            } else if (chooseCreate.equalsIgnoreCase("D")) {
                System.out.println(color.GREEN + "Enter directory name");
                name = bf.readLine();
                fileHelper.createDir(name, dirExist);

            } else {
                System.out.println(color.RED + "Incorrect try again");
            }

        }
    }

    private void getAllFileAndDir(BufferedReader bf) throws IOException {
        System.out.println(color.GREEN + "Enter name of directory for use");
        String dirName = bf.readLine();
        File dirExist = new File(dirName);
        if (!dirExist.exists() || !dirExist.isDirectory()) {
            System.out.println(color.RED + "Sorry this directory was not found try again");
            getAllFileAndDir(bf);
        } else {
            System.out.println(color.YELLOW + "In directory " + dirExist.getAbsolutePath());
            fileHelper.observeDirectory(dirExist);
        }
    }

    private void replaceFileOrDir(BufferedReader bf) throws IOException {
        System.out.println(color.GREEN + "Enter name of directory that include desire file or dir");
        String oldDir = bf.readLine();
        File oldPath = new File(oldDir);
        if (!oldPath.exists() || !oldPath.isDirectory()) {
            System.out.println(color.RED + "Sorry this directory was not found try again");
            replaceFileOrDir(bf);
        }
        System.out.println(color.GREEN + "Enter name of directory where you want to replace dir or file");
        String newDir = bf.readLine();
        File newPath = new File(newDir);
        if (!newPath.exists() || !newPath.isDirectory()) {
            System.out.println(color.RED + "Sorry this directory was not found try again");
            replaceFileOrDir(bf);
        }
        System.out.println(color.GREEN + "Enter name file or directory for replace");
        String fileDirName = bf.readLine();
        File fileDirPath = new File(oldPath + "/" + fileDirName);
        if (fileDirPath.exists() && fileDirPath.isDirectory()) {
            System.out.println(color.YELLOW + "Start replace data from directory" + oldPath.getAbsolutePath() + "/" + fileDirName);
            System.out.println(color.YELLOW + "To directory " + newPath.getAbsolutePath());
            fileHelper.createDir(fileDirName, newPath);
            fileHelper.copyDirectory(new File(oldDir + "/" + fileDirName), new File(newPath.getName() + "/" + fileDirName));
            fileHelper.removeDir(new File(oldDir + "/" + fileDirName));
        } else if (fileDirPath.isFile()) {
            System.out.println(color.BLUE + "Start replace file from directory" + oldPath.getAbsolutePath() + "/" + fileDirName);
            System.out.println(color.BLUE + "To directory " + newPath.getAbsolutePath());
            fileHelper.copyFile(new File(oldDir + "/" + fileDirName), new File(newPath + "/" + fileDirName));
            fileHelper.removeFile(oldDir + "/" + fileDirName);
        } else {
            System.out.println(color.RED + "The file or dir with that name not found, try again");
            replaceFileOrDir(bf);
        }
    }

    private void searchFileOrDir(BufferedReader bf) throws IOException {
        System.out.println(color.GREEN + "Enter name of directory where you want to search file or directory");
        String searchPath = bf.readLine();
        File searchDir = new File(searchPath);
        System.out.println(color.YELLOW + "You choose directory for search " + searchDir.getAbsolutePath());
        if (!searchDir.exists() || !searchDir.isDirectory()) {
            System.out.println(color.RED + "Sorry this directory was not found try again");
            searchFileOrDir(bf);
        }
        System.out.println(color.GREEN + "What you want to find : \n " +
                color.GREEN + "if you want to find file enter" + color.PURPLE + " \"F\" \n " +
                color.GREEN + "if you want find directory enter" + color.PURPLE + " \"D\" ");
        String chooseFind = bf.readLine();
        if (chooseFind.equalsIgnoreCase("f")) {
            System.out.println(color.BLUE + "Enter name of file  which you want to search");
            String fileSearch = bf.readLine();
            if (fileSearch == "" || !fileSearch.matches("^[\\w,\\s-]+\\.[A-Za-z]{3,4}$")) {
                System.out.println(color.RED + "Incorrect data, please try again");
            } else {
                fileHelper.findFile(fileSearch, searchDir);
            }
        } else if (chooseFind.equalsIgnoreCase("d")) {
            System.out.println(color.YELLOW + "Enter name of directory which you want to search");
            String dirSearch = bf.readLine();
            if (dirSearch == "" || !dirSearch.matches("^[a-z0-9_-]{3,15}$")) {
                System.out.println(color.RED + "Incorrect data, please try again");
            } else {
                fileHelper.findDir(dirSearch, searchDir);
            }
        } else {
            System.out.println(color.RED + "Wrong choice try again");
            searchFileOrDir(bf);
        }
    }

    private void removeFileOrDir(BufferedReader bf) throws IOException {
        System.out.println(color.GREEN + "Enter name of directory where you want to delete file ");
        String deletePath = bf.readLine();
        File deleteDir = new File(deletePath);
        System.out.println(color.YELLOW + "You choose directory " + deleteDir.getAbsolutePath());
        if (!deleteDir.exists() || !deleteDir.isDirectory()) {
            System.out.println(color.RED + "Sorry this directory was not found try again");
            removeFileOrDir(bf);
        }
        System.out.println(color.GREEN + "Enter name of file or directory which you want to delete");
        String dataDelete = bf.readLine();
        File deleteData = new File(deletePath + "/" + dataDelete);
        if (deleteData.exists() && deleteData.isDirectory()) {
            System.out.println(color.YELLOW + "Start delete directory " + deleteData.getAbsolutePath());
            fileHelper.removeDir(new File(deletePath + "/" + dataDelete));
        } else if (deleteData.isFile()) {
            System.out.println(color.GREEN + "Start delete file " + deleteData.getAbsolutePath());
            fileHelper.removeFile(deletePath + "/" + dataDelete);
        } else {
            System.out.println(color.RED + "The file or dir with that name not found, try again");
            removeFileOrDir(bf);
        }
    }

    private void searchTextInAllFiles(BufferedReader bf) throws IOException {
        System.out.println(color.GREEN + "Enter where you want to search text");
        String search = bf.readLine();
        File searchDir = new File(search);
        System.out.println(color.YELLOW + "You choose directory " + searchDir.getAbsolutePath());
        if (!searchDir.exists() || !searchDir.isDirectory()) {
            System.out.println(color.RED + "Sorry this directory was not found try again");
            searchTextInAllFiles(bf);
        }
        System.out.println(color.PURPLE + "Enter the text that you want to find ");
        String text = bf.readLine();
        if (text != null && text != "") {
            fileHelper.findText(searchDir, text);
        } else {
            System.out.println(color.RED + "Wrong data, try again");
            searchTextInAllFiles(bf);
        }
    }

    private void quit() {
        System.exit(0);
    }
}
