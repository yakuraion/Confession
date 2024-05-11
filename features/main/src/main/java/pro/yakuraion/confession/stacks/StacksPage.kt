package pro.yakuraion.confession.stacks

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pro.yakuraion.confession.commonui.R

enum class StacksPage(
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int,
) {

    HOME(
        R.drawable.ic_home_page,
        R.string.stacks_navigation_label_home,
    ),
    TOPICS(
        R.drawable.ic_holy_bible,
        R.string.stacks_navigation_label_topics,
    ),
    CONFESSION(
        R.drawable.ic_heart_cross,
        R.string.stacks_navigation_label_confession,
    ),
    FOR_CHILDREN(
        R.drawable.ic_baby_face,
        R.string.stacks_navigation_label_for_children,
    ),
}
