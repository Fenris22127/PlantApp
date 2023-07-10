package de.fenris.plantapp2.data

import android.content.Context
import androidx.annotation.StringRes
import de.fenris.plantapp2.R

enum class Room(@StringRes val value: Int) {
    ALL_ROOMS(R.string.room_all_rooms),
    BATHROOM(R.string.room_bathroom),
    LIVING_ROOM(R.string.room_living_room),
    KITCHEN(R.string.room_kitchen);

    fun toString(context: Context) = context.getString(value)
}