package api.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.load("api.conf");
    }

    String URL = readConfig().getString("url");
    String TOKEN = readConfig().getString("token");
    String ENDPOINT_USER = readConfig().getString("endpoints.user");
    String ENDPOINT_ISSUE = readConfig().getString("endpoints.issue");
    String ENDPOINT_PROJECT = readConfig().getString("endpoints.project");
    String ENDPOINT_AUTH = readConfig().getString("endpoints.auth");
}
