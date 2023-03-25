This is a fork of the No Tree Punching mod for 1.12.2 but I removed everything except for the flint knapping mechanic, and made it configurable (i.e. custom knapping recipes) because I need that for modpack making purposes. Anyone else is free to use this for their pack as well.

On top of that, I also left in the tools (knife, saw, mattock) as I need them for modpack purposes, however, for the sake of people using this mod as more of a CraftTweaker addon featuring a knapping mechanic a config option has been added that disables those tools by default.



This fork comes with CraftTweaker compatibility for the kanpping mechanic. Here's the methods:
`mods.yesflintknapping.Knapping.add(IIngredient input, IItemStack... output)`
`mods.yesflintknapping.Knapping.remove(IIngredient input)`

Example code:
```
import mods.yesflintknapping.Knapping;

Knapping.add(<minecraft:dirt>, <minecraft:diamond>*2, <minecraft:emerald>*5);
// This will knap a dirt block into two diamonds and five emeralds upon a successful knap.
```