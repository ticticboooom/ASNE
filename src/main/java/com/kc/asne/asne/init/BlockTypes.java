package com.kc.asne.asne.init;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.block.ManualPressControllerBlock;
import com.kc.asne.asne.block.RocketeersCraftingTableBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockTypes {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Asne.MOD_ID);

    public static final RegistryObject<Block> ROCKETEERS_CRAFTING_TABLE = BLOCKS.register("rocketeers_crafting_table", RocketeersCraftingTableBlock::new);
    public static final RegistryObject<Block> MANUAL_PRESS_CONTROLLER = BLOCKS.register("manual_press_controller", ManualPressControllerBlock::new);
}
