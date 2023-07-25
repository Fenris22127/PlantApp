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
                    R.string.water_peace_lily,
                    R.string.note_drama_queen,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_peace_lily,
                    listOf(
                        R.drawable.ph_peace_lily_1,
                        R.drawable.ph_peace_lily_2,
                        R.drawable.ph_peace_lily_3,
                        R.drawable.ph_peace_lily_4
                    )
                ),
                Plant(
                    R.string.spider_plant,
                    "Chlorophytum comosum",
                    listOf(Room.BATHROOM, Room.LIVING_ROOM, Room.KITCHEN),
                    R.string.water_spider_plant,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_spider_plant,
                    listOf(
                        R.drawable.ph_spider_plant_1,
                        R.drawable.ph_spider_plant_2,
                        R.drawable.ph_spider_plant_3,
                        R.drawable.ph_spider_plant_4,
                        R.drawable.ph_spider_plant_5,
                        R.drawable.ph_spider_plant_6,
                        R.drawable.ph_spider_plant_7
                    )
                ),
                Plant(
                    R.string.monstera,
                    "Monstera deliciosa",
                    listOf(Room.BATHROOM),
                    R.string._300ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_monstera,
                    listOf(R.drawable.ph_monstera)
                ),
                Plant(
                    R.string.golden_pothos,
                    "Epipremnum aureum",
                    listOf(Room.BATHROOM),
                    R.string._150_200ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.droopy_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_golden_pothos,
                    listOf(R.drawable.ph_golden_pothos)
                ),
                Plant(
                    R.string.pink_orchid,
                    "Phalaenopsis Sogo Berry",
                    listOf(Room.BATHROOM),
                    R.string._100ml,
                    R.string.spray_with_water,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.floppy_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_floppy_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_pink_orchid,
                    listOf(R.drawable.ph_pink_moth_orchid)
                ),
                Plant(
                    R.string.white_orchid,
                    "Phalaenopsis",
                    listOf(Room.BATHROOM),
                    R.string._50ml,
                    R.string.spray_with_water,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.floppy_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_floppy_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_tiny_orchid,
                    listOf(R.drawable.ph_white_orchid)
                ),
                Plant(
                    R.string.spotted_orchid,
                    "Phalaenopsis 'Purple Rain'",
                    listOf(Room.BATHROOM),
                    R.string._100ml,
                    R.string.spray_with_water,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.floppy_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_floppy_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_spotted_orchid,
                    listOf(R.drawable.ph_purple_rain_orchid)
                ),
                Plant(
                    R.string.moth_orchid,
                    "Phalaenopsis 'Jungle Cat Stripes'",
                    listOf(Room.BATHROOM),
                    R.string._150_200ml,
                    R.string.spray_with_water,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.floppy_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_floppy_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_yellow_orchid,
                    listOf(R.drawable.ph_jungle_stripes_orchid)
                ),
                Plant(
                    R.string.kalanchoe,
                    "Kalanchoe blossfeldiana",
                    listOf(Room.BATHROOM, Room.LIVING_ROOM),
                    R.string.water_kalanchoe,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.falling_leaves_rotting_stem, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_kalanchoe,
                    listOf(
                        R.drawable.ph_kalanchoe_1,
                        R.drawable.ph_kalanchoe_2,
                        R.drawable.ph_kalanchoe_3,
                        R.drawable.ph_kalanchoe_4,
                        R.drawable.ph_kalanchoe_5
                    )
                ),
                Plant(
                    R.string.asparagus_fern,
                    "Asparagus setaceus",
                    listOf(Room.BATHROOM),
                    R.string._100ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.falling_brown_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_asparagus_fern,
                    listOf(R.drawable.ph_asparagus_fern)
                ),
                Plant(
                    R.string.wandering_jew,
                    "Tradescantia zebrina",
                    listOf(Room.BATHROOM, Room.LIVING_ROOM),
                    R.string.water_tradescantia,
                    R.string.pale_leaves_happen,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_silver_inch_plant,
                    listOf(
                        R.drawable.ph_tradescantia_1,
                        R.drawable.ph_tradescantia_2,
                        R.drawable.ph_tradescantia_3,
                        R.drawable.ph_tradescantia_4,
                        R.drawable.ph_tradescantia_5,
                        R.drawable.ph_tradescantia_6,
                        R.drawable.ph_tradescantia_7,
                        R.drawable.ph_tradescantia_8,
                        R.drawable.ph_tradescantia_9
                    )
                ),
                /* LIVING ROOM */
                Plant(
                    R.string.bonsai,
                    "Ligustrum sinense",
                    listOf(Room.LIVING_ROOM),
                    R.string._200ml,
                    R.string.note_drama_queen,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.droopy_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less),
                        WarningSign(R.string.black_spots, R.string.black_spot_fungus)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_bonsai,
                    listOf(R.drawable.ph_bonsai)
                ),
                Plant(
                    R.string.banana,
                    "Musa oriana",
                    listOf(Room.LIVING_ROOM),
                    R.string._50ml,
                    R.string.brown_leaves_happen,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_banana,
                    listOf(R.drawable.ph_banana)
                ),
                Plant(
                    R.string.snake_plant,
                    "Sansevieria trifasciata",
                    listOf(Room.LIVING_ROOM),
                    R.string._50ml,
                    R.string.note_empty,
                    R.string.frequency_none,
                    listOf(),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_snake_plant,
                    listOf(
                        R.drawable.ph_snake_plant_1,
                        R.drawable.ph_snake_plant_2
                    )
                ),
                Plant(
                    R.string.aloe,
                    "Aloe vera",
                    listOf(Room.LIVING_ROOM),
                    R.string._50ml,
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.leathery_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_aloe_vera,
                    listOf(
                        R.drawable.ph_aloe_very_1,
                        R.drawable.ph_aloe_very_2
                    )
                ),
                Plant(
                    R.string.false_christmas_cactus,
                    "Schlumbergera truncata",
                    listOf(Room.LIVING_ROOM),
                    R.string._10_pumps,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.falling_leaves, R.string.water_more)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_false_christmas_cactus,
                    listOf(R.drawable.ph_false_christmas_cactus)
                ),
                Plant(
                    R.string.japanese_woodland_stonecrop,
                    "Sedum makinoi 'Tundra Tornado'",
                    listOf(Room.LIVING_ROOM),
                    R.string._10_pumps,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.falling_leaves, R.string.water_more)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_japanese_woodland_stonecrop,
                    listOf(R.drawable.ph_tundra_tornado)
                ),
                Plant(
                    R.string.chinese_money_plant,
                    "Pilea peperomioides",
                    listOf(Room.LIVING_ROOM),
                    R.string.water_chinese_money,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.falling_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_chinese_money_plant,
                    listOf(
                        R.drawable.ph_chinese_money_plant_1,
                        R.drawable.ph_chinese_money_plant_2
                    )
                ),
                Plant(
                    R.string.amaryllis,
                    "Hippeastrum",
                    listOf(Room.LIVING_ROOM),
                    R.string._50ml,
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.yellowing_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_amaryllis,
                    listOf(R.drawable.ph_amaryllis)
                ),
                Plant(
                    R.string.fig_cactus,
                    "Opuntia ficus-indica",
                    listOf(Room.LIVING_ROOM),
                    R.string._50ml,
                    R.string.note_cactus_spikes,
                    R.string.frequency_none,
                    listOf(
                        WarningSign(R.string.shrivelling_up, R.string.water_more)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_fig_cactus,
                    listOf(
                        R.drawable.ph_fig_cactus_1,
                        R.drawable.ph_fig_cactus_2,
                        R.drawable.ph_fig_cactus_3
                    )
                ),
                Plant(
                    R.string.argentine_giant,
                    "Echinopsis candicans",
                    listOf(Room.LIVING_ROOM),
                    R.string._10ml,
                    R.string.note_empty,
                    R.string.frequency_none,
                    listOf(
                        WarningSign(R.string.shrivelling_up, R.string.water_more)
                    ),
                    Sensitivity.INDESTRUCTIBLE,
                    R.drawable.img_argentine_giant,
                    listOf(
                        R.drawable.ph_argentine_giant,
                        R.drawable.ph_argentine_giant_2
                    )
                ),
                Plant(
                    R.string.parlor_palm,
                    "Chamaedorea elegans",
                    listOf(Room.LIVING_ROOM),
                    R.string._100ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_parlor_palm,
                    listOf(R.drawable.ph_parlor_palm)
                ),
                Plant(
                    R.string.calathea,
                    "Calathea luisiae",
                    listOf(Room.LIVING_ROOM),
                    R.string._200ml,
                    R.string.spray_with_water,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.dry_edges_pale_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.HIGH,
                    R.drawable.img_calathea,
                    listOf(R.drawable.ph_calathea)
                ),
                Plant(
                    R.string.bamboo,
                    "Dracaena sanderiana",
                    listOf(Room.LIVING_ROOM),
                    R.string._100ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_bamboo,
                    listOf(R.drawable.ph_bamboo)
                ),
                Plant(
                    R.string.arrowhead,
                    "Syngonium podophyllum",
                    listOf(Room.LIVING_ROOM),
                    R.string._600ml,
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_arrowhead,
                    listOf(R.drawable.ph_arrowhead_plant)
                ),
                Plant(
                    R.string.lizard_tail,
                    "Gasteraloe beguinii",
                    listOf(Room.LIVING_ROOM),
                    R.string._20ml,
                    R.string.note_empty,
                    R.string.frequency_none,
                    listOf(
                        WarningSign(R.string.leathery_wrinkled_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_wrinkled_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_lizard_tail,
                    listOf(R.drawable.ph_lizard_tail)
                ),
                Plant(
                    R.string.moon_valley_pilea,
                    "Pilea mollis 'Moon Valley'",
                    listOf(Room.LIVING_ROOM),
                    R.string._200ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.dry_edges_pale_leaves, R.string.water_more)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_moon_valley_pilea,
                    listOf(R.drawable.img_plant)
                ),
                Plant(
                    R.string.guzmania_hope,
                    "Guzmania lingulata 'Hope'",
                    listOf(Room.LIVING_ROOM),
                    R.string._50ml,
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.brown_dry_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_hope,
                    listOf(R.drawable.ph_guzmania)
                ),
                Plant(
                    R.string.jade_pothos,
                    "Epipremnum aureum",
                    listOf(Room.LIVING_ROOM),
                    R.string._400ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_jade_pothos,
                    listOf(R.drawable.ph_jade_pothos)
                ),
                Plant(
                    R.string.heart_leaf,
                    "Philodendron hederaceum",
                    listOf(Room.LIVING_ROOM),
                    R.string._100ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.pale_hanging_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_heart_leaf,
                    listOf(R.drawable.ph_philodendron)
                ),
                Plant(
                    R.string.jade_plant,
                    "Crassula ovata",
                    listOf(Room.LIVING_ROOM),
                    R.string._50ml,
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.shrivelled_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_jade_plant,
                    listOf(R.drawable.ph_jade_plant)
                ),
                Plant(
                    R.string.clearweed,
                    "Pilea depressa",
                    listOf(Room.LIVING_ROOM),
                    R.string._200ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.dry_falling_leaves_vines, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_clearweed,
                    listOf(R.drawable.ph_clearweed)
                ),
                Plant(
                    R.string.dracaena,
                    "Dracaena marginata 'Bicolor'",
                    listOf(Room.LIVING_ROOM),
                    R.string._100ml,
                    R.string.note_empty,
                    R.string.once_a_week,
                    listOf(
                        WarningSign(R.string.dry_falling_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_dracaena,
                    listOf(R.drawable.ph_dracaena)
                ),
                Plant(
                    R.string.flaming_sword,
                    "Vriesea-Cutivars 'Davine'",
                    listOf(Room.LIVING_ROOM),
                    R.string._200ml,
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.brown_dry_leaves, R.string.water_more)
                    ),
                    Sensitivity.LOW,
                    R.drawable.img_flaming_sword,
                    listOf(R.drawable.ph_flaming_sword)
                ),
                Plant(
                    R.string.rabbits_foot_fern,
                    "Davallia fejeensis",
                    listOf(Room.LIVING_ROOM),
                    R.string._200ml,
                    R.string.note_empty,
                    R.string.once_in_2_weeks,
                    listOf(
                        WarningSign(R.string.brown_dry_leaves, R.string.water_more),
                        WarningSign(R.string.yellowing_leaves, R.string.water_less)
                    ),
                    Sensitivity.MEDIUM,
                    R.drawable.img_rabbits_foot,
                    listOf(R.drawable.ph_rabbits_foot_fern)
                ),
            )
        )
    }

    companion object {
        val instance = PlantList()
    }
}

