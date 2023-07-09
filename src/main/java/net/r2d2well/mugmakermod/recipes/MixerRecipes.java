package net.r2d2well.mugmakermod.recipes;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.r2d2well.mugmakermod.Item.ModItemsInit;

import java.util.ArrayList;

public class MixerRecipes {
    private static ArrayList <Item[]> list;

    private static Item [] A_W = {
            ModItemsInit.CARBONATED_WATER.get(),
            ModItemsInit.VANILLA.get(),
            Items.SUGAR,
            Items.HONEY_BOTTLE,
            ModItemsInit.A_W.get()
    };

    private static Item [] BARQ = {
            ModItemsInit.CARBONATED_WATER.get(),
            ModItemsInit.VANILLA.get(),
            Items.SUGAR,
            Items.SWEET_BERRIES,
            ModItemsInit.BARQ.get()
    };

    private static Item [] IBC = {
            ModItemsInit.CARBONATED_WATER.get(),
            ModItemsInit.VANILLA.get(),
            Items.SUGAR,
            Items.FERMENTED_SPIDER_EYE,
            ModItemsInit.IBC.get()
    };

    private static Item [] MUG = {
            ModItemsInit.CARBONATED_WATER.get(),
            ModItemsInit.VANILLA.get(),
            Items.SUGAR,
            Items.GOLDEN_CARROT,
            ModItemsInit.MUG.get()
    };

    private static Item [] CARBONATED_WATER = {
            Items.WHEAT,
            Items.WATER_BUCKET,
            null,
            null,
            ModItemsInit.CARBONATED_WATER.get()
    };

    private static void addRecipes (){
        list = new ArrayList<>();
        list.add(A_W);
        list.add(BARQ);
        list.add(IBC);
        list.add(MUG);
        list.add(CARBONATED_WATER);
    }

    public static ArrayList <Item[]> getRecipes (){
        addRecipes();
        return list;
    }

    public static void addRecipe(Item [] items){
        if (items.length != 5){
            return;
        }
        list.add(items);
    }
}
