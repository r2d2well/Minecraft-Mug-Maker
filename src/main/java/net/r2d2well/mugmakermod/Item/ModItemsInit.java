package net.r2d2well.mugmakermod.Item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.r2d2well.mugmakermod.Block.ModBlocksInit;
import net.r2d2well.mugmakermod.MugMakerMod;

import static net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS;
import static net.minecraftforge.registries.ForgeRegistries.POTIONS;
import static net.r2d2well.mugmakermod.CreativeTab.addToTab;

public class ModItemsInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MugMakerMod.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject <Item> A_W = addToTab(ITEMS.register("a_w", () ->
            new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .build()))));

    public static final RegistryObject <Item> MUG = addToTab(ITEMS.register("mug", () ->
            new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .build()))));

    public static final RegistryObject <Item> IBC = addToTab(ITEMS.register("ibc", () ->
            new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .build()))));

    public static final RegistryObject <Item> BARQ = addToTab(ITEMS.register("barq", () ->
            new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .build()))));

    public static final RegistryObject <Item> CARBONATED_WATER = addToTab(ITEMS.register("carbonated_water", () ->
            new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .effect(new MobEffectInstance(MobEffects.CONFUSION), 600)
                            .build()))));

    public static final RegistryObject <BlockItem> MIXER = addToTab(ITEMS.register("mixer", () ->
            new BlockItem(ModBlocksInit.MIXER.get(), new Item.Properties())));

    public static final RegistryObject <Item> VANILLA_SEEDS = addToTab(ITEMS.register("vanilla_seeds", () ->
            new ItemNameBlockItem(ModBlocksInit.VANILLA_CROP.get(),
            new Item.Properties())));

    public static final RegistryObject <Item> VANILLA = addToTab(ITEMS.register("vanilla", () ->
            new Item(new Item.Properties())));
}