package com.mdcore.MysticalDiscoveries.tileentity.guis;

import com.mdcore.MysticalDiscoveries.tileentity.TileEntityCrafterEvaporating;
import com.mdcore.MysticalDiscoveries.tileentity.containers.ContainerCrafterEvaporating;
import com.mdcore.MysticalDiscoveries.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCrafterEvaporating extends GuiContainer{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/gui/crafter_evaporating_gui.png");
	private final InventoryPlayer player;
	private final TileEntityCrafterEvaporating tileentity;
	
	public GuiCrafterEvaporating(InventoryPlayer player, TileEntityCrafterEvaporating tileentity) {
		super(new ContainerCrafterEvaporating(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if (TileEntityCrafterEvaporating.isCrafting(tileentity)) {
			int k = this.getEvaporationLeftScaled(14);
			this.drawTexturedModalRect(this.guiLeft + 12, this.guiTop + 26 + 13 - k, 178, 19 - k, 15, k + 1);
		}
		
		int l = this.getCraftingProgressScaled(22);
		this.drawTexturedModalRect(this.guiLeft + 105, this.guiTop + 35, 177, 0, l, 16);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tilename = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tilename, (this.xSize / 2 - this.fontRenderer.getStringWidth(tilename) / 2), 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8 + this.fontRenderer.getStringWidth(tilename) / 2, this.ySize - 96 + 2, 4210752);
	}
	
	private int getEvaporationLeftScaled(int pixels) {
		int i = this.tileentity.getField(1);
		if (i == 0) i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}
	
	private int getCraftingProgressScaled(int pixels) {
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

}
