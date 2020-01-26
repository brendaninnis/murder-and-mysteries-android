package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import ca.brendaninnis.murdermysteries.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlayFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_play, container, false)

        view.findViewById<FloatingActionButton>(R.id.play_fab).setOnClickListener {
            findNavController().navigate(R.id.newPartyFragment)
        }

        viewManager = LinearLayoutManager(context)
        viewAdapter = PartiesAdapter()

        recyclerView = view.findViewById<RecyclerView>(R.id.play_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return view
    }

    class PartiesAdapter : RecyclerView.Adapter<PartiesAdapter.ViewHolder>() {
        class ViewHolder(cardView: View) : RecyclerView.ViewHolder(cardView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val cardView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.party_item, parent, false)
            return ViewHolder(cardView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 1
        }
    }
}
