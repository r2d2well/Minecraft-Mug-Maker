package net.r2d2well.mugmakermod.Block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.r2d2well.mugmakermod.MugMakerMod;

import static net.r2d2well.mugmakermod.CreativeTab.addToTabBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, MugMakerMod.MOD_ID);

    public static void register(IEventBus eventBus){ BLOCK.register(eventBus); }

    public static final RegistryObject <Block> MIXER = addToTabBlock(BLOCK.register("mixer",
            () -> new Block(BlockBehaviour.Properties.of().instabreak()
            )));

}
