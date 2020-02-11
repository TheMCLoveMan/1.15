package cofh.lib.util.control;

import cofh.core.network.packet.server.TransferControlPacket;
import cofh.lib.util.Utils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

import static cofh.lib.util.constants.NBTTags.TAG_ENABLE_IN;
import static cofh.lib.util.constants.NBTTags.TAG_ENABLE_OUT;

public class TransferControlModule implements ITransferControllable {

    protected ITransferControllableTile tile;

    protected boolean hasAutoInput;
    protected boolean hasAutoOutput;

    protected boolean enableAutoInput;
    protected boolean enableAutoOutput;

    public TransferControlModule(ITransferControllableTile tile) {

        this(tile, true, true);
    }

    public TransferControlModule(ITransferControllableTile tile, boolean hasAutoInput, boolean hasAutoOutput) {

        this.tile = tile;
        this.hasAutoInput = hasAutoInput;
        this.hasAutoOutput = hasAutoOutput;
    }

    // region NETWORK
    public void readFromBuffer(PacketBuffer buffer) {

        hasAutoInput = buffer.readBoolean();
        hasAutoOutput = buffer.readBoolean();

        enableAutoInput = buffer.readBoolean();
        enableAutoOutput = buffer.readBoolean();
    }

    public void writeToBuffer(PacketBuffer buffer) {

        buffer.writeBoolean(hasAutoInput);
        buffer.writeBoolean(hasAutoOutput);

        buffer.writeBoolean(enableAutoInput);
        buffer.writeBoolean(enableAutoOutput);
    }
    // endregion

    // region NBT
    public TransferControlModule read(CompoundNBT nbt) {

        enableAutoInput = nbt.getBoolean(TAG_ENABLE_IN);
        enableAutoOutput = nbt.getBoolean(TAG_ENABLE_OUT);

        return this;
    }

    public CompoundNBT write(CompoundNBT nbt) {

        nbt.putBoolean(TAG_ENABLE_IN, enableAutoInput);
        nbt.putBoolean(TAG_ENABLE_OUT, enableAutoOutput);

        return nbt;
    }
    // endregion

    // region ITransferControl
    @Override
    public boolean hasTransferIn() {

        return hasAutoInput;
    }

    @Override
    public boolean hasTransferOut() {

        return hasAutoOutput;
    }

    @Override
    public boolean getTransferIn() {

        return hasTransferIn() && enableAutoInput;
    }

    @Override
    public boolean getTransferOut() {

        return hasTransferOut() && enableAutoOutput;
    }

    @Override
    public void setControl(boolean input, boolean output) {

        boolean curInput = this.enableAutoInput;
        boolean curOutput = this.enableAutoOutput;

        if (hasTransferIn()) {
            this.enableAutoInput = input;
        }
        if (hasTransferOut()) {
            this.enableAutoOutput = output;
        }
        if (Utils.isClientWorld(tile.world())) {
            TransferControlPacket.sendToServer(tile);
            this.enableAutoInput = curInput;
            this.enableAutoOutput = curOutput;
        } else {
            tile.onControlUpdate();
        }
    }
    // endregion
}
