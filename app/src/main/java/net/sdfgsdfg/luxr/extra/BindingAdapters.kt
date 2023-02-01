package net.sdfgsdfg.luxr.extra

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import net.sdfgsdfg.luxr.R
import net.sdfgsdfg.luxr.extra.DateTimeUtils.toReadableDate
import net.sdfgsdfg.luxr.ui.dashboard.LaunchItem


@BindingAdapter("visible")
fun View.visible(value: Boolean) {
    isVisible = value
}

@BindingAdapter("imageUrl")
fun setImagePicasso(view: ImageView, url: String?) {
    url?.let {
        Picasso.get().load("$url").placeholder(R.drawable.ic_fortune_cat).into(view)
    }
}

@BindingAdapter("imageSuccess")
fun setImageSuccess(view: ImageView, success: Boolean?) {
    success?.let {
        view.setImageDrawable(
            if (success == true)
                AppCompatResources.getDrawable(view.context, R.drawable.ic_fortune_cat)
            else null
        )
    }
}

@BindingAdapter("text")
fun setText(view: TextView, text: String?) {
    view.text = text?.let {
        if (text.isNotEmpty())
            text
        else
            view.context.getString(R.string.launch_empty_description)
    } ?: view.context.getString(R.string.launch_no_description)
}

@BindingAdapter("successText")
fun setSuccessText(view: TextView, success: Boolean?) {
    view.text = success?.let {
        if (success)
            view.context.getString(R.string.launch_text_success)
        else
            view.context.getString(R.string.launch_text_fail)
    } ?: view.context.getString(R.string.launch_text_unknown)
}

@BindingAdapter("date")
fun setDate(view: TextView, launchItem: LaunchItem?) {
    val dateText = launchItem?.date?.toReadableDate() ?: view.context.getString(R.string.launch_text_date_unknown)
    val missionText = launchItem?.missionName.orEmpty()
    view.text = view.context.getString(R.string.date_and_mission_text, dateText, missionText)
}


/**====================================================================================
 *                                                                                    =
 *                                                                                    =
 *                                                                                    =
 *                                Y  O  L  O                                          =
 *                                                                                    =
 *                                                                                    =
 *                                                                                    =
 *                                                                                    =
 *                                                                                    =
 * =====================================================================================
 */

//@BindingAdapter("setCardTitle")
//fun setCardTitle(v: TextView, card: PaymentMethodItem?) {
//    v.text = card?.let {
//        cardTitle(v.context, it.cardDisplayTitle, it.cardNumberMasked)
//    } ?: v.context.getString(R.string.pay_details_selected_card_no_data)
//}
//
//fun cardTitle(context: Context, cardDisplayTitle: String, cardNumberMasked: String) =
//    context.resources.getString(
//        R.string.payment_details_card_title,
//        cardDisplayTitle,
//        cardNumberMasked
//    )
//
//@BindingAdapter("showCardExpiryIndicator")
//fun setExpiryIndicatorVisible(v: View, card: PaymentMethodItem?) {
//    card?.let {
//        v.visibility = it.isExpired().toVisibility()
//    }
//}
//
//@BindingAdapter("showSelectedIndicator")
//fun showSelectedIndicator(v: View, card: PaymentMethodItem?) {
//    card?.let {
//        v.visibility = it.selected.toVisibility()
//    }
//}
//
//@BindingAdapter("layoutMarginStart")
//fun setLayoutMarginStart(view: View, card: PaymentMethodItem?) {
//    card?.let {
//        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
//        layoutParams.marginStart = view.dip(if (it.isExpired()) 6F else 0F)
//        view.layoutParams = layoutParams
//    }
//}
//
//@BindingAdapter("setCardExpiry")
//fun setCardExpiry(v: TextView, card: PaymentMethodItem?) {
//    card?.let {
//        if (it.isExpired()) {
//            v.text = v.context.resources.getString(
//                R.string.payment_details_card_expired,
//                String.format("%02d", card.expiryMonth),
//                String.format("%02d", card.expiryYear % 100)
//            )
//            v.setTextColor(v.context.resources.getColor(R.color.red, null))
//        } else {
//            v.setTextColor(v.context.resources.getColor(R.color.Dark700, null))
//            v.text = v.context.resources.getString(
//                R.string.payment_details_card_expiry,
//                String.format("%02d", card.expiryMonth),
//                String.format("%02d", card.expiryYear % 100)
//            )
//        }
//    }
//}
//
//@BindingAdapter("expiredTextColor")
//fun setExpiredTextColor(v: TextView, card: PaymentMethodItem?) {
//    card?.let {
//        if (it.isExpired()) {
//            v.setTextColor(v.context.resources.getColor(R.color.Dark700, null))
//        } else {
//            v.setTextColor(v.context.resources.getColor(R.color.Dark, null))
//        }
//    }
//}
//
//@BindingAdapter("setExpiredContainer")
//fun setExpiredContainer(v: ConstraintLayout, card: PaymentMethodItem?) {
//    card?.let {
//        if (it.isExpired()) {
//            v.foreground = null
//        } else {
//            val outValue = TypedValue()
//            v.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
//            v.foreground = getDrawable(v.context, outValue.resourceId)
//        }
//    }
//}
//
//@BindingAdapter("setExpiredIconAlpha")
//fun setExpiredImageTransparency(v: ImageView, card: PaymentMethodItem?) {
//    card?.let {
//        if (it.isExpired()) {
//            v.alpha = 0.70F // 70% transparency on expired cards
//        } else {
//            v.alpha = 1F
//        }
//    }
//}
//
//@BindingAdapter("visibleIfTrue")
//fun showIfVisible(v: View, isVisible: Boolean) {
//    if (isVisible) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
//
//@BindingAdapter("showDefaultPaymentMethod")
//fun showIfDefaultPaymentMethod(v: View, card: PaymentMethodItem?) {
//    card?.let {
//        if (card.default)
//            v.visibility = VISIBLE
//        else
//            v.visibility = GONE
//    }
//}
//
//@BindingAdapter("showIfNonDefaultPaymentMethod")
//fun showIfNonDefaultPaymentMethod(v: View, card: PaymentMethodItem?) {
//    card?.let {
//        if (!card.default)
//            v.visibility = VISIBLE
//        else
//            v.visibility = GONE
//    }
//}
//
//@BindingAdapter("srcOpenIndicator")
//fun setOpenHoursIndicator(v: ImageView, openHours: String) {
//    if (openHours.lowercase(Locale.getDefault()).contains("closed"))
//        v.setImageResource(R.drawable.ic_store_closed)
//    else
//        v.setImageResource(R.drawable.ic_store_open)
//}
//
//@BindingAdapter("cardSchemeIcon")
//fun setCardSchemeIcon(v: ImageView, scheme: CardScheme) {
//    v.setImageResource(scheme.iconForScheme())
//}
//
//@BindingAdapter("cardSchemeIcon2")
//fun setCardSchemeIcon2(v: ImageView, card: PaymentMethodItem?) {
//    card?.let {
//        setCardSchemeIcon(v, it.cardScheme)
//    }
//}
//
//@BindingAdapter("visibleIfFalse")
//fun visibleIfFalse(v: View, isVisible: Boolean) {
//    if (!isVisible) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
//

