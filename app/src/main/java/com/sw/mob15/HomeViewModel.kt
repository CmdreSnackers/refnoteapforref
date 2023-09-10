package com.sw.mob15

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.mob15.data.Person
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.internal.NopCollector
import kotlinx.coroutines.flow.internal.NopCollector.emit
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val firstName: MutableStateFlow<String> = MutableStateFlow("")
    val lastName: MutableStateFlow<String> = MutableStateFlow("")
    val finish1: MutableStateFlow<String> = MutableStateFlow("Finish initial value")
    val finish2: MutableStateFlow<String> = MutableStateFlow("Finish initial value")





    fun counter(ss: Int) = flow<Int> {
        var s = ss
        while (ss >= 0) {
            emit(s)
            s--
            delay(1000)
        }
    }

    fun submit() {

        val person = Person(firstName.value, lastName.value)

        finish1.value = person.toString()

        viewModelScope.launch {
            finish2.emit(person.toString())
        }

    }
}