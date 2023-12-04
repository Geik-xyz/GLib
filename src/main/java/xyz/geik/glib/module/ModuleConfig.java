package xyz.geik.glib.module;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import lombok.Getter;
import lombok.Setter;

/**
 * Module config
 */
@Getter
@Setter
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class ModuleConfig extends OkaeriConfig {

    @Comment("Main settings")
    private Settings settings = new Settings();

    /**
     * Settings configuration of config
     *
     * @author geik
     * @since 2.0
     */
    @Getter
    @Setter
    public static class Settings extends OkaeriConfig {
        @Comment({"if you don't want to use this system",
                "you can enable/disable this feature"})
        private boolean feature = true;
    }
}
