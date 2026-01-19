import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {

    public static void saveConfig(AppConfig config, Path file) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(file))) {

            out.writeObject(config);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save config", e);
        }
    }

    public static AppConfig loadConfig(Path file) {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file))) {

            return (AppConfig) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }
}