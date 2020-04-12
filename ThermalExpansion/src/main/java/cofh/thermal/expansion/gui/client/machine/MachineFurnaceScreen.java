package cofh.thermal.expansion.gui.client.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.machine.MachineFurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineFurnaceScreen extends MachineScreenBase<MachineFurnaceContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/furnace.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineFurnaceScreen(MachineFurnaceContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_furnace"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_furnace");
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 53, 26, tile));

        addElement(createLargeOutputSlot(this, 116, 35, tile));

        progress = addElement(createDefaultProgress(this, 79, 34, PROG_ARROW_RIGHT));
        speed = addElement(createDefaultSpeed(this, 53, 44, SCALE_FLAME));
    }

}
