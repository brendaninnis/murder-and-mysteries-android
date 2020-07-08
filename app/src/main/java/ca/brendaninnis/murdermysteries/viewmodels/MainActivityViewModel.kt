package ca.brendaninnis.murdermysteries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.brendaninnis.murdermysteries.models.Party
import java.util.*

class MainActivityViewModel: ViewModel() {
    private val _party: MutableLiveData<Party?> by lazy {
        MutableLiveData<Party?>().also {
            it.value = Party(0, 0, "Danielle Innis", Date(1604170800000), "141-200 Dallas rd.", "solution")
        }
    }
    val party: LiveData<Party?>
        get() = _party
}