package net.danygames2014.uniwrench.api.event;

import net.danygames2014.uniwrench.item.UniversalWrench;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;

/**
 * Use this event to add modes onto the default Universal wrench which can be accessed using event.wrench
 */
@EventPhases(StationAPI.INTERNAL_PHASE)
public class UniversalWrenchModeEvent extends Event {
    public UniversalWrench wrench;

    public UniversalWrenchModeEvent(UniversalWrench wrench) {
        this.wrench = wrench;
    }
}
