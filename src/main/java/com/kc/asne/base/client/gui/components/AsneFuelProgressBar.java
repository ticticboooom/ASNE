package com.kc.asne.base.client.gui.components;

import com.kc.asne.asne.Asne;
import com.kc.asne.base.client.gui.AsneContainerScreen;
import com.kc.asne.base.client.gui.IAsneScreenPart;
import com.kc.asne.base.container.slot.IBasicProgressSlot;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.util.ResourceLocation;

public class AsneFuelProgressBar implements IAsneScreenPart {
    private final IBasicProgressSlot slot;
    private final AsneContainerScreen caller;

    public AsneFuelProgressBar(IBasicProgressSlot slot, AsneContainerScreen caller) {

        this.slot = slot;
        this.caller = caller;
    }

    protected ResourceLocation getBarTexture() {
        return AsneConstants.RL.SLOT_PARTS_TEXTURE;
    }

    protected int getProgressHeight() {
        return (int)((1-slot.getNormalisedPercentage()) * 13);
    }

    @Override
    public void renderBackground(float partialTicks, int mouseX, int mouseY) {
        this.caller.getMinecraft().getTextureManager().bindTexture(getBarTexture());
        int x = (this.caller.width - this.caller.getXSize()) / 2;
        int y = (this.caller.height - this.caller.getYSize()) / 2;
        this.caller.blit(x + this.slot.getXPosition(), y + this.slot.getYPosition(), 50,0, 14, 13);
        this.caller.blit(x + this.slot.getXPosition(), y + this.slot.getYPosition(), 65, 0, 14, getProgressHeight());

    }
}
