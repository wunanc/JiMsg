package top.wunanc.jiMsg.handler;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import top.wunanc.jiMsg.ConfigManager;
import top.wunanc.jiMsg.JiMsg;
import top.wunanc.jiMsg.utils.ColorUtils;

public class PlayerQuitHandler implements Listener {
    private final JiMsg plugin;
    private final ConfigManager configManager;

    public PlayerQuitHandler(JiMsg plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // 检查是否启用退出消息
        if (configManager.isQuitMessageEnabled()) {
            // 获取自定义退出消息
            String quitMessage = configManager.getQuitMessage();
            quitMessage = quitMessage.replace("%player%", player.getName());

            // 将颜色代码转换为ChatColor
            quitMessage = ChatColor.translateAlternateColorCodes('&', quitMessage);

            // 设置为null以防止原版退出消息显示
            event.setQuitMessage(null); // 不显示原版退出消息

            // 发送自定义退出消息
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                ColorUtils.sendMessage(onlinePlayer, quitMessage);
            }

            // 在控制台输出
            plugin.getLogger().info(ChatColor.stripColor(quitMessage));
        } else {
            // 如果禁用了自定义消息，恢复原版消息
            event.setQuitMessage(null); // 不显示任何消息（包括原版）
        }
    }
}