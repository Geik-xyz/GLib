package xyz.geik.glib.module;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.geik.glib.GLib;
import xyz.geik.glib.api.ModuleDisableEvent;
import xyz.geik.glib.api.ModuleEnableEvent;
import xyz.geik.glib.chat.ChatUtils;

import java.io.File;
import java.util.HashMap;

/**
 * ModuleManager of leaderos-plugin
 *
 * @author poyrazinan
 * @since 1.0
 */
public class ModuleManager {

    /**
     * List of modules
     */
    @Getter
    private static HashMap<String, Modulable> modules = new HashMap<>();

    /**
     * Module getter
     * @param name of module
     * @return module
     */
    public static Modulable getModule(String name) {
        return modules.get(name);
    }

    /**
     * Registers module to module list
     * @param module of leaderos-plugin
     */
    public void registerModule(Modulable module) {
        modules.put(module.getName(), module);
    }

    /**
     * Gets module status
     * @param moduleName name of module
     * @return status
     */
    public static boolean getModuleStatus(String moduleName) {
        File moduleFile = new File("plugins/" + GLib.getInstance().getDescription().getName() + "/modules.yml");
        FileConfiguration modules = YamlConfiguration.loadConfiguration(moduleFile);
        return modules.getBoolean(moduleName + ".status");
    }

    /**
     * Enables all modules
     */
    public void enableModules() {
        modules.keySet().forEach(moduleName -> {
            Modulable module = getModule(moduleName);
            // Checks if module has dependency
            if (!module.getDependencies().isEmpty()) {
                // Dependency status of module
                boolean dependStatus = module.getDependencies().stream()
                        .allMatch(ModuleManager::getModuleStatus);
                // If requirements not met disables module
                if (!dependStatus) {
                    module.setEnabled(false);
                    String message = "&3[" + GLib.getInstance().getName() + "] &4" + module.getName() + " couldn't find dependencies " + module.getDependencyListAsString();
                    ChatUtils.sendMessage(org.bukkit.Bukkit.getConsoleSender(), message);
                    return;
                }
            }
            if (getModuleStatus(module.getName())) {
                module.setEnabled(true);
                // Enable event
                org.bukkit.Bukkit.getPluginManager().callEvent(new ModuleEnableEvent(module));
                module.onEnable();
                String message = "&3[" + GLib.getInstance().getName() + "] &a" + module.getName() + " enabled.";
                ChatUtils.sendMessage(org.bukkit.Bukkit.getConsoleSender(), message);
            }
            else {
                module.setEnabled(false);
                String message = "&3[" + GLib.getInstance().getName() + "] &c" + module.getName() + " closed.";
                ChatUtils.sendMessage(org.bukkit.Bukkit.getConsoleSender(), message);
            }
        });
    }

    /**
     * Disables all modules
     */
    public void disableModules() {
        modules.keySet().forEach(moduleName -> {
            Modulable module = modules.get(moduleName);
            if (module.isEnabled()) {
                module.setEnabled(false);
                // Disable event
                org.bukkit.Bukkit.getPluginManager().callEvent(new ModuleDisableEvent(module));
                module.onDisable();
                String message = "&3[" + GLib.getInstance().getName() + "] &c" + module.getName() + " disabled.";
                ChatUtils.sendMessage(org.bukkit.Bukkit.getConsoleSender(), message);
            }
        });
    }

    /**
     * reload modules
     */
    private void reload() {
        modules.keySet().forEach(moduleName -> {
            Modulable module = modules.get(moduleName);
            if (module.isEnabled())
                module.onReload();
        });
    }

    /**
     * Reload modules
     */
    public void reloadModules() {
        reload();
        disableModules();
        enableModules();
    }

    /**
     * Constructor of ModuleManager
     */
    public ModuleManager() {}
}
