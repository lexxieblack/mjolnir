package dev.lexxie.mjolnir;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Supplier;

@Mod( Mjolnir.MOD_ID )
public class Mjolnir {
	public static final String MOD_ID = "mjolnir";

	public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

	public static final Supplier<EntityType<MjolnirEntity>> MJOLNIR_ENTITY_TYPE = ENTITY_TYPES.register("mjolnir",
			() -> EntityType.Builder.of(MjolnirEntity::new, MobCategory.MISC).noSummon().noLootTable().fireImmune().build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "mjolnir"))));

	public static final DeferredItem<Item> MJOLNIR_ITEM = ITEMS.registerItem("mjolnir", MjolnirItem::new);

	@SubscribeEvent
	public static void registerEntityRenderers( EntityRenderersEvent.RegisterRenderers event ) {
		event.registerEntityRenderer(MJOLNIR_ENTITY_TYPE.get(), MjolnirRenderer::new);
	}

	@SubscribeEvent
	public static void buildContents( BuildCreativeModeTabContentsEvent event ) {
		if( event.getTabKey() != CreativeModeTabs.COMBAT ) return;
		event.accept(Mjolnir.MJOLNIR_ITEM.get());
	}

	@SubscribeEvent
	public static void registerItems( RegisterEvent event ) {
		event.register(BuiltInRegistries.ITEM, registry -> {
//			registry.register(Identifier.fromNamespaceAndPath(MOD_ID, "mjolnir"), MjolnirItem::new);
//			registry.register(Identifier.fromNamespaceAndPath(MOD_ID, "mjolnir"), MJOLNIR_ITEM.get());
//			registry.register(Identifier.fromNamespaceAndPath(MOD_ID, "mjolnir"), new MjolnirItem(new Item.Properties()));
			registry.register(Identifier.fromNamespaceAndPath(MOD_ID, "mjolnir"), new Item(new Item.Properties()));
		});

		event.register(BuiltInRegistries.BLOCK, registry -> {
			registry.register(Identifier.fromNamespaceAndPath(MOD_ID, "test"), new Block(BlockBehaviour.Properties.of()));
		});
	}
}
