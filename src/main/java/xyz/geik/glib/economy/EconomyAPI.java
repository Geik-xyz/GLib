package xyz.geik.glib.economy;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * EconomyAPI
 *
 * @author poyrazinan
 * @since 1.0
 */
public class EconomyAPI {

    protected static JavaPlugin instance;

    @Getter
    private Economy economy;

    public EconomyAPI(JavaPlugin instance, String economy) {
        EconomyAPI.instance = instance;
        this.economy = EconomyType.getEconomy(EconomyType.getEconomyType(economy));
    }
}
