package com.kc.asne.base.client.gui.components.gauge;

import com.kc.asne.base.client.AsneRenderer;
import net.minecraft.util.ResourceLocation;

public enum GaugeType {
    STANDARD_FLUID(AsneRenderer.rl("textures/gui/elements/fluid_gauge.png"), 5, 10);

    private final ResourceLocation resourceLocation;
    private final int width;
    private final int height;

    GaugeType(ResourceLocation imageFileName, int width, int height) {

        this.resourceLocation = imageFileName;
        this.width = width;
        this.height = height;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
