package ca.brendaninnis.murdermysteries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.CharacterRole
import ca.brendaninnis.murdermysteries.models.Player

class AccusationViewModel: ViewModel() {
    private val _characters: MutableLiveData<List<CharacterRole>> by lazy {
        MutableLiveData<List<CharacterRole>>().also {
            it.value = listOf(
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
    }
    val characters: LiveData<List<CharacterRole>>
        get() = _characters

    private val _accused: MutableLiveData<CharacterRole?> = MutableLiveData(null)
    val accused: LiveData<CharacterRole?>
        get() = _accused

    private val _bestDressed: MutableLiveData<Player?> = MutableLiveData(null)
    val bestDressed: LiveData<Player?>
        get() = _bestDressed

    private val _bestActing: MutableLiveData<Player?> = MutableLiveData(null)
    val bestActing: LiveData<Player?>
        get() = _bestActing

    private val _bestFun: MutableLiveData<Player?> = MutableLiveData(null)
    val bestFun: LiveData<Player?>
        get() = _bestFun
}