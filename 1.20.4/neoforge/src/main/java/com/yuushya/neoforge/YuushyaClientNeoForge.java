package com.yuushya.neoforge;

import com.yuushya.Yuushya;
import com.yuushya.YuushyaClient;
import com.yuushya.entity.ChairEntity;
import com.yuushya.entity.ChairEntityRender;
import com.yuushya.particle.YuushyaParticleFactory;
import com.yuushya.registries.YuushyaRegistries;
import com.yuushya.registries.YuushyaRegistryConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforgespi.language.IModFileInfo;
import net.neoforged.neoforgespi.locating.IModFile;

import java.util.Objects;

import static jdk.internal.loader.BootLoader.findResource;

@Mod.EventBusSubscriber(modid = Yuushya.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YuushyaClientNeoForge {
    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        YuushyaClient.onInitializeClient();

    }


    @SubscribeEvent
    public static void onEntityRendererRegister(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer((EntityType<ChairEntity>) YuushyaRegistries.CHAIR_ENTITY.get(), ChairEntityRender::new);
    }


    @SubscribeEvent
    public static void onParticleFactoryRegistration(RegisterParticleProvidersEvent event) {
        YuushyaRegistryConfig.YuushyaRawParticleMap.values().forEach((e)->{
            Minecraft.getInstance().particleEngine.register((ParticleType<SimpleParticleType>)YuushyaRegistries.PARTICLE_TYPES.get(e.name).get(), (spriteSet)-> YuushyaParticleFactory.create(e,spriteSet));
        });
        //Minecraft.getInstance().particleEngine.register((ParticleType<SimpleParticleType>) YuushyaRegistries.PARTICLE_TYPES.get("leaf_particle").get(), LeafParticle.Factory::new);
    }

    @SubscribeEvent
    public void packSetup(AddPackFindersEvent event) {
        IModFileInfo modFileInfo = ModList.get().getModFileById(Yuushya.MOD_ID);
        IModFile modFile = modFileInfo.getFile();
        event.addRepositorySource(consumer -> {
            Pack pack = Pack.readMetaAndCreate(
                "fusion_combine",
                    Component.translatable("pack.yuushya_fusion_combine.name"),
                    false,
                    new Pack.ResourcesSupplier(Yuushya.MOD_ID, true, modFile.findResource("resourcepacks/mcpatcher_feature"))
                    , PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN)};
        event.addPackFinders(
                Objects.requireNonNull(ResourceLocation.tryBuild(Yuushya.MOD_ID, "resourcepacks/mcpatcher_feature")),
                PackType.CLIENT_RESOURCES,
                Component.translatable("pack.yuushya_mcpatcher_feature.name"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP);

    }
    private Pack.ResourcesSupplier onCreateVanillaBuiltinResourcePack(String name, Component displayName, boolean alwaysEnabled,
                                                                      Pack.ResourcesSupplier packFactory, PackType type, Pack.Position position, PackSource source) {
        return factory -> new ResourceLocation(name,name);
    }
}
/*
        for(BlockState blockState: YuushyaRegistries.BLOCKS.get("showblock").get().getStateDefinition().getPossibleStates()){
            ModelResourceLocation modelResourceLocation= BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = event.getModelRegistry().get(modelResourceLocation);
            if (existingModel !=null&&!(existingModel instanceof ShowBlockModel))
                event.getModelRegistry().put(modelResourceLocation,new ShowBlockModel(existingModel));
        }
 */


