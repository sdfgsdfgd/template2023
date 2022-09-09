package kaan.template2023.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class ViewBindFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {
    protected abstract val vm: VM

    //    protected lateinit var binding: VB        //xx switch to shorter version instead, what if it leaks ? they're always garbage collected - right ? Confirm this.
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding(inflater, container)

        return binding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}


//
//typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
//
//abstract class ViewBindFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
//    private var _binding: VB? = null
//    val binding get() = _binding!!
//    abstract val vm: ViewModel
//
//    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View? {
//        _binding = inflate.invoke(i, c, false)
//
//        return binding.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}




