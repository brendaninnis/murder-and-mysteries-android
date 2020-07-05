package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.viewmodels.AccusationViewModel
import ca.brendaninnis.murdermysteries.viewmodels.MainActivityViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AccusationFragment : Fragment() {

    val model: AccusationViewModel by viewModels()
    private val activityModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_accusation, container, false)

        view.findViewById<MaterialCardView>(R.id.accusation_character_card).setOnClickListener {
            model.characters.value?.let { characters ->
                val choices = Array(characters.size) { int ->
                    characters[int].name
                }

                MaterialAlertDialogBuilder(context)
                    .setTitle("Choose a character")
                    .setItems(choices) { dialog, which ->
                        Toast.makeText(context, "Selected ${choices[which]}", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        view.findViewById<MaterialCardView>(R.id.accusation_best_dressed_selection).setOnClickListener {
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

        view.findViewById<MaterialCardView>(R.id.accusation_best_acting_selection).setOnClickListener {
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

        view.findViewById<MaterialCardView>(R.id.accusation_best_fun_selection).setOnClickListener {
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

        val confirmButton = view.findViewById<MaterialButton>(R.id.accusation_character_confirm)
        val cancelButton = view.findViewById<MaterialButton>(R.id.accusation_character_cancel)
        confirmButton.setOnClickListener {
            confirmButton.setText("Confirmed")
            confirmButton.isEnabled = false
            cancelButton.visibility = View.GONE
        }

        val noAccused = view.findViewById<AppCompatTextView>(R.id.accusation_no_character)
        cancelButton.setOnClickListener {
            noAccused.visibility = View.VISIBLE
        }

        observeViewModel(
            model,
            noAccused,
            view.findViewById(R.id.accusation_character_image),
            view.findViewById(R.id.accusation_character_name),
            view.findViewById(R.id.accusation_character_title),
            view.findViewById(R.id.accusation_best_dressed_text),
            view.findViewById(R.id.accusation_best_acting_text),
            view.findViewById(R.id.accusation_best_fun_text)
        )

        return view
    }

    fun observeViewModel(
        model: AccusationViewModel,
        noAccused: AppCompatTextView,
        accusedImage: AppCompatImageView,
        accusedName: AppCompatTextView,
        accusedTitle: AppCompatTextView,
        bestDressed: AppCompatTextView,
        bestActing: AppCompatTextView,
        bestFun: AppCompatTextView) {

        model.accused.observe(viewLifecycleOwner, Observer {
            noAccused.visibility = if (it == null) View.VISIBLE else View.GONE

            it?.let {
                accusedImage.setImageResource(it.imageId)
                accusedName.setText(it.name)
                accusedTitle.setText(it.description)
            }
        })

        model.bestDressed.observe(viewLifecycleOwner, Observer {
            bestDressed.setText("Tap to select a character")
            it?.let {
                bestDressed.setText(it.name)
            }
        })

        model.bestActing.observe(viewLifecycleOwner, Observer {
            bestActing.setText("Tap to select a character")
            it?.let {
                bestActing.setText(it.name)
            }
        })

        model.bestFun.observe(viewLifecycleOwner, Observer {
            bestFun.setText("Tap to select a character")
            it?.let {
                bestFun.setText(it.name)
            }
        })
    }
}