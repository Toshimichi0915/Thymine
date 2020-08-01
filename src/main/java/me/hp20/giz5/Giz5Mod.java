package me.hp20.giz5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Giz5Mod implements ModInitializer {

    private static Giz5Options options;

    private static final File configFile = new File("giz5.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void onInitialize() {
        loadOptions();
    }

    public static Giz5Options getOptions() {
        return options;
    }

    public static void saveOptions() {
        try {
            FileUtils.write(configFile, gson.toJson(options), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadOptions() {
        if (configFile.exists()) {
            try {
                String text = FileUtils.readFileToString(configFile, StandardCharsets.UTF_8);
                options = gson.fromJson(text, Giz5Options.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            options = new Giz5Options();
        }
    }
}
