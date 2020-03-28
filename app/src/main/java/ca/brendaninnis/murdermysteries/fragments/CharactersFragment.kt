package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.adapters.CharactersAdapter
import ca.brendaninnis.murdermysteries.models.CharacterRole
import ca.brendaninnis.murdermysteries.utils.RecyclerItemTouchListener
import ca.brendaninnis.murdermysteries.viewmodels.MainActivityViewModel

class CharactersFragment : Fragment() {

    private var charactersAdapter: CharactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_characters, container, false)

        with (view.findViewById<RecyclerView>(R.id.characters_recycler)) {
            setHasFixedSize(true)
            adapter = charactersAdapter
            layoutManager = GridLayoutManager(context, 2)
            addOnItemTouchListener(
                RecyclerItemTouchListener(
                    context, this, object : RecyclerItemTouchListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            charactersAdapter.characterSelected(view, position)
                        }

                        override fun onLongItemClick(view: View?, position: Int) {
                            view?.let { charactersAdapter.characterSelected(it, position) }
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

        val model: MainActivityViewModel by activityViewModels()
        observeActivityViewModel(model)
    }

    private fun observeActivityViewModel(model: MainActivityViewModel) {
        model.party.observe(viewLifecycleOwner, Observer { party ->
            charactersAdapter.submitList(party?.mystery?.characters)
        })
    }

}
