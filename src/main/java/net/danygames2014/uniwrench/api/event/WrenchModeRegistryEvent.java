package net.danygames2014.uniwrench.api.event;

import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;

/**
 * Use this event to create new Wrench Modes
 */
@EventPhases(StationAPI.INTERNAL_PHASE)
public class WrenchModeRegistryEvent extends Event {
}
