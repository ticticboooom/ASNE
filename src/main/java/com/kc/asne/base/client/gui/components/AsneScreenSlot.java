package com.kc.asne.base.client.gui.components;

import com.kc.asne.asne.Asne;
import com.kc.asne.base.client.gui.IAsneScreenPart;
import com.kc.asne.base.math.Vec2i;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class AsneScreenSlot implements IAsneScreenPart {
    private Slot inventorySlot;
    private ContainerScreen caller;

    public AsneScreenSlot(Slot inventorySlot, ContainerScreen caller) {

        this.inventorySlot = inventorySlot;
        this.caller = caller;
    }

    protected Vec2i getEdgeOffset(){
        return new Vec2i(1,1);
    }
    protected Vec2i getTextureEndCorner(){ return new Vec2i(18, 18);}
    protected Vec2i getTextureStartCorner(){ return new Vec2i(0, 26);}

    protected ResourceLocation getSlotTexture() {
        return new ResourceLocation(Asne.MOD_ID, "textures/gui/slot_parts.png");
    }

    @Override
    public void renderBackground(float partialTicks, int mouseX, int mouseY) {
        this.caller.getMinecraft().getTextureManager().bindTexture(getSlotTexture());
        int x = (this.caller.width - this.caller.getXSize()) / 2;
        int y = (this.caller.height - this.caller.getYSize()) / 2;
        Vec2i edgeOffset = getEdgeOffset();
        this.caller.blit(x + this.inventorySlot.xPos - edgeOffset.getX(), (y + this.inventorySlot.yPos) - edgeOffset.getY(), getTextureStartCorner().getX(),getTextureStartCorner().getY(), getTextureEndCorner().getX(), getTextureEndCorner().getY());
    }
}
