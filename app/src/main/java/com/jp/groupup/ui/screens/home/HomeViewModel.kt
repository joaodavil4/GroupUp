package com.jp.groupup.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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

    private val _selectedTimeStamp: MutableState<CalendarTimestamp?> = mutableStateOf(null)
    val selectedTimeStamp: State<CalendarTimestamp?> = _selectedTimeStamp

    private val mockTimes = arrayListOf(
        CalendarTimestamp("1 hour", Calendar.HOUR),
        CalendarTimestamp("1 day", Calendar.DAY_OF_WEEK)
    )

    init {
        loadData()
    }

    private fun loadData(){
        timeStamps.addAll(mockTimes)
    }

    fun onClickTimeStamp(timestamp: CalendarTimestamp){
        _selectedTimeStamp.value = timestamp
    }

    sealed class HomeUiState {
        object Empty : HomeUiState()
        object Loading : HomeUiState()
        class Loaded(val data: HomeUiState) : HomeUiState()
        class Error(val message: String) : HomeUiState()
    }
}