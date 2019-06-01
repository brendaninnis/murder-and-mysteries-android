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
import androidx.navigation.ui.NavigationUI
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
        NewPartyFragment.OnFragmentInteractionListener,
        FragmentManager.OnBackStackChangedListener {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mDrawerArrowDrawable: DrawerArrowDrawable
    private lateinit var mNavigationView: NavigationView
    private lateinit var mNavController: NavController
    private var mBackStackIsEmpty = true

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

        // Set DrawerArrowDrawable to animate between menu and back icons
        mDrawerArrowDrawable = DrawerArrowDrawable(this).also {
            it.color = ContextCompat.getColor(this, R.color.color_on_secondary)
        }
        toolbar.navigationIcon = mDrawerArrowDrawable

        supportFragmentManager.apply {
            addOnBackStackChangedListener(this@MainActivity)
        }

        // Set up the nav controller
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController, mDrawerLayout)
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

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, mDrawerLayout)
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onBackStackChanged() {
//        mBackStackIsEmpty = supportFragmentManager.backStackEntryCount == 0
//
//        // Animate the DrawerArrowDrawable
//        if (mBackStackIsEmpty) {
//            ObjectAnimator.ofFloat(mDrawerArrowDrawable, "progress", 0f).start()
//            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
//            title = getString(R.string.app_name)
//            title = mNavigationView.checkedItem?.title
//        } else {
//            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
//            ObjectAnimator.ofFloat(mDrawerArrowDrawable, "progress", 1f).start()
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (mBackStackIsEmpty) {
                    mDrawerLayout.openDrawer(GravityCompat.START)
                } else {
                    onBackPressed()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
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