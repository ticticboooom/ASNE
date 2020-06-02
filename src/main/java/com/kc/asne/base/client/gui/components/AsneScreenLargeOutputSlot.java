package com.kc.asne.base.client.gui.components;

import com.kc.asne.asne.Asne;
import com.kc.asne.base.math.Vec2i;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;

public class AsneScreenLargeOutputSlot extends AsneScreenSlot {
    public AsneScreenLargeOutputSlot(Slot inventorySlot, ContainerScreen caller) {
        super(inventorySlot, caller);
    }

    @Override
    protected ResourceLocation getSlotTexture() {
        return new ResourceLocation(Asne.MOD_ID, "textures/gui/slot_parts.png");
    }

    @Override
    protected Vec2i getTextureEndCorner() {
        return new Vec2i(26, 26);
    }

    @Override
    protected Vec2i getEdgeOffset() {
        return new Vec2i(5, 5);
    }

    @Override
    protected Vec2i getTextureStartCorner() {
        return new Vec2i(0,0);
    }
}
