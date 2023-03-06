package de.yanneckreiss.workmanager_sample_project.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.yanneckreiss.workmanager_sample_project.domain.models.Animal
import de.yanneckreiss.workmanager_sample_project.domain.use_cases.SaveAnimalUseCase
import de.yanneckreiss.workmanager_sample_project.domain.use_cases.WatchAnimalsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val saveAnimalUseCase: SaveAnimalUseCase,
    watchAnimalsUseCase: WatchAnimalsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    val animals: Flow<List<Animal>> = watchAnimalsUseCase
        .call()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun saveAnimal() {

        val animalName = _state.value.name

        if(animalName.isBlank()) return

        val animal = Animal(name = animalName)

        viewModelScope.launch {
            saveAnimalUseCase.call(animal)
            _state.update { state -> state.copy(name = "") }
        }
    }

    fun onNameUpdated(updatedName: String) {
        _state.update { state -> state.copy(name = updatedName) }
    }
}
