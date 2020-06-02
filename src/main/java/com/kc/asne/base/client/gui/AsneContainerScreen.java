package com.kc.asne.base.client.gui;

import com.kc.asne.asne.Asne;
import com.kc.asne.base.client.gui.components.AsneFuelProgressBar;
import com.kc.asne.base.client.gui.components.AsneProcessProgressBar;
import com.kc.asne.base.client.gui.components.AsneScreenLargeOutputSlot;
import com.kc.asne.base.client.gui.components.AsneScreenSlot;
import com.kc.asne.base.container.AsneMachineContainer;
import com.kc.asne.base.container.slot.*;
import com.kc.asne.base.math.Vec2i;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;

public class AsneContainerScreen<T extends AsneMachineContainer> extends ContainerScreen<T> {
    private final T screenContainer;
    private final PlayerInventory inv;
    protected ArrayList<IAsneScreenPart> renderSlots = new ArrayList<>();
    public ArrayList<Button> widgets = new ArrayList<>();

    public AsneContainerScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.screenContainer = screenContainer;
        this.inv = inv;


        for (int i = 0; i < this.screenContainer.inventorySlots.size(); i++) {
            Slot slot = this.screenContainer.inventorySlots.get(i);
            if (slot instanceof LargeOutputSlot) {
                renderSlots.add(new AsneScreenLargeOutputSlot(slot, this));
            } else if (slot instanceof BasicSlot) {
                renderSlots.add(new AsneScreenSlot(slot, this));
            }
        }
        for (int i = 0; i < this.screenContainer.progressSlots.size(); i++) {
            IBasicProgressSlot slot = (IBasicProgressSlot) this.screenContainer.progressSlots.get(i);
            if (slot instanceof ProcessProgressSlot<?>) {
                renderSlots.add(new AsneProcessProgressBar(slot, this));
            } else if (slot instanceof FuelProgressSlot) {
                renderSlots.add(new AsneFuelProgressBar(slot, this));
            }
        }
    }

    protected Vec2i getGuiBottomRightCorner() {
        return new Vec2i(175, 221);
    }

    protected ResourceLocation getBackgroundTexture() {
        return new ResourceLocation(Asne.MOD_ID, "textures/gui/generic_54.png");
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.f, 1.f, 1.f, 1.f);
        this.minecraft.getTextureManager().bindTexture(getBackgroundTexture());
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;
        Vec2i corner = getGuiBottomRightCorner();
        this.blit(x, y, 0, 0, corner.getX(), corner.getY());
        for (int i = 0; i < this.renderSlots.size(); i++) {
            this.renderSlots.get(i).renderBackground(partialTicks, mouseX, mouseY);
        }
        for (int i = 0; i < widgets.size(); i++) {
            widgets.get(i).renderButton(mouseX, mouseY, partialTicks);
        }
    }
}
