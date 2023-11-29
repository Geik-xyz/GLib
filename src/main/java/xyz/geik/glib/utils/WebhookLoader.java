package xyz.geik.glib.utils;


import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import xyz.geik.glib.GLib;
import xyz.geik.webhook.discord.Webhook;

import java.io.File;

/**
 * @author poyrazinan
 */
public class WebhookLoader {

    /**
     * Loads webhook from webhooks.yml file
     *
     * @param webhookName Name of webhook
     * @return Webhook object
     */
    public static @NotNull Webhook loadWebhook(String webhookName) {
        File webhookFile = new File("plugins/" + GLib.getInstance().getDescription().getName() + "/webhooks.yml");
        FileConfiguration wh = YamlConfiguration.loadConfiguration(webhookFile);
        // Loading webhooks.yml file
        OfflinePlayer player = Bukkit.getOnlinePlayers().stream().findFirst().orElse(null);
        if (player == null)
            player = Bukkit.getOfflinePlayers()[0];
        // Creating webhook object and adding it to list
        return new Webhook(webhookName,
                wh.getString(webhookName + ".url"),
                PlaceholderAPI.setPlaceholders(player, wh.getString(webhookName + ".username")),
                PlaceholderAPI.setPlaceholders(player, wh.getString(webhookName + ".image")),
                PlaceholderAPI.setPlaceholders(player, wh.getString(webhookName + ".thumbnail")),
                PlaceholderAPI.setPlaceholders(player, wh.getString(webhookName + ".title")),
                null,
                PlaceholderAPI.setPlaceholders(player, wh.getString(webhookName + ".description")),
                PlaceholderAPI.setPlaceholders(player, wh.getString(webhookName + ".content")),
                PlaceholderAPI.setPlaceholders(player, wh.getString(webhookName + ".footer")),
                Webhook.GColor.valueOf(wh.getString(webhookName + ".color")),
                PlaceholderAPI.setPlaceholders(player, wh.getString(webhookName + ".webUrl")));
    }
}
