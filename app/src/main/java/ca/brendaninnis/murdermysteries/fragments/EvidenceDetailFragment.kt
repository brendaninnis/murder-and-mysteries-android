package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.Evidence

private const val ARG_EVIDENCE = "evidence"

class EvidenceDetailFragment : Fragment() {
    private var evidence: Evidence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            evidence = it.getParcelable(ARG_EVIDENCE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_evidence_detail, container, false)

        evidence?.let {
            view.findViewById<AppCompatTextView>(R.id.evidence_detail_title).setText(it.name)
            view.findViewById<AppCompatTextView>(R.id.evidence_detail_description).setText(it.description)
            view.findViewById<AppCompatImageView>(R.id.evidence_detail_image).setImageResource(it.image)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(evidence: Evidence?) =
            EvidenceDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_EVIDENCE, evidence)
                }
            }
    }
}