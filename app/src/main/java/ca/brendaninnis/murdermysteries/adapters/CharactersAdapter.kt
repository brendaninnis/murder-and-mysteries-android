package ca.brendaninnis.murdermysteries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.fragments.CharactersFragmentDirections
import ca.brendaninnis.murdermysteries.models.CharacterRole

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CharacterRole>() {
    override fun areItemsTheSame(oldItem: CharacterRole, newItem: CharacterRole) = oldItem.id === newItem.id
    override fun areContentsTheSame(oldItem: CharacterRole, newItem: CharacterRole) = oldItem.id == newItem.id
}

class CharactersAdapter : ListAdapter<CharacterRole, CharactersAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val cardView: View) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)

        with(holder.cardView) {
            with (findViewById<AppCompatImageView>(R.id.character_item_image)) {
                setImageDrawable(context.getDrawable(character.imageId))
                transitionName = character.name
            }
            findViewById<AppCompatTextView>(R.id.character_item_text).setText(character.name)
            findViewById<AppCompatTextView>(R.id.character_item_assignee).setText(
                if (character.assigneeName == null)
                    "Unassigned"
                else
                    character.assigneeName
            )
        }
    }

    fun characterSelected(view: View, position: Int) {
        val character = getItem(position)
        val imageView = view.findViewById<AppCompatImageView>(R.id.character_item_image)
        val extras = FragmentNavigatorExtras(imageView to character.name)
        val action = CharactersFragmentDirections.
            actionCharactersFragmentToCharacterDetailFragment(characterId = character.id, title = character.name)
        view.findNavController().navigate(action, extras)
    }
}