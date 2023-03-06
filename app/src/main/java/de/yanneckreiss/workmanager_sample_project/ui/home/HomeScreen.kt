@file:OptIn(ExperimentalFoundationApi::class)

package de.yanneckreiss.workmanager_sample_project.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.yanneckreiss.workmanager_sample_project.domain.models.Animal
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {

    val state: HomeState by viewModel.state.collectAsStateWithLifecycle()
    val animals: List<Animal> by viewModel.animals.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar { Text(text = "WorkManager Synchronization Sample") } },
    ) { paddingValues ->
        HomeScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            name = state.name,
            onNameUpdated = viewModel::onNameUpdated,
            onSaveAnimal = viewModel::saveAnimal,
            animals = animals
        )
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    name: String,
    onNameUpdated: (String) -> Unit,
    onSaveAnimal: () -> Unit,
    animals: List<Animal>,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp),
        reverseLayout = true
    ) {

        items(
            count = animals.size,
            contentType = { Animal::class },
            key = { index -> animals[index].uid }
        ) { index ->
            AnimalItem(
                modifier = Modifier.animateItemPlacement(),
                name = animals[index].name
            )
        }

        item {
            Button(onClick = onSaveAnimal) {
                Text("Save Animal")
            }
        }

        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                placeholder = { Text("Animal Name") },
                onValueChange = onNameUpdated
            )
        }
    }
}

@Composable
private fun AnimalItem(
    modifier: Modifier = Modifier,
    name: String
) {
    Text(
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        text = name
    )
}

@Preview
@Composable
private fun Preview_HomeScreenContent() {
    HomeScreenContent(
        modifier = Modifier,
        name = "Name",
        onNameUpdated = {},
        onSaveAnimal = {},
        animals = emptyList(),
    )
}
