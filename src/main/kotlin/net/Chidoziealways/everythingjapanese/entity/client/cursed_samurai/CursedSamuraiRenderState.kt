package net.Chidoziealways.everythingjapanese.entity.client.cursed_samurai

import com.geckolib.constant.dataticket.DataTicket
import com.geckolib.renderer.base.GeoRenderState
import net.minecraft.client.renderer.entity.state.EntityRenderState

class CursedSamuraiRenderState : GeoRenderState, EntityRenderState() {
    private val geckoData: MutableMap<DataTicket<*>, Any> = mutableMapOf()

    override fun <D : Any> addGeckolibData(dataTicket: DataTicket<D>, data: D) {
        geckoData[dataTicket] = data
    }

    override fun hasGeckolibData(dataTicket: DataTicket<*>): Boolean {
        return geckoData.containsKey(dataTicket)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <D : Any> getGeckolibData(dataTicket: DataTicket<D>): D {
        return geckoData[dataTicket] as D
    }

    override fun getDataMap(): Map<DataTicket<*>, Any> {
        return geckoData
    }
}
