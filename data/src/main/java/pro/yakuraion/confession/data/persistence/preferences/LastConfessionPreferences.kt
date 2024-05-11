package pro.yakuraion.confession.data.persistence.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pro.yakuraion.confession.data.persistence.preferences.models.LastConfessionModel

class LastConfessionPreferences(
    private val context: Context,
    private val gson: Gson,
) {

    private val Context.dataStore by preferencesDataStore(NAME)

    val lastConfession: Flow<LastConfessionModel?> = context.dataStore.data.map { preferences ->
        val stringValue = preferences[LAST_CONFESSION_JSON]
        stringValue?.let { gson.fromJson(it, LastConfessionModel::class.java) }
    }

    val pakutaChecked: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[PAKUTA_CHECKED] ?: false
    }

    suspend fun setLastConfession(value: LastConfessionModel?) {
        context.dataStore.edit { preferences ->
            val stringValue = value?.let { gson.toJson(it) }
            if (stringValue == null) {
                preferences.remove(LAST_CONFESSION_JSON)
            } else {
                preferences[LAST_CONFESSION_JSON] = stringValue
            }
        }
    }

    suspend fun setPakutaChecked(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PAKUTA_CHECKED] = value
        }
    }

    companion object {

        private const val NAME = "last_confession_preferences"

        private val LAST_CONFESSION_JSON = stringPreferencesKey("lastConfessionJson")
        private val PAKUTA_CHECKED = booleanPreferencesKey("pakutaChecked")
    }
}
