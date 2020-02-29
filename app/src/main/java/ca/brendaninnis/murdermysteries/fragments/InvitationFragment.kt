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
        
        val overlay = view.findViewById<View>(R.id.invitation_overlay)
        overlay.postDelayed({
            with (view.findViewById<View>(R.id.invitation_bottom_spacer)) {
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

                with (view.findViewById<View>(R.id.invitation_top_spacer)) {
                    val params = layoutParams
                    params.height = view.measuredHeight - (184 * metrics.density).toInt()
                    layoutParams = params
                }

                return true
            }
        })

        return view
    }
}
