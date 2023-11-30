package xyz.geik.glib;

import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import dev.triumphteam.cmd.bukkit.message.BukkitMessageKey;
import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.message.MessageKey;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.geik.glib.configuration.Language;
import xyz.geik.glib.chat.ChatUtils;

public class GLib {

    @Getter
    private static JavaPlugin instance;

    @Getter
    private static Language langFile;

    /**
     * CommandManager
     */
    @Getter
    private static BukkitCommandManager<CommandSender> commandManager;

    public GLib(JavaPlugin instance, Language langFile) {
        GLib.instance = instance;
        GLib.langFile = langFile;
    }

    /**
     * Setups commands
     */
    private void setupCommands(JavaPlugin instance, BaseCommand defaultCommand) {
        commandManager = BukkitCommandManager.create(instance);
        commandManager.registerCommand(defaultCommand);
        commandManager.registerMessage(MessageKey.INVALID_ARGUMENT, (sender, invalidArgumentContext) ->
                ChatUtils.sendMessage(sender, getLangFile().getMessages().getInvalidArgument()));
        commandManager.registerMessage(MessageKey.UNKNOWN_COMMAND, (sender, invalidArgumentContext) ->
                ChatUtils.sendMessage(sender, getLangFile().getMessages().getUnknownCommand()));
        commandManager.registerMessage(MessageKey.NOT_ENOUGH_ARGUMENTS, (sender, invalidArgumentContext) ->
                ChatUtils.sendMessage(sender, getLangFile().getMessages().getNotEnoughArguments()));
        commandManager.registerMessage(MessageKey.TOO_MANY_ARGUMENTS, (sender, invalidArgumentContext) ->
                ChatUtils.sendMessage(sender, getLangFile().getMessages().getTooManyArguments()));
        commandManager.registerMessage(BukkitMessageKey.NO_PERMISSION, (sender, invalidArgumentContext) ->
                ChatUtils.sendMessage(sender, getLangFile().getMessages().getNoPerm()));
    }
}
