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

public class ModBlocksInit {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, MugMakerMod.MOD_ID);

    public static final RegistryObject <Mixer> MIXER = addToTabBlock(BLOCK.register("mixer",
            () -> new Mixer(BlockBehaviour.Properties.of().ignitedByLava()
            )));

    public static final RegistryObject <VanillaCropBlock> VANILLA_CROP = BLOCK.register("vanilla_crop",
            () -> new VanillaCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
}
