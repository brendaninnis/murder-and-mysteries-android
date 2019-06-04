package ca.brendaninnis.murdermysteries.activities

import android.animation.ObjectAnimator
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.FragmentManager
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import android.transition.Fade
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import ca.brendaninnis.murdermysteries.App
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.fragments.*
import ca.brendaninnis.murdermysteries.models.Mystery
import ca.brendaninnis.murdermysteries.utils.DetailsTransition
import java.util.*

class MainActivity : AppCompatActivity(),
        MysteriesFragment.OnFragmentInteractionListener,
        PlayFragment.OnFragmentInteractionListener,
        MysteryDetailFragment.OnFragmentInteractionListener,
        NewPartyFragment.OnFragmentInteractionListener {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavigationView = findViewById(R.id.nav_view)
        mDrawerLayout = findViewById(R.id.drawer_layout)

        // New users should get a UUID
        with(getSharedPreferences("meta", Context.MODE_PRIVATE)) {
            if (getString("user_id", null) == null) {
                edit().putString("user_id", UUID.randomUUID().toString()).apply()
            }
        }

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

    override fun showMystery(mystery: Mystery, view: View) {
//        val fragment = MysteryDetailFragment.newInstance(mystery).apply {
//            sharedElementEnterTransition = DetailsTransition()
//            enterTransition = Fade()
//            sharedElementReturnTransition = DetailsTransition()
//        }
//
//        supportFragmentManager.beginTransaction().run {
//            addSharedElement(view.findViewById<ImageView>(R.id.mystery_splash_image), mystery.name)
//            replace(R.id.main_content, fragment)
//            addToBackStack(null)
//            commit()
//        }
//
//        title = mystery.name
    }

    override fun createParty(mystery: Mystery, splashImage: View, button: View) {
//        val fragment = NewPartyFragment.newInstance(mystery).apply {
//            sharedElementEnterTransition = DetailsTransition()
//            enterTransition = Fade()
//            sharedElementReturnTransition = DetailsTransition()
//        }
//
//        supportFragmentManager.beginTransaction().run {
////            addSharedElement(splashImage, mystery.name)
//            addSharedElement(button, "host_party_button")
//            replace(R.id.main_content, fragment)
//            addToBackStack(null)
//            commit()
//        }
//
//        title = getString(R.string.new_party)
    }

    override fun partySelected() {
    }
}