package net.gamingtavern.bazaarmessages.commands;

import net.gamingtavern.bazaarmessages.BazaarMessages;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang3.time.StopWatch;

@CommandAlias("bazaarmessages|bazaarmessage|bzmsgs|bzmsg")
@Description("Discord commands")
@CommandPermission("bazaarmessages")
public class BazaarMessagesCommand extends BaseCommand {
    MiniMessage msg = MiniMessage.miniMessage();

    @Default
    @Subcommand("help")
    @Description("Learn about the bazaar messages commands")
    @CommandPermission("bazaarmessages")
    public void onHelp(Player sender) {
        sender.sendMessage(msg.deserialize("Hello3 " + sender.getUsername()));
    }

    @Subcommand("reload")
    @Description("Reload Bazaar Messages' config")
    @CommandPermission("bazaarmessages.reload")
    public void onReload(Player sender) {
        // Start a timer
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        // Reload the config
        sender.sendMessage(Msg.get("<green>Reloading Bazaar Messages"));
        BazaarMessages.getInstance().reloadConfiguration();

        // Stop the timer and send the message to the player
        stopwatch.stop();
        sender.sendMessage(Msg.get("<green>Finished reloading <white>config.yml <green>in <yellow>" + stopwatch.getSplitTime()));
    }
}