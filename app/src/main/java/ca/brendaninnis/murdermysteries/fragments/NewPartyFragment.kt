package ca.brendaninnis.murdermysteries.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

import ca.brendaninnis.murdermysteries.models.Mystery
import com.google.android.material.textfield.TextInputEditText
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*

import ca.brendaninnis.murdermysteries.R

private const val MYSTERY_PARAM = "mystery_param"

class NewPartyFragment : Fragment() {

//    private lateinit var splashImage: ImageView
//    private lateinit var dateEditText: TextInputEditText
//    private var mystery: Mystery? = null
//    private var listener: OnFragmentInteractionListener? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            mystery = it.getParcelable<Mystery>(MYSTERY_PARAM)
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_new_party, container, false)
//
//        splashImage = view.findViewById(R.id.mystery_splash_image)
//        with(splashImage) {
//            mystery?.let {
//                setImageDrawable(context.getDrawable(it.splashImageId))
//                transitionName = it.name
//            }
//        }
//
//        dateEditText = view.findViewById(R.id.new_party_date_edittext)
//        dateEditText.showSoftInputOnFocus = false
//        dateEditText.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                fragmentManager?.let {
//                    val now = Calendar.getInstance()
//                    DatePickerDialog.newInstance(
//                            this,
//                            now.get(Calendar.YEAR), // Initial year selection
//                            now.get(Calendar.MONTH), // Initial month selection
//                            now.get(Calendar.DAY_OF_MONTH) // Initial day selection
//                    ).apply {
//                        setOkColor("#eeeeee") // @color/textPrimary
//                        setCancelColor("#eeeeee")
//                        version = DatePickerDialog.Version.VERSION_2
//                        isThemeDark = true
//                        show(it, "Datepickerdialog")
//                    }
//                }
//            }
//        }
//
//        return view
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException("$context must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
//
//    @SuppressLint("SetTextI18n")
//    override fun onTimeSet(view: TimePickerDialog, hourOfDay: Int, minute: Int, second: Int) {
//        val hourOfDayTwelve = if (hourOfDay > 12) hourOfDay - 12 else if (hourOfDay == 0) 12 else hourOfDay
//        val minutes = "$minute".padStart(2, '0')
//        val period = if (hourOfDay > 12) "p.m." else "a.m."
//
//        with(dateEditText) {
//            setText(text.toString() + "$hourOfDayTwelve:$minutes $period")
//            clearFocus()
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    override fun onDateSet(view: DatePickerDialog, year: Int, monthOfYear: Int, dayOfMonth: Int) {
//        val adjustedMonth = monthOfYear + 1
//        dateEditText.setText("$dayOfMonth/$adjustedMonth/$year ")
//
//        fragmentManager?.let {
//            val now = Calendar.getInstance()
//            TimePickerDialog.newInstance(
//                    this,
//                    now.get(Calendar.HOUR_OF_DAY),
//                    now.get(Calendar.MINUTE),
//                    false
//            ).apply {
//                setOkColor("#eeeeee") // @color/textPrimary
//                setCancelColor("#eeeeee")
//                version = TimePickerDialog.Version.VERSION_2;
//                isThemeDark = true
//                show(it, "Timepickerdialog")
//            }
//        }
//    }

    interface OnFragmentInteractionListener {
    }

    companion object {
        @JvmStatic
        fun newInstance(mystery: Mystery) =
                NewPartyFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(MYSTERY_PARAM, mystery)
                    }
                }
    }
}
