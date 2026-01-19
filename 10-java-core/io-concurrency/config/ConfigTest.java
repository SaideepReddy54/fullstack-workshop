import java.nio.file.Path;

public class ConfigTest {

    public static void main(String[] args) {

        AppConfig config = new AppConfig("MyApp", "1.0", 100, 30000);

        Path file = Path.of("config.ser");

        ConfigManager.saveConfig(config, file);

        AppConfig loaded = ConfigManager.loadConfig(file);

        System.out.println("Loaded config:");
        System.out.println(loaded);
        System.out.println("Last Loaded: " +
                loaded.getLastLoaded());
    }
}
