package com.example.firstnetworkapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstnetworkapi.databinding.LetterItemBinding
import com.example.firstnetworkapi.model.SchoolSATScoresItem
import com.example.firstnetworkapi.model.SchoolsItem
import com.google.android.gms.ads.mediation.Adapter

class DetailsAdapter(
            private val detailSet: MutableList<DetailsViewType> = mutableListOf(),
            private val onClickedDetails: (SchoolSATScoresItem) -> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun updateSchools(newDetails: List<SchoolSATScoresItem>) {
        var tempChar = '+'
        newDetails.sortedBy { it.schoolName }.forEach { school ->
            val firstLetter = school.schoolName?.first() ?: '+'
            if (firstLetter != tempChar) {
                detailSet.add(DetailsViewType.LETTER(firstLetter.toString()))
                tempChar = firstLetter
            }
            detailSet.add(DetailsViewType.DETAILS(schoolName))
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, DetailsViewType: Int): RecyclerView.ViewHolder {
        return
            DetailsViewHolder(
                DetailBinding.inflate(
                    LayoutInflater.from(parent.context),


                )

            )
    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when(val item = detailSet[position]) {
        is DetailsViewType.DETAILS -> {
            (holder as DetailsViewHolder).DetailBinding(item.schoolSATScoresItem, onClickedDetails)
        }

    }
}







    class DetailsViewHolder(
        private val binding: DetailBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun DetailBinding(
            details: SchoolSATScoresItem,
            onClickedDetails: (SchoolSATScoresItem) -> Unit
        ) {
            binding.schoolName.text = details.schoolName
            binding.schoolTestTakers.text = details.numOfSatTestTakers
            binding.schoolSATMathScores.text = details.satMathAvgScore
            binding.schoolSATReadingScores.text = details.satCriticalReadingAvgScore
            binding.schoolSATWritingScores.text = details.satWritingAvgScore

            binding.moreBtn.setOnClickListener {
                onClickedDetails(details)
            }

        }

    }


 }
