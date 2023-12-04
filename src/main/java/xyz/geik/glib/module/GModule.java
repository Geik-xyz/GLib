package xyz.geik.glib.module;


import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a Module and its main class. It contains fundamental methods
 * and fields for a module to be loaded and work properly. This is an indirect
 * implementation of {@link Modulable}.
 * @author poyrazinan
 * @since 1.0
 */
public abstract class GModule implements Modulable {

    @Getter
    private OkaeriConfig configFile;

    /**
     * Registers config to file
     * @param configClass config class
     * @param instance of plugin
     */
    public void registerConfig(Class configClass, JavaPlugin instance) {
        this.configFile = ConfigManager.create(configClass, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer());
            it.withBindFile(new File(instance.getDataFolder() + "/modules/" + getName(), "config.yml"));
            it.saveDefaults();
            it.load(true);
        });
    }

    /**
     * Reloads config file
     */
    public void reloadConfig() {
        getConfigFile().load(true);
    }

    /**
     * List of dependencies contains dependency tree
     */
    @Getter
    private List<String> dependencies = new ArrayList<>();

    /**
     * Gets dependency list as string data for placeholders or messages.
     *
     * @return String of dependency list.
     */
    public String getDependencyListAsString() {
        if (getDependencies().isEmpty())
            return "";
        else
            return IntStream.range(0, getDependencies().size())
                    .mapToObj(i -> (i + 1) + ". " + getDependencies().get(i))
                    .collect(Collectors.joining(", "));
    }

    /**
     * Adds dependency to module.
     * @param name should be a module and name of it.
     */
    @Override
    public void addDependency(String name) {
        dependencies.add(name);
    }

    /**
     * Gets name of module
     * @return String name of module
     */
    @Override
    public @NotNull String getName() {
        return getClass().getSimpleName().replace("Module", "");
    }

    /**
     * is module enabled or not
     */
    @Getter
    private boolean isEnabled = false;

    /**
     * Sets status of isEnabled
     * @param isEnabled module
     */
    @Override
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * onEnable method of module
     */
    @Override
    public void onEnable() {}

    /**
     * onDisable method of module
     */
    @Override
    public void onDisable() {}

    /**
     * onReload method of module
     */
    @Override
    public void onReload() {}

    /**
     * Constructor of LeaderOSModule
     */
    public GModule() {}
}
