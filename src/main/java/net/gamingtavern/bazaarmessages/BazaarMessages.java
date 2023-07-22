package net.gamingtavern.bazaarmessages;

import lombok.Getter;
import net.gamingtavern.bazaarmessages.listeners.onEnquiryCreate;
import net.gamingtavern.bazaarmessages.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BazaarMessages extends JavaPlugin implements Listener {
    private static @Getter BazaarMessages instance;
    public static Logger log;
    public static String sellMessage;
    public static String buyMessage;



    @Override
    public void onEnable() {
        instance = this;
        log = Bukkit.getLogger();

        // Register Events
        log.info("Register Events");
        new onEnquiryCreate();

        // Loading Commands
        log.info("Loading Commands");

        // Load the configs
        log.info("Loading config.yml");
        sellMessage = ConfigUtil.getTemplateMessage(this, "sell");
        buyMessage  = ConfigUtil.getTemplateMessage(this, "buy" );
    }

    public void reloadConfiguration() {
        log.info("Reloading config.yml");
        reloadConfig();
        sellMessage = ConfigUtil.getTemplateMessage(this, "sell");
        buyMessage  = ConfigUtil.getTemplateMessage(this, "buy" );
    }
}
