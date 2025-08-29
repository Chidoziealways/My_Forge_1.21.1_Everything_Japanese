package net.Chidoziealways.everythingjapanese.util

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid

class ModTags {
    object Blocks {
        val NEEDS_PYRITE_TOOL: TagKey<Block> = createBlockTag("needs_pyrite_tool")
        val NEEDS_NEPHRITE_TOOL: TagKey<Block> = createBlockTag("needs_nephrite_tool")
        val INCORRECT_FOR_PYRITE_TOOL: TagKey<Block> = createBlockTag("incorrect_for_pyrite_tool")
        val INCORRECT_FOR_NEPHRITE_TOOL: TagKey<Block> = createBlockTag("incorrect_for_nephrite_tool")
        val INFINIBURN_HELL: TagKey<Block> = createBlockTag("infiniburn_hell")
        val HELL_FIRE_BASE_BLOCK = createBlockTag("hell_fire_base_block")

        private fun createBlockTag(name: String): TagKey<Block> {
            return TagKey.create(
                Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
            )
        }
    }

    object Items {
        val TRANSFORMABLE_ITEMS: TagKey<Item> = createItemTag("transformable_items")
        val PYRITE_TOOL_MATERIALS: TagKey<Item> = createItemTag("pyrite_tool_materials")
        val NEPHRITE_TOOL_MATERIALS: TagKey<Item> = createItemTag("nephrite_tool_materials")
        val REPAIRS_PYRITE_ARMOR: TagKey<Item> = createItemTag("repairs_pyrite_armor")
        val REPAIRS_NEPHRITE_ARMOR: TagKey<Item> = createItemTag("repairs_nephrite_armor")
        val HINOKI_MARUTA: TagKey<Item> = createItemTag("hinoki_maruta")
        val ANIMAL_SKIN: TagKey<Item> = createItemTag("animal_skin")

        private fun createItemTag(name: String): TagKey<Item> {
            return TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
            )
        }
    }

    object Biomes {
        val HAS_HELL_TEMPLE: TagKey<Biome?> = createBiomeTag("has_structure/hell_temple")
        val HAS_DOJO: TagKey<Biome?> = createBiomeTag("has_structure/dojo")
        val IS_HELL: TagKey<Biome?> = createBiomeTag("is_hell")

        private fun createBiomeTag(name: String): TagKey<Biome?> {
            return TagKey.create<Biome?>(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
            )
        }
    }

    object Fluids {
        val BLOOD: TagKey<Fluid> = createFluidTage("blood")

        private fun createFluidTage(name: String): TagKey<Fluid> {
            return TagKey.create(
                Registries.FLUID,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
            )
        }
    }
}
