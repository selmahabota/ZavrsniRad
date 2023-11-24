package utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import model.LoginUser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Utils {
    public static Dotenv dotEnv() {
        return Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }

    public static List<LoginUser> getLoginDataFromJson() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir") + "/src/test/resources/user.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(reader, new TypeToken<List<LoginUser>>() {
        }.getType());
    }

    public static void waitForSeconds(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
