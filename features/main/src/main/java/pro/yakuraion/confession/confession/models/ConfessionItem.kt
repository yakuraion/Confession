package pro.yakuraion.confession.confession.models

import androidx.annotation.StringRes
import pro.yakuraion.confession.commonui.R

enum class ConfessionItem(@StringRes val titleRes: Int) {
    ABOUT(R.string.confession_about),
    ORDER(R.string.confession_order),
    SINS(R.string.confession_sins),
    PRAYING(R.string.confession_praying),
    SINS_FOR_TEENAGERS(R.string.confession_sins_for_teenagers),
    EVERYDAY_SINS(R.string.confession_everyday_sins),
}
