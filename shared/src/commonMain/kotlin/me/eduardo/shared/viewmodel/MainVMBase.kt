package me.eduardo.shared.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import me.eduardo.shared.network.CatAPI

class MainVMBase {

    private val api = CatAPI()

    private val _catIMG = MutableStateFlow<String>("")
    val catIMG get() = _catIMG

    private val _error =  MutableStateFlow<Boolean>(false)
    val error get() = _error

    suspend fun  getCat(){
        try {
            val cat = api.getCat()
            _catIMG.value = cat.file
        }catch (err: Exception){
            _error.value = true
        }
    }
}