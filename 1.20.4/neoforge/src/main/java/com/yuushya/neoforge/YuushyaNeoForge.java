package com.yuushya.neoforge;

import com.yuushya.Yuushya;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.Resource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforgespi.language.IModFileInfo;
import net.neoforged.neoforgespi.locating.IModFile;

import java.nio.file.Path;

@Mod(Yuushya.MOD_ID)
public class YuushyaNeoForge {
    public YuushyaNeoForge(IEventBus modBus) {
        Yuushya.init();

        modBus.addListener(this::packSetup);
    }
    public void packSetup(AddPackFindersEvent event) {
        IModFile modFile = ModList.get().getModFileById(Yuushya.MOD_ID).getFile();
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addRepositorySource(consumer -> consumer.accept(Pack.readMetaAndCreate(
                    String.valueOf(new ResourceLocation(Yuushya.MOD_ID,"resourcepacks/fusion_combine")),
                    Component.translatable("pack.yuushya_fusion_combine.name"),
                    false,
                    new FilePackResources.FileResourcesSupplier(modFile.findResource("resourcepacks/fusion_combine"),true),
                    PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN)));
        /*event.addPackFinders(
                Objects.requireNonNull(ResourceLocation.tryBuild(Yuushya.MOD_ID, "resourcepacks/mcpatcher_feature")),
                PackType.CLIENT_RESOURCES,
                Component.translatable("pack.yuushya_mcpatcher_feature.name"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP);*/

        }}
}
