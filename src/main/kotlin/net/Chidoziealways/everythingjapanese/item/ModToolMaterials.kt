package net.Chidoziealways.everythingjapanese.item

import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.ToolMaterial

object ModToolMaterials {
    var PYRITE: ToolMaterial = ToolMaterial(
        BlockTags.INCORRECT_FOR_GOLD_TOOL,
        1000,
        10.5f,
        2.5f,
        20,
        ItemTags.GOLD_TOOL_MATERIALS
    )

    var NEPHRITE: ToolMaterial = ToolMaterial(
        BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
        200000,
        30.9f,
        10.5f,
        30,
        ItemTags.NETHERITE_TOOL_MATERIALS
    )
}
