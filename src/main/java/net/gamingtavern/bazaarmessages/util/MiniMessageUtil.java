package net.gamingtavern.bazaarmessages.util;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.gamingtavern.bazaarmessages.BazaarMessages.log;

public class MiniMessageUtil {
    /**
     * Get a mini message formatted string from a Configuration Section
     * @param configuration
     * @return string
     */
    public static String getMessageFromConfigurationSection(@NotNull ConfigurationSection configuration) {
        String name = configuration.getName();
        log.info("Getting the " + name + " message");

        boolean enabled = configuration.getBoolean("enabled");

        if (!enabled) {
            return null;
        }

        String miniMessage = configuration.getString("mini-message");

        if (miniMessage != null) {
            return miniMessage;
        }

        String message      = getListFromSectionWithDelimiter(configuration, "message", "<newline>");
        String hoverMessage = getListFromSectionWithDelimiter(configuration, "hover-message", "<newline>");
        String command      = configuration.getString("command");

        String prefix = "";
        String suffix = "";

        // Set the command
        if (command != null && !command.equals("null") && !command.equals("") && !command.contains("/")) {
            prefix = joinMessages(prefix, "<click:run_command:'"+ command +"'>");
            suffix = joinMessages(prefix, "</click>");
        }

        // Set the hover message if it is not empty
        if (!hoverMessage.equals("")) {
            prefix = joinMessages(prefix, "<hover:show_text:'" + hoverMessage + "'>");
            suffix = joinMessages(prefix, "</hover>");
        }

        String finalMessage = String.join(prefix, message, suffix);

        return finalMessage;
    }

    /**
     * Join two messages without a delimiter
     * @param messages
     * @return string
     */
    private static String joinMessages(String... messages) {
        return String.join("", messages);
    }

    /**
     * Gets a string taken from a list at the configuration section with a delimiter
     * @param configurationSection
     * @param string
     * @param delimiter
     * @return string
     */
    private static String getListFromSectionWithDelimiter(ConfigurationSection configurationSection, String string, String delimiter) {
        // Get the list of strings
        List<String> messages = configurationSection.getStringList(string);
        // Put delimiter in between the strings
        return String.join(delimiter, messages);
    }
}
