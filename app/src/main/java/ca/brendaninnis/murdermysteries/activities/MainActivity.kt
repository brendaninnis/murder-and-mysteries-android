package ca.brendaninnis.murdermysteries.activities

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import ca.brendaninnis.murdermysteries.App
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.viewmodels.MainActivityViewModel
import com.google.android.material.navigation.NavigationView

const val host = true

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private val mViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observe night mode preference and set the delegate's local night mode
        (application as App).preferenceRepository
            .nightModeLive.observe(this, Observer { nightMode ->
                nightMode?.let { delegate.localNightMode = it }
            }
        )

        // Set toolbar as action bar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up navigation
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        mNavigationView = findViewById<NavigationView>(R.id.nav_view).apply {
            setupWithNavController(mNavController)
        }
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mAppBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.playFragment,
                R.id.mysteriesFragment,
                R.id.howToPlayFragment,
                R.id.awardFragment,
                R.id.helpFragment
            ), mDrawerLayout
        )
        setupActionBarWithNavController(mNavController, mAppBarConfiguration)

        subscribeToModel(mViewModel, mNavigationView)
    }

    override fun onSupportNavigateUp(): Boolean {
        hideSoftKeyboard()
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            hideSoftKeyboard()
        }
    }

    private fun subscribeToModel(viewModel: MainActivityViewModel, navigationView: NavigationView) {
        viewModel.party.observe(this, Observer { party ->
            navigationView.menu.clear()

            if (party?.phase == "scheduled") {
                if (host) {
                    navigationView.inflateMenu(R.menu.host_scheduled_menu)
                    with(navigationView.getHeaderView(0)) {
                        background = ContextCompat.getDrawable(context, party.mystery.splashImageId)
                        findViewById<AppCompatTextView>(R.id.nav_header_title_textview).text = party.mystery.name
                        toggleNavigationView(this, true)
                    }
                }
            } else {
                navigationView.inflateMenu(R.menu.main_menu)
                with(navigationView.getHeaderView(0)) {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.color_primary))
                    findViewById<AppCompatTextView>(R.id.nav_header_title_textview).text = getString(R.string.app_name)
                    toggleNavigationView(this, false)
                }
            }
        })
    }

    private fun toggleNavigationView(navHeaderView: View, party: Boolean) {
        navHeaderView.findViewById<View>(R.id.nav_header_overlay).visibility = if (party) View.VISIBLE else View.INVISIBLE
        navHeaderView.findViewById<View>(R.id.nav_header_date_icon).visibility = if (party) View.VISIBLE else View.GONE
        navHeaderView.findViewById<View>(R.id.nav_header_date_textview).visibility = if (party) View.VISIBLE else View.GONE
        navHeaderView.findViewById<View>(R.id.nav_header_address_icon).visibility = if (party) View.VISIBLE else View.GONE
        navHeaderView.findViewById<View>(R.id.nav_header_address_textview).visibility = if (party) View.VISIBLE else View.GONE
    }

    private fun hideSoftKeyboard() {
        with(getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager) {
            if (isActive) hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            currentFocus?.clearFocus()
        }
    }
}