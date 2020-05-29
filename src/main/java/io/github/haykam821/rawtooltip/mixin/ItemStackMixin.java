package io.github.haykam821.rawtooltip.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@ModifyVariable(method = "getTooltip", name = "text", at = @At(value = "STORE", ordinal = 0))
	public Text addRawId(Text itemName) {
		Item item = ((ItemStack) (Object) this).getItem();
		int rawId = Item.getRawId(item);

		return itemName.append(" ").append(new TranslatableText("gui.item_tooltip.raw_id", rawId));
	}
}