package net.r2d2well.mugmakermod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.r2d2well.mugmakermod.Block.ModBlocksInit;
import net.r2d2well.mugmakermod.Block.entity.BlockEntityInit;
import net.r2d2well.mugmakermod.Item.ModItemsInit;
import net.r2d2well.mugmakermod.Screen.MixerScreen;
import net.r2d2well.mugmakermod.Screen.ModMenuTypes;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MugMakerMod.MOD_ID)
public class MugMakerMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "mug_maker";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MugMakerMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItemsInit.register(modEventBus);

        ModBlocksInit.BLOCK.register(modEventBus);

        BlockEntityInit.register(modEventBus);

        CreativeTab.TABS.register(modEventBus);

        ModMenuTypes.MENUS.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            ItemBlockRenderTypes.setRenderLayer(ModBlocksInit.VANILLA_CROP.get(), RenderType.cutout());
            MenuScreens.register(ModMenuTypes.MIXER_MENU.get(), MixerScreen::new);
        }

    }
}
