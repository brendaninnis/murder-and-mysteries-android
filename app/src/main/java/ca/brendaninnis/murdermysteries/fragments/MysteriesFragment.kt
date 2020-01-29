package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.adapters.MysteriesAdapter
import ca.brendaninnis.murdermysteries.utils.RecyclerItemTouchListener
import ca.brendaninnis.murdermysteries.viewmodels.MysteriesViewModel

class MysteriesFragment : Fragment() {

    private var viewAdapter: MysteriesAdapter = MysteriesAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_mysteries, container, false)

        viewManager = LinearLayoutManager(context)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.mysteries_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addOnItemTouchListener(
                RecyclerItemTouchListener(
                    context, this, object : RecyclerItemTouchListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            viewAdapter.mysterySelected(view, position)
                        }

                        override fun onLongItemClick(view: View?, position: Int) {
                            view?.let { viewAdapter.mysterySelected(it, position) }
                        }
            }))

            // Awesome hack fix for returning shared element transition
            // https://stackoverflow.com/a/56378178/3593889
            postponeEnterTransition()
            viewTreeObserver
                .addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model: MysteriesViewModel by viewModels()

        model.mysteries.observe(viewLifecycleOwner, Observer { mysteries ->
            mysteries?.let { viewAdapter.submitList(it) }
        })
    }
}
