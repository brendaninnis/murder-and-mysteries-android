package ca.brendaninnis.murdermysteries.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import ca.brendaninnis.murdermysteries.models.Mystery
import com.google.android.material.textfield.TextInputEditText
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*

import ca.brendaninnis.murdermysteries.R

private const val MYSTERY_PARAM = "mystery_param"

class NewPartyFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var dateEditText: TextInputEditText
    private var mystery: Mystery? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mystery = it.getParcelable(MYSTERY_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_party, container, false)

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

    companion object {
        @JvmStatic
        fun newInstance(mystery: Mystery) =
                NewPartyFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(MYSTERY_PARAM, mystery )
                    }
                }
    }
}
