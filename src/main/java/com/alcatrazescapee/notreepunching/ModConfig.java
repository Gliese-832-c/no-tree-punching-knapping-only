/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching;

import net.minecraftforge.common.config.Config;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;

@Config(modid = MOD_ID, category = "")
@SuppressWarnings("WeakerAccess")
public final class ModConfig
{
    public static final GeneralConfig GENERAL = new GeneralConfig();
    public static final BalanceConfig BALANCE = new BalanceConfig();
    public static final ToolsConfig TOOLS = new ToolsConfig();

    public static class GeneralConfig
    {
        @Config.Comment({"Blocks that are always breakable. Use the format modid:registryname or modid:registryname:metadata", "Note that this will not make blocks drop if they wouldn't normally drop when broken with fists."})
        public String[] alwaysBreakable = new String[] {"minecraft:leaves", "minecraft:gravel", "minecraft:sand", "minecraft:dirt", "minecraft:grass"};

        @Config.RequiresMcRestart
        @Config.Comment({"If true, NTP will add its own log->plank and plank->stick recipes and remove old versions",
                "If false, NTP will still add its own recipes, but it will not remove any other vanilla or modded recipes"})
        public boolean replaceLogRecipes = true;

        @Config.RequiresMcRestart
        @Config.Comment({"If true, NTP will remove recipes for vanilla wooden + stone tools. It will also attempt to hide these items from the creative inventory.",
                "If false, NTP will not add or remove any vanilla recipes"})
        public boolean replaceVanillaRecipes = true;

        @Config.RequiresMcRestart
        @Config.Comment("If true, this will allow NTP to search aggressively for recipes and try and replace log -> wood recipes. (Makes NTPs wood chopping / saw recipes more automatic.) For pack makers: this WILL run after craft tweaker, and it WILL override any recipes craft tweaker adds.")
        public boolean enableAdvancedRecipeReplacement = true;

        @Config.Comment("If false, this will disable all changes to breaking speed + block drops.")
        public boolean enableBreakingChanges = true;

        private GeneralConfig() {}
    }

    public static class BalanceConfig
    {
        @Config.Comment("Chance for a knapping to occur. Set to zero to disable knapping.")
        @Config.RangeDouble(min = 0, max = 1)
        public double knappingChance = 0.6;

        @Config.Comment("Chance for a successful knapping")
        @Config.RangeDouble(min = 0, max = 1)
        public double knappingSuccessChance = 0.7;

        @Config.Comment("Chance for a log chopping to occur")
        @Config.RangeDouble(min = 0, max = 1)
        public double logChoppingChance = 0.6;

        @Config.Comment("The chance for leaves to drop sticks when broken")
        @Config.RangeDouble(min = 0, max = 1)
        public double leavesStickDropChance = 0.2;

        private BalanceConfig() {}
    }

    public static class ToolsConfig
    {
        @Config.Comment("Mining level of tin tools. 0 = Wood, 1 = Stone, 2 = Iron, 3 = Diamond")
        @Config.RangeInt(min = 0, max = 4)
        @Config.RequiresMcRestart
        public int miningLevelTin = 0;

        @Config.Comment("Mining level of copper tools. 0 = Wood, 1 = Stone, 2 = Iron, 3 = Diamond")
        @Config.RangeInt(min = 0, max = 4)
        @Config.RequiresMcRestart
        public int miningLevelCopper = 1;

        @Config.Comment("Mining level of bronze tools. 0 = Wood, 1 = Stone, 2 = Iron, 3 = Diamond")
        @Config.RangeInt(min = 0, max = 4)
        @Config.RequiresMcRestart
        public int miningLevelBronze = 2;

        @Config.Comment("Mining level of steel tools. 0 = Wood, 1 = Stone, 2 = Iron, 3 = Diamond")
        @Config.RangeInt(min = 0, max = 4)
        @Config.RequiresMcRestart
        public int miningLevelSteel = 3;

        @Config.Comment("Enable copper versions of NTP tools (knife, mattock, saw)")
        @Config.RequiresMcRestart
        public boolean enableCopperTools = true;

        @Config.Comment("Enable tin versions of NTP tools (knife, mattock, saw)")
        @Config.RequiresMcRestart
        public boolean enableTinTools = true;

        @Config.Comment("Enable bronze versions of NTP tools (knife, mattock, saw)")
        @Config.RequiresMcRestart
        public boolean enableBronzeTools = true;

        @Config.Comment("Enable steel versions of NTP tools (knife, mattock, saw)")
        @Config.RequiresMcRestart
        public boolean enableSteelTools = true;

        private ToolsConfig() {}
    }
}
