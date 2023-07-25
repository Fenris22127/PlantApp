package de.fenris.plantapp2.viewmodel.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import de.fenris.plantapp2.data.Plant


class SliderViewModelFactory(private val plant: Plant) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return SliderViewModel(plant) as T
    }
}