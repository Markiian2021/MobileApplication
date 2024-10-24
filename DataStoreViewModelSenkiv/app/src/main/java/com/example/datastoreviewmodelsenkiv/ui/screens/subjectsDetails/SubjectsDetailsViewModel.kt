package com.example.datastoreviewmodelsenkiv.ui.screens.subjectsDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastoreviewmodelsenkiv.data.db.IntoDataBase
import com.example.datastoreviewmodelsenkiv.data.entity.SubjectEntity
import com.example.datastoreviewmodelsenkiv.data.entity.SubjectLabEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubjectsDetailsViewModel(
    private val database: IntoDataBase
) : ViewModel() {

    private val _subjectStateFlow = MutableStateFlow<SubjectEntity?>(null)
    val subjectStateFlow: StateFlow<SubjectEntity?>
        get() = _subjectStateFlow

    private val _subjectLabsListStateFlow = MutableStateFlow<List<SubjectLabEntity>>(emptyList())
    val subjectLabsListStateFlow: StateFlow<List<SubjectLabEntity>>
        get() = _subjectLabsListStateFlow

    fun initData(id: Int) {
        viewModelScope.launch {
            _subjectStateFlow.value = database.subjectsDao.getSubjectById(id)
            _subjectLabsListStateFlow.value = database.subjectLabsDao.getSubjectLabsBySubjectId(id)
        }
    }
    fun loadLabs(subjectId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val labs = database.subjectLabsDao.getSubjectLabsBySubjectId(subjectId)
            _subjectLabsListStateFlow.value = labs
        }
    }
    fun insertLab(newLab: SubjectLabEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            database.subjectLabsDao.insert(newLab)
            loadLabs(newLab.subjectId)
        }
    }
    fun updateLab(lab: SubjectLabEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            database.subjectLabsDao.update(lab)
            loadLabs(lab.subjectId)
        }
    }
    fun deleteLab(lab: SubjectLabEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            database.subjectLabsDao.delete(lab)
            loadLabs(lab.subjectId)
        }
    }
}
