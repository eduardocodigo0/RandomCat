package me.eduardo.androidApp.ui

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.eduardo.shared.viewmodel.MainVMBase

class MainViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    val baseVM = MainVMBase()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getCat() {
        viewModelScope.launch(Dispatchers.IO) {
            baseVM.getCat()
        }
    }


}