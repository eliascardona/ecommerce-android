package com.eliascardona.ecommerce.domain.shopping_cart

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(
            name = "shopping_cart"
        )