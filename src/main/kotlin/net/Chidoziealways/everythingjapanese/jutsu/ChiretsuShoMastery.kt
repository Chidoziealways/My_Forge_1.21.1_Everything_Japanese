package net.Chidoziealways.everythingjapanese.jutsu

enum class ChiretsuShoMastery(val minMastery: Float, val radius: Int) {
    NOVICE(0f, 0),       // 1 block radius
    ADEPT(100f, 1),       // 2 block radius
    EXPERT(400f, 2),      // 3 block radius
    MASTER(700f, 3),     // 5 block radius
    GOD(1000f, 4);       // 6 block Radius

    companion object {
        fun fromMastery(mastery: Float): ChiretsuShoMastery {
            return entries
                .sortedByDescending { it.minMastery }
                .firstOrNull { mastery >= it.minMastery }
                ?: NOVICE
        }
    }
}
