package com.kc.asne.asne.util;

import com.kc.asne.asne.Asne;
import com.kc.asne.asne.client.gui.RubyBlockContainerScreen;
import com.kc.asne.asne.init.ContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Asne.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ContainerTypes.RUBY_BLOCK_CONTAINER.get(), RubyBlockContainerScreen::new);
    }
}
