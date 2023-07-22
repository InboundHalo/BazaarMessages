package net.gamingtavern.bazaarmessages.commands;

import  co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.gamingtavern.bazaarmessages.BazaarMessages;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang3.time.StopWatch;
import org.bukkit.entity.Player;

@CommandAlias("bazaarmessages|bazaarmessage|bzmsgs|bzmsg")
@Description("Discord commands")
@CommandPermission("bazaarmessages")
public class BazaarMessagesCommand extends BaseCommand {
    MiniMessage msg = MiniMessage.miniMessage();

    @Subcommand("reload")
    @Description("Reload Bazaar Messages' config")
    @CommandPermission("bazaarmessages.reload")
    public void onReload(Player sender) {
        // Start a timer
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        // Reload the config
        sender.sendMessage(msg.deserialize("<green>Reloading Bazaar Messages"));
        BazaarMessages.getInstance().reloadConfiguration();

        // Stop the timer and send the message to the player
        stopwatch.stop();
        sender.sendMessage(msg.deserialize("<green>Finished reloading <white>config.yml <green>in <yellow>" + stopwatch.getSplitTime()));
    }
}