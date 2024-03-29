package at.overflow.bukkit.matrixbridge;

import java.io.*;
import java.util.Properties;

public class BridgePropertyReader {

    private final String PROPERTIES_FILENAME = "matrixbridge.properties";

    private final String MATRIX_USERID_KEY = "matrix.id";
    private final String MATRIX_PASSWORD_KEY = "matrix.password";
    private final String MATRIX_ROOMID_KEY = "matrix.room";
    private final String MATRIX_ADMINID_KEY = "matrix.admin";

    private final String MINECRAFT_SERVER_NAME_KEY = "minecraft.server.name";

    private Properties properties = new Properties();

    public BridgePropertyReader() {
        File file = new File(PROPERTIES_FILENAME);

        if (file.exists()) {
            try {
                properties.load(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            generateEmptyFile(file);
            throw new RuntimeException("Matrixbridge properties are empty.");
        }
    }

    private void generateEmptyFile(File file) {
            properties.setProperty(MATRIX_USERID_KEY, "");
            properties.setProperty(MATRIX_PASSWORD_KEY, "");
            properties.setProperty(MATRIX_ADMINID_KEY, "");
            properties.setProperty(MINECRAFT_SERVER_NAME_KEY, "Server");
        try {
            properties.store(new FileOutputStream(file), "Matrix Bridge Properties");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDomain() {
        String tmp = properties.getProperty(MATRIX_USERID_KEY);
        return tmp.substring(tmp.indexOf(":") + 1);
    }

    public String getUsername() {
        String tmp = properties.getProperty(MATRIX_USERID_KEY);
        return tmp.substring(1, tmp.indexOf(":"));
    }

    public String getMinecraftServerName() {
        return properties.getProperty(MINECRAFT_SERVER_NAME_KEY);
    }

    public String getPassword() {
        return properties.getProperty(MATRIX_PASSWORD_KEY);
    }

    public String getRoom() {
        return properties.getProperty(MATRIX_ROOMID_KEY);
    }

    public String getAdmin() {
        return properties.getProperty(MATRIX_ADMINID_KEY);
    };
}
