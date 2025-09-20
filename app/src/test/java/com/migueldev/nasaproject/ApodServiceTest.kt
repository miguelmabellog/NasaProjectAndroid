package com.migueldev.nasaproject

import com.google.gson.GsonBuilder
import com.migueldev.nasaproject.data.source.remote.dto.NasaResponseDto
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.test.runTest
import com.migueldev.nasaproject.data.source.remote.NasaApiService


class ApodServiceTest {

    private lateinit var server: MockWebServer
    private lateinit var service: NasaApiService

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        val gson = GsonBuilder().create()
        service = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NasaApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `getApod returns expected fields`() = runTest {
        val body = """
            {
              "copyright": "Tommy Lease",
              "date": "2025-09-19",
              "explanation": "A study in contrasts, this colorful cosmic skyscape features stars, dust, and glowing gas in the vicinity of NGC 6914. The interstellar complex of nebulae lies some 6,000 light-years away, toward the high-flying northern constellation Cygnus and the plane of our Milky Way Galaxy. Obscuring interstellar dust clouds appear in silhouette while reddish hydrogen emission nebulae, along with the dusty blue reflection nebulae, fill the cosmic canvas. Ultraviolet radiation from the massive, hot, young stars of the extensive Cygnus OB2 association ionize the region's atomic hydrogen gas, producing the characteristic red glow as protons and electrons recombine. Embedded Cygnus OB2 stars also provide the blue starlight strongly reflected by the dust clouds. The over one degree wide telescopic field of view spans about 100 light-years at the estimated distance of NGC 6914.",
              "hdurl": "https://apod.nasa.gov/apod/image/2509/NGC6914_2048.jpg",
              "media_type": "image",
              "service_version": "v1",
              "title": "The NGC 6914 Complex",
              "url": "https://apod.nasa.gov/apod/image/2509/NGC6914_1024.jpg"
            }
        """.trimIndent()

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        )

        val response: NasaResponseDto = service.getAstronomyPictureOfTheDay("DEMO_KEY")

        assertEquals("2025-09-19", response.date)
        assertEquals("The NGC 6914 Complex", response.title)
        // Only assert fields provided by ApodResponse in data layer
        assertEquals("The NGC 6914 Complex", response.title)
    }
}


