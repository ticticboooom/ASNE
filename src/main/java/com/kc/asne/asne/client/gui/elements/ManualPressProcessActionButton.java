package com.kc.asne.asne.client.gui.elements;

import com.kc.asne.asne.client.gui.ManualPressContainerScreen;
import com.kc.asne.asne.container.ManualPressContainer;
import com.kc.asne.base.client.gui.components.button.ActionButton;
import com.kc.asne.base.tileentity.AsneMachineTileEntity;
import net.minecraft.client.gui.widget.button.Button;

public class ManualPressProcessActionButton extends ActionButton {
    public ManualPressProcessActionButton(ManualPressContainerScreen screen, int widthIn, int heightIn, int width, int height, IPressable onPress) {
        super(screen, widthIn, heightIn, width, height, onPress);
    }
}
