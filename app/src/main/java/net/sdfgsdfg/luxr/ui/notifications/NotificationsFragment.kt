package net.sdfgsdfg.luxr.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import net.sdfgsdfg.luxr.R
import net.sdfgsdfg.luxr.base.ViewBindFragment
import net.sdfgsdfg.luxr.databinding.FragmentNotificationsBinding

@AndroidEntryPoint
class NotificationsFragment : ViewBindFragment<NotificationsViewModel, FragmentNotificationsBinding>() {
    override val vm: NotificationsViewModel by hiltNavGraphViewModels(R.id.navigation_notifications)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.text.observe(viewLifecycleOwner) {
            binding.textNotifications.text = it
        }

    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentNotificationsBinding.inflate(layoutInflater)
}