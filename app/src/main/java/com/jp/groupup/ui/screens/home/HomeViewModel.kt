package com.jp.groupup.ui.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.jp.groupup.domain.model.CalendarTimestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(){

    private val _timeStamps = mutableStateListOf<CalendarTimestamp>()
    val timeStamps: SnapshotStateList<CalendarTimestamp> = _timeStamps

    val mockTimes = arrayListOf(CalendarTimestamp("1 hour", Calendar.HOUR))

    fun loadData(){
        timeStamps.addAll(mockTimes)
    }

    sealed class HomeUiState {
        object Empty : HomeUiState()
        object Loading : HomeUiState()
        class Loaded(val data: HomeUiState) : HomeUiState()
        class Error(val message: String) : HomeUiState()
    }
}