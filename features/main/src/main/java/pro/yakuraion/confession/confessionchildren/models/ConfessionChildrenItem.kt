package pro.yakuraion.confession.confessionchildren.models

import androidx.annotation.StringRes
import pro.yakuraion.confession.commonui.R

enum class ConfessionChildrenItem(@StringRes val titleRes: Int) {
    ABOUT(R.string.confession_children_about),
    ORDER(R.string.confession_children_order),
    FIRST_CONFESSION(R.string.confession_children_first_confession),
    SINS(R.string.confession_children_sins),
    PRAYING(R.string.confession_children_praying),
    PARADS(R.string.confession_children_parads),
}