//@BindingAdapter("visibleIfLoading")
//fun visibleIfLoading(v: View, result: Result<*>?) {
//    if (result is Result.Loading) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
//
//@BindingAdapter("invisibleIfLoading")
//fun invisibleIfLoading(v: View, result: Result<*>) {
//    if (result !is Result.Loading) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
//
//@BindingAdapter("visibleIfSuccess")
//fun visibleIfSuccess(v: View, result: Result<*>) {
//    if (result is Result.Success) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
//
//@BindingAdapter("visibleIfCards")
//fun setVisibleIfAnyCards(v: View, result: Result<PaymentMethodsResponse>?) {
//    if (result is Result.Success && result.data.paymentMethods?.any() == true) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
//
//@BindingAdapter("invisibleIfCardOrLoading")
//fun setInvisibleIfAnyCardsOrLoading(v: View, result: Result<PaymentMethodsResponse>?) {
//    if (result is Result.Loading || !(result is Result.Success && result.data.paymentMethods.isNullOrEmpty())) {
//        v.visibility = GONE
//    } else {
//        v.visibility = VISIBLE
//    }
//}
//
//// Store search
//@BindingAdapter("visibleIfPayAtPump")
//fun visibleIfPayAtPump(v: View, payAtPump: String?) {
//    if (payAtPump == "DISABLED" || payAtPump == null) {
//        v.visibility = GONE
//    } else {
//        v.visibility = VISIBLE
//    }
//}
//
//@BindingAdapter("visibleIfNoData")
//fun visibleIfNoData(v: View, result: Result<*>) {
//    if (result is Result.NoData) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
//
//@BindingAdapter("visibleIfError")
//fun visibleIfError(view: View, result: Result<*>) {
//    if (result is Result.Error) {
//        view.visibility = VISIBLE
//    } else {
//        view.visibility = GONE
//    }
//}
//
//@BindingAdapter("invisIfNoDataOrError", "locEnabled")
//fun invisibleIfNoDataOrError(v: View, result: Result<*>, locEnabled: Boolean) {
//    if (!locEnabled || result is Result.NoData || result is Result.Error) {
//        v.visibility = GONE
//    } else {
//        v.visibility = VISIBLE
//    }
//}
//
//// If haven't switched to map (toggled) and NoData exception was sent, don't display the map v link.
//@BindingAdapter("invisIfNoData", "toggled", requireAll = true)
//fun toggleMapLink(v: View, result: Result<*>, toggled: Boolean) {
//    if (result is Result.NoData && !toggled) {
//        v.visibility = GONE
//    } else {
//        v.visibility = VISIBLE
//    }
//}
//
//// If haven't switched to map (toggled) and NoData exception was sent, display the error v.
//@BindingAdapter(value = ["visibleIfNoData", "toggled"], requireAll = true)
//fun toggleErrorView(v: ScrollView, r: Result<*>?, toggled: Boolean?) {
//    if (r is Result.NoData && toggled == false) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
//
//// If successful stores result OR have switched to map mode for manual navigation, then we dont show errors, we show blank search list.
//@BindingAdapter("visibleIfSuccess", "visibleIfTrue", requireAll = true)
//fun showStores(v: ViewGroup, r: Result<*>?, toggled: Boolean) {
//    if (r is Result.Success || toggled) {
//        v.visibility = VISIBLE
//    } else {
//        v.visibility = GONE
//    }
//}
