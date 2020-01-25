package ca.brendaninnis.murdermysteries.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Mystery(val id: Int, val name: String, val minimumPlayers: Int, val maximumPlayers: Int, val splashImageId: Int) : Parcelable