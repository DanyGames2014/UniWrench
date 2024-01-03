package net.danygames2014.uniwrench.item;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.modificationstation.stationapi.api.util.Identifier;

public class UniversalWrench extends WrenchBase {
    public UniversalWrench(Identifier identifier) {
        super(identifier);
        this.addWrenchMode(WrenchMode.MODE_WRENCH);
        this.addWrenchMode(WrenchMode.MODE_ROTATE);
    }
}
