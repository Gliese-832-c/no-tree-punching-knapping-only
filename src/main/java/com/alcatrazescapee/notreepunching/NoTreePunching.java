/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching;

import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.alcatrazescapee.notreepunching.client.ModGuiHandler;
import com.alcatrazescapee.notreepunching.client.ModSounds;
import com.alcatrazescapee.notreepunching.common.blocks.ModBlocks;
import com.alcatrazescapee.notreepunching.common.capability.CapabilityPlayerItem;
import com.alcatrazescapee.notreepunching.common.items.ModItems;
import com.alcatrazescapee.notreepunching.common.recipe.ModRecipes;
import com.alcatrazescapee.notreepunching.util.HarvestBlockHandler;
import com.alcatrazescapee.notreepunching.util.WoodRecipeHandler;

@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(modid = NoTreePunching.MOD_ID, version = NoTreePunching.VERSION, dependencies = NoTreePunching.DEPENDENCIES, useMetadata = true, certificateFingerprint = "3c2d6be715971d1ed58a028cdb3fae72987fc934")
public final class NoTreePunching
{

    public static final String MOD_ID = "yesflintknapping";
    public static final String MOD_NAME = "Yes Flint Knapping";
    public static final String VERSION = "1.1.0";

    // Versioning
    private static final String FORGE_MIN = "14.23.5.2847";
    private static final String FORGE_MAX = "15.0.0.0";
    private static final String ALC_MIN = "1.0.0";
    private static final String ALC_MAX = "2.0.0";

    public static final String DEPENDENCIES = "required-after:forge@[" + FORGE_MIN + "," + FORGE_MAX + ");required-after:alcatrazcore@[" + ALC_MIN + "," + ALC_MAX + ");";

    @Mod.Instance
    private static NoTreePunching instance;
    private static Logger log;

    public static NoTreePunching getInstance()
    {
        return instance;
    }

    public static Logger getLog()
    {
        return log;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        log = event.getModLog();
        log.debug("If you can see this, debug logging is working :)");

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());

        // Pre-Init Managers
        CapabilityPlayerItem.preInit();
        ModBlocks.preInit();
        ModItems.preInit();
        ModSounds.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Init Managers
        ModRecipes.init();
        ModItems.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Post-Init Managers
        HarvestBlockHandler.postInit();

        if (ModConfig.GENERAL.enableAdvancedRecipeReplacement)
        {
            WoodRecipeHandler.postInit();
        }
        ModRecipes.postInit();
    }

    /*@Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event)
    {
        isSignedBuild = false;
        FMLCommonHandler.instance().registerCrashCallable(new ICrashCallable()
        {
            @Override
            public String getLabel()
            {
                return MOD_NAME;
            }

            @Override
            public String call()
            {
                return "You are not running an official build. This version will NOT be supported by the author.";
            }
        });
    }*/
}