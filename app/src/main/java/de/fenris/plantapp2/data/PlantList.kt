package de.fenris.plantapp2.data

import de.fenris.plantapp2.R

class PlantList {

    val allPlants = mutableListOf<Plant>()

    fun setData() {
        allPlants.addAll(
            listOf(
                /* BATHROOM */
                Plant(
                    R.string.peace_lily,
                    "Spathiphyllum wallisii",
                    listOf(Room.BATHROOM, Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_peace_lily,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.spider_plant,
                    "Chlorophytum comosum",
                    listOf(Room.BATHROOM, Room.LIVING_ROOM, Room.KITCHEN),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_spider_plant,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.monstera,
                    "Monstera deliciosa",
                    listOf(Room.BATHROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_monstera,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.golden_pothos,
                    "Epipremnum aureum",
                    listOf(Room.BATHROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.droopy_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_golden_pothos,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.pink_orchid,
                    "Phalaenopsis Sogo Berry",
                    listOf(Room.BATHROOM),
                    "0",
                    R.string.spray_with_water,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.floppy_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_floppy_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_pink_orchid,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.white_orchid,
                    "Phalaenopsis",
                    listOf(Room.BATHROOM),
                    "0",
                    R.string.spray_with_water,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.floppy_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_floppy_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_tiny_orchid,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.spotted_orchid,
                    "Phalaenopsis 'Purple Rain'",
                    listOf(Room.BATHROOM),
                    "0",
                    R.string.spray_with_water,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.floppy_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_floppy_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_spotted_orchid,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.kalanchoe,
                    "Kalanchoe blossfeldiana",
                    listOf(Room.BATHROOM, Room.LIVING_ROOM),
                    "Small red cup: 15 pumps from the spray bottle",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.falling_leaves_rotting_stem, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_kalanchoe,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.asparagus_fern,
                    "Asparagus setaceus",
                    listOf(Room.BATHROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.falling_brown_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_asparagus_fern,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.wandering_jew,
                    "Tradescantia zebrina",
                    listOf(Room.BATHROOM, Room.LIVING_ROOM),
                    "0",
                    R.string.pale_leaves_happen,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_silver_inch_plant,
                    R.drawable.img_plant
                ),
                /* LIVING ROOM */
                Plant(
                    R.string.bonsai,
                    "Ligustrum sinense",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.droopy_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less),
                        WarningSign(R.string.black_spots, R.string.black_spot_fungus)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_bonsai,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.banana,
                    "Musa oriana",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.brown_leaves_happen,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_banana,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.snake_plant,
                    "Sansevieria trifasciata",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.frequency_none,
                    listOf(),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_snake_plant,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.aloe,
                    "Aloe vera",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.leathery_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_aloe_vera,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.false_christmas_cactus,
                    "Schlumbergera truncata",
                    listOf(Room.LIVING_ROOM),
                    "10 pumps of the spray bottle",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.falling_leaves, R.string.water_more)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_false_christmas_cactus,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.japanese_woodland_stonecrop,
                    "Sedum makinoi 'Tundra Tornado'",
                    listOf(Room.LIVING_ROOM),
                    "10 pumps from the spray bottle",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.falling_leaves, R.string.water_more)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_japanese_woodland_stonecrop,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.chinese_money_plant,
                    "Pilea peperomioides",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.falling_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_chinese_money_plant,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.amaryllis,
                    "Hippeastrum",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.yellowing_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_amaryllis,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.fig_cactus,
                    "Opuntia ficus-indica",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.frequency_none,
                    listOf(
                        WarningSign(R.string.shrivelling_up, R.string.water_more)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_fig_cactus,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.argentine_giant,
                    "Echinopsis candicans",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.frequency_none,
                    listOf(
                        WarningSign(R.string.shrivelling_up, R.string.water_more)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_argentine_giant,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.parlor_palm,
                    "Chamaedorea elegans",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_parlor_palm,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.calathea,
                    "Calathea luisiae",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.spray_with_water,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.dry_edges_pale_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.HIGH,
                    R.drawable.img_calathea,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.bamboo,
                    "Dracaena sanderiana",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_bamboo,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.arrowhead,
                    "Syngonium podophyllum",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_arrowhead,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.lizard_tail,
                    "Gasteraloe beguinii",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.frequency_none,
                    listOf(
                        WarningSign(R.string.leathery_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_lizard_tail,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.moon_valley_pilea,
                    "Pilea mollis 'Moon Valley'",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.dry_edges_pale_leaves, R.string.water_more)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_moon_valley_pilea,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.guzmania_hope,
                    "Guzmania lingulata 'Hope'",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.brown_dry_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_hope,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.jade_pothos,
                    "Epipremnum aureum",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_jade_pothos,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.heart_leaf,
                    "Philodendron hederaceum",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_heart_leaf,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.jade_plant,
                    "Crassula ovata",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.shrivelled_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_jade_plant,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.clearweed,
                    "Pilea depressa",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.dry_falling_leaves_vines, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_clearweed,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.dracaena,
                    "Dracaena marginata 'Bicolor'",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.dry_falling_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_dracaena,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.flaming_sword,
                    "Vriesea-Cutivars 'Davine'",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.brown_dry_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_flaming_sword,
                    R.drawable.img_plant
                ),
                Plant(
                    R.string.rabbits_foot_fern,
                    "Davallia fejeensis",
                    listOf(Room.LIVING_ROOM),
                    "0",
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.brown_dry_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_rabbits_foot,
                    R.drawable.img_plant
                ),
            )
        )
    }

    companion object {
        val instance = PlantList()
    }
}

