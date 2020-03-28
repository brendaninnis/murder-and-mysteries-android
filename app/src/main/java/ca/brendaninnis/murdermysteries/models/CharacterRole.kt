package ca.brendaninnis.murdermysteries.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CharacterRole(val id: Int, val name: String, val description: String, val appearance: String, val imageId: Int) : Parcelable {
    var assigneeName: String? = null
}