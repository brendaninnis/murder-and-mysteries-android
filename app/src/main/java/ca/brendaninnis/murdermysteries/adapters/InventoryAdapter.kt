package ca.brendaninnis.murdermysteries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.InventoryItem

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<InventoryItem>() {
    override fun areItemsTheSame(oldItem: InventoryItem, newItem: InventoryItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: InventoryItem, newItem: InventoryItem) = oldItem.id == newItem.id
}

class InventoryAdapter : ListAdapter<InventoryItem, InventoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(val cardView: View) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.inventroy_item, parent, false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.cardView) {
            findViewById<AppCompatTextView>(R.id.inventory_item_title).setText(item.name)
            findViewById<AppCompatTextView>(R.id.inventory_item_detail).setText(item.description)
            findViewById<AppCompatImageView>(R.id.inventory_item_image).setImageResource(item.image)
        }
    }
}