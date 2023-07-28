package com.nocountry.movie_no_country.feature_search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildPosterUrlUseCase
import com.nocountry.movie_no_country.feature_search.domain.usecase.GetSearchUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchs : GetSearchUseCase,
    private val buildPosterUrlUseCase: BuildPosterUrlUseCase
):ViewModel() {
    private val _search = MutableLiveData<List<String>>()
    var search : LiveData<List<String>> = _search

    fun getSearch(query:String){
        viewModelScope.launch {
            when(val result = getSearchs(query = query)){
                is NetworkResult.Error -> Log.d("SearchViewModel", "error Search")
                is NetworkResult.Exception -> Log.d("SearchViewModel", "exception Search")
                is NetworkResult.Success -> _search.value = result.data.results.map {
                    buildPosterUrlUseCase(it.posterPath)?: ""
                }
            }
        }
    }
}