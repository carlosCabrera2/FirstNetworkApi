package com.example.firstnetworkapi.view

import com.example.firstnetworkapi.model.SchoolSATScoresItem
import com.example.firstnetworkapi.model.SchoolsItem

sealed class DetailsUIState {
    object LOADING: DetailsUIState()
    data class SUCCESS(val SATResponse: List<SchoolSATScoresItem>,
                       val schoolResponse: List<SchoolsItem> ):
                        DetailsUIState()
    data class  ERROR(val error: Exception): DetailsUIState()
}