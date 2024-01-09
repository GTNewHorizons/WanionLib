package wanion.lib.recipe;

/*
 * Created by WanionCane(https://github.com/WanionCane). This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 */

import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

import gnu.trove.map.TObjectCharMap;
import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TObjectCharHashMap;
import wanion.lib.common.MetaItem;

public final class RecipeHelper {

    private RecipeHelper() {}

    @Nonnull
    public static Object[] rawShapeToShape(@Nonnull final Object[] objects) {
        return rawShapeToShape(objects, 3);
    }

    @Nonnull
    public static Object[] rawShapeToShape(@Nonnull final Object[] objects, final int squareRoot) {
        int f = 1;
        final char[][] almostTheShape = new char[squareRoot][squareRoot];
        final TObjectCharMap<Object> thingToCharMap = new TObjectCharHashMap<>();
        final Map<Integer, ItemStack> keyStackMap = new THashMap<>();
        boolean done = false;
        for (int x = 0; x < squareRoot && !done; x++) {
            for (int y = 0; y < squareRoot && !done; y++) {
                final int index = y * squareRoot + x;
                if ((done = !(index < objects.length)) || objects[index] == null) continue;
                final Object key = objects[index] instanceof ItemStack ? MetaItem.get((ItemStack) objects[index])
                        : objects[index];
                if (key instanceof Integer) keyStackMap.put((Integer) key, (ItemStack) objects[index]);
                if (thingToCharMap.containsKey(key)) almostTheShape[y][x] = thingToCharMap.get(key);
                else thingToCharMap.put(key, almostTheShape[y][x] = (char) f++);
            }
        }
        final Object[] shape = new Object[squareRoot + thingToCharMap.size() * 2];
        for (int i = 0; i < squareRoot; i++) shape[i] = new String(almostTheShape[i]);
        int i = 0;
        for (final Object object : thingToCharMap.keySet()) {
            shape[squareRoot + (2 * i)] = thingToCharMap.get(object);
            shape[squareRoot + 1 + (2 * i++)] = (object instanceof Integer) ? keyStackMap.get(object) : object;
        }
        return shape;
    }

    @Nonnull
    public static Object[] rawShapeToShape(@Nonnull final Object[][] objects, final int squareRoot) {
        int f = 1;
        final char[][] almostTheShape = new char[objects.length][squareRoot];
        final TObjectCharMap<Object> thingToCharMap = new TObjectCharHashMap<>();
        final Map<Integer, ItemStack> keyStackMap = new THashMap<>();
        for (int y = 0; y < objects.length; y++) {
            final Object[] inputArray = objects[y];
            if (inputArray == null) continue;
            for (int x = 0; x < inputArray.length; x++) {
                final Object key = inputArray[x] instanceof ItemStack ? MetaItem.get((ItemStack) inputArray[x])
                        : inputArray[x];
                if (key == null) continue;
                else if (key instanceof Integer && !keyStackMap.containsKey(key))
                    keyStackMap.put((Integer) key, (ItemStack) inputArray[x]);
                if (thingToCharMap.containsKey(key)) almostTheShape[y][x] = thingToCharMap.get(key);
                else thingToCharMap.put(key, almostTheShape[y][x] = (char) f++);
            }
        }
        final Object[] shape = new Object[objects.length + thingToCharMap.size() * 2];
        for (int i = 0; i < objects.length; i++) shape[i] = new String(almostTheShape[i]);
        int i = 0;
        for (final Object object : thingToCharMap.keySet()) {
            shape[objects.length + (2 * i)] = thingToCharMap.get(object);
            shape[objects.length + 1 + (2 * i++)] = (object instanceof Integer) ? keyStackMap.get(object) : object;
        }
        return shape;
    }
}
