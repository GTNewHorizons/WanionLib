package wanion.lib.recipe.advanced;

/*
 * Created by WanionCane(https://github.com/WanionCane). This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 */

import javax.annotation.Nonnull;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import wanion.lib.common.IRemovable;

public interface IAdvancedRecipe extends IRemovable {

    short getRecipeKey();

    short getRecipeSize();

    boolean recipeMatch(@Nonnull final InventoryCrafting inventoryCrafting, final int offSetX, final int offSetY);

    @Nonnull
    ItemStack getOutput();
}
