package ca.brendaninnis.murdermysteries.activities

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import ca.brendaninnis.murdermysteries.App
import ca.brendaninnis.murdermysteries.R
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavigationView = findViewById(R.id.nav_view)
        mDrawerLayout = findViewById(R.id.drawer_layout)

        // Observe night mode preference and set the delegate's local night mode
        (application as App).preferenceRepository
            .nightModeLive.observe(this, Observer { nightMode ->
                nightMode?.let { delegate.localNightMode = it }
            }
        )

        // Set toolbar as action bar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up the nav controller
        mAppBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.playFragment,
                R.id.mysteriesFragment,
                R.id.howToPlayFragment,
                R.id.awardFragment,
                R.id.helpFragment
            ), mDrawerLayout
        )

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration)

        // Set navigation view item selected listener
        mNavigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true

            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            when(menuItem.itemId) {
                R.id.nav_play -> {
                    mNavController.navigate(R.id.playFragment)
                }
                R.id.nav_mysteries -> {
                    mNavController.navigate(R.id.mysteriesFragment)
                }
                R.id.nav_how_to_play -> {
                    mNavController.navigate(R.id.howToPlayFragment)
                }
                R.id.nav_awards -> {
                    mNavController.navigate(R.id.awardFragment)
                }
                R.id.nav_help -> {
                    mNavController.navigate(R.id.helpFragment)
                }
            }

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = item.onNavDestinationSelected(
        Navigation.findNavController(this, R.id.nav_host_fragment))
            || super.onOptionsItemSelected(item)

    override fun onSupportNavigateUp(): Boolean {
        checkCurrentDestination()
        hideSoftKeyboard()
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            checkCurrentDestination()
            hideSoftKeyboard()
        }
    }

    private fun hideSoftKeyboard() {
        with(getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager) {
            if (isActive) hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            currentFocus?.clearFocus()
        }
    }

    private fun checkCurrentDestination() {
        when (mNavController.currentDestination) {
            mNavController.graph.findNode(R.id.playFragment) -> mNavigationView.menu.getItem(0).isChecked = true
            mNavController.graph.findNode(R.id.mysteriesFragment) -> mNavigationView.menu.getItem(1).isChecked = true
            mNavController.graph.findNode(R.id.howToPlayFragment) -> mNavigationView.menu.getItem(2).isChecked = true
            mNavController.graph.findNode(R.id.awardFragment) -> mNavigationView.menu.getItem(3).isChecked = true
            mNavController.graph.findNode(R.id.helpFragment) -> mNavigationView.menu.getItem(4).isChecked = true
        }
    }
}