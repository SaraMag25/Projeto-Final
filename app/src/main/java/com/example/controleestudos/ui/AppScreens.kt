package com.example.controleestudos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.controleestudos.viewmodel.StudyViewModel

@Composable
fun StudyListScreen(viewModel: StudyViewModel, onNavigateToAdd: () -> Unit) {
    val studies by viewModel.allStudies.observeAsState(emptyList())
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, "Adicionar")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(studies) { study ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(study.subject, style = MaterialTheme.typography.headlineSmall)
                        Text(study.topic)
                    }
                }
            }
        }
    }
}

@Composable
fun AddStudyScreen(viewModel: StudyViewModel, onBack: () -> Unit) {
    var subject by remember { mutableStateOf("") }
    var topic by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text("Matéria") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = topic,
            onValueChange = { topic = it },
            label = { Text("Assunto") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.addStudy(subject, topic, "")
                onBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }
    }
}