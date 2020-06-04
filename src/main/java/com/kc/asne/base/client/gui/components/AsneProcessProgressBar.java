package com.kc.asne.base.client.gui.components;

import com.kc.asne.asne.Asne;
import com.kc.asne.base.client.gui.AsneContainerScreen;
import com.kc.asne.base.client.gui.GuiUtils;
import com.kc.asne.base.client.gui.IAsneScreenPart;
import com.kc.asne.base.container.slot.IBasicProgressSlot;
import com.kc.asne.base.container.slot.ProcessProgressSlot;
import com.kc.asne.base.general.constants.AsneConstants;
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
        return AsneConstants.RL.SLOT_PARTS_TEXTURE;
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

    @Override
    public void renderToolTip(int mouseX, int mouseY) {
        int x = (this.caller.width - this.caller.getXSize()) / 2;
        int y = (this.caller.height - this.caller.getYSize()) / 2;
        if (GuiUtils.isHoveringOverWH(mouseX, mouseY, x + this.slot.getXPosition(), y + this.slot.getYPosition(), 26, 17))
        this.caller.renderTooltip("Process Progress", mouseX, mouseY);
    }
}
