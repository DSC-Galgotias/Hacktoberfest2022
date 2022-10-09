package com.github.danishjamal104.notes.ui.fragment.note

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
class NoteViewModel
@Inject constructor(
    private val notesRepository: NotesRepository
): ViewModel(), Event<NoteEvent> {

    private val _noteState: MutableLiveData<NoteState> = MutableLiveData()
    val noteSate: LiveData<NoteState> get() = _noteState

    override fun setEvent(event: NoteEvent) {
        viewModelScope.launch {
            when(event) {
                is NoteEvent.CreateNote -> createNote(event.note)
                is NoteEvent.DeleteNote -> deleteNote(event.note)
                is NoteEvent.UpdateNote -> updateNote(event.note)
                is NoteEvent.GetNote -> getNote(event.noteId)
            }
        }
    }

    private suspend fun getNote(noteId: Int) {
        _noteState.value = NoteState.Loading
        when(val result = notesRepository.getNote(noteId)) {
            is ServiceResult.Success -> {
                _noteState.postValue(NoteState.GetNoteSuccess(result.data))
            }
            is ServiceResult.Error ->
                _noteState.postValue(NoteState.GetNoteFailure(result.reason))
        }
    }


    private suspend fun createNote(note: String) {
        _noteState.value = NoteState.Loading
        when(val result = notesRepository.createNote(note)) {
            is ServiceResult.Success -> {
                _noteState.postValue(NoteState.EventResult(true,
                    "Note created successfully"))
            }
            is ServiceResult.Error ->
                _noteState.postValue(NoteState.EventResult(false, result.reason))
        }
    }

    private suspend fun updateNote(note: Note) {
        _noteState.value = NoteState.Loading
        when(val result = notesRepository.updateNote(note)) {
            is ServiceResult.Success -> {
                _noteState.postValue(NoteState.EventResult(true,
                    "Note updated successfully"))
            }
            is ServiceResult.Error ->
                _noteState.postValue(NoteState.EventResult(false, result.reason))
        }
    }

    private suspend fun deleteNote(note: Note) {
        _noteState.value = NoteState.Loading
        when(val result = notesRepository.deleteNote(note)) {
            is ServiceResult.Success -> {
                _noteState.postValue(NoteState.EventResult(true,
                    "Note deleted successfully"))
            }
            is ServiceResult.Error ->
                _noteState.postValue(NoteState.EventResult(false, result.reason))
        }
    }

}

sealed class NoteState {
    data class GetNoteSuccess(val note: Note): NoteState()
    data class GetNoteFailure(val reason: String): NoteState()
    data class EventResult(val success: Boolean, val info: String) : NoteState()
    object Loading : NoteState()
}

sealed class NoteEvent {
    data class GetNote(val noteId: Int) : NoteEvent()
    data class CreateNote(val note: String) : NoteEvent()
    data class UpdateNote(val note: Note) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
}