package xyz.geik.glib.utils;

import org.bukkit.OfflinePlayer;
import xyz.geik.glib.GLibAPI;

public class EconAPI {

    /**
     * Get player Money
     *
     * @param player
     * @return
     */
    public float getMoney(OfflinePlayer player) {
        return (float) GLibAPI.getEcon().getBalance(player);
    }

    /**
     * Add money to player
     *
     * @param player
     * @param value
     */
    public void addMoney(OfflinePlayer player, float value) {
        GLibAPI.getEcon().depositPlayer(player, value);
    }

    /**
     * Remove money from player.
     * replace 0 if player cannot afford the value
     *
     * @param player
     * @param value
     */
    public void removeMoney(OfflinePlayer player, float value) {
        float money = getMoney(player);
        if (money - value < 0)
            GLibAPI.getEcon().withdrawPlayer(player, money);
        else
            GLibAPI.getEcon().withdrawPlayer(player, value);
    }
}
