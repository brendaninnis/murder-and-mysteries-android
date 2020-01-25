package ca.brendaninnis.murdermysteries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.Mystery

class MysteryListViewModel : ViewModel() {
    private val _mysteries: MutableLiveData<List<Mystery>> by lazy {
        MutableLiveData<List<Mystery>>().also {
            it.value = listOf(
                Mystery(0, "Murder and Dragons", 8, 20, R.drawable.murder_and_dragons),
                Mystery(1, "Avada Kedavra", 6, 16, R.drawable.avadakedavra),
                Mystery(2, "The Games Night Murder", 10, 24, R.drawable.gamesnight)
            )
        }
    }
    val mysteries: LiveData<List<Mystery>>
    get() = _mysteries
}