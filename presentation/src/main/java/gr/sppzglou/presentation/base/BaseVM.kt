package gr.sppzglou.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseVM : ViewModel() {

    fun launch(block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }
}