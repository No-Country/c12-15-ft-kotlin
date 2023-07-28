package com.nocountry.movie_no_country.feature_search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildPosterUrlUseCase
import com.nocountry.movie_no_country.feature_home.presentation.model.HomeRecyclerItem
import com.nocountry.movie_no_country.feature_search.domain.usecase.GetSearchUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchs : GetSearchUseCase,
    private val buildPosterUrlUseCase: BuildPosterUrlUseCase
):ViewModel() {
    private val _search = MutableLiveData<UiState>(UiState.Loading)
    var search : LiveData<UiState> = _search

    fun getSearch(query:String){
        viewModelScope.launch {
            when(val result = getSearchs(query = query)){
                is NetworkResult.Error -> UiState.Error(result.message.toString())
                is NetworkResult.Exception -> UiState.Error(result.e.message.toString())
                is NetworkResult.Success -> {
                    _search.value = UiState.Success(
                        result.data.results.map {
                            buildPosterUrlUseCase(it.posterPath) ?: ""
                        }
                    )
                }
            }
        }
    }
}
sealed class UiState {
    data class Success(val items: List<String>) : UiState()
    object Loading : UiState()
    data class Error(val msg: String) : UiState()
}