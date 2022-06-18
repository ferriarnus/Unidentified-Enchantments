package com.ferri.arnus.unidentifiedenchantments.compat.jei;

import java.util.ArrayList;
import java.util.List;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusFactory;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.registration.IAdvancedRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

@JeiPlugin
public class UnidentifiedEnchantmentsPlugin implements IModPlugin{
	public static List<IJeiAnvilRecipe> hiddenlist = new ArrayList<>();
	private List<ItemStack> enchantedBooks = new ArrayList<>();

	public UnidentifiedEnchantmentsPlugin() {
		for (Enchantment enchantment: Registry.ENCHANTMENT) {
			enchantedBooks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, 1)));
		}
	}
	
	@Override
	public ResourceLocation getPluginUid() {
		// TODO Auto-generated method stub
		return new ResourceLocation(UnidentifiedEnchantments.MODID, "jeiplugin");
	}
	
	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

	    IRecipeManager recipeManager = jeiRuntime.getRecipeManager();
	    IJeiHelpers jeiHelpers = jeiRuntime.getJeiHelpers();
	    IFocusFactory focusFactory = jeiHelpers.getFocusFactory();

	    List<IFocus<ItemStack>> enchantedBookFocuses = enchantedBooks.stream()
	        .map(enchantedBook -> focusFactory.createFocus(RecipeIngredientRole.INPUT, VanillaTypes.ITEM_STACK, enchantedBook))
	        .toList();

	    List<IJeiAnvilRecipe> enchantedBookRecipes = recipeManager.createRecipeLookup(RecipeTypes.ANVIL)
	        .limitFocus(enchantedBookFocuses)
	        .get()
	        .toList();
	    
	    hiddenlist = enchantedBookRecipes;

	    recipeManager.hideRecipes(RecipeTypes.ANVIL, enchantedBookRecipes);
	}
	
	@Override
	public void registerAdvanced(IAdvancedRegistration registration) {
		registration.addRecipeManagerPlugin(new UnidentifiedEnchantmentRecipeManagerPlugin());
	}

}
