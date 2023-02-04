package com.example.firstnetworkapi.adapter

import com.example.firstnetworkapi.model.SchoolSATScoresItem
import com.example.firstnetworkapi.model.SchoolsItem

sealed class ViewType {
    data class SCHOOL(val schoolItem: SchoolsItem) : ViewType()
    data class LETTER(val letter: String): ViewType()
}

sealed class DetailsViewType{
    data class DETAILS( val schoolSATScoresItem: SchoolSATScoresItem): ViewType()

}
