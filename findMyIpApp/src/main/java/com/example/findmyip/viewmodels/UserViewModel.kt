package com.example.findmyip.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmyip.repository.UserRepository
import com.example.findmyip.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)
init {
    fetchUsers()
}
    fun fetchUsers() {
        viewModelScope.launch {

            repository.fetchData().onStart {

                response.value= ApiState.Loading
                Log.d("Akash", response.value.toString())
            }.catch {
                response.value= ApiState.Failure(it)
            }.collect {
                response.value=ApiState.Success(it)
                Log.d("Akash success", response.value.toString())

            }
        }
    }
}