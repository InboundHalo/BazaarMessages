package net.gamingtavern.bazaarmessages.util;

import net.gamingtavern.bazaarmessages.BazaarMessages;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

import static net.gamingtavern.bazaarmessages.util.MiniMessageUtil.getMessageFromConfigurationSection;
import static net.gamingtavern.bazaarmessages.BazaarMessages.log;

public class ConfigUtil {
    public static String getTemplateMessage(BazaarMessages bazaarMessages, String s) {
        FileConfiguration config = bazaarMessages.getConfig();
        ConfigurationSection messageSection = config.getConfigurationSection(s + "-message");

        return getMessage(messageSection, s + "-message");
    }

    private static String getMessage(ConfigurationSection configurationSection, String sectionName) {
        if (configurationSection == null) {
            log.severe(sectionName + " does not exist");
            log.severe("disabling the message from being sent");
            return null;
        } else {
            return MiniMessageUtil.getMessageFromConfigurationSection(configurationSection);
        }
    }
}
