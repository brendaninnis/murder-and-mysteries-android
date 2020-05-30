package ca.brendaninnis.murdermysteries.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Objective(val id: Int, val title: String, val description: String, var complete: Boolean) : Parcelable