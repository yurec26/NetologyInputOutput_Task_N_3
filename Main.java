import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {

        openZip("C:/Users/Юрий/Desktop/GamesNetology/savegames/saves_Game.zip",
                "C:/Users/Юрий/Desktop/GamesNetology/savegames/");

        System.out.println(openProgress("C:/Users/Юрий/Desktop/GamesNetology/savegames/save1.dat"));


    }

    public static void openZip(String pathOrigin, String pathDestination) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathOrigin))) {
            ZipEntry entry = zin.getNextEntry();
            String name = entry.getName();
            FileOutputStream fout = new FileOutputStream(pathDestination + name);
            for (int c = zin.read(); c != -1; c = zin.read()) {
                fout.write(c);
            }
            fout.flush();
            zin.closeEntry();
            fout.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String path) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}