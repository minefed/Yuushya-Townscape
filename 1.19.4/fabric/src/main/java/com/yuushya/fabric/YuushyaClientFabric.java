package com.yuushya.fabric;

import com.yuushya.Yuushya;
import com.yuushya.fabriclike.YuushyaClientFabricLike;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public class YuushyaClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        FabricLoader.getInstance().getModContainer(Yuushya.MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation(Yuushya.MOD_ID, "fusion_combine"),
                    container, Component.translatable("pack.yuushya_fusion_combine.name"), ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation(Yuushya.MOD_ID, "mcpatcher_feature"),
                    container, Component.translatable("pack.yuushya_mcpatcher_feature.name"), ResourcePackActivationType.NORMAL);
        });YuushyaClientFabricLike.onInitializeClient();
    }
}