package com.kc.asne.base.client.gui.components.gauge;

import com.kc.asne.base.client.AsneRenderer;
import com.kc.asne.base.container.slot.FluidSlot;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class FluidGauge extends GuiGauge {
    private final FluidSlot slot;
    private final ContainerScreen<?> screen;

    public FluidGauge(FluidSlot slot, ContainerScreen<?> screen) {
        super(screen, slot.getXPosition(), slot.getYPosition());
        this.slot = slot;
        this.screen = screen;
    }

    @Override
    protected float getPercentageUsed() {
        return this.slot.getNormalisedPercentage();
    }
}
