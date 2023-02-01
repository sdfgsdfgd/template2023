package net.sdfgsdfg.luxr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.valueIterator
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import net.sdfgsdfg.luxr.databinding.ActivityMainBinding
import net.sdfgsdfg.luxr.extra.visible

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val noBottomBarFragmentList = listOf<Int>(
//        R.id.navigation_dashboard
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNav()
    }

    private fun setupNav() {
        navController = findNavController(R.id.nav_host_fragment_activity_main)

        val navView: BottomNavigationView = binding.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_1, R.id.navigation_2, R.id.navigation_3 //xx  id of the  top nav graphs
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
//            updateToolbarTitleForDestination(destination, navGraphIds)
            binding.navView.visible(!noBottomBarFragmentList.contains(destination.id))
        }
    }

    /**
     *  xx: This is used just for navigation from different places, better way to do this?
     *
     *  ps: from cexp
     */
    private fun selectTab() {
//        sharedViewModel.onBottomTabChangeRequested.observe(
//            this
//        ) { tabId: Int? ->
//            when (tabId) {
//                R.id.pay -> binding.bottomNav.selectedItemId = R.id.pay
//                R.id.more -> binding.bottomNav.selectedItemId = R.id.more
//                R.id.offers -> binding.bottomNav.selectedItemId = R.id.offers
//            }
//        }
    }

    /**
     * Pop up to starting destination, on tab reselection.
     *
     *
     * ps: from cexp
     */
    private fun tabReselectionParentConfig() {
        binding.navView.setOnItemReselectedListener { selectedTab ->
            navController.graph.nodes.valueIterator().asSequence().find { it ->
                it.id == selectedTab.itemId
            }?.id?.let {
                navController.popBackStack((navController.graph.findNode(it) as NavGraph).startDestinationId, false)
            }
        }
    }
}