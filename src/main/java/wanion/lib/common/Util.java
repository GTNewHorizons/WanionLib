package wanion.lib.common;

/*
 * Created by WanionCane(https://github.com/WanionCane). This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 */

import java.lang.reflect.Field;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameData;

public final class Util {

    private Util() {}

    @SuppressWarnings("unchecked")
    public static <T, E extends T> E getField(Class clas, String name, Object instance, Class<T> expectedClass) {
        try {
            final Field field = clas.getDeclaredField(name);
            field.setAccessible(true);
            return (E) expectedClass.cast(field.get(instance));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> void setField(Class clas, String name, Object instance, E newInstance) {
        try {
            final Field field = clas.getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, newInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getModName(final ItemStack itemStack) {
        String name = GameData.getItemRegistry().getNameForObject(itemStack.getItem());
        return name.substring(0, name.indexOf(58));
    }
}
