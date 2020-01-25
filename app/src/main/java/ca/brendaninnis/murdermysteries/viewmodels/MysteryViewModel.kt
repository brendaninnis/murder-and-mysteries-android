package ca.brendaninnis.murdermysteries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.brendaninnis.murdermysteries.models.Mystery
import androidx.lifecycle.ViewModelProvider
import ca.brendaninnis.murdermysteries.R

class MysteryViewModel(private val mysteryId: Int) : ViewModel() {
    private val _mystery: MutableLiveData<Mystery> by lazy {
        MutableLiveData<Mystery>().also {
            it.value = listOf(
                Mystery(0, "Murder and Dragons", 8, 20, R.drawable.murder_and_dragons),
                Mystery(1, "Avada Kedavra", 6, 16, R.drawable.avadakedavra),
                Mystery(2, "The Games Night Murder", 10, 24, R.drawable.gamesnight)
            )[mysteryId]
        }
    }
    val mystery: LiveData<Mystery>
    get() = _mystery

    class Factory(private val mysteryId: Int) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = MysteryViewModel(mysteryId) as T
    }
}