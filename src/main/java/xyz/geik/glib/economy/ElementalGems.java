package xyz.geik.glib.economy;

import me.elementalgaming.ElementalGems.GemAPI;
import org.bukkit.OfflinePlayer;

/**
 * ElementalGems economy integration class
 *
 * @author Amowny
 * @since v6-b003
 */
class ElementalGems implements Economy {

    private boolean economy;

    /**
     * Constructor register event of super class
     */
    public ElementalGems() {
        this.economy = true;
        if (!setupEconomy()) {
            this.economy = false;
        }
    }

    private boolean setupEconomy() {
        return (EconomyAPI.instance.getServer().getPluginManager().getPlugin("ElementalGems") != null);
    }

    /**
     * Withdraw player balance
     * @param player
     * @param price
     * @return price
     */
    @Override
    public double withdrawPlayer(OfflinePlayer player, double price) {
        if (this.economy) {
            GemAPI gemAPI = new GemAPI();
            gemAPI.removeGems(player.getUniqueId(), price);
        }
        return price;
    }

    /**
     * Deposit player balance
     * @param player
     * @param price
     * @return price
     */
    @Override
    public double depositPlayer(OfflinePlayer player, double price) {
        if (this.economy) {
            GemAPI gemAPI = new GemAPI();
            gemAPI.addGems(player.getUniqueId(), price);
        }
        return price;
    }

    /**
     * Get player balance
     * @param player
     * @return player balance
     */
    public double getBalance(OfflinePlayer player) {
        if (this.economy) {
            GemAPI gemAPI = new GemAPI();
            return gemAPI.getGems(player.getUniqueId());
        }
        return 0;
    }
}
