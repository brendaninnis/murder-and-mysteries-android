package ca.brendaninnis.murdermysteries.extensions

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.DimenRes

fun Resources.getFloatValue(@DimenRes floatRes:Int):Float{
    val out = TypedValue()
    getValue(floatRes, out, true)
    return out.float
}