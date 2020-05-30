package ca.brendaninnis.murdermysteries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.brendaninnis.murdermysteries.models.Objective

class ObjectivesViewModel: ViewModel() {
    private val _objectives: MutableLiveData<List<Objective>> by lazy {
        MutableLiveData<List<Objective>>().also {
            it.value = listOf(
                Objective(0, "Threaten the Captain of the Guard", "The captain has been attempting to subvert your plans to skim from the city coffers and is a suitor competing for the attention of the countess.", true),
                Objective(1, "Try to convince the Countess to marry you", "The Prince is a foreigner and this marriage is just a political convenience. The Countess should be interested in a handsome Baron with a respectable lineage such as yourself.", false),
                Objective(2, "Bribe the Diplomat", "Offer the Diplomat a handsome sum from your coffers in order to disrespect the customs of Rosedell and insult the ruling family, in order to sow discord betweent the kingdoms.", false)
            )
        }
    }
    val objectives: LiveData<List<Objective>>
        get() = _objectives

    fun toggleObjective(position: Int) {
        _objectives.value?.get(position)?.let { objective ->
            objective.complete = !objective.complete
        }
        _objectives.postValue(_objectives.value)
    }
}