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
        this.joinDataManager = new JoinDataManager(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUuid = player.getUniqueId().toString();

        if (joinDataManager.isFirstJoin(playerUuid)) {
            joinDataManager.recordJoin(playerUuid);

            if (configManager.isWelcomeMessageEnabled()) {
                String welcomeMessage = configManager.getWelcomeMessage();
                welcomeMessage = welcomeMessage.replace("%player%", player.getName());

                welcomeMessage = ChatColor.translateAlternateColorCodes('&', welcomeMessage);

                for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                    ColorUtils.sendMessage(onlinePlayer, welcomeMessage);
                }

                plugin.getLogger().info(ChatColor.stripColor(welcomeMessage));
            }
        }

        if (configManager.isJoinMessageEnabled()) {
            String joinMessage = configManager.getJoinMessage();
            joinMessage = joinMessage.replace("%player%", player.getName());

            joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage);

            event.setJoinMessage(null);

            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                ColorUtils.sendMessage(onlinePlayer, joinMessage);
            }

            plugin.getLogger().info(ChatColor.stripColor(joinMessage));
        } else {
            event.setJoinMessage(null);
        }
    }

    public void saveJoinData() {
        joinDataManager.saveJoinData();
    }

    public void saveJoinDataSync() {
        joinDataManager.saveJoinDataSync();
    }
}