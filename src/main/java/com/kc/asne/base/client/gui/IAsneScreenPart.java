package com.kc.asne.base.client.gui;

public interface IAsneScreenPart {
    void renderBackground(float partialTicks, int mouseX, int mouseY);
    void renderToolTip(int mouseX, int mouseY);
}
