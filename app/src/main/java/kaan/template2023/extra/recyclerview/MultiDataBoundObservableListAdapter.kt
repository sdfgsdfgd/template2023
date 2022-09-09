package kaan.template2023.extra.recyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MultiDataBoundListAdapter<T>(
    items: LiveData<List<T>>,
    private val itemLayoutProvider: (T) -> Int,
    itemDiff: DiffUtil.ItemCallback<T>
) : DataBoundListAdapter<T>(items, itemDiff) {

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int = itemLayoutProvider(getItem(position))
}

class MultiDataBoundObservableListAdapter<T>(
    items: ObservableList<T>,
    private val itemLayoutProvider: (T) -> Int,
    itemIdProvider: ((T) -> Long)? = null
) : DataBoundObservableListAdapter<T>(items, itemIdProvider) {

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int = itemLayoutProvider(getItem(position))
}

class SingleDataBoundObservableListAdapter<T>(
    items: ObservableList<T>,
    @LayoutRes private val layoutId: Int,
    itemIdProvider: ((T) -> Long)? = null
) : DataBoundObservableListAdapter<T>(items, itemIdProvider) {

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int = layoutId
}

class SingleDataBoundListAdapter<T>(
    items: LiveData<List<T>>,
    @LayoutRes private val itemLayout: Int,
    itemDiff: DiffUtil.ItemCallback<T>
) : DataBoundListAdapter<T>(items, itemDiff) {

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int = itemLayout
}

abstract class DataBoundObservableListAdapter<T>(
    private val items: ObservableList<T>,
    private val itemIdProvider: ((T) -> Long)? = null
) : BaseDataBoundAdapter<T>() {

    private val onListChangedCallback = object : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        override fun onChanged(sender: ObservableList<T>) {
            notifyDataSetChanged()
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            notifyItemRangeRemoved(positionStart, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            for (i in 0 until itemCount) {
                notifyItemMoved(fromPosition + i, toPosition + i)
            }
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            notifyItemRangeChanged(positionStart, itemCount)
        }
    }

    init {
        if (itemIdProvider != null) setHasStableIds(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        items.addOnListChangedCallback(onListChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        items.removeOnListChangedCallback(onListChangedCallback)
    }

    final override fun getItemCount(): Int = items.size

    final override fun getItem(position: Int): T = items[position]

    override fun getItemId(position: Int): Long =
        itemIdProvider?.invoke(getItem(position)) ?: super.getItemId(position)
}
