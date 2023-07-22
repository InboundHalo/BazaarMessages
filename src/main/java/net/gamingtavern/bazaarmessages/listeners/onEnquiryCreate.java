package net.gamingtavern.bazaarmessages.listeners;

import de.ancash.bazaar.core.Enquiry;
import de.ancash.bazaar.events.EnquiryCreateEvent;
import net.gamingtavern.bazaarmessages.BazaarMessages;
import net.gamingtavern.bazaarmessages.util.BazaarUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.UUID;

public class onEnquiryCreate implements Listener {

    public void onEnquiryCreate() {
        Bukkit.getPluginManager().registerEvents(this, BazaarMessages.getInstance());
    }

    @EventHandler
    private void onEnquiryCreateEvent(EnquiryCreateEvent event) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        if (players.isEmpty())
            return; // Return if there are no players to message

        Enquiry.EnquiryType type = event.getType();

        String message = BazaarUtil.getUnformatedString(type);

        if (message == null) {
            return; // Return if there is no config section
        }

        UUID playerUUID = event.getPlayer();
        ItemStack item = BazaarUtil.getItemstack(event);
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);

        /*
         If multiple servers are linked together and
         someone joined one and made an enquiry the
         other servers would get null as the player
         has not logged on. This puts a placeholder instead.
        */
        String displayName = player.getName();
        if (displayName == null) displayName = "NotFound";

        String itemName    = BazaarUtil.getItemstack(event)
                .getItemMeta()
                .getDisplayName();

        int amount         = event.getAmount();
        double pricePer    = event.getPrice();

        message = message
                .replace("%player%", displayName)
                .replace("%item-name%", itemName)
                .replace("%amount%", Integer.toString(amount))
                .replace("%price-per%", Double.toString(pricePer));

        Component component = MiniMessage.miniMessage().deserialize(message);

        for (Player p : players) {
            p.sendMessage(component);
        }
    }
}
