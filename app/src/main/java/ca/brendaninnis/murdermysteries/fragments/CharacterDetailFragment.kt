package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.viewmodels.CharacterDetailViewModel
import ca.brendaninnis.murdermysteries.viewmodels.MainActivityViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CharacterDetailFragment : Fragment() {

    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var characterImage: AppCompatImageView
    private val activityModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)

        characterImage = view.findViewById(R.id.character_detail_image)

        // Setup shared element transition
        val transitionInflater = TransitionInflater.from(context)
        transitionInflater.inflateTransition(android.R.transition.move).let {
            sharedElementEnterTransition = it
            sharedElementReturnTransition = it
        }
        enterTransition = transitionInflater.inflateTransition(android.R.transition.explode)

        view.findViewById<MaterialButton>(R.id.character_detail_assign_button).setOnClickListener {
            activityModel.party.value?.players?.let { players ->
                val choices = Array(players.size) { int ->
                    players[int].name
                }

                MaterialAlertDialogBuilder(context)
                    .setTitle("Choose a player")
                    .setItems(choices) { dialog, which ->
                        Toast.makeText(context, "Selected ${choices[which]}", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Update the UI with the Mystery
        val model: CharacterDetailViewModel by viewModels{ CharacterDetailViewModel.CharacterDetailViewModelFactory(args.characterId) }

        subscribeToModel(model)
    }

    private fun subscribeToModel(model: CharacterDetailViewModel) {
        model.character.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                // Set the splash image
                with(characterImage) {
                    setImageDrawable(context.getDrawable(it.imageId))
                    transitionName = it.name
                }
            }
        })
    }

}
