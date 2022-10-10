package com.github.danishjamal104.notes.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.danishjamal104.notes.data.model.Note
import com.github.danishjamal104.notes.data.repository.note.NotesRepository
import com.github.danishjamal104.notes.ui.Event
import com.github.danishjamal104.notes.util.ServiceResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val notesRepository: NotesRepository
): ViewModel(), Event<HomeEvent> {

    private val _homeState: MutableLiveData<HomeState> = MutableLiveData()
    val authState: LiveData<HomeState> get() = _homeState

    override fun setEvent(event: HomeEvent) {
        viewModelScope.launch {
            when(event) {
                HomeEvent.GetNotes -> getNotes()
            }
        }

    }

    private suspend fun getNotes() {
        _homeState.value = HomeState.Loading
        when(val result = notesRepository.getNotes()) {
            is ServiceResult.Error -> _homeState.postValue(HomeState.GetNotesFailure(result.reason))
            is ServiceResult.Success -> _homeState.postValue(HomeState.GetNotesSuccess(result.data))
        }
    }

}

sealed class HomeState {
    data class GetNotesSuccess(val notes: List<Note>) : HomeState()
    data class GetNotesFailure(val reason: String) : HomeState()
    object Loading : HomeState()
}

sealed class HomeEvent {
    object GetNotes : HomeEvent()
}