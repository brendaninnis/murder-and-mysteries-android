package ca.brendaninnis.murdermysteries.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.Mystery
import com.google.android.material.button.MaterialButton

private const val MYSTERY_PARAM = "mystery_param"

class MysteryDetailFragment : Fragment() {

    private lateinit var splashImage: ImageView
    private var mystery: Mystery? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mystery = it.getParcelable<Mystery>(MYSTERY_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mystery_detail, container, false)

        splashImage = view.findViewById<ImageView>(R.id.mystery_splash_image)
        with(splashImage) {
            mystery?.let {
                setImageDrawable(context.getDrawable(it.splashImageId))
                transitionName = it.name
            }
        }

        with(view.findViewById<ScrollView>(R.id.mystery_scroll_view)) {
            viewTreeObserver.addOnScrollChangedListener {
                splashImage.translationY = this.scrollY.toFloat() / 2
            }
        }

        with(view.findViewById<MaterialButton>(R.id.mystery_button)) {
            text = getString(R.string.host_party)
            setOnClickListener {
                mystery?.let {
                    listener?.createParty(it, splashImage, findViewById(R.id.mystery_button))
                }
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun createParty(mystery: Mystery, splashImage: View, button: View)
    }

    companion object {
        @JvmStatic
        fun newInstance(mystery: Mystery) =
                MysteryDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(MYSTERY_PARAM, mystery)
                    }
                }
    }
}
