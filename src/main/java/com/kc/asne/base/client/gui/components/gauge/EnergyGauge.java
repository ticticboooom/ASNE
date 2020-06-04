package com.kc.asne.base.client.gui.components.gauge;

import com.kc.asne.base.container.slot.EnergySlot;
import com.kc.asne.base.math.Vec2i;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;

public class EnergyGauge extends GuiGauge {
    private final EnergySlot slot;

    public EnergyGauge(EnergySlot slot, ContainerScreen<?> caller) {
        super(caller, slot.getXPosition(), slot.getYPosition());
        this.slot = slot;
    }

    @Override
    protected Vec2i getBackgroundBarStartPos() {
        return new Vec2i(50, 14);
    }

    @Override
    protected Vec2i getForegroundBarStartPos() {
        return new Vec2i(86, 14);
    }

    @Override
    protected float getPercentageUsed() {
        return slot.getNormalisedPercentage();
    }
}
