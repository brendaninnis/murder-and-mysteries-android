package ca.brendaninnis.murdermysteries.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import ca.brendaninnis.murdermysteries.models.Mystery
import com.google.android.material.textfield.TextInputEditText
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*

import ca.brendaninnis.murdermysteries.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_new_party.*

class NewPartyFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private val args: NewPartyFragmentArgs by navArgs()
    private lateinit var mysteryEditText: TextInputEditText
    private lateinit var nameEditText: TextInputEditText
    private lateinit var dateEditText: TextInputEditText
    private lateinit var placeEditText: TextInputEditText
    private var mystery: Mystery? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_party, container, false)

        if (args.mysteryId >= 0) {
            mystery = listOf(
                Mystery(0, "Murder and Dragons", 8, 20, R.drawable.murder_and_dragons),
                Mystery(1, "Avada Kedavra", 6, 16, R.drawable.avadakedavra),
                Mystery(2, "The Games Night Murder", 10, 24, R.drawable.gamesnight)
            )[args.mysteryId]
        }

        view.findViewById<TextInputEditText>(R.id.new_party_mystery_edittext).setText(mystery?.name)

        mysteryEditText = view.findViewById(R.id.new_party_mystery_edittext)
        nameEditText = view.findViewById(R.id.new_party_name_edittext)
        dateEditText = view.findViewById(R.id.new_party_date_edittext)
        placeEditText = view.findViewById(R.id.new_party_place_edittext)

        view.findViewById<View>(R.id.new_party_date_target).setOnClickListener {
            dateTapped()
        }

        view.findViewById<View>(R.id.new_party_mystery_target).setOnClickListener {
            mysteryTapped()
        }

        view.findViewById<MaterialButton>(R.id.new_party_submit_button).setOnClickListener {
            createParty()
        }

        return view
    }

    private fun mysteryTapped() {
        findNavController().navigate(R.id.mysteriesFragment)
    }

    private fun dateTapped() {
        activity?.supportFragmentManager?.let {
            val now = Calendar.getInstance()
            DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Initial day selection
            ).apply {
                minDate = now
                setOkColor("#eeeeee") // @color/textPrimary
                setCancelColor("#eeeeee")
                version = DatePickerDialog.Version.VERSION_2
                isThemeDark = true
                setOnCancelListener {
                    dateEditText.clearFocus()
                }
                show(it, "Datepickerdialog")
            }
        }
    }

    private fun createParty() {
        if (validate()) {
            // Create party
        }
    }

    private fun validate(): Boolean {
        var result = true
        if (mysteryEditText.text == null || mysteryEditText.text.toString() == "") {
            mysteryEditText.error = "Pick a mystery for your party"
            result = false
        }
        if (nameEditText.text == null || nameEditText.text.toString() == "") {
            nameEditText.error = "Pick a name for yourself"
            result = false
        }
        if (dateEditText.text == null || dateEditText.text.toString() == "") {
            dateEditText.error = "Pick a date for your party"
            result = false
        }
        if (placeEditText.text == null || placeEditText.text.toString() == "") {
            placeEditText.error = "Pick a place for your party"
            result = false
        }
        return result
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePickerDialog, hourOfDay: Int, minute: Int, second: Int) {
        val hourOfDayTwelve = if (hourOfDay > 12) hourOfDay - 12 else if (hourOfDay == 0) 12 else hourOfDay
        val minutes = "$minute".padStart(2, '0')
        val period = if (hourOfDay > 12) "p.m." else "a.m."

        with(dateEditText) {
            clearFocus()
            setText(text.toString() + "$hourOfDayTwelve:$minutes $period")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePickerDialog, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val adjustedMonth = monthOfYear + 1
        dateEditText.setText("$dayOfMonth/$adjustedMonth/$year ")

        activity?.supportFragmentManager?.let {
            val now = Calendar.getInstance()
            TimePickerDialog.newInstance(
                    this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    false
            ).apply {
                setOkColor("#eeeeee") // @color/textPrimary
                setCancelColor("#eeeeee")
                version = TimePickerDialog.Version.VERSION_2;
                isThemeDark = true
                setOnCancelListener {
                    dateEditText.setText("")
                    dateEditText.clearFocus()
                }
                show(it, "Timepickerdialog")
            }
        }
    }
}
