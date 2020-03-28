package ca.brendaninnis.murdermysteries.models

import android.os.Parcelable
import ca.brendaninnis.murdermysteries.R
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Party(val id: Int, val mysteryId: Int, val host: String, val date: Date, val address: String, val phase: String) : Parcelable {
    @IgnoredOnParcel
    val mystery: Mystery by lazy {
        listOf(
            Mystery(0, "Murder and Dragons", 8, 20, R.drawable.murder_and_dragons),
            Mystery(1, "Avada Kedavra", 6, 16, R.drawable.avadakedavra),
            Mystery(2, "The Games Night Murder", 10, 24, R.drawable.gamesnight)
        )[mysteryId]
    }

    @IgnoredOnParcel
    val players: List<Player> by lazy {
        listOf(
            Player(0, "Brendan Innis"),
            Player(1, "Danielle Innis"),
            Player(2, "Richy"),
            Player(3, "Rozlyn Robinson"),
            Player(4, "Paul Robinson"),
            Player(5, "Deborah Robinson"),
            Player(6, "Ben Rollo"),
            Player(7, "Andrea Pilgrim"),
            Player(8, "Jerek"),
            Player(9, "Erinne"),
            Player(10, "Adam Verno"),
            Player(11, "Adam Beech"),
            Player(12, "Kronos"),
            Player(13, "Kratos")
        )
    }
}