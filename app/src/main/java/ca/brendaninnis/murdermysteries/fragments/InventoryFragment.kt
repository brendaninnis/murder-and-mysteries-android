package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.adapters.InventoryAdapter
import ca.brendaninnis.murdermysteries.viewmodels.InventoryViewModel
import ca.brendaninnis.murdermysteries.viewmodels.ObjectivesViewModel

class InventoryFragment : Fragment() {

    private var inventoryAdapter = InventoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        with (view.findViewById<RecyclerView>(R.id.inventory_recycler)) {
            setHasFixedSize(true)
            adapter = inventoryAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model: InventoryViewModel by viewModels()

        model.inventory.observe(viewLifecycleOwner, Observer { inventory ->
            inventory?.let {
                inventoryAdapter.submitList(it)
            }
        })
    }

}
