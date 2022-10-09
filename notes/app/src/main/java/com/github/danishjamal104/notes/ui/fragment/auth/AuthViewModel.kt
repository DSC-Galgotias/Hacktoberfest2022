package com.github.danishjamal104.notes.ui.fragment.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.danishjamal104.notes.data.model.User
import com.github.danishjamal104.notes.data.repository.auth.AuthRepository
import com.github.danishjamal104.notes.ui.Event
import com.github.danishjamal104.notes.util.ServiceResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(), Event<AuthEvent> {

    private val _authState: MutableLiveData<AuthState<User>> = MutableLiveData()
    val authState: LiveData<AuthState<User>> get() = _authState

    override fun setEvent(event: AuthEvent) {
        viewModelScope.launch {
            when(event) {
                is AuthEvent.Login -> login(event.googleTaskResult)
            }
        }
    }

    private suspend fun login(googleTaskResult: Task<GoogleSignInAccount>) {
        val account: GoogleSignInAccount
        try {
            account = googleTaskResult.getResult(ApiException::class.java)
        } catch (e: ApiException) {
            _authState.value = AuthState.LogInFailure(""+e.localizedMessage)
            return
        }

        _authState.value = AuthState.Loading
        val res = authRepository.login(account)
        if(res is ServiceResult.Error) {
            _authState.postValue(AuthState.LogInFailure(res.reason))
        } else {
            _authState.postValue(AuthState.LogInSuccess((res as ServiceResult.Success).data))
        }
    }

}

sealed class AuthState<out T> {
    data class LogInSuccess<out T>(val user: User) : AuthState<T>()
    data class LogInFailure(val reason: String) : AuthState<Nothing>()
    object Loading : AuthState<Nothing>()
}

sealed class AuthEvent {
    data class Login(val googleTaskResult: Task<GoogleSignInAccount>) : AuthEvent()
}
