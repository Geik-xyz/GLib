package xyz.geik.glib.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import lombok.Getter;
import lombok.Setter;

/**
 * @author geik
 * @since 2.0
 */
@Getter
@Setter
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class Language extends OkaeriConfig {

    /**
     * Settings menu of config
     */
    @Comment("Chat messages")
    private Messages messages = new Messages();

    /**
     * Messages of plugin
     *
     * @author geik
     * @since 2.0
     */
    @Getter
    @Setter
    public static class Messages extends OkaeriConfig {

        @Comment("Prefix of messages")
        private String prefix = "&3Plugin &8»";

        private String playerNotOnline = "{prefix} &cTarget player is not online.";
        private String playerNotAvailable = "{prefix} &cPlayer is not available.";
        private String targetPlayerNotAvailable = "{prefix} &cTarget player is not available.";
        private String configReloaded = "{prefix} &aConfig reloaded successfully.";
        private String invalidArgument = "{prefix} &cInvalid argument!";
        private String unknownCommand = "{prefix} &cUnknown command!";
        private String notEnoughArguments = "{prefix} &cNot enough arguments!";
        private String tooManyArguments = "{prefix} &cToo many arguments!";
        private String noPerm = "{prefix} &cYou do not have permission to do this action!";
    }
}
