package top.wunanc.jiMsg;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private final JavaPlugin plugin;
    private FileConfiguration config;
    private File configFile;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        reloadConfig();
    }

    public void reloadConfig() {
        configFile = new File(plugin.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        
        if (!checkConfigIntegrity()) {
            plugin.getLogger().severe("配置文件部分内容缺失,请删除配置文件重新生成或者前往 https://github.com/wunanc/JiMsg 检查缺失内容");
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
    }

    private boolean checkConfigIntegrity() {
        String[] requiredKeys = {
            "welcome-message",
            "welcome-message-enabled",
            "join-message",
            "join-message-enabled",
            "quit-message",
            "quit-message-enabled"
        };

        for (String key : requiredKeys) {
            if (!config.contains(key)) {
                return false;
            }
        }

        return true;
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    public void saveConfig() {
        if (config == null || configFile == null) {
            return;
        }

        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            plugin.getLogger().severe("无法保存配置文件: " + configFile);
            ex.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
            config = YamlConfiguration.loadConfiguration(configFile);
        }
    }

    public File getDataFolder() {
        return plugin.getDataFolder();
    }

    public boolean isWelcomeMessageEnabled() {
        return getConfig().getBoolean("welcome-message-enabled", true);
    }

    public String getWelcomeMessage() {
        return getConfig().getString("welcome-message", "&a欢迎 %player% 加入服务器!");
    }

    public boolean isJoinMessageEnabled() {
        return getConfig().getBoolean("join-message-enabled", true);
    }

    public String getJoinMessage() {
        return getConfig().getString("join-message", "%player% 加入了服务器");
    }

    public boolean isQuitMessageEnabled() {
        return getConfig().getBoolean("quit-message-enabled", true);
    }

    public String getQuitMessage() {
        return getConfig().getString("quit-message", "%player% 离开了服务器");
    }
}