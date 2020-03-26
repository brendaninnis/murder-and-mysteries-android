package ca.brendaninnis.murdermysteries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.CharacterRole

val characters = listOf(
    CharacterRole(
        0,
        "Countess Whatsername",
        "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
        "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
        R.drawable.person
    ),
    CharacterRole(
        1,
        "Prince Whatthefuck",
        "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
        "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
        R.drawable.person
    ),
    CharacterRole(
        2,
        "Captain of the Guard",
        "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
        "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
        R.drawable.person
    ),
    CharacterRole(
        3,
        "Baron Harkonen",
        "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
        "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
        R.drawable.person
    ),
    CharacterRole(
        4,
        "Wizard Harry",
        "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
        "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
        R.drawable.person
    ),
    CharacterRole(
        5,
        "Legolas My Eggolas",
        "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
        "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
        R.drawable.person
    )
)

class CharacterDetailViewModel(private val characterId: Int) : ViewModel() {
    private val _character: MutableLiveData<CharacterRole> by lazy {
        MutableLiveData<CharacterRole>().also {
            it.value = characters[characterId]
        }
    }
    val character: LiveData<CharacterRole>
        get() = _character

    class CharacterDetailViewModelFactory(val characterId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterDetailViewModel(characterId) as T
        }
    }
}