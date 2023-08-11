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
        @Config.Comment("If true, this will allow NTP to search aggressively for recipes and try and replace log -> wood recipes. (Makes NTPs wood chopping / saw recipes more automatic.) For pack makers: this WILL run after craft tweaker, and it WILL override any recipes craft tweaker adds.")
        public boolean enableAdvancedRecipeReplacement = true;

        @Config.Comment("If false, this will disable all changes to breaking speed + block drops.")
        public boolean enableBreakingChanges = true;

        private GeneralConfig() {}
    }

    public static class BalanceConfig
    {
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
        @Config.Comment("Whether to enable tools at all")
        @Config.RequiresMcRestart
        public boolean enableTools = true;

        private ToolsConfig() {}
    }
}
