package kaan.template2023.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kaan.template2023.BR

abstract class DataBindFragment<VB : ViewDataBinding>(private val layout: Int) : Fragment() {
    protected abstract val vm: ViewModel
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View? =
        DataBindingUtil.inflate<VB>(i, layout, c, false).apply {
            _binding = this

            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, vm)
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// xx Express BaseFragment  -  ( for Data Binding )
//abstract class BaseFragment<T : ViewDataBinding>(private val layoutId: Int) : Fragment() {
//
//    protected lateinit var binding: T
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
//        return binding.root
//    }
//}
