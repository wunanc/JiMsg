package top.wunanc.jiMsg.utils;

import org.bukkit.ChatColor;

public class AnsiColorUtils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_DARK_GRAY = "\u0001B[90m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    public static final String ANSI_BRIGHT_MAGENTA = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

    public static String convertChatColorToAnsi(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String processedInput = ChatColor.translateAlternateColorCodes('&', input);
        String result = convertColorCodeToAnsi(processedInput);
        if (!result.endsWith(ANSI_RESET)) {
            result += ANSI_RESET;
        }
        return result;
    }

    private static String convertColorCodeToAnsi(String input) {
        return input
                .replace(ChatColor.BLACK.toString(), ANSI_BLACK)
                .replace(ChatColor.DARK_BLUE.toString(), ANSI_BLUE)
                .replace(ChatColor.DARK_GREEN.toString(), ANSI_GREEN)
                .replace(ChatColor.DARK_AQUA.toString(), ANSI_CYAN)
                .replace(ChatColor.DARK_RED.toString(), ANSI_RED)
                .replace(ChatColor.DARK_PURPLE.toString(), ANSI_PURPLE)
                .replace(ChatColor.GOLD.toString(), ANSI_YELLOW)
                .replace(ChatColor.GRAY.toString(), ANSI_WHITE)
                .replace(ChatColor.DARK_GRAY.toString(), ANSI_DARK_GRAY)
                .replace(ChatColor.BLUE.toString(), ANSI_BLUE)
                .replace(ChatColor.GREEN.toString(), ANSI_GREEN)
                .replace(ChatColor.AQUA.toString(), ANSI_CYAN)
                .replace(ChatColor.RED.toString(), ANSI_RED)
                .replace(ChatColor.LIGHT_PURPLE.toString(), ANSI_BRIGHT_MAGENTA)
                .replace(ChatColor.YELLOW.toString(), ANSI_YELLOW)
                .replace(ChatColor.WHITE.toString(), ANSI_WHITE)
                .replace(ChatColor.MAGIC.toString(), "")
                .replace(ChatColor.BOLD.toString(), ANSI_BOLD)
                .replace(ChatColor.STRIKETHROUGH.toString(), "")
                .replace(ChatColor.UNDERLINE.toString(), "")
                .replace(ChatColor.ITALIC.toString(), "")
                .replace(ChatColor.RESET.toString(), ANSI_RESET);
    }
    public static void logColoredMessage(org.bukkit.plugin.java.JavaPlugin plugin, String message) {
        String coloredMessage = convertChatColorToAnsi(message);
        plugin.getLogger().info(coloredMessage);
    }
}