package me.cortex.voxy.client.core.util;

import net.fabricmc.loader.api.FabricLoader;
import net.irisshaders.iris.api.v0.IrisApi;
import org.spongepowered.asm.mixin.Unique;

public class IrisUtil {
    private static final boolean IRIS_INSTALLED = FabricLoader.getInstance().isModLoaded("iris");

    public static boolean irisShadowActive() {
        return IRIS_INSTALLED && IrisApi.getInstance().isRenderingShadowPass();
    }
}
