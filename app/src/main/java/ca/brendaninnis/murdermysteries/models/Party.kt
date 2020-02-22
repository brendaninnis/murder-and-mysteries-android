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
}