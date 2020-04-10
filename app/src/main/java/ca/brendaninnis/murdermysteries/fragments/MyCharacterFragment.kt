package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.viewmodels.MyCharacterViewModel

class MyCharacterFragment : Fragment() {

    val args: MyCharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_character, container, false)

        val splashImage = view.findViewById<AppCompatImageView>(R.id.my_character_image)

        // Scroll image in parallax
        with(view.findViewById<ScrollView>(R.id.my_character_scrollview)) {
            viewTreeObserver.addOnScrollChangedListener {
                splashImage.translationY = this.scrollY.toFloat() / 2
            }
        }

        val noCharacterLayout = view.findViewById<LinearLayout>(R.id.my_character_no_character_layout)
        val model : MyCharacterViewModel by viewModels{ MyCharacterViewModel.MyCharacterViewModelFactory(args.characterId) }

        subscribeToModel(model, noCharacterLayout)

        return view
    }

    private fun subscribeToModel(model: MyCharacterViewModel, noCharacterLayout: LinearLayout) {
        model.character.observe(viewLifecycleOwner, Observer {
            noCharacterLayout.visibility = if (it == null) View.VISIBLE else View.GONE
            it?.let { character ->
                Log.d("MM", "Character ${character.name}")
            }
        })
    }

}
