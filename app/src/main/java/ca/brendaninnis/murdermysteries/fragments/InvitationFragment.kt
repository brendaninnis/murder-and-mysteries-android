package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ca.brendaninnis.murdermysteries.R


class InvitationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_invitation, container, false)

        setupSpacers(view,
            view.findViewById(R.id.invitation_overlay),
            view.findViewById(R.id.invitation_top_spacer),
            view.findViewById(R.id.invitation_bottom_spacer)
        )

        return view
    }

    private fun setupSpacers(view: View, overlay: View, topSpacer: View, bottomSpacer: View) {

        // Post delayed 500ms to let the layout "settle" -- This is a hack
        overlay.postDelayed({
            with (bottomSpacer) {
                val params = layoutParams
                val overlayHeight = overlay.measuredHeight
                var height = view.measuredHeight - overlayHeight
                if (height < 0) height = 0
                params.height = height
                layoutParams = params
            }
        }, 500)

        view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                view.viewTreeObserver.removeOnPreDrawListener(this)

                val metrics = view.context.resources.displayMetrics

                with (topSpacer) {
                    val params = layoutParams
                    params.height = view.measuredHeight - (184 * metrics.density).toInt()
                    layoutParams = params
                }

                return true
            }
        })
    }
}
