package top.wunanc.jiMsg.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorUtils {

    public static void sendMessage(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(message);
        } else {
            sender.sendMessage(ChatColor.stripColor(message));
        }
    }

    public static void sendMessageWithConsoleColors(CommandSender sender, String message, org.bukkit.plugin.java.JavaPlugin plugin) {
        if (sender instanceof Player) {
            sender.sendMessage(message);
        } else {
            AnsiColorUtils.logColoredMessage(plugin, message);
        }
    }

    public static String stripColors(String message) {
        return ChatColor.stripColor(message);
    }

    public static String translateColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}