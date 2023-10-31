import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        File file = new File("Games/temp/temp.txt");
        file.delete();

        createDirectories(directories);
        createDirs("Games/src/main");
        createDirs("Games/src/test");
        createFiles("Games/src/main/Main.java");
        createFiles("Games/src/main/Utils.java");
        createDirectories(resDirectories);

        logger("Games/temp/temp.txt", sb.toString());

        GameProgress gameProgress1 = new GameProgress(111, 3, 5, 125.5);
        GameProgress gameProgress2 = new GameProgress(112, 4, 6, 126.5);
        GameProgress gameProgress3 = new GameProgress(113, 5, 7, 127.5);

        List<String> filesToZip = List.of(
                "Games/savegames/save1.dat",
                "Games/savegames/save2.dat",
                "Games/savegames/save3.dat");


        gameProgress1.saveGame("Games/savegames/save1.dat", gameProgress1);
        gameProgress2.saveGame("Games/savegames/save2.dat", gameProgress2);
        gameProgress3.saveGame("Games/savegames/save3.dat", gameProgress3);

        gameProgress1.zipFiles("Games/savegames/save.zip", filesToZip);
        deleteFiles(filesToZip);
        logger("Games/temp/temp.txt", sb.toString());


    }

    static List<String> directories = new ArrayList<>(List.of(
            "Games/src",
            "Games/res",
            "Games/savegames",
            "Games/temp"
    ));
    static List<String> resDirectories = new ArrayList<>(List.of(
            "Games/res/drawables",
            "Games/res/vectors",
            "Games/res/icons"
    ));


    static StringBuilder sb = new StringBuilder("");


    public static void createDirs(String dirs) {
        File file = new File(dirs);
        if (!file.exists()) {
            file.mkdirs();
            sb.append("Директория: " + file.getName() + " успешно добавлена в лог. \n");
            System.out.println("Директория: " + file.getName() + " создана успешно.");
        } else {
            sb.append("Директория: " + file.getName() + " не создана/или создана раннее. \n");
            System.out.println("Директория не создана/или создана раннее");
        }
    }

    public static void logger(String filePath, String log) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(log);
            writer.close();
            sb.setLength(0);
            System.out.println("Лог успешно записан в файл: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFiles(String file) {
        File file1 = new File(file);
        if (!file1.exists()) {
            try {
                file1.createNewFile();
                sb.append("Файл: " + file1.getName() + " успешно добавлен в лог. \n");
                System.out.println("Файл: " + file1.getName() + " успешно создан.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            sb.append("Создания файла не выполнено/или выполнено раннее. \n");
            System.out.println("Создания файла не выполнено/или выполнено раннее.");
        }
    }

    public static void createDirectories(List<String> directoryPaths) {
        for (String directoryPath : directoryPaths) {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
                sb.append("Директория: " + directory.getName() + " успешно добавлена в лог. \n");
                System.out.println("Директория " + directory.getName() + " успешно создана.");
            } else {
                sb.append("Директория: " + directory.getName() + " не создана/или создана раннее. \n");
                System.out.println("Не удалось создать директорию " + directory.getName());
            }
        }
    }

    public static void deleteFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    sb.append("Файл: " + file.getName() + " удален и добавлен в лог. \n");
                    System.out.println("Файл удален: " + file.getName());
                } else {
                    sb.append("Файл: " + file.getName() + " удален/или был создан раннее. \n");
                    System.err.println("Не удалось удалить файл: " + file.getName());
                }
            }
        }
    }
}