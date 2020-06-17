package ca.brendaninnis.murdermysteries.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Evidence(val id: Int, val name: String, val description: String, val image: Int) : Parcelable