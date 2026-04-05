package com.mobileapp.kurs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.mobileapp.kurs.ui.screens.MainScreen
import com.mobileapp.kurs.ui.theme.KursTheme
import com.mobileapp.kurs.viewmodel.MainViewModel
import com.mobileapp.kurs.viewmodel.MainViewModelFactory
import com.mobileapp.kurs.viewmodel.SearchViewModel
import com.mobileapp.kurs.viewmodel.SearchViewModelFactory

class MainActivity : ComponentActivity() {
    private val weatherApplication: WeatherApplication by lazy {
        application as WeatherApplication
    }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory.create(
            cityRepository = weatherApplication.cityRepository,
            weatherRepository = weatherApplication.weatherRepository
        )
    }

    private val searchViewModel: SearchViewModel by viewModels {
        SearchViewModelFactory.create(
            cityRepository = weatherApplication.cityRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KursTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(
                        mainViewModel = mainViewModel,
                        searchViewModel = searchViewModel
                    )
                }
            }
        }
    }
}
