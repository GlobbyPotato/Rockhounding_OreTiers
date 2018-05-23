//----------------------------------
//----------Drying Pallet-----------
//----------------------------------
//Parameters = input, output, time
//input = the input itemstack
//oredict = is the input extended to its oredict (if unused will be set false by default)
//output = the output itemstack
//time = the refining time to do the task (if unused, the config "Base Drying Time" will ve used)
mods.rockhounding_oretiers.DryingPallet.add(<minecraft:rotten_flesh>, true, <minecraft:leather>, 500);

//Parameters = input
//input = the input itemstack
mods.rockhounding_oretiers.DryingPallet.remove(<rockhounding_oretiers:tiersItems:3>);


//----------------------------------
//-----------Coal Refiner------------
//----------------------------------
//Parameters = input, output, time
//input = the input itemstack
//oredict = is the input extended to its oredict (if unused will be set false by default)
//output = the refined output itemstack 
//time = the refining time to do the task (if unused, the config "Base Refining Time" will ve used)
mods.rockhounding_oretiers.CoalRefiner.add(<rockhounding_oretiers:tiersItems:1>, false, <rockhounding_oretiers:tiersItems:0>, 100);

//Parameters = input
//input = the itemstack representing the input to remove
mods.rockhounding_oretiers.CoalRefiner.remove(<minecraft:coal:0>);


//----------------------------------
//------------Bloomery--------------
//----------------------------------
//Parameters = ore, molten, output
//ore = the itemstack being smelted
//molten = the molten fluidstack
//moltenAmount = the resulting amount of molten
//output = the casted output itemstack
mods.rockhounding_oretiers.Bloomery.add(<minecraft:slime_ball>, <liquid:lava>, 500, <minecraft:magma>);

//Parameters = input
//input = the itemstack representing the input to remove
mods.rockhounding_oretiers.Bloomery.remove(<minecraft:iron_ore>);