package com.kc.asne.asne.client.gui;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.container.ManualPressContainer;
import com.kc.asne.base.client.gui.AsneContainerScreen;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ManualPressContainerScreen extends AsneContainerScreen<ManualPressContainer> {
    public ManualPressContainerScreen(ManualPressContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        this.widgets.forEach(z -> z.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_));
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }
}
