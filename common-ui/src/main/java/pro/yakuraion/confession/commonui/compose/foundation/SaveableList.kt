package pro.yakuraion.confession.commonui.compose.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

@Composable
fun <T : Any> rememberSaveableMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveableMutableStateListOf(
        elements = elements,
        toBundleValue = { it },
        fromBundleValue = { it },
    )
}

@Composable
fun <T, BundleType : Any> rememberSaveableMutableStateListOf(
    toBundleValue: (T) -> BundleType,
    fromBundleValue: (BundleType) -> T,
    vararg elements: T,
): SnapshotStateList<T> {
    return rememberSaveable(
        saver = listSaver(
            save = { stateList ->
                val bundledList = stateList.map(toBundleValue)
                if (bundledList.isNotEmpty()) {
                    val first = bundledList.first()
                    if (!canBeSaved(first)) {
                        error("${first::class} cannot be saved. By default only types which can be stored in the Bundle class can be saved.")
                    }
                }
                bundledList
            },
            restore = { it.map(fromBundleValue).toMutableStateList() }
        )
    ) {
        mutableStateListOf(*elements)
    }
}
