package net.sdfgsdfg.luxr.ui.dashboard

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.sdfgsdfg.luxr.R
import net.sdfgsdfg.luxr.base.DataBindFragment
import net.sdfgsdfg.luxr.databinding.FragmentDashboardBinding
import net.sdfgsdfg.luxr.extra.*

/**
 * Temporary content set at the SpaceXLaunches
 */
@AndroidEntryPoint
class DashboardFragment : DataBindFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {
    override val vm: DashboardViewModel by hiltNavGraphViewModels(R.id.navigation_dashboard)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO:    baseDB takes care of these, remove.
//        binding.viewModel = vm
//        binding.lifecycleOwner = viewLifecycleOwner


        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.launchesList.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            vm.setPortraitOrientation(true)
        } else {
            binding.launchesList.layoutManager = GridLayoutManager(requireContext(), 2)

            vm.setPortraitOrientation(false)
        }

//        hideActionBar()
//        hideSystemUI()

        observers()
    }

    override fun onDetach() {
        super.onDetach()

//        showSystemUI()
//        showActionBar()
    }

    private fun observers() {
        vm.assetsFlow.observe(viewLifecycleOwner) {
            Log.d("XXX", "assets result -------------> [${it.javaClass}]---------> [${it}]")

            when (it) {
                is Result.Success -> {
                    binding.progress.gone()

                    vm.assets.value = it.successData()
                }
                is Result.Error -> {
                    it.errorType.printStackTrace()
                }
                Result.Loading -> binding.progress.visible()
                Result.NoData -> vm.assets.value = listOf()
            }
        }
    }
}