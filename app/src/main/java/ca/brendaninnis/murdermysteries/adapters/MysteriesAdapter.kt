package ca.brendaninnis.murdermysteries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.fragments.MysteriesFragmentDirections
import ca.brendaninnis.murdermysteries.models.Mystery

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Mystery>() {
    override fun areItemsTheSame(oldItem: Mystery, newItem: Mystery) = oldItem.name === newItem.name
    override fun areContentsTheSame(oldItem: Mystery, newItem: Mystery) = oldItem.name == newItem.name
}

class MysteriesAdapter : ListAdapter<Mystery, MysteriesAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val cardView: View) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mystery_item, parent, false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mystery = getItem(position)

        with(holder.cardView) {
            with(findViewById<ImageView>(R.id.mystery_splash_image)) {
                setImageDrawable(context.getDrawable(mystery.splashImageId))
                transitionName = mystery.name
            }
            findViewById<TextView>(R.id.mystery_title).text = mystery.name
            findViewById<TextView>(R.id.mystery_players).text = context.getString(
                R.string.mystery_players,
                mystery.minimumPlayers,
                mystery.maximumPlayers
            )
        }
    }

    fun mysterySelected(view: View, position: Int) {
        val mystery = getItem(position)
        val imageView = view.findViewById<ImageView>(R.id.mystery_splash_image)
        val extras = FragmentNavigatorExtras(imageView to mystery.name)
        val action = MysteriesFragmentDirections.
            actionMysteriesFragmentToMysteryDetailFragment(mysteryId = mystery.id, title = mystery.name)
        view.findNavController().navigate(action, extras)
    }
}