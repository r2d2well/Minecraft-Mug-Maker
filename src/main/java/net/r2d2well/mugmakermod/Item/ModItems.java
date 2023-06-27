package net.r2d2well.mugmakermod.Item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.r2d2well.mugmakermod.Block.ModBlocks;
import net.r2d2well.mugmakermod.MugMakerMod;

import static net.r2d2well.mugmakermod.CreativeTab.addToTab;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MugMakerMod.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject <Item> A_W = addToTab(ITEMS.register("a_w", () ->
            new Item(new Item.Properties()
                    .stacksTo(16).
                    food(new FoodProperties.Builder()
                            .nutrition(8)
                            .build()))));

    public static final RegistryObject <Item> MUG = addToTab(ITEMS.register("mug", () ->
            new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .build()))));

    public static final RegistryObject <Item> IBC = addToTab(ITEMS.register("ibc", () ->
            new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .build()))));

    public static final RegistryObject <Item> BARQ = addToTab(ITEMS.register("barq", () ->
            new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .build()))));

    public static final RegistryObject<BlockItem> MIXER = addToTab(ITEMS.register("mixer", () ->
            new BlockItem(ModBlocks.MIXER.get(), new Item.Properties())));
}