package com.example.weektwoassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    // Data to share between fragments
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    fun setUsername(value: String) {
        _username.value = value
    }
    fun clearUsername() {
        _username.value = ""
    }
}