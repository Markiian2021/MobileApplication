package com.example.datastoreviewmodelsenkiv.ui.screens.subjectsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastoreviewmodelsenkiv.data.db.IntoDataBase
import com.example.datastoreviewmodelsenkiv.data.entity.SubjectEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubjectsListViewModel(
    private val database: IntoDataBase
) : ViewModel() {

    private val _subjectListStateFlow = MutableStateFlow<List<SubjectEntity>>(emptyList())
    val subjectListStateFlow: StateFlow<List<SubjectEntity>>
        get() = _subjectListStateFlow

    init {
        loadSubjects()
    }
    private fun loadSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            _subjectListStateFlow.value = database.subjectsDao.getAllSubjects()
        }
    }

    fun addSubject(newSubjectTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (newSubjectTitle.isNotBlank()) {
                val currentList = _subjectListStateFlow.value
                val newSubject = SubjectEntity(id = currentList.size + 1, title = newSubjectTitle)

                database.subjectsDao.addSubject(newSubject)

                _subjectListStateFlow.value = database.subjectsDao.getAllSubjects()
            }
        }
    }

    fun deleteSubject(subject: SubjectEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            database.subjectsDao.deleteSubject(subject)
            _subjectListStateFlow.value = database.subjectsDao.getAllSubjects()
        }
    }

}
