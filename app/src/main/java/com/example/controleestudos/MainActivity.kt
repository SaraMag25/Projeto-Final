package com.example.controleestudos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.controleestudos.data.StudyDatabase
import com.example.controleestudos.ui.AddStudyScreen
import com.example.controleestudos.ui.StudyListScreen
import com.example.controleestudos.ui.theme.ControleEstudosTheme
import com.example.controleestudos.viewmodel.StudyViewModel
import com.example.controleestudos.viewmodel.StudyViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dao = StudyDatabase.getDatabase(this).studyDao()
        val factory = StudyViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, factory)[StudyViewModel::class.java]

        setContent {
            ControleEstudosTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        StudyListScreen(
                            viewModel = viewModel,
                            onNavigateToAdd = { navController.navigate("add") }
                        )
                    }
                    composable("add") {
                        AddStudyScreen(
                            viewModel = viewModel,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}