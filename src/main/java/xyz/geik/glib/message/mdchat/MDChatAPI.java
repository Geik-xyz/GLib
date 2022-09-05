package xyz.geik.glib.message.mdchat;

import net.md_5.bungee.api.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

public class MDChatAPI {

    /**
     * Send modern messages (Hoverable, linked messages)
     *
     * @param message
     */
    public static @NotNull TextComponent getFormattedMessage(String message) {
        return MDChat.getMessageFromString(message, true);
    }
}
