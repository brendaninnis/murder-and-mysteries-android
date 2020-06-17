package ca.brendaninnis.murdermysteries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.Evidence

class EvidenceViewModel: ViewModel() {
    private val _evidence: MutableLiveData<List<Evidence>> by lazy {
        MutableLiveData<List<Evidence>>().also {
            it.value = listOf(
                Evidence(0, "Coin Purse", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis mattis sit amet arcu blandit commodo. Donec vestibulum augue eu sapien lacinia pretium. Duis non suscipit ligula. Etiam sit amet finibus urna, nec mattis orci. Curabitur nec tempus arcu. Mauris sit amet arcu aliquam, suscipit neque ac, dictum magna. Mauris lorem erat, semper non est at, vestibulum dignissim tortor. Vestibulum ornare erat sed neque vehicula, nec posuere felis luctus.", R.drawable.coin_purse),
                Evidence(1, "Receipt", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis mattis sit amet arcu blandit commodo. Donec vestibulum augue eu sapien lacinia pretium. Duis non suscipit ligula. Etiam sit amet finibus urna, nec mattis orci. Curabitur nec tempus arcu. Mauris sit amet arcu aliquam, suscipit neque ac, dictum magna. Mauris lorem erat, semper non est at, vestibulum dignissim tortor. Vestibulum ornare erat sed neque vehicula, nec posuere felis luctus.", R.drawable.alchemist_receipt),
                Evidence(2, "Broach", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis mattis sit amet arcu blandit commodo. Donec vestibulum augue eu sapien lacinia pretium. Duis non suscipit ligula. Etiam sit amet finibus urna, nec mattis orci. Curabitur nec tempus arcu. Mauris sit amet arcu aliquam, suscipit neque ac, dictum magna. Mauris lorem erat, semper non est at, vestibulum dignissim tortor. Vestibulum ornare erat sed neque vehicula, nec posuere felis luctus.", R.drawable.broach)
            )
        }
    }
    val evidence: LiveData<List<Evidence>>
        get() = _evidence
}