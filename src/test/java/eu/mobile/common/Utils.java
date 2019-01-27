package eu.mobile.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class Utils {
    public static File getMobileAppDir() {
        return new File(getResourcesDir(), "apps");
    }

    public static File getResourcesDir() {
        File projectRoot = new File(System.getProperty("user.dir"));
        return new File(projectRoot, "src/test/resources");
    }

    public static Map<String, String> fromJson(String json) {
        Gson gson = new Gson();
        Type genericClassType = new TypeToken<Map<String, String>>() {
        }.getType();
//        Gson would best guess key/value type without type hint
        return gson.fromJson(json, genericClassType);
    }

    public static Map<String, String> fromJson(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return fromJson(String.join("\n", lines));
    }
}
