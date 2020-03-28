package ca.brendaninnis.murdermysteries.models

import android.os.Parcelable
import ca.brendaninnis.murdermysteries.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class Mystery(val id: Int, val name: String, val minimumPlayers: Int, val maximumPlayers: Int, val splashImageId: Int) : Parcelable {
    val characters: List<CharacterRole> = listOf(
        CharacterRole(
            0,
            "Countess Whatsername",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.countess
        ),
        CharacterRole(
            1,
            "Prince Whatthefuck",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.prince
        ),
        CharacterRole(
            2,
            "Captain of the Guard",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.guard
        ),
        CharacterRole(
            3,
            "Baron Harkonen",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.baron
        ),
        CharacterRole(
            4,
            "Wizard Harry",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.wizard
        ),
        CharacterRole(
            5,
            "Legolas My Eggolas",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.elf
        )
    )
}