package top.wunanc.jiMsg;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import top.wunanc.jiMsg.data.JoinDataManager;
import top.wunanc.jiMsg.handler.PlayerJoinHandler;
import top.wunanc.jiMsg.handler.PlayerQuitHandler;

public final class JiMsg extends JavaPlugin {
    private ConfigManager configManager;
    private JoinDataManager joinDataManager;
    private PlayerJoinHandler playerJoinHandler;
    private PlayerQuitHandler playerQuitHandler;

    @Override
    public void onEnable() {
        int pluginId = 28788;
        Metrics metrics = new Metrics(this, pluginId);
        configManager = new ConfigManager(this);
        
        configManager.reloadConfig();

        joinDataManager = new JoinDataManager(this);

        playerJoinHandler = new PlayerJoinHandler(this, configManager);
        getServer().getPluginManager().registerEvents(playerJoinHandler, this);

        playerQuitHandler = new PlayerQuitHandler(this, configManager);
        getServer().getPluginManager().registerEvents(playerQuitHandler, this);

        getLogger().info("JiMsg 插件已启用!");
    }

    @Override
    public void onDisable() {
        if (configManager != null) {
            configManager.saveConfig();
        }

        if (joinDataManager != null) {
            joinDataManager.saveJoinDataSync();
        }

        getLogger().info("JiMsg 插件已禁用!");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public JoinDataManager getPlayerDataManager() {
        return joinDataManager;
    }

    public PlayerJoinHandler getPlayerJoinHandler() {
        return playerJoinHandler;
    }

    public PlayerQuitHandler getPlayerQuitHandler() {
        return playerQuitHandler;
    }
}