package pro.yakuraion.parish.commonui.compose.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap

@Composable
inline fun <reified V : Any> rememberSaveableMutableStateMapOf(
    vararg elements: Pair<String, V>,
): SnapshotStateMap<String, V> {
    return rememberSaveableMutableStateMapOf(
        keyToString = { it },
        keyFromString = { it },
        valueToBundleValue = { it },
        valueFromBundleValue = { it },
        elements = elements
    )
}

@Composable
inline fun <K, V, reified ValueBundleType : Any> rememberSaveableMutableStateMapOf(
    crossinline keyToString: (K) -> String,
    crossinline keyFromString: (String) -> K,
    crossinline valueToBundleValue: (V) -> ValueBundleType,
    crossinline valueFromBundleValue: (ValueBundleType) -> V,
    vararg elements: Pair<K, V>,
): SnapshotStateMap<K, V> {
    return rememberSaveable(
        saver = mapSaver(
            save = { stateMap ->
                val bundledMap = stateMap
                    .map { keyToString(it.key) to valueToBundleValue(it.value) }
                    .toMap()
                if (bundledMap.isNotEmpty()) {
                    val first = bundledMap.values.first()
                    if (!canBeSaved(first)) {
                        error("${first::class} cannot be saved. By default only types which can be stored in the Bundle class can be saved.")
                    }
                }
                bundledMap
            },
            restore = { bundledMap ->
                bundledMap
                    .map { keyFromString(it.key) to valueFromBundleValue(it.value as ValueBundleType) }
                    .toMutableStateMap()
            }
        )
    ) {
        mutableStateMapOf(*elements)
    }
}
