package net.danygames2014.uwtest.event;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.event.UniversalWrenchModeEvent;
import net.danygames2014.uniwrench.api.event.WrenchModeRegistryEvent;
import net.danygames2014.uniwrench.api.event.WrenchableBlockRegisterEvent;
import net.danygames2014.uwtest.block.SoftDependentBlock;
import net.danygames2014.uwtest.item.TestWrenchMode;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;

public class WrenchModeListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;
    
    public static WrenchMode testMode1;
    public static WrenchMode testMode2;
    public static WrenchMode testMode3;
    public static WrenchMode testMode4;
    public static WrenchMode testMode5;
    
    @EventListener
    public void registerWrenchModes(WrenchModeRegistryEvent event){
        testMode1 = new TestWrenchMode(NAMESPACE.id("test_mode_1"));
        testMode2 = new WrenchMode(NAMESPACE.id("test_mode_2"));
        testMode3 = new WrenchMode(NAMESPACE.id("test_mode_3"));
        testMode4 = new WrenchMode(NAMESPACE.id("test_mode_4"));
        testMode5 = new WrenchMode(NAMESPACE.id("test_mode_5"));
    }

    @EventListener
    public void wrenchableBlock(WrenchableBlockRegisterEvent event){
        event.registerLeftClickAction(TestListener.softDependentBlock, ((SoftDependentBlock)TestListener.softDependentBlock)::wrenchLeftClick);
        event.registerRightClickAction(TestListener.softDependentBlock, ((SoftDependentBlock)TestListener.softDependentBlock)::wrenchRightClick);
    }

    @EventListener
    public void addWrenchModes(UniversalWrenchModeEvent event){
        event.addWrenchMode(testMode1);
    }
}
