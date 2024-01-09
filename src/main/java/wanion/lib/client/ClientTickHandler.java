package wanion.lib.client;

/*
 * Created by WanionCane(https://github.com/WanionCane). This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import wanion.lib.WanionLib;
import wanion.lib.client.animation.Animation;

@SideOnly(Side.CLIENT)
public final class ClientTickHandler implements WanionLib.IDependency {

    private final List<Animation> registeredAnimations = new ArrayList<>();

    private ClientTickHandler() {
        FMLCommonHandler.instance().bus().register(this);
    }

    public void registerAnimation(@Nonnull final Animation animation) {
        registeredAnimations.add(animation);
    }

    @SubscribeEvent
    public void tickEvent(final TickEvent.ClientTickEvent event) {
        registeredAnimations.forEach(Animation::updateAnimation);
    }
}
