package kaan.template2023.extra.recyclerview

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.OnRebindCallback
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import kaan.template2023.extra.recyclerview.DataBoundViewHolder.Companion.create
import kaan.template2023.BR

abstract class BaseDataBoundAdapter<T> protected constructor() : RecyclerView.Adapter<DataBoundViewHolder>(), LifecycleOwner {
    private val mLifecycleRegistry = LifecycleRegistry(this)
    private var mRecyclerView: RecyclerView? = null

    /**
     * This is used to block items from updating themselves. RecyclerView wants to know when an
     * item is invalidated and it prefers to refresh it via onRebind. It also helps with performance
     * since data binding will not update views that are not changed.
     */
    private val mOnRebindCallback = object : OnRebindCallback<ViewDataBinding>() {
        override fun onPreBind(binding: ViewDataBinding?): Boolean {
            if (mRecyclerView == null || mRecyclerView!!.isComputingLayout) {
                return true
            }
            val childAdapterPosition = mRecyclerView!!.getChildAdapterPosition(binding!!.root)
            if (childAdapterPosition == RecyclerView.NO_POSITION) {
                return true
            }
            notifyItemChanged(childAdapterPosition, DB_PAYLOAD)
            return false
        }
    }

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val vh = create(parent, viewType)
        vh.binding.addOnRebindCallback(mOnRebindCallback)
        return vh
    }

    override fun onBindViewHolder(
        holder: DataBoundViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        // when a VH is rebound to the same item, we don't have to call the setters
        if (payloads.isEmpty() || hasNonDataBindingInvalidate(payloads)) {
            bindItem(holder, position, payloads)
        }
        if (holder.binding.lifecycleOwner == null) {
            holder.binding.lifecycleOwner = this
        }
        holder.binding.executePendingBindings()
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        throw IllegalArgumentException("just overridden to make final.")
    }

    /**
     * Override this method to handle binding your items into views
     *
     * @param holder The ViewHolder that has the binding instance
     * @param position The position of the item in the adapter
     * @param payloads The payloads that were passed into the onBind method
     */
        //TODO:  RecyclerView - 2 and RecyclerView - 3 additions from CEXP rabbit guy.
    @CallSuper
    protected fun bindItem(holder: DataBoundViewHolder, position: Int, payloads: List<Any>?) {

        holder.binding.setVariable(BR.item, getItem(position))
    }

    private fun hasNonDataBindingInvalidate(payloads: List<Any>): Boolean {
        for (payload in payloads) {
            if (payload !== DB_PAYLOAD) {
                return true
            }
        }
        return false
    }

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
        mLifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    @CallSuper
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = null
        mLifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun getItemViewType(position: Int): Int {
        return getItemLayoutId(position)
    }

    protected abstract fun getItem(position: Int): T

    @LayoutRes
    protected abstract fun getItemLayoutId(position: Int): Int
    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    companion object {
        private val DB_PAYLOAD = Any()
    }

    init {
        mLifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }
}
