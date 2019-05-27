package ca.brendaninnis.murdermysteries.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.transition.*
import android.view.ViewGroup


class DetailsTransition : TransitionSet() {
    init {
        ordering = ORDERING_TOGETHER
        addTransition(Explode())
                .addTransition(ChangeBounds())
                .addTransition(ChangeTransform())
                .addTransition(ChangeImageTransform())
//                .addTransition(FullWidthButtonTransition())
    }
}

class FullWidthButtonTransition : Transition() {
    // Define a key for storing a property value in
    // TransitionValues.values with the syntax
    // package_name:transition_class:property_name to avoid collisions
    private val PROPNAME_BACKGROUND = "ca.brendaninnis.android.mysteryparties:FullWidthButtonTransition:background"
    private val PROPNAME_X = "ca.brendaninnis.android.mysteryparties:FullWidthButtonTransition:x"
    private val PROPNAME_Y = "ca.brendaninnis.android.mysteryparties:FullWidthButtonTransition:y"
    private val PROPNAME_WIDTH = "ca.brendaninnis.android.mysteryparties:FullWidthButtonTransition:width"
    private val PROPNAME_HEIGHT = "ca.brendaninnis.android.mysteryparties:FullWidthButtonTransition:height"

    override fun captureStartValues(transitionValues: TransitionValues) {
        // Call the convenience method captureValues
        captureValues(transitionValues)
    }

    // For the view in transitionValues.view, get the values you
    // want and put them in transitionValues.values
    private fun captureValues(transitionValues: TransitionValues) {
        // Get a reference to the view
        val view = transitionValues.view
        // Store its background property in the values map
        transitionValues.values[PROPNAME_BACKGROUND] = view.background
        transitionValues.values[PROPNAME_X] = view.x
        transitionValues.values[PROPNAME_Y] = view.y
        transitionValues.values[PROPNAME_WIDTH] = view.measuredWidth
        transitionValues.values[PROPNAME_HEIGHT] = view.measuredHeight
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun createAnimator(
            sceneRoot: ViewGroup,
            startValues: TransitionValues?,
            endValues: TransitionValues?
    ): Animator? {
        if (startValues == null || endValues == null) return null

        val animator = AnimatorSet()
//
//        val view = endValues.view
//        val startBackground = startValues.values[PROPNAME_BACKGROUND] as GradientDrawable
//        val endBackground = endValues.values[PROPNAME_BACKGROUND] as GradientDrawable
//        val cornerAnimator = ObjectAnimator.ofFloat(startBackground.cornerRadius, endBackground.cornerRadius).apply {
//            addUpdateListener {
//                (view.background as GradientDrawable).cornerRadius = animatedValue as Float
//            }
//        }
//
//        animator.playTogether(cornerAnimator)
        return animator
    }
}