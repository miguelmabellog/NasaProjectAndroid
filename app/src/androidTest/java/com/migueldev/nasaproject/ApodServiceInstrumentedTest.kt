package com.migueldev.nasaproject

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.migueldev.nasaproject.domain.model.NasaItem
import com.migueldev.nasaproject.domain.repository.NasaRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ApodServiceInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: NasaRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun realApodCall_returnsValidData() {
        // Run synchronously using runBlocking for instrumentation
        val response: NasaItem = kotlinx.coroutines.runBlocking {
            repository.getFeaturedItem()
        }

        assertTrue(response.title.isNotBlank())
        assertTrue(response.description.isNotBlank())
        assertTrue(response.id.isNotBlank())
        assertTrue(response.date.isNotBlank())
        assertTrue(response.isAvailable)
    }
}