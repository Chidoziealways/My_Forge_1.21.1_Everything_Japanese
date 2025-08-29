package net.Chidoziealways.everythingjapanese.toast

import net.Chidoziealways.everythingjapanese.quest.Quest
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.toasts.Toast
import net.minecraft.client.gui.components.toasts.ToastManager
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.ARGB
import net.minecraft.util.FormattedCharSequence
import net.minecraft.util.Mth

class QuestToast(private val quest: Quest): Toast {
    var wantedVisibility1: Toast.Visibility = Toast.Visibility.HIDE
    val BACKGROUND_SPRITE = ResourceLocation.withDefaultNamespace("toast/advancement")

    override fun getWantedVisibility(): Toast.Visibility {
        return wantedVisibility1
    }

    override fun update(manager: ToastManager, long: Long) {
        this.wantedVisibility1 = if ( long >= 5000.0 * manager.notificationDisplayTimeMultiplier) Toast.Visibility.HIDE else Toast.Visibility.SHOW
    }

    override fun getSoundEvent(): SoundEvent? {
        return SoundEvents.UI_TOAST_IN
    }

    override fun render(
        graphics: GuiGraphics,
        font: Font,
        long: Long
    ) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BACKGROUND_SPRITE, 0, 0, width(), height())

        val list: List<FormattedCharSequence> = font.split(quest.title, 125)
        val i = -256

        if (list.size == 1) {
            graphics.drawString(font, quest.title, 30, 7, i, false)
            graphics.drawString(font, list[0], 30, 18, -1, false)
        } else {
            val j = 1500
            val f = 300.0F

            if (long < 1500L) {
                val k = Mth.floor(Mth.clamp((1500L - long) / 300.0F, 0.0F, 1.0F) * 255.0F)
                graphics.drawString(font, quest.title, 30, 11, ARGB.color(k, i), false)
            } else {
                val i1 = Mth.floor(Mth.clamp((long - 1500L) / 300.0F, 0.0F, 1.0F) * 252.0F)
                var l = height() / 2 - list.size * 9 / 2

                for (formattedCharSequence in list) {
                    graphics.drawString(font, formattedCharSequence, 30, l, ARGB.color(i1, -1), false)
                    l += 9
                }
            }
        }
    }
}