package br.com.snowpet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _teste = MutableLiveData<Int>()
    val teste: LiveData<Int> = _teste

    fun setTeste(n: Int) {
        _teste.postValue(n)
    }
}
