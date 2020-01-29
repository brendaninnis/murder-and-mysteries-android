package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.fragments.MysteryDetailFragmentDirections.Companion.actionMysteryDetailFragmentToNewPartyFragment
import ca.brendaninnis.murdermysteries.viewmodels.MysteryDetailViewModel
import com.google.android.material.button.MaterialButton

class MysteryDetailFragment : Fragment() {

    private val args: MysteryDetailFragmentArgs by navArgs()
    private lateinit var splashImage: ImageView
    private lateinit var button: MaterialButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mystery_detail, container, false)

        splashImage = view.findViewById(R.id.mystery_splash_image)

        // Setup shared element transition
        val transitionInflater = TransitionInflater.from(context)
        transitionInflater.inflateTransition(android.R.transition.move).let {
            sharedElementEnterTransition = it
            sharedElementReturnTransition = it
        }
        enterTransition = transitionInflater.inflateTransition(android.R.transition.explode)

        // Scroll image in parallax
        with(view.findViewById<ScrollView>(R.id.mystery_scroll_view)) {
            viewTreeObserver.addOnScrollChangedListener {
                splashImage.translationY = this.scrollY.toFloat() / 2
            }
        }

        // Set button text based on the purchase state of this Mystery
        button = view.findViewById<MaterialButton>(R.id.mystery_button).apply {
            text = getString(R.string.host_party)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Update the UI with the Mystery
        val model: MysteryDetailViewModel by viewModels()

        subscribeToModel(model)
    }

    private fun subscribeToModel(model: MysteryDetailViewModel) {
        model.mystery.observe(viewLifecycleOwner, Observer { mystery ->
            mystery?.let {

                // Set the splash image
                with(splashImage) {
                    setImageDrawable(context.getDrawable(it.splashImageId))
                    transitionName = it.name
                }

                // FAB initiates purchase flow or host party
                button.setOnClickListener {
                    Log.w("MM", "Buy Mystery ${mystery.name}")
                    val action = actionMysteryDetailFragmentToNewPartyFragment(mysteryId = mystery.id)
                    findNavController().navigate(action)
                }
            }
        })
    }
}
