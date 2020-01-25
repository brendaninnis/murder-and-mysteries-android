package ca.brendaninnis.murdermysteries.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import ca.brendaninnis.murdermysteries.App
import ca.brendaninnis.murdermysteries.R

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
        NavigationUI.setupWithNavController(mNavigationView, mNavController);

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

    override fun onSupportNavigateUp() = NavigationUI.navigateUp(mNavController, mAppBarConfiguration)

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}