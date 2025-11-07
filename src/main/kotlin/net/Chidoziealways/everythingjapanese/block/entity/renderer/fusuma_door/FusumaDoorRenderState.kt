package net.Chidoziealways.everythingjapanese.block.entity.renderer.fusuma_door

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import software.bernie.geckolib.constant.dataticket.DataTicket
import software.bernie.geckolib.renderer.base.GeoRenderState

class FusumaDoorRenderState: BlockEntityRenderState(), GeoRenderState {
    private val geckoData: MutableMap<DataTicket<*>, Any?> = mutableMapOf()

    override fun <D : Any?> addGeckolibData(dataTicket: DataTicket<D?>, data: D?) {
        geckoData[dataTicket] = data
    }

    override fun hasGeckolibData(dataTicket: DataTicket<*>): Boolean {
        return geckoData.containsKey(dataTicket)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <D : Any?> getGeckolibData(dataTicket: DataTicket<D?>): D? {
        return geckoData[dataTicket] as? D
    }

    override fun getDataMap(): Map<DataTicket<*>, Any?> {
        return geckoData
    }
}