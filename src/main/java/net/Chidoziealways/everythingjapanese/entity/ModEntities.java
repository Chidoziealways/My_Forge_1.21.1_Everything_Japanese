package net.Chidoziealways.everythingjapanese.entity;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EverythingJapanese.MOD_ID);

    public static final RegistryObject<EntityType<TriceratopsEntity>> TRICERATOPS =
            ENTITY_TYPES.register("triceratops", () -> EntityType.Builder.of(TriceratopsEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.5f).build("triceratops"));

    public static final RegistryObject<EntityType<SikaDeerEntity>> SIKA_DEER =
            ENTITY_TYPES.register("sika_deer", () -> EntityType.Builder.of(SikaDeerEntity::new, MobCategory.CREATURE)
                    .sized(3.0f, 10.0f).build("sika_deer"));

    public static final RegistryObject<EntityType<IronBattleAxeProjectileEntity>> IRON_BATTLE_AXE =
            ENTITY_TYPES.register("iron_battle_axe", () -> EntityType.Builder.<IronBattleAxeProjectileEntity>of(IronBattleAxeProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f).build("iron_battle_axe"));

    public static final RegistryObject<EntityType<YaProjectileEntity>> YA =
            ENTITY_TYPES.register("ya", () -> EntityType.Builder.<YaProjectileEntity>of(YaProjectileEntity::new, MobCategory.MISC)
                    .sized(1.15f, 1f).build("ya"));

    public static final RegistryObject<EntityType<ChairEntity>> CHAIR =
            ENTITY_TYPES.register("chair_entity", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("chair_entity"));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
