package com.kc.asne.base.client;

import com.kc.asne.base.general.constants.AsneConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class AsneRenderer {


    public static TextureAtlasSprite getSprite(ResourceLocation spriteLocation) {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(spriteLocation);
    }
    public static ResourceLocation getFluidTexture(@Nonnull FluidStack fluidStack, @Nonnull boolean still) {
        Fluid fluid = fluidStack.getFluid();
        ResourceLocation spriteLocation;
        if (still) {
            spriteLocation = fluid.getAttributes().getStillTexture(fluidStack);
        } else {
            spriteLocation = fluid.getAttributes().getFlowingTexture(fluidStack);
        }
        return spriteLocation;
    }

    public static ResourceLocation rl(String path){
        return new ResourceLocation(AsneConstants.MOD_ID, path);
    }

    public static void resetColor() {
        RenderSystem.color4f(1, 1, 1, 1);
    }
}
