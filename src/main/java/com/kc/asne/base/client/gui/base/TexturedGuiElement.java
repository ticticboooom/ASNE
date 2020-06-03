package com.kc.asne.base.client.gui.base;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.util.ResourceLocation;

public class TexturedGuiElement extends GuiElement{
    protected final ResourceLocation resource;
    protected final int relativeX;
    protected final int relativeY;
    public TexturedGuiElement(ContainerScreen<?> screen, int x, int y, int width, int height, String text, ResourceLocation resource) {
        super(screen, x, y, width, height, text);
        this.resource = resource;
        relativeX = x;
        relativeY = y;
    }

    protected ResourceLocation getResource() {
        return resource;
    }
}
