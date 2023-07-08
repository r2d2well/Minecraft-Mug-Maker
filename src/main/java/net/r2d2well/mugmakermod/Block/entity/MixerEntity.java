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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.r2d2well.mugmakermod.Item.ModItemsInit;
import net.r2d2well.mugmakermod.Screen.MixerMenu;
import org.jetbrains.annotations.Nullable;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class MixerEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemStackHandler = new ItemStackHandler(5){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    public int progress;
    public final int maxProgress = 5;

    private LazyOptional<BasicComboBoxUI.ItemHandler> lazyItemHandler = LazyOptional.empty();

    public MixerEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.MIXER.get(), pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state, MixerEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(level, pos, state);

            if(pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
                pEntity.resetProgress();
            }
        }
        else {
            pEntity.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(MixerEntity entity) {
        if (hasWheatInSlot(entity)){
            craftCarbonatedWater(entity);
        }
    }

    private static boolean hasRecipe(MixerEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
        for (int i = 0; i < entity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }

        Item item = ModItemsInit.CARBONATED_WATER.get();

        return hasWheatInSlot(entity) && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, new ItemStack(item, 1));
    }

    private static boolean hasWheatInSlot(MixerEntity entity){
        for (int x = 0; x < 4; x++){
            if (entity.itemStackHandler.getStackInSlot(x).getItem() == Items.WHEAT){
                return true;
            }
        }
        return false;
    }

    private static void craftCarbonatedWater(MixerEntity entity){
        for (int x = 0; x < 4; x++){
            if (entity.itemStackHandler.getStackInSlot(x).getItem() == Items.WHEAT){
                entity.itemStackHandler.extractItem(x,1, false);
                entity.itemStackHandler.setStackInSlot(4, new ItemStack(ModItemsInit.CARBONATED_WATER.get(),
                        entity.itemStackHandler.getStackInSlot(4).getCount() + 1));
                return;
            }
        }
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(4).getItem() == stack.getItem() || inventory.getItem(4).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(4).getMaxStackSize() > inventory.getItem(4).getCount();
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

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MixerMenu(id, inventory, this);
    }
}
