package net.sdfgsdfg.luxr.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import net.sdfgsdfg.luxr.R
import net.sdfgsdfg.luxr.domain.spacex.SpacexRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val spacexRepository: SpacexRepository) : ViewModel() {
    private var portrait = false    //TODO: complete from cexp

    init {
        Log.d("XXX", "------ init ------------------")

        fetchLaunches()
    }


    /**
     * 3 adapter bindings for the launches list
     */
    val assetsFlow = spacexRepository.launches.asLiveData()

    val assets = MutableLiveData<List<ListItem>>()
    val assetsDiff: DiffUtil.ItemCallback<ListItem> = LaunchDiffUtil()
    val assetsLayoutProvider: (ListItem) -> Int = { item ->
        when (item) {
            is HeaderItem -> R.layout.view_group_header
            is LaunchItem -> if (portrait) R.layout.asset_item_portrait else R.layout.asset_item_landscape
        }
    }

    private fun fetchLaunches() {
        viewModelScope.launch {
            spacexRepository.getLaunches(::onLaunchClick)
        }
    }

    fun onFilterClicked() {
        viewModelScope.launch {
            spacexRepository.toggleSuccessfulLaunches()

//            _filterSuccessfulLaunches.value = !(filterSuccessfulLaunches.value ?: false)
        }
    }

    fun setPortraitOrientation(isPortrait: Boolean) {
        this.portrait = isPortrait
    }


    private fun onLaunchClick(launchItem: LaunchItem) {
//        _navigate.value = launchItem
    }
}


/**
 * DiffUtil feature enables list diff'ing and other processing on recyclerview, animating both the item and content changes;
 * also keeping the remaining UI between list updates.
 */
class LaunchDiffUtil : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) =
        oldItem is HeaderItem && newItem is HeaderItem && oldItem.text == newItem.text ||
                oldItem is LaunchItem && newItem is LaunchItem && oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) = false
}