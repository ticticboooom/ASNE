package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.block.MachineStructureBlock;
import com.kc.asne.asne.block.ManualPressControllerBlock;
import com.kc.asne.asne.block.RocketeersCraftingTableBlock;
import com.kc.asne.asne.block.SteamGeneratorControllerBlock;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockTypes {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, AsneConstants.MOD_ID);

    public static final RegistryObject<Block> ROCKETEERS_CRAFTING_TABLE = BLOCKS.register("rocketeers_crafting_table", RocketeersCraftingTableBlock::new);
    public static final RegistryObject<Block> MANUAL_PRESS_CONTROLLER = BLOCKS.register("manual_press_controller", ManualPressControllerBlock::new);
    public static final RegistryObject<Block> MACHINE_STRUCTURE = BLOCKS.register("machine_structure", MachineStructureBlock::new);
    public static final RegistryObject<Block> STEAM_GENERATOR_CONTROLLER = BLOCKS.register("steam_generator_controller", SteamGeneratorControllerBlock::new);
}
