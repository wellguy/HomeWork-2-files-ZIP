import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress[] games = new GameProgress[]{
                new GameProgress(10, 1, 1, 20.5),
                new GameProgress(15, 2, 2, 25.5),
                new GameProgress(17, 3, 3, 35.5)
        };

        String gamePathSave = "F://java/JavaCore/Games/savegames/save";
        String gamePathSaveZIP = "F://java/JavaCore/Games/savegames/save.zip";

        saveGame(gamePathSave, games);
        File dir = new File("F://java/JavaCore/Games/savegames/");

        File[] fileList = null;

        if (dir.isDirectory()) {
            fileList = dir.listFiles();
        }

        zipFiles(gamePathSaveZIP, fileList);

    }

    static void saveGame(String path, GameProgress[] games) {
        for (int i = 0; i < games.length; i++ ) {
            try (FileOutputStream fileSave = new FileOutputStream(path + i + ".dat");
                 ObjectOutputStream obj = new ObjectOutputStream(fileSave);) {

                obj.writeObject(games[i]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    static void zipFiles(String gamePathSaveZIP, File[] fileList) {

            try(ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(gamePathSaveZIP))) {
                for (File file : fileList) {
                    FileInputStream fis = new FileInputStream(file.getPath());
                    ZipEntry zentry = new ZipEntry(file.getName());
                    zip.putNextEntry(zentry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zip.write(buffer);
                    zip.closeEntry();
                    fis.close();
                }
            } catch (IOException e) {
                e.getMessage();
            }

        for (File path : fileList) {
            path.delete();
        }

    }

}
