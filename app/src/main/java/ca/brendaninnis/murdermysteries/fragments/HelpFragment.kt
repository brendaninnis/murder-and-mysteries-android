package ca.brendaninnis.murdermysteries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import ca.brendaninnis.murdermysteries.App

import ca.brendaninnis.murdermysteries.R
import com.google.android.material.switchmaterial.SwitchMaterial

class HelpFragment : Fragment() {

    private lateinit var notificationSwitch: SwitchMaterial
    private lateinit var darkModeSwitch: SwitchMaterial

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        notificationSwitch = view.findViewById(R.id.help_switch)
        darkModeSwitch = view.findViewById(R.id.dark_mode_switch)

        val preferenceRepository = (requireActivity().application as App).preferenceRepository

        preferenceRepository.allowNotificationsLive.observe(viewLifecycleOwner, Observer { allowNotifications ->
            allowNotifications?.let {
                notificationSwitch.isChecked = it
            }
        })

        notificationSwitch.setOnCheckedChangeListener { _, checked ->
            preferenceRepository.allowNotifications = checked
        }

        preferenceRepository.isDarkThemeLive.observe(viewLifecycleOwner, Observer { isDarkTheme ->
            isDarkTheme?.let { darkModeSwitch.isChecked = it }
        })

        darkModeSwitch.setOnCheckedChangeListener { _, checked ->
            preferenceRepository.isDarkTheme = checked
        }

        return view
    }
}
