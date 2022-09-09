package kaan.template2023.extra.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.*

abstract class DataBoundListAdapter<T> protected constructor(items: LiveData<List<T>>, itemDiff: DiffUtil.ItemCallback<T>) : BaseDataBoundAdapter<T>() {
    private val mDiffer: AsyncListDiffer<T>

    /**
     * Submits a new list to be diffed, and displayed.
     *
     *
     * If a list is already being displayed, a diff will be computed on a background thread, which
     * will dispatch Adapter.notifyItem events on the main thread.
     *
     * @param list The new list to be displayed.
     */
    private fun submitList(list: List<T>?) {
        mDiffer.submitList(list)
    }

    override fun getItem(position: Int): T {
        return mDiffer.currentList[position]
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    init {
        mDiffer = AsyncListDiffer(
            AdapterListUpdateCallback(this),
            AsyncDifferConfig.Builder(itemDiff).build()
        )
        items.observe(this) { list -> submitList(list) }
    }
}

class DataBoundViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        @JvmStatic
        fun create(
            parent: ViewGroup,
            @LayoutRes layoutId: Int
        ): DataBoundViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                layoutId, parent, false
            )
            return DataBoundViewHolder(binding)
        }
    }
}

class NegativeDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = false
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = false
}
