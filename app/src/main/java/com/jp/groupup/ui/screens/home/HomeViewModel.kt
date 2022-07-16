package com.jp.groupup.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jp.groupup.domain.model.CalendarTimestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(){
    private val _timeStamps = MutableLiveData<List<CalendarTimestamp>>()
    val timeStamps: LiveData<List<CalendarTimestamp>>
        get() = _timeStamps

    val mockTimes = arrayListOf(CalendarTimestamp("1 hour", Calendar.HOUR))

    fun loadData(){
        _timeStamps.value = mockTimes
    }

}