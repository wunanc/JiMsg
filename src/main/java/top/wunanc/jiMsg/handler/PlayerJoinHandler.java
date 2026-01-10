package top.wunanc.jiMsg.handler;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import top.wunanc.jiMsg.ConfigManager;
import top.wunanc.jiMsg.JiMsg;
import top.wunanc.jiMsg.data.JoinDataManager;
import top.wunanc.jiMsg.utils.ColorUtils;

public class PlayerJoinHandler implements Listener {
    private final JiMsg plugin;
    private final ConfigManager configManager;
    private final JoinDataManager joinDataManager;

    public PlayerJoinHandler(JiMsg plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.joinDataManager = new JoinDataManager(plugin, configManager);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUuid = player.getUniqueId().toString();

        // 检查玩家是否是首次加入
        if (joinDataManager.isFirstJoin(playerUuid)) {
            // 记录玩家已加入
            joinDataManager.recordJoin(playerUuid);

            // 检查是否启用首次加入欢迎消息
            if (configManager.isWelcomeMessageEnabled()) {
                // 发送欢迎消息
                String welcomeMessage = configManager.getWelcomeMessage();
                welcomeMessage = welcomeMessage.replace("%player%", player.getName());

                // 将颜色代码转换为ChatColor
                welcomeMessage = ChatColor.translateAlternateColorCodes('&', welcomeMessage);

                // 发送给所有在线玩家
                for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                    ColorUtils.sendMessage(onlinePlayer, welcomeMessage);
                }

                // 同时也在控制台输出
                plugin.getLogger().info(ChatColor.stripColor(welcomeMessage));
            }
        }

        // 检查是否启用加入消息
        if (configManager.isJoinMessageEnabled()) {
            // 发送自定义加入消息
            String joinMessage = configManager.getJoinMessage();
            joinMessage = joinMessage.replace("%player%", player.getName());

            // 将颜色代码转换为ChatColor
            joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage);

            // 设置默认消息为空字符串以防止原版消息显示
            event.setJoinMessage(null); // 不显示原版加入消息

            // 发送自定义加入消息
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                ColorUtils.sendMessage(onlinePlayer, joinMessage);
            }

            // 在控制台输出
            plugin.getLogger().info(ChatColor.stripColor(joinMessage));
        } else {
            // 如果禁用了自定义消息，恢复原版消息
            event.setJoinMessage(null); // 不显示任何消息（包括原版）
        }
    }

    /**
     * 异步保存数据（插件运行时使用）
     */
    public void saveJoinData() {
        joinDataManager.saveJoinData();
    }

    /**
     * 同步保存数据（插件禁用时使用）
     */
    public void saveJoinDataSync() {
        joinDataManager.saveJoinDataSync();
    }
}