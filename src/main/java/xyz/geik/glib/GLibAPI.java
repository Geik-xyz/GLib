package xyz.geik.glib;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class GLibAPI {

    private static Permission perms;
    public static Permission getPerms() {
        return perms;
    }
    private static Economy econ;
    public static Economy getEcon() {
        return econ;
    }

    private static Plugin instance;

    public static Plugin getInstance() {
        return GLibAPI.instance;
    }

    public GLibAPI(Plugin instance) {
        GLibAPI.instance = instance;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
