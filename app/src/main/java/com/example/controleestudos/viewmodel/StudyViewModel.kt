package com.example.controleestudos.viewmodel
import androidx.lifecycle.*
import com.example.controleestudos.data.*
import kotlinx.coroutines.launch

class StudyViewModel(private val dao: StudyDao) : ViewModel() {
    val allStudies: LiveData<List<Study>> = dao.getAllStudies().asLiveData()

    fun addStudy(subject: String, topic: String, description: String) {
        viewModelScope.launch {
            dao.insert(Study(subject = subject, topic = topic, description = description))
        }
    }
}

class StudyViewModelFactory(private val dao: StudyDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudyViewModel(dao) as T
    }
}