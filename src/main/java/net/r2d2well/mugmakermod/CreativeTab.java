package net.r2d2well.mugmakermod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.r2d2well.mugmakermod.Item.ModItemsInit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MugMakerMod.MOD_ID);

    public static final List<Supplier<? extends ItemLike>> EXAMPLE_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> MUG_MAKER = TABS.register("mug_maker_tab",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.mug_maker_tab"))
                    .icon(ModItemsInit.MUG.get()::getDefaultInstance)
                    .displayItems((displayParms, output) -> {
                        EXAMPLE_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));
                    })
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike){
        EXAMPLE_TAB_ITEMS.add(itemLike);
        return itemLike;
    }

    public static <T extends Block> RegistryObject<T> addToTabBlock(RegistryObject<T> itemLike){
        EXAMPLE_TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}