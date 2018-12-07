//----------------------------------
//----------Drying Pallet-----------
//----------------------------------
//Parameters = input, output, time
//input = the input itemstack
//output = the output itemstack
//time = the refining time to do the task (if unused, the config "Base Drying Time" will ve used)
mods.rockhounding_oretiers.DryingPallet.add(<minecraft:rotten_flesh>, <minecraft:leather>, 500);
mods.rockhounding_oretiers.DryingPallet.add("rottenFlesh", <minecraft:leather>, 500);

//Parameters = input
//input = the input itemstack
mods.rockhounding_oretiers.DryingPallet.remove(<rockhounding_oretiers:tiersItems:3>);


//----------------------------------
//-----------Coal Refiner------------
//----------------------------------
//Parameters = input, output, time
//input = the input itemstack
//output = the refined output itemstack 
//time = the refining time to do the task (if unused, the config "Base Refining Time" will ve used)
mods.rockhounding_oretiers.CoalRefiner.add(<rockhounding_oretiers:tiersItems:1>, <rockhounding_oretiers:tiersItems:0>, 100);
mods.rockhounding_oretiers.CoalRefiner.add("itemLignite", <minecraft:coal>, 100);

//Parameters = input
//input = the itemstack representing the input to remove
mods.rockhounding_oretiers.CoalRefiner.remove(<minecraft:coal:0>);


//----------------------------------
//------------Bloomery--------------
//----------------------------------
//Parameters = ore, molten, output
//ore = the itemstack being smelted
//molten = the molten fluidstack
//output = the casted output itemstack
mods.rockhounding_oretiers.Bloomery.add(<minecraft:slime_ball>, <liquid:lava>*500, <minecraft:magma>);
mods.rockhounding_oretiers.Bloomery.add("oreCopper", <liquid:molten_copper>*500, <rockhounding_chemistry:metal_items:11>);

//Parameters = input
//input = the itemstack representing the input to remove
mods.rockhounding_oretiers.Bloomery.remove(<minecraft:iron_ore>);