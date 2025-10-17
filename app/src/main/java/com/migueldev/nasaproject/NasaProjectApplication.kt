package com.migueldev.nasaproject

import android.app.Application
import com.migueldev.nasaproject.core.common.preferences.DataStoreManager
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for NASA Project
 * This class is required for Hilt to work properly
 */
@HiltAndroidApp
class NasaProjectApplication : Application() {
  val dataStoreManager by lazy { DataStoreManager(this) }
}
