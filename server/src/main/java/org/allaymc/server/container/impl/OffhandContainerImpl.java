package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.OffhandContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class OffhandContainerImpl extends AbstractPlayerContainer implements OffhandContainer {

    public OffhandContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerTypes.OFFHAND, playerSupplier);
        addSlotChangeListener(0, this::onOffhandChange);
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.OFFHAND;
    }

    protected void onOffhandChange(ItemStack newItemStack) {
        var player = playerSupplier.get();
        player.forEachViewers(viewer -> viewer.viewEntityOffhand(player));
    }

    @Override
    public void loadNBT(List<NbtMap> nbtList) {
        loadNBTPositional(nbtList);
        onOffhandChange(null);
    }

    @Override
    public List<NbtMap> saveNBT(boolean saveEmptySlots) {
        return saveNBTPositional();
    }
}
