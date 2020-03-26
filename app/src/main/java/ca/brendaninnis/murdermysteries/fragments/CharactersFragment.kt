package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.adapters.CharactersAdapter
import ca.brendaninnis.murdermysteries.models.CharacterRole
import ca.brendaninnis.murdermysteries.utils.RecyclerItemTouchListener

class CharactersFragment : Fragment() {

    private var charactersAdapter: CharactersAdapter = CharactersAdapter()
    private val characters = listOf(
        CharacterRole(
            0,
            "Countess Whatsername",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.person
        ),
        CharacterRole(
            1,
            "Prince Whatthefuck",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.person
        ),
        CharacterRole(
            2,
            "Captain of the Guard",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.person
        ),
        CharacterRole(
            3,
            "Baron Harkonen",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.person
        ),
        CharacterRole(
            4,
            "Wizard Harry",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.person
        ),
        CharacterRole(
            5,
            "Legolas My Eggolas",
            "The Lady Rosedell is loved and admired by the nobility and peasantry alike. Her beauty and grace are unmatched in all the kingdom. Her wedding to the prince will be an auspicious occation.",
            "Ms. Lockwood is an elegant noble wearing fine silks and jewlery. She ought to be the most well dressed person at the feast.",
            R.drawable.person
        )
    )

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

        charactersAdapter.submitList(characters)

        return view
    }

}
