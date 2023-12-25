package net.danygames2014.uniwrench.api;

import net.danygames2014.uniwrench.init.ItemListener;
import net.minecraft.client.resource.language.I18n;
import net.modificationstation.stationapi.api.util.Identifier;

public class WrenchMode {
    public static WrenchMode MODE_WRENCH = new WrenchMode(ItemListener.MOD_ID.id("wrench"));
    public static WrenchMode MODE_ROTATE = new WrenchMode(ItemListener.MOD_ID.id("rotate"));

    public String name = "";
    public String translationKey = "";

    public WrenchMode(Identifier identifier) {
        this.name = identifier.path;
        this.translationKey = "wrenchmode." + identifier.namespace + ":" + name + ".name";
    }

    public String getTranslatedName(){
        return I18n.getTranslation(translationKey);
    }
}
