package ca.brendaninnis.murdermysteries.fragments

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.utils.DepthPageTransformer
import ca.brendaninnis.murdermysteries.viewmodels.EvidenceViewModel
import java.lang.Math.abs

class EvidenceFragment : Fragment() {

    val model: EvidenceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_evidence, container, false)

        activity?.let {
            with(view.findViewById<ViewPager2>(R.id.evidence_viewpager)) {
                offscreenPageLimit = 1
                val itemDecoration = HorizontalMarginItemDecoration(
                    context,
                    R.dimen.viewpager_current_item_horizontal_margin
                )
                addItemDecoration(itemDecoration)
                adapter = EvidencePagerAdapter(it)

                // Add a PageTransformer that translates the next and previous items horizontally
                // towards the center of the screen, which makes them visible
                val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
                val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
                val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
                val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                    page.translationX = -pageTranslationX * position
                    // Next line scales the item's height. You can remove it if you don't want this effect
                    page.scaleY = 1 - (0.25f * abs(position))
                    // If you want a fading effect uncomment the next line:
                    // page.alpha = 0.25f + (1 - abs(position))
                }
                setPageTransformer(pageTransformer)

            }
        }

        return view
    }

    /**
     * Adds margin to the left and right sides of the RecyclerView item.
     * Adapted from https://stackoverflow.com/a/27664023/4034572
     * @param horizontalMarginInDp the margin resource, in dp.
     */
    class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
        RecyclerView.ItemDecoration() {

        private val horizontalMarginInPx: Int =
            context.resources.getDimension(horizontalMarginInDp).toInt()

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }

    }

    private inner class EvidencePagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
        override fun getItemCount(): Int = if (model.evidence.value != null) model.evidence.value!!.size else 0
        override fun createFragment(position: Int): Fragment = EvidenceDetailFragment.newInstance(model.evidence.value?.get(position))
    }
}