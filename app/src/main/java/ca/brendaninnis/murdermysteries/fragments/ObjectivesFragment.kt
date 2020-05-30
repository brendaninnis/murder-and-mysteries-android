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
import ca.brendaninnis.murdermysteries.adapters.ObjectivesAdapter
import ca.brendaninnis.murdermysteries.utils.RecyclerItemTouchListener
import ca.brendaninnis.murdermysteries.viewmodels.ObjectivesViewModel

class ObjectivesFragment : Fragment() {

    private var objectivesAdapter: ObjectivesAdapter = ObjectivesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_objectives, container, false)

        val model: ObjectivesViewModel by viewModels()

        with (view.findViewById<RecyclerView>(R.id.objectives_recycler)) {
            setHasFixedSize(true)
            adapter = objectivesAdapter
            layoutManager = LinearLayoutManager(context)
            addOnItemTouchListener(
                RecyclerItemTouchListener(
                    context, this, object : RecyclerItemTouchListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            model.toggleObjective(position)
                            objectivesAdapter.objectiveSelected(view, position)
                        }

                        override fun onLongItemClick(view: View?, position: Int) {
                            model.toggleObjective(position)
                            view?.let { objectivesAdapter.objectiveSelected(view, position) }
                        }
                    })
            )

            // Awesome hack fix for returning shared element transition
            // https://stackoverflow.com/a/56378178/3593889
            postponeEnterTransition()
            viewTreeObserver
                .addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model: ObjectivesViewModel by viewModels()

        model.objectives.observe(viewLifecycleOwner, Observer { objectives ->
            objectives?.let {
                objectivesAdapter.submitList(it)
            }
        })
    }
}
