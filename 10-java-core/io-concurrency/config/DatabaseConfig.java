import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.IOException;
import java.util.Base64;

public class DatabaseConfig implements Externalizable {

    private String host;
    private int port;
    private String username;
    private String password;

    // Mandatory no-arg constructor
    public DatabaseConfig() {
    }

    public DatabaseConfig(String host, int port,
            String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(host);
        out.writeInt(port);
        out.writeUTF(username);

        // Encrypt password (Base64 for demo)
        String encrypted = Base64.getEncoder().encodeToString(password.getBytes());
        out.writeUTF(encrypted);
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {

        host = in.readUTF();
        port = in.readInt();
        username = in.readUTF();

        // Decrypt password
        String encrypted = in.readUTF();
        password = new String(
                Base64.getDecoder().decode(encrypted));
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}