package xyz.geik.glib.message.title;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleAPI {

    /**
     * Send title
     *
     * @param player
     * @param title
     */
    public static void sendTitle(final Player player, final String title) {
        new TitleModifier().sendTitle(player, title, "", 100);
    }

    /**
     * Send title with duration
     *
     * @param player
     * @param title
     * @param duration
     */
    public static void sendTitle(final Player player, final String title, final int duration) {
        new TitleModifier().sendTitle(player, title, "", duration);
    }

    /**
     * Send subtitle
     *
     * @param player
     * @param subtitle
     */
    public static void sendSubtitle(final Player player, final String subtitle) {
        new TitleModifier().sendTitle(player, "", subtitle, 100);
    }

    /**
     * Send subtitle with duration
     *
     * @param player
     * @param subtitle
     * @param duration
     */
    public static void sendSubtitle(final Player player, final String subtitle, final int duration) {
        new TitleModifier().sendTitle(player, "", subtitle, duration);
    }

    /**
     * Send title with duration
     *
     * @param player
     * @param title
     * @param subtitle
     */
    public static void sendFullTitle(final Player player, final String title, final String subtitle) {
        new TitleModifier().sendTitle(player, title, subtitle, 100);
    }

    /**
     * Send title and subtitle with duration
     *
     * @param player
     * @param title
     * @param subtitle
     * @param duration
     */
    public static void sendFullTitle(final Player player, final String title, final String subtitle, final int duration) {
        new TitleModifier().sendTitle(player, title, subtitle, duration);
    }

    /**
     * Broadcast title
     *
     * @param title
     */
    public static void broadcastTitle(final String title) {
        if (Bukkit.getOnlinePlayers() != null && !Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers())
                new TitleModifier().sendTitle(player, title, "", 100);
    }

    /**
     * Broadcast title with duration
     *
     * @param title
     * @param duration
     */
    public static void broadcastTitle(final String title, final int duration) {
        if (Bukkit.getOnlinePlayers() != null && !Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers())
                new TitleModifier().sendTitle(player, title, "", duration);
    }

    /**
     * Broadcast subtitle
     *
     * @param subtitle
     */
    public static void broadcastSubtitle(final String subtitle) {
        if (Bukkit.getOnlinePlayers() != null && !Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers())
                new TitleModifier().sendTitle(player, "", subtitle, 100);
    }

    /**
     * Broadcast subtitle with duration
     *
     * @param subtitle
     * @param duration
     */
    public static void broadcastSubtitle(final String subtitle, final int duration) {
        if (Bukkit.getOnlinePlayers() != null && !Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers())
                new TitleModifier().sendTitle(player, "", subtitle, duration);
    }

    /**
     * Broadcast title and subtitle
     *
     * @param title
     * @param subtitle
     */
    public static void broadcastFullTitle(final String title, final String subtitle) {
        if (Bukkit.getOnlinePlayers() != null && !Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers())
                new TitleModifier().sendTitle(player, title, subtitle, 100);
    }

    /**
     * Broadcast title and subtitle with duration
     *
     * @param title
     * @param subtitle
     * @param duration
     */
    public static void broadcastFullTitle(final String title, final String subtitle, final int duration) {
        if (Bukkit.getOnlinePlayers() != null && !Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers())
                new TitleModifier().sendTitle(player, title, subtitle, duration);
    }

    /**
     * Clear title
     *
     * @param player
     */
    public static void clearTitle(Player player) {
        new TitleModifier().sendTitle(player, "", "", 0);
    }

}
