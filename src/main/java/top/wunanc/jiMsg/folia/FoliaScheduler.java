package top.wunanc.jiMsg.folia;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

/**
 * Folia 和传统 Bukkit 调度器的兼容层
 */
public class FoliaScheduler {

    /**
     * 在指定位置异步运行任务（Folia 区域调度）
     */
    public static void runAtLocation(Plugin plugin, Location location, Runnable task) {
        if (isFolia()) {
            // Folia: 使用区域调度器
            Bukkit.getRegionScheduler().run(plugin, location, scheduledTask -> task.run());
        } else {
            // 传统 Bukkit: 使用异步任务
            Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
        }
    }

    /**
     * 在指定位置异步运行任务（带延迟）
     */
    public static void runAtLocationDelayed(Plugin plugin, Location location, Runnable task, long delayTicks) {
        if (isFolia()) {
            // Folia: 使用区域调度器
            Bukkit.getRegionScheduler().runDelayed(plugin, location, scheduledTask -> task.run(), delayTicks);
        } else {
            // 传统 Bukkit: 使用异步任务
            Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delayTicks);
        }
    }

    /**
     * 异步运行任务（全局）
     */
    public static void runAsync(Plugin plugin, Runnable task) {
        if (isFolia()) {
            // Folia: 使用全局调度器
            Bukkit.getGlobalRegionScheduler().run(plugin, scheduledTask -> task.run());
        } else {
            // 传统 Bukkit: 使用异步任务
            Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
        }
    }

    /**
     * 在实体上运行任务
     */
    public static void runAtEntity(Plugin plugin, Entity entity, Consumer<Object> task) {
        if (isFolia()) {
            // Folia: 使用实体调度器，返回 ScheduledTask 类型
            entity.getScheduler().run(plugin, scheduledTask -> task.accept(scheduledTask), null);
        } else {
            // 传统 Bukkit: 使用同步任务，返回 BukkitTask 类型
            Bukkit.getScheduler().runTask(plugin, () -> task.accept(null));
        }
    }

    /**
     * 简化版本：在实体上运行任务（只执行Runnable，不传递任务对象）
     */
    public static void runAtEntitySimple(Plugin plugin, Entity entity, Runnable task) {
        if (isFolia()) {
            // Folia: 使用实体调度器
            entity.getScheduler().run(plugin, scheduledTask -> task.run(), null);
        } else {
            // 传统 Bukkit: 使用同步任务
            Bukkit.getScheduler().runTask(plugin, task);
        }
    }

    /**
     * 检查是否是 Folia 服务器
     */
    public static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}