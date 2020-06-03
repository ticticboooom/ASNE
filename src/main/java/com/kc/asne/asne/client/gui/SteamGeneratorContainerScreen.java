package com.kc.asne.asne.client.gui;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.container.SteamGeneratorContainer;
import com.kc.asne.base.client.gui.AsneContainerScreen;
import com.kc.asne.base.container.slot.FluidSlot;
import com.kc.asne.base.container.slot.IBasicProgressSlot;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SteamGeneratorContainerScreen extends AsneContainerScreen<SteamGeneratorContainer> {
    public SteamGeneratorContainerScreen(SteamGeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 165;

    }

    @Override
    protected ResourceLocation getBackgroundTexture() {
        return AsneConstants.RL.GENERIC_MACHINE_BACKGROUND_GUI_TEXTURE;
    }
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, 73.f, 4210752);
        String text = "";
        for (IBasicProgressSlot x : container.progressSlots) {
            if (x instanceof FluidSlot) {
                text = Float.toString(x.getNormalisedPercentage());
            }
        }
        this.font.drawString(text, 1.f, 1.f, 4210752);
    }
}
