package com.example.firstnetworkapi.viewmodel

import android.telecom.Call.Details
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstnetworkapi.service.ServiceApi
import com.example.firstnetworkapi.view.DetailsUIState
import com.example.firstnetworkapi.view.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG= "DetailsViewModel"

class DetailsViewModel (
    private  val serviceApi: ServiceApi,
    private val ioDispatcher: CoroutineDispatcher=
            Dispatchers.IO): ViewModel() {

      private val _details: MutableLiveData<DetailsUIState> = MutableLiveData(DetailsUIState.LOADING)
      val details: LiveData<DetailsUIState> get() = _details


    fun getAllDetails(){
        viewModelScope.launch(ioDispatcher){
            _details.postValue(DetailsUIState.LOADING)

            try {
                val response=serviceApi.getAllDetails()
                if(response.isSuccessful){
                    response.body()?.let{
                        _details.postValue(DetailsUIState.SUCCESS(it))
                        withContext(Dispatchers.Main){
                            Log.d(TAG,"onCreate: $it")
                        }
                    }?: throw Exception("Error null detail response")
                }else{throw Exception (response.errorBody()?.string())
            }
        }catch (e: Exception){
            _details.postValue(DetailsUIState.ERROR(e))
                Log.e(TAG,"onCreate: ${e.localizedMessage}", e)
            }
        }



    }

}