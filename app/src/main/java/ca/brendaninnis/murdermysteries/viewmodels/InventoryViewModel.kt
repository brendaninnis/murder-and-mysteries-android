package ca.brendaninnis.murdermysteries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.InventoryItem

class InventoryViewModel: ViewModel() {
    private val _inventory: MutableLiveData<List<InventoryItem>> by lazy {
        MutableLiveData<List<InventoryItem>>().also {
            it.value = listOf(
                InventoryItem(0, "Coin Purse", "You have 12 silver drakes.", R.drawable.coin_purse),
                InventoryItem(1, "Receipt", "This receipt from The Swirling Draught alchemist shop lists Worm's Tongue, Eye of Newt, Mandrake and Nightshade. The payment names the buyer as “R.T.”", R.drawable.alchemist_receipt),
                InventoryItem(2, "Broach", "This broach belongs to Countess Ellowyn and has been in her family for 13 generations. It is engraved with the names of each Lady who wore it.", R.drawable.broach)
            )
        }
    }
    val inventory: LiveData<List<InventoryItem>>
        get() = _inventory
}