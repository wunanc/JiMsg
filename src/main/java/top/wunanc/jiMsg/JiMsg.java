package top.wunanc.jiMsg;

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
        // 初始化配置管理器
        configManager = new ConfigManager(this);
        
        // 确保配置文件存在并加载
        configManager.reloadConfig();

        // 初始化玩家数据管理器
        joinDataManager = new JoinDataManager(this);

        // 初始化玩家加入事件处理器
        playerJoinHandler = new PlayerJoinHandler(this, configManager);
        getServer().getPluginManager().registerEvents(playerJoinHandler, this);

        // 初始化玩家退出事件处理器
        playerQuitHandler = new PlayerQuitHandler(this, configManager);
        getServer().getPluginManager().registerEvents(playerQuitHandler, this);

        getLogger().info("JiMsg 插件已启用!");
    }

    @Override
    public void onDisable() {
        // 保存配置
        if (configManager != null) {
            configManager.saveConfig();
        }

        // 保存玩家数据
        if (joinDataManager != null) {
            joinDataManager.saveData();
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