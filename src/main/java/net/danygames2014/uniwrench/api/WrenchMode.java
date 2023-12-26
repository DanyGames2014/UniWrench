package net.danygames2014.uniwrench.api;

import net.minecraft.client.resource.language.I18n;
import net.modificationstation.stationapi.api.util.Identifier;

public class WrenchMode {
    public static WrenchMode MODE_WRENCH;
    public static WrenchMode MODE_ROTATE;

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
