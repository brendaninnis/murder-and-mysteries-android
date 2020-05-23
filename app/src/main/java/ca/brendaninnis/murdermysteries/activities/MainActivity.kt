package ca.brendaninnis.murdermysteries.activities

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import ca.brendaninnis.murdermysteries.App
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.viewmodels.MainActivityViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

const val host = true

class MainActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private val mViewModel: MainActivityViewModel by viewModels()

    private val colorOnPrimary by lazy {
        ResourcesCompat.getColor(resources, R.color.color_on_primary, theme)
    }
    private val colorOnImageOverlay by lazy {
        ResourcesCompat.getColor(resources, R.color.color_on_image_overlay, theme)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observe night mode preference and set the delegate's local night mode
        (application as App).preferenceRepository
            .nightModeLive.observe(this, Observer { nightMode ->
                nightMode?.let { delegate.localNightMode = it }
            }
        )

        setSupportActionBar(toolbar)
        setupNavigation()
        subscribeToModel(mViewModel, nav_view)
    }

    override fun onSupportNavigateUp(): Boolean {
        hideSoftKeyboard()
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            hideSoftKeyboard()
        }
    }

    private fun setupNavigation() {
        mNavController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(mNavController)

        mAppBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mysteriesFragment,
                R.id.howToPlayFragment,
                R.id.awardFragment,
                R.id.helpFragment,
                R.id.invitationFragment,
                R.id.instructionsFragment,
                R.id.charactersFragment,
                R.id.myCharacterFragment,
                R.id.objectivesFragment,
                R.id.inventoryFragment
            ), drawer_layout
        )
        setupActionBarWithNavController(mNavController, mAppBarConfiguration)
    }

    private fun subscribeToModel(viewModel: MainActivityViewModel, navigationView: NavigationView) {
        viewModel.party.observe(this, Observer { party ->
            navigationView.menu.clear()

            when (party?.phase) {
                "scheduled" -> {
                    if (host) {
                        navigationView.inflateMenu(R.menu.host_scheduled_menu)
                        with(navigationView.getHeaderView(0)) {
                            background = ContextCompat.getDrawable(context, party.mystery.splashImageId)
                            with (findViewById<AppCompatTextView>(R.id.nav_header_title_textview)) {
                                text = party.mystery.name
                                setTextColor(colorOnImageOverlay)
                            }
                            findViewById<AppCompatTextView>(R.id.nav_header_title_textview).text = party.mystery.name
                            toggleNavigationView(this, true)
                        }
                    }
                }
                "before_murder" -> {
                    if (host) {
                        navigationView.inflateMenu(R.menu.before_murder_menu)
                        with(navigationView.getHeaderView(0)) {
                            background = ContextCompat.getDrawable(context, party.mystery.splashImageId)
                            with (findViewById<AppCompatTextView>(R.id.nav_header_title_textview)) {
                                text = party.mystery.name
                                setTextColor(colorOnImageOverlay)
                            }
                            findViewById<AppCompatTextView>(R.id.nav_header_title_textview).text = party.mystery.name
                            toggleNavigationView(this, true)
                        }
                    }
                }
                else -> {
                    navigationView.inflateMenu(R.menu.main_menu)
                    with(navigationView.getHeaderView(0)) {
                        setBackgroundColor(ContextCompat.getColor(context, R.color.color_primary))
                        with (findViewById<AppCompatTextView>(R.id.nav_header_title_textview)) {
                            text = getString(R.string.app_name)
                            setTextColor(colorOnPrimary)
                        }
                        toggleNavigationView(this, false)
                    }
                }
            }
        })
    }

    private fun toggleNavigationView(navHeaderView: View, party: Boolean) {
        navHeaderView.findViewById<View>(R.id.nav_header_overlay).visibility = if (party) View.VISIBLE else View.INVISIBLE
        with (navHeaderView.findViewById<AppCompatImageView>(R.id.nav_header_date_icon)) {
            visibility = if (party) View.VISIBLE else View.GONE
            setColorFilter(if (party) colorOnImageOverlay else colorOnPrimary)
        }
        with (navHeaderView.findViewById<AppCompatTextView>(R.id.nav_header_date_textview)) {
            visibility = if (party) View.VISIBLE else View.GONE
            setTextColor(if (party) colorOnImageOverlay else colorOnPrimary)
        }
        with (navHeaderView.findViewById<AppCompatImageView>(R.id.nav_header_address_icon)) {
            visibility = if (party) View.VISIBLE else View.GONE
            setColorFilter(if (party) colorOnImageOverlay else colorOnPrimary)
        }
        with (navHeaderView.findViewById<AppCompatTextView>(R.id.nav_header_address_textview)) {
            visibility = if (party) View.VISIBLE else View.GONE
            setTextColor(if (party) colorOnImageOverlay else colorOnPrimary)
        }
    }

    private fun hideSoftKeyboard() {
        with(getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager) {
            if (isActive) hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            currentFocus?.clearFocus()
        }
    }
}