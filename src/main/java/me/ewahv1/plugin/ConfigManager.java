package me.ewahv1.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigManager {
    private File configFile;
    private JsonObject configData;
    private Gson gson = new Gson();

    public ConfigManager(String pluginName) {
        try {
            String configPath = "plugins/" + pluginName + "/configuraciones/config.json";
            configFile = new File(configPath);
            if (!configFile.exists()) {
                // Crear las carpetas y el archivo si no existen
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();

                // Inicializar el archivo con un objeto JSON vacío
                FileWriter writer = new FileWriter(configFile);
                writer.write("{\"bee\": {\"Angry\": false, \"Strength\": 0}}");
                writer.close();
            }

            // Leer el archivo y convertirlo en un objeto JSON
            String content = new String(Files.readAllBytes(Paths.get(configPath)));
            configData = gson.fromJson(content, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAttribute(String attribute, Object value) {
        // Actualizar el objeto JSON y escribirlo en el archivo
        configData.getAsJsonObject("bee").addProperty(attribute, value.toString());
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(gson.toJson(configData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getAttribute(String attribute) {
        // Devolver el valor del atributo, o null si no existe
        JsonObject bee = configData.getAsJsonObject("bee");
        return bee.has(attribute) ? bee.get(attribute).getAsString() : null;
    }

    public void reload() {
        try {
            // Leer el archivo y convertirlo en un objeto JSON
            String content = new String(Files.readAllBytes(configFile.toPath()));
            configData = gson.fromJson(content, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
