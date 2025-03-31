package com.yuushya.forge;

import com.yuushya.Yuushya;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.locating.IModFile;

@Mod(Yuushya.MOD_ID)
public class YuushyaForge {
    public YuushyaForge() {
        // Submit our event bus to let architectury register our content on the right time
        try{
            if(EventBuses.getModEventBus(Yuushya.MOD_ID).isEmpty())
                EventBuses.registerModEventBus(Yuushya.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        } catch (IllegalStateException ignored) {}
        Yuushya.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::packSetup);
    }
    public void packSetup(AddPackFindersEvent event) {
        IModFile modFile = ModList.get().getModFileById(Yuushya.MOD_ID).getFile();
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addRepositorySource(consumer -> consumer.accept(Pack.readMetaAndCreate(
                    String.valueOf(new ResourceLocation(Yuushya.MOD_ID,"resourcepacks/fusion_combine")),
                    Component.translatable("pack.yuushya_fusion_combine.name"),
                    false,
                    id -> new ModFilePackRes(id,modFile,"resourcepacks/fusion_combine"),
                    PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN)));
            event.addRepositorySource(consumer -> consumer.accept(Pack.readMetaAndCreate(
                    String.valueOf(new ResourceLocation(Yuushya.MOD_ID,"resourcepacks/mcpatcher_feature")),
                    Component.translatable("pack.yuushya_mcpatcher_feature.name"),
                    false,
                    id -> new ModFilePackRes(id,modFile,"resourcepacks/mcpatcher_feature"),
                    PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN)));
            event.addRepositorySource(consumer -> consumer.accept(Pack.readMetaAndCreate(
                    String.valueOf(new ResourceLocation(Yuushya.MOD_ID,"resourcepacks/ctm_support")),
                    Component.translatable("pack.yuushya_ctm_support.name"),
                    false,
                    id -> new ModFilePackRes(id,modFile,"resourcepacks/ctm_support"),
                    PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN)));
        }
    }
}
