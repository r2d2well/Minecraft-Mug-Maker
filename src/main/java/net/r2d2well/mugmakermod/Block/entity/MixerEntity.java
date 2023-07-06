package net.r2d2well.mugmakermod.Block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.r2d2well.mugmakermod.Block.Mixer;
import net.r2d2well.mugmakermod.Item.ModItemsInit;
import net.r2d2well.mugmakermod.Screen.MixerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class MixerEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    /*
    protected final ContainerData data;
    private int progress;
    private int MaxProgress = 100;
    */
    private LazyOptional<BasicComboBoxUI.ItemHandler> lazyItemHandler = LazyOptional.empty();

    public MixerEntity(BlockPos pos, BlockState state){
        super (BlockEntityInit.MIXER.get(), pos, state);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MixerMenu(id, inventory, this);
    }

    public void tick(MixerEntity pEntity){

        /*
        progress++;
        if (progress >= MaxProgress){
            progress = 0;
            Pig pig = new Pig(EntityType.PIG, this.level);
            pig.setPos(this.worldPosition.getX(), this.worldPosition.getY() + 1, this.worldPosition.getZ());
            this.level.addFreshEntity(pig);
        }
        */
        if (hasRecipe(pEntity)){
            craftItem(pEntity);
        }
    }

    private void craftItem(MixerEntity entity){
        if (hasRecipe(entity)){
            entity.itemStackHandler.extractItem(1,1,false);
            entity.itemStackHandler.setStackInSlot(2, new ItemStack(ModItemsInit.MUG.get(),
                    entity.itemStackHandler.getStackInSlot(2).getCount() + 1));
        }
    }

    private boolean hasRecipe(MixerEntity entity){
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
        for (int i = 0; i < entity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }

        boolean hasRawGemInFirstSlot = entity.itemStackHandler.getStackInSlot(1).getItem() == Items.COAL;

        return hasRawGemInFirstSlot && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, new ItemStack(Items.COAL, 1));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Mixer Station");
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    /*
    public void onLoad(){
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }
    */

    protected void saveAdditional(CompoundTag nbt){
        nbt.put("inventory", itemStackHandler.serializeNBT());
    }

    public void load(CompoundTag nbt){
        super.load(nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    @Nullable
    public void invalidateCaps(){
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int x = 0; x < itemStackHandler.getSlots(); x++){
            inventory.setItem(x, itemStackHandler.getStackInSlot(x));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
}
