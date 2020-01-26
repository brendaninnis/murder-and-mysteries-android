package ca.brendaninnis.murdermysteries.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import ca.brendaninnis.murdermysteries.models.Mystery
import com.google.android.material.textfield.TextInputEditText
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*

import ca.brendaninnis.murdermysteries.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_new_party.*

class NewPartyFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private val args: NewPartyFragmentArgs by navArgs()
    private lateinit var dateEditText: TextInputEditText
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

        with (view.findViewById<TextInputEditText>(R.id.new_party_mystery_edittext)) {
            showSoftInputOnFocus = false
            setText(mystery?.name)
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    findNavController().navigate(R.id.mysteriesFragment)
                }
            }
        }

        dateEditText = view.findViewById(R.id.new_party_date_edittext)
        dateEditText.showSoftInputOnFocus = false
        dateEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                fragmentManager?.let {
                    val now = Calendar.getInstance()
                    DatePickerDialog.newInstance(
                            this,
                            now.get(Calendar.YEAR), // Initial year selection
                            now.get(Calendar.MONTH), // Initial month selection
                            now.get(Calendar.DAY_OF_MONTH) // Initial day selection
                    ).apply {
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
        }

        return view
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

        fragmentManager?.let {
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
