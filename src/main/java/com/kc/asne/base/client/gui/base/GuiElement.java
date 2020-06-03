package com.kc.asne.base.client.gui.base;

import com.kc.asne.base.client.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

public abstract class GuiElement extends Widget {
    public static final Minecraft minecraft = Minecraft.getInstance();
    public GuiElement(ContainerScreen<?> screen, int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    public void renderExtendedTexture(ResourceLocation resourceLocation, int sideWidth, int sideHeight) {
        GuiUtils.renderExtendedTexture(resourceLocation, sideWidth, sideHeight, x, y, width, height);
    }

    protected void drawTiledSprite(int xPosition, int yPosition, int yOffset, int desiredWidth, int desiredHeight, TextureAtlasSprite sprite) {
        GuiUtils.drawTiledSprite(xPosition, yPosition, yOffset, desiredWidth, desiredHeight, sprite, 16, 16, getBlitOffset());
    }
}
