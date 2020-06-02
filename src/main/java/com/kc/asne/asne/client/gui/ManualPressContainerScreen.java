package com.kc.asne.asne.client.gui;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.client.gui.elements.ManualPressProcessActionButton;
import com.kc.asne.asne.container.ManualPressContainer;
import com.kc.asne.asne.net.PacketHandler;
import com.kc.asne.asne.net.packets.ManualPressProcessPacket;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.client.gui.AsneContainerScreen;
import com.kc.asne.base.client.gui.components.button.ActionButton;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.MinecraftServerGui;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.loading.FMLEnvironment;

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
        return new ResourceLocation(Asne.MOD_ID, "textures/gui/manual_press.png");
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
