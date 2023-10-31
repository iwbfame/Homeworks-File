import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(gameProgress);
            System.out.println("Объект успешно сериализован в файл: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка при сериализации объекта: " + e.getMessage());
        }
    }

    public void zipFiles(String archivePath, List<String> filesToAdd) {
        try (FileOutputStream fos = new FileOutputStream(archivePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String filePath : filesToAdd) {
                File file = new File(filePath);

                if (file.exists() && file.isFile()) {
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                    fis.close();
                }
            }

            System.out.println("ZIP-архив " + archivePath + " успешно создан.");
        } catch (IOException e) {
            System.err.println("Ошибка при создании ZIP-архива: " + e.getMessage());
        }
    }
}