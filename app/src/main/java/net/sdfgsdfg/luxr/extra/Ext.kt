package net.sdfgsdfg.luxr.extra

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.LocationManager
import android.util.TypedValue
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.dip(dips: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, context.resources.displayMetrics).toInt()





// Mo general
fun Context.isLocationEnabled(): Boolean {
    val locationManager =
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    locationManager.let {
        if (!it.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
            !it.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        ) {
            return false
        }
    }
    return true
}

fun Application.getDrawableResAsBitmap(@DrawableRes drawableResId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableResId)
    val canvas = Canvas()
    if (drawable != null) {
        val bitmap: Bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }
    return null
}