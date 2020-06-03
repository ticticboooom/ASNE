package com.kc.asne.asne.util;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.client.gui.ManualPressContainerScreen;
import com.kc.asne.asne.client.gui.RocketeersCraftingTableContainerScreen;
import com.kc.asne.asne.client.gui.SteamGeneratorContainerScreen;
import com.kc.asne.asne.init.ContainerTypes;
import com.kc.asne.base.general.constants.AsneConstants;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AsneConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ContainerTypes.ROCKETEERS_CRAFTING_TABLE.get(), RocketeersCraftingTableContainerScreen::new);
        ScreenManager.registerFactory(ContainerTypes.MANUAL_PRESS.get(), ManualPressContainerScreen::new);
        ScreenManager.registerFactory(ContainerTypes.STEAM_GENERATOR.get(), SteamGeneratorContainerScreen::new);
    }
}
