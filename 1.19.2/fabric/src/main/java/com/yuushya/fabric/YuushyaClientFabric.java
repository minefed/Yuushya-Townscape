package com.yuushya.fabric;

import com.yuushya.Yuushya;
import com.yuushya.fabriclike.YuushyaClientFabricLike;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

public class YuushyaClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        FabricLoader.getInstance().getModContainer(Yuushya.MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation(Yuushya.MOD_ID, "fusion_combine"),
                    container, "Yuushya Fusion Combine", ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation(Yuushya.MOD_ID, "mcpatcher_feature"),
                    container, "Yuushya Mcpatcher Feature", ResourcePackActivationType.NORMAL);
        });
        YuushyaClientFabricLike.onInitializeClient();
    }
}