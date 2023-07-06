package net.r2d2well.mugmakermod.Block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.r2d2well.mugmakermod.Block.ModBlocksInit;
import net.r2d2well.mugmakermod.MugMakerMod;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MugMakerMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<MixerEntity>> MIXER = BLOCK_ENTITY.register("mixer", () ->
            BlockEntityType.Builder.of(MixerEntity::new, ModBlocksInit.MIXER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITY.register(eventBus);
    }
}
