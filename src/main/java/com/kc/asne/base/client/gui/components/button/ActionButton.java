package com.kc.asne.base.client.gui.components.button;


import com.kc.asne.asne.Asne;
import com.kc.asne.asne.client.gui.ManualPressContainerScreen;
import com.kc.asne.asne.tileentity.ManualPressTileEntity;
import com.kc.asne.base.client.gui.AsneContainerScreen;
import com.kc.asne.base.container.AsneMachineContainer;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.java.games.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class ActionButton extends Button {


    public ActionButton(AsneContainerScreen<?> screen, int widthIn, int heightIn, int width, int height, Button.IPressable onPress) {
        super(((Minecraft.getInstance().getMainWindow().getScaledWidth() - screen.getXSize()) / 2) + widthIn, ((Minecraft.getInstance().getMainWindow().getScaledHeight() - screen.getYSize()) / 2)+ heightIn, width, height, "", onPress);
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float partialTicks) {
        super.renderButton(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }
}
