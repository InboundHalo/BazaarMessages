package net.gamingtavern.bazaarmessages.util;

import de.ancash.bazaar.core.Category;
import de.ancash.bazaar.core.Enquiry;
import de.ancash.bazaar.events.EnquiryCreateEvent;
import net.gamingtavern.bazaarmessages.BazaarMessages;
import org.bukkit.inventory.ItemStack;

import static net.gamingtavern.bazaarmessages.BazaarMessages.log;

public class BazaarUtil {
    public static ItemStack getItemstack(EnquiryCreateEvent enquiryCreateEvent) {
        int cat = enquiryCreateEvent.getCat();
        int sub = enquiryCreateEvent.getSub();
        int subSub = enquiryCreateEvent.getSubsub();
        return Category.getCategory(cat).getSubCategory(sub).getSubSubCategory(subSub).getShow();
    }

    public static String getUnformatedString(Enquiry.EnquiryType type) {
        if (type == Enquiry.EnquiryType.SELL_OFFER) {
            return BazaarMessages.sellMessage;
        } else if (type == Enquiry.EnquiryType.BUY_ORDER) {
            return BazaarMessages.buyMessage;
        } else {
            log.warning("Enquiry Created without the type SELL_OFFER or BUY_ORDER");
            return null;
        }
    }
}
