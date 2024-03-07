package com.example.findmyip.viewmodels

import MockResponseFileReader
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.findmyip.network.ApiService
import com.example.findmyip.repository.UserRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class UserViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    lateinit var userMovieRepository: UserRepository
    lateinit var userViewmodel: UserViewModel

    @Mock
    lateinit var apiInterface: ApiService


    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val server = MockWebServer()
    private lateinit var repository: UserRepository
    private lateinit var mockedResponse: String
    private val gson = GsonBuilder()
        .setLenient()
        .create()
    @Before
    fun setUp() {
        server.start(8000)
        var BASE_URL = server.url("/").toString()
        val okHttpClient = OkHttpClient
            .Builder()
            .build()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(ApiService::class.java)

        repository = UserRepository(service)
    }

    @Test
    fun userViewModelTest() {

        mockedResponse = MockResponseFileReader("IpList.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runTest { repository.fetchData().toList() }

        Assert.assertNotNull(response)
    }
}