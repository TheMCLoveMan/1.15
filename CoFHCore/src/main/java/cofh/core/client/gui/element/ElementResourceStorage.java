package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import cofh.core.util.IResourceStorage;
import cofh.core.util.helpers.MathHelper;
import cofh.core.util.helpers.RenderHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;
import java.util.function.BooleanSupplier;

import static cofh.core.util.constants.Constants.FALSE;
import static cofh.core.util.constants.Constants.TRUE;
import static cofh.core.util.helpers.StringHelper.format;
import static net.minecraft.client.gui.screen.Screen.hasAltDown;
import static net.minecraft.client.gui.screen.Screen.hasShiftDown;

public abstract class ElementResourceStorage extends ElementBase {

    protected ResourceLocation underlayTexture;
    protected ResourceLocation overlayTexture;

    protected IResourceStorage storage;
    protected boolean infinite;
    protected int minDisplay = 1;

    protected BooleanSupplier drawUnderlay = TRUE;
    protected BooleanSupplier drawOverlay = TRUE;

    protected BooleanSupplier clearStorage = FALSE;

    public ElementResourceStorage(IGuiAccess gui, int posX, int posY, IResourceStorage storage) {

        super(gui, posX, posY);
        this.storage = storage;
    }

    public final ElementResourceStorage setUnderlayTexture(String texture) {

        return setUnderlayTexture(texture, TRUE);
    }

    public final ElementResourceStorage setUnderlayTexture(String texture, BooleanSupplier draw) {

        this.underlayTexture = new ResourceLocation(texture);
        this.drawUnderlay = draw;
        return this;
    }

    public final ElementResourceStorage setOverlayTexture(String texture) {

        return setOverlayTexture(texture, TRUE);
    }

    public final ElementResourceStorage setOverlayTexture(String texture, BooleanSupplier draw) {

        this.overlayTexture = new ResourceLocation(texture);
        this.drawOverlay = draw;
        return this;
    }

    public final ElementResourceStorage setClearStorage(BooleanSupplier clearStorage) {

        this.clearStorage = clearStorage;
        return this;
    }

    public ElementResourceStorage setInfinite(boolean infinite) {

        this.infinite = infinite;
        return this;
    }

    public ElementResourceStorage setMinDisplay(int minDisplay) {

        this.minDisplay = minDisplay;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        drawStorage();
        drawUnderlayTexture();
        drawResource();
        drawOverlayTexture();
    }

    @Override
    public void addTooltip(List<ITextComponent> tooltipList, int mouseX, int mouseY) {

        if (infinite) {
            tooltipList.add(new TranslationTextComponent("info.cofh.infinite"));
        } else {
            tooltipList.add(new StringTextComponent(format(storage.getStored()) + " / " + format(storage.getCapacity()) + " " + storage.getUnit()));
        }
        if (hasAltDown() || hasShiftDown()) {
            tooltipList.add(new TranslationTextComponent("info.cofh.clear_storage").applyTextStyle(TextFormatting.GRAY));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        if (hasShiftDown() && Screen.hasAltDown()) {
            return clearStorage.getAsBoolean();
        }
        return false;
    }

    protected int getScaled(int scale) {

        if (storage.getCapacity() <= 0 || infinite) {
            return scale;
        }
        double fraction = (double) storage.getStored() * scale / storage.getCapacity();
        int amount = MathHelper.clamp(MathHelper.round(fraction), 0, scale);
        return fraction > 0 ? Math.max(minDisplay, amount) : amount;
    }

    protected void drawStorage() {

        RenderHelper.bindTexture(texture);
        drawTexturedModalRect(posX(), posY(), 0, 0, width, height);
    }

    protected void drawUnderlayTexture() {

        if (drawUnderlay.getAsBoolean() && underlayTexture != null) {
            RenderHelper.bindTexture(underlayTexture);
            drawTexturedModalRect(posX(), posY(), 0, 0, width, height);
        }
    }

    protected abstract void drawResource();

    protected void drawOverlayTexture() {

        if (drawOverlay.getAsBoolean() && overlayTexture != null) {
            RenderHelper.bindTexture(overlayTexture);
            drawTexturedModalRect(posX(), posY(), 0, 0, width, height);
        }
    }

}
