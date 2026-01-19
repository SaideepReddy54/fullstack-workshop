import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AppConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appName;
    private String version;
    private int maxConnections;
    private int timeout;

    // Transient field (not serialized)
    private transient LocalDateTime lastLoaded;

    public AppConfig(String appName, String version,
            int maxConnections, int timeout) {
        this.appName = appName;
        this.version = version;
        this.maxConnections = maxConnections;
        this.timeout = timeout;
    }

    public LocalDateTime getLastLoaded() {
        return lastLoaded;
    }

    // Custom deserialization logic
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        in.defaultReadObject(); // Read normal fields
        this.lastLoaded = LocalDateTime.now(); // Set runtime value
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "appName='" + appName + '\'' +
                ", version='" + version + '\'' +
                ", maxConnections=" + maxConnections +
                ", timeout=" + timeout +
                ", lastLoaded=" + lastLoaded +
                '}';
    }
}
