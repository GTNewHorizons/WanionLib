package wanion.lib;

/*
 * Created by WanionCane(https://github.com/WanionCane). This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 */

import static wanion.lib.Reference.ACCEPTED_MINECRAFT;
import static wanion.lib.Reference.MOD_ID;
import static wanion.lib.Reference.MOD_NAME;
import static wanion.lib.Reference.MOD_VERSION;

import java.util.Map;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import wanion.lib.client.ClientTickHandler;
import wanion.lib.common.Dependencies;

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION, acceptedMinecraftVersions = ACCEPTED_MINECRAFT)
public final class WanionLib {

    @Mod.Instance(MOD_ID)
    public static WanionLib instance;

    private Dependencies<IDependency> dependencies = new Dependencies<>();
    private Logger logger;

    public static Dependencies<IDependency> getDependencies() {
        return instance.dependencies;
    }

    public static Logger getLogger() {
        return instance.logger;
    }

    @SideOnly(Side.CLIENT)
    public static ClientTickHandler getClientTickHandler() {
        return instance.dependencies.get(ClientTickHandler.class);
    }

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void loadComplete(final FMLLoadCompleteEvent event) {
        dependencies = null;
    }

    public boolean matchModVersions(final Map<String, String> remoteVersions, final Side side) {
        return side == Side.CLIENT ? remoteVersions.containsKey(MOD_ID)
                : !remoteVersions.containsKey(MOD_ID) || remoteVersions.get(MOD_ID).equals(MOD_VERSION);
    }

    public interface IDependency {
    }
}
