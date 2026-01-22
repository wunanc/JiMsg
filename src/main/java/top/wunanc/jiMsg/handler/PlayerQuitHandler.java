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

        if (configManager.isQuitMessageEnabled()) {
            String quitMessage = configManager.getQuitMessage();
            quitMessage = quitMessage.replace("%player%", player.getName());

            quitMessage = ChatColor.translateAlternateColorCodes('&', quitMessage);

            event.setQuitMessage(null);

            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                ColorUtils.sendMessage(onlinePlayer, quitMessage);
            }

            plugin.getServer().getConsoleSender().sendMessage(ChatColor.stripColor(quitMessage));
        } else {
            event.setQuitMessage(null);
        }
    }
}