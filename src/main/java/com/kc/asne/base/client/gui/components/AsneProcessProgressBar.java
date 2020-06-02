package com.kc.asne.base.client.gui.components;

import com.kc.asne.asne.Asne;
import com.kc.asne.base.client.gui.AsneContainerScreen;
import com.kc.asne.base.client.gui.IAsneScreenPart;
import com.kc.asne.base.container.slot.IBasicProgressSlot;
import com.kc.asne.base.container.slot.ProcessProgressSlot;
import jdk.internal.loader.Resource;
import net.minecraft.util.ResourceLocation;

public class AsneProcessProgressBar implements IAsneScreenPart {
    private final IBasicProgressSlot slot;
    private final AsneContainerScreen caller;

    public AsneProcessProgressBar(IBasicProgressSlot slot, AsneContainerScreen caller){
        this.slot = slot;
        this.caller = caller;
    }

    protected ResourceLocation getBarTexture() {
        return new ResourceLocation(Asne.MOD_ID, "textures/gui/slot_parts.png");
    }

    protected int getProgressWidth() {
        return (int)(slot.getNormalisedPercentage() * 24);
    }

    @Override
    public void renderBackground(float partialTicks, int mouseX, int mouseY) {
        this.caller.getMinecraft().getTextureManager().bindTexture(getBarTexture());
        int x = (this.caller.width - this.caller.getXSize()) / 2;
        int y = (this.caller.height - this.caller.getYSize()) / 2;
        this.caller.blit(x + this.slot.getXPosition(), y + this.slot.getYPosition(), 26,17, 24, 17);
        this.caller.blit(x + this.slot.getXPosition(), y + this.slot.getYPosition(), 26, 0, getProgressWidth(), 17);
    }
}
