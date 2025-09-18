package net.danygames2014.uniwrench.api;

import net.danygames2014.uniwrench.UniWrench;
import net.danygames2014.uniwrench.init.WrenchModeListener;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.HashMap;

@SuppressWarnings("unused")
public class WrenchMode {
    public static WrenchMode MODE_WRENCH;
    public static WrenchMode MODE_ROTATE;
    public static WrenchMode MODE_DEBUG;
    public static final WrenchMode INVALID = new WrenchMode(WrenchModeListener.NAMESPACE.id("invalid_wrench_mode"));

    public Identifier identifier;
    public String name;
    public String translationKey;

    public static HashMap<Identifier,WrenchMode> WRENCH_MODES;

    public WrenchMode(Identifier identifier) {
        UniWrench.LOGGER.info("Creating wrench mode {}", identifier);

        this.identifier = identifier;
        this.name = identifier.path;
        this.translationKey = "wrenchmode." + identifier.namespace + "." + name + ".name";

        if (WRENCH_MODES == null) {
            WRENCH_MODES = new HashMap<>();
        }
        
        WRENCH_MODES.putIfAbsent(identifier, this);
    }

    // Wrench Mode Actions
    /**
     * This method will be fired when the block is right-clicked with a wrench
     * @param stack ItemStack of the wrench
     * @param player Player which right-clicked the block
     * @param isSneaking If the player is sneaking
     * @param world The world in which this happened
     * @param x x-coordinate of the right-clicked block
     * @param y y-coordinate of the right-clicked block
     * @param z z-coordinate of the right-clicked block
     * @param side Side of the block which was right-clicked
     * @param wrenchMode The current wrench mode of the wrench
     * @return If the action was susccesfull, returning true will cancel the onUse method on the block aswell as all further actions
     */
    public boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        return false;
    }

    /**
     * This method will be fired when the block is left-clicked with a wrench
     * @param stack ItemStack of the wrench
     * @param player Player which left-clicked the block
     * @param isSneaking If the player is sneaking
     * @param world The world in which this happened
     * @param x x-coordinate of the left-clicked block
     * @param y y-coordinate of the left-clicked block
     * @param z z-coordinate of the left-clicked block
     * @param side Side of the block which was left-clicked
     * @param wrenchMode The current wrench mode of the wrench
     * @return If the action was susccesfull, returning true will cancel all further actions
     */
    public boolean wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        return false;
    }

    public String getTranslatedName(){
        return I18n.getTranslation(translationKey);
    }
    
    // Registry Methods
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
}
