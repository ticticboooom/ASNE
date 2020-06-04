package com.kc.asne.base.general.constants;

import com.kc.asne.base.client.AsneRenderer;
import net.minecraft.util.ResourceLocation;

public class AsneResourceLocationConstants {
    public final ResourceLocation SLOT_PARTS_TEXTURE =  AsneRenderer.rl("textures/gui/slot_parts.png");
    public final ResourceLocation GENERIC_LARGE_CHEST_GUI_TEXTURE =  AsneRenderer.rl("textures/gui/generic_54.png");
    public final ResourceLocation GENERIC_MACHINE_BACKGROUND_GUI_TEXTURE =  AsneRenderer.rl("textures/gui/generic_machine_background.png");
    public final ResourceLocation ROCKETEERS_CRAFTING_TABLE_BACKGROUND_GUI_TEXTURE =  AsneRenderer.rl("textures/gui/rocketeers_crafting_table.png");

    public final ResourceLocation MACHINE_STRUCTURE_TAG = new ResourceLocation(AsneConstants.MOD_ID, "machine/structure");
}
