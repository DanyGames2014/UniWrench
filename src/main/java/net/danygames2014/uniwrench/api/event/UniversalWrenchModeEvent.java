package net.danygames2014.uniwrench.api.event;

import net.danygames2014.uniwrench.item.UniversalWrench;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.StationAPI;

@EventPhases(StationAPI.INTERNAL_PHASE)
public class UniversalWrenchModeEvent extends Event {
    public UniversalWrench wrench;

    public UniversalWrenchModeEvent(UniversalWrench wrench) {
        this.wrench = wrench;
    }
}
