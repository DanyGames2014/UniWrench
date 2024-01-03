package net.danygames2014.uniwrench.api;

import net.minecraft.client.resource.language.I18n;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.HashMap;

@SuppressWarnings("unused")
public class WrenchMode {
    public static WrenchMode MODE_WRENCH;
    public static WrenchMode MODE_ROTATE;
    public static final WrenchMode INVALID = new WrenchMode(Identifier.of("invalid_wrench_mode"));

    public Identifier identifier;
    public String name;
    public String translationKey;

    public static HashMap<Identifier,WrenchMode> WRENCH_MODES;

    public WrenchMode(Identifier identifier) {
        this.identifier = identifier;
        this.name = identifier.path;
        this.translationKey = "wrenchmode." + identifier.namespace + ":" + name + ".name";

        if(WRENCH_MODES == null){
            WRENCH_MODES = new HashMap<>();
        }

        WRENCH_MODES.putIfAbsent(identifier, this);
    }

    public static WrenchMode getWrenchMode(Identifier identifier){
        return WRENCH_MODES.getOrDefault(identifier, INVALID);
    }

    public static WrenchMode getWrenchMode(String name){
        for(var wrenchMode : WRENCH_MODES.values()){
            if(wrenchMode.name.equals(name)){
                return wrenchMode;
            }
        }
        return INVALID;
    }

    public String getTranslatedName(){
        return I18n.getTranslation(translationKey);
    }
}
