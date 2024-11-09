package net.danygames2014.uniwrench.api;

import net.danygames2014.uniwrench.UniWrench;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class WrenchableBlockRegistry {
    public final HashMap<Block, ArrayList<WrenchFunction>> leftClickActions;
    public final HashMap<Block, ArrayList<WrenchFunction>> rightClickActions;
    private static WrenchableBlockRegistry INSTANCE;

    private static WrenchableBlockRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WrenchableBlockRegistry();
        }
        return INSTANCE;
    }

    public WrenchableBlockRegistry() {
        this.leftClickActions = new HashMap<>();
        this.rightClickActions = new HashMap<>();
    }

    /**
     * Registers a function to be called when the specified block is left clicked with a wrench
     *
     * @param block      The block that the function will be called for
     * @param wrenchable The function with a signature of <code>(stack, player, isSneaking, world, x, y, z, side, wrenchMode)</code>
     */
    public static void registerLeftClickAction(Block block, WrenchFunction wrenchable) {
        WrenchableBlockRegistry registry = getInstance();

        if (!registry.leftClickActions.containsKey(block)) {
            registry.leftClickActions.put(block, new ArrayList<>());
        }

        UniWrench.LOGGER.info("Registering Left Click Action for block {}", block);
        getInstance().leftClickActions.get(block).add(wrenchable);
    }

    /**
     * Registers a function to be called when the specified block is right clicked with a wrench
     *
     * @param block      The block that the function will be called for
     * @param wrenchable The function with a signature of <code>(stack, player, isSneaking, world, x, y, z, side, wrenchMode)</code>
     */
    public static void registerRightClickAction(Block block, WrenchFunction wrenchable) {
        WrenchableBlockRegistry registry = getInstance();

        if (!registry.rightClickActions.containsKey(block)) {
            registry.rightClickActions.put(block, new ArrayList<>());
        }

        UniWrench.LOGGER.info("Registering Right Click Action for block {}", block);
        getInstance().rightClickActions.get(block).add(wrenchable);
    }

    /**
     * Gets the list of registered actions for the specified block
     *
     * @param block The block to look for action for
     * @return The actions for that block
     */
    public static ArrayList<WrenchFunction> getLeftClickActions(Block block) {
        return getInstance().leftClickActions.getOrDefault(block, new ArrayList<>());
    }

    /**
     * Gets the list of registered actions for the specified block
     *
     * @param block The block to look for action for
     * @return The actions for that block
     */
    public static ArrayList<WrenchFunction> getRightClickActions(Block block) {
        return getInstance().rightClickActions.getOrDefault(block, new ArrayList<>());
    }

    public static boolean doRightClickActionsExist(Block block) {
        return getInstance().rightClickActions.containsKey(block);
    }

    public static boolean doLeftClickActionsExist(Block block) {
        return getInstance().leftClickActions.containsKey(block);
    }
}
