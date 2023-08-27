package com.ferri.arnus.unidentifiedenchantments.compat.jei;


import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.advanced.IRecipeManagerPlugin;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.library.plugins.vanilla.anvil.AnvilRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class UnidentifiedEnchantmentRecipeManagerPlugin implements IRecipeManagerPlugin {

	public UnidentifiedEnchantmentRecipeManagerPlugin() {

	}

	@Override
	public <V> List<RecipeType<?>> getRecipeTypes(IFocus<V> focus) {
		if (focus.getRole() == RecipeIngredientRole.INPUT) {
            Optional<ItemStack> itemStack = focus.getTypedValue().getIngredient(VanillaTypes.ITEM_STACK);
            if (itemStack.isPresent()) {
            	AtomicBoolean b = new AtomicBoolean();
            	itemStack.get().getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap -> {
            		b.set(cap.getHiddenMap().isEmpty());
            	});
            	if (!b.get()) {
            		 return List.of(RecipeTypes.ANVIL);
            	}
            }
        }
        return List.of();
	}

	@NotNull
	@Override
	public <T, V> List<T> getRecipes(IRecipeCategory<T> recipeCategory, @NotNull IFocus<V> focus) {
		if (!recipeCategory.getRecipeType().equals(RecipeTypes.ANVIL)) {
			return List.of();
		}
		if (focus.getRole() == RecipeIngredientRole.INPUT) {
			Optional<ItemStack> itemStack = focus.getTypedValue().getIngredient(VanillaTypes.ITEM_STACK);
			if (itemStack.isPresent()) {
				AtomicBoolean b = new AtomicBoolean();
				itemStack.get().getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap -> {
					b.set(cap.getHiddenMap().isEmpty());
				});
				if (b.get()) {
					List<T> list = new ArrayList<>();
					for (IJeiAnvilRecipe recipe: UnidentifiedEnchantmentsPlugin.hiddenlist) {
						ItemStack stack = recipe.getRightInputs().get(0);
							Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
							Map<Enchantment, Integer> enchantments2 = EnchantmentHelper.getEnchantments(itemStack.get());
							if (enchantments.size() == 1 && enchantments2.size() == 1) {
								if (enchantments.containsKey(enchantments2.keySet().toArray()[0])) {
									list.add((T) new AnvilRecipe(recipe.getLeftInputs(), recipe.getRightInputs(), recipe.getOutputs()));
								}
							}

					}
					return list;
				}
			}
		}
		return List.of();
	}

	@NotNull
	@Override
	public <T> List<T> getRecipes(IRecipeCategory<T> recipeCategory) {
		return List.of();
	}

}
