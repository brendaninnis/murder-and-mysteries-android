package ca.brendaninnis.murdermysteries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.extensions.getFloatValue
import ca.brendaninnis.murdermysteries.models.Objective
import ca.brendaninnis.murdermysteries.viewmodels.ObjectivesViewModel

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Objective>() {
    override fun areItemsTheSame(oldItem: Objective, newItem: Objective) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Objective, newItem: Objective) = oldItem.id == newItem.id && oldItem.complete == newItem.complete
}

class ObjectivesAdapter : ListAdapter<Objective, ObjectivesAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val cardView: View) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.objective_item, parent, false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val objective = getItem(position)

        with(holder.cardView) {
            findViewById<AppCompatTextView>(R.id.objective_item_title).setText(objective.title)
            findViewById<AppCompatTextView>(R.id.objective_item_detail).setText(objective.description)

            val highEmphasis = context.resources.getFloatValue(R.dimen.alpha_emphasis_high)
            val mediumEmphasis = context.resources.getFloatValue(R.dimen.alpha_emphasis_medium)

            if (objective.complete) {
                findViewById<AppCompatTextView>(R.id.objective_item_title).alpha = mediumEmphasis
                findViewById<AppCompatTextView>(R.id.objective_item_detail).alpha = mediumEmphasis
                findViewById<AppCompatTextView>(R.id.objective_item_complete_status).setText("Completed")
                findViewById<AppCompatTextView>(R.id.objective_item_complete_status).alpha = highEmphasis
                findViewById<AppCompatImageView>(R.id.objective_item_checkbox).setImageDrawable(
                    ContextCompat.getDrawable(context, R.drawable.ic_check_box_black_24dp)
                )
            } else {
                findViewById<AppCompatTextView>(R.id.objective_item_title).alpha = highEmphasis
                findViewById<AppCompatTextView>(R.id.objective_item_detail).alpha = highEmphasis
                findViewById<AppCompatTextView>(R.id.objective_item_complete_status).setText("Not completed")
                findViewById<AppCompatTextView>(R.id.objective_item_complete_status).alpha = mediumEmphasis
                findViewById<AppCompatImageView>(R.id.objective_item_checkbox).setImageDrawable(
                    ContextCompat.getDrawable(context, R.drawable.ic_check_box_outline_24dp)
                )
            }

        }
    }

    fun objectiveSelected(view: View, position: Int) {
        val objective = getItem(position)

        val highEmphasis = view.context.resources.getFloatValue(R.dimen.alpha_emphasis_high)
        val mediumEmphasis = view.context.resources.getFloatValue(R.dimen.alpha_emphasis_medium)

        if (objective.complete) {
            view.findViewById<AppCompatTextView>(R.id.objective_item_title).alpha = mediumEmphasis
            view.findViewById<AppCompatTextView>(R.id.objective_item_detail).alpha = mediumEmphasis
            view.findViewById<AppCompatTextView>(R.id.objective_item_complete_status).setText("Completed")
            view.findViewById<AppCompatTextView>(R.id.objective_item_complete_status).alpha = highEmphasis
            view.findViewById<AppCompatImageView>(R.id.objective_item_checkbox).setImageDrawable(
                ContextCompat.getDrawable(view.context, R.drawable.ic_check_box_black_24dp)
            )
        } else {
            view.findViewById<AppCompatTextView>(R.id.objective_item_title).alpha = highEmphasis
            view.findViewById<AppCompatTextView>(R.id.objective_item_detail).alpha = highEmphasis
            view.findViewById<AppCompatTextView>(R.id.objective_item_complete_status).setText("Not completed")
            view.findViewById<AppCompatTextView>(R.id.objective_item_complete_status).alpha = mediumEmphasis
            view.findViewById<AppCompatImageView>(R.id.objective_item_checkbox).setImageDrawable(
                ContextCompat.getDrawable(view.context, R.drawable.ic_check_box_outline_24dp)
            )
        }
    }
}