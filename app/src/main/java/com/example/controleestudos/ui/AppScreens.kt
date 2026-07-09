package com.example.controleestudos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.controleestudos.viewmodel.StudyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.controleestudos.viewmodel.MuralViewModel

@Composable
fun StudyListScreen(
    viewModel: StudyViewModel,
    onNavigateToAdd: () -> Unit,
    onNavigateToMural: () -> Unit
) {
    val studies by viewModel.allStudies.observeAsState(emptyList())
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, "Adicionar")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {

            Button(
                onClick = onNavigateToMural,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Ver Mural de Editais e Dicas")
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(studies) { study ->
                    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(study.subject, style = MaterialTheme.typography.headlineSmall)
                            Text(study.topic)
                        }
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

@Composable
fun MuralScreen(
    onBack: () -> Unit,
    muralViewModel: MuralViewModel = viewModel()
) {

    val dicas by muralViewModel.dicas.collectAsState()
    val mensagem by muralViewModel.mensagem.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mural de Editais e Dicas (API)", style = MaterialTheme.typography.titleLarge)

        if (mensagem.isNotEmpty()) {
            Text(text = mensagem, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(8.dp))
        }

        Button(
            onClick = { muralViewModel.enviarMeta("Estudo de Android Concluído!") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Simular Envio de Meta (POST)")
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(dicas) { dica ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = dica.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = dica.body, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            Text("Voltar ao Dashboard")
        }
    }
}