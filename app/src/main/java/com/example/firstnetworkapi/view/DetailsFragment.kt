package com.example.firstnetworkapi.view

import android.app.AlertDialog
import android.net.Network
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstnetworkapi.R
import com.example.firstnetworkapi.adapter.DetailsAdapter
import com.example.firstnetworkapi.databinding.FragmentDetailsBinding
import com.example.firstnetworkapi.viewmodel.DetailsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    private val binding by lazy{
        FragmentDetailsBinding.inflate(layoutInflater)
    }


    private val detailsAdapter by lazy {
        DetailsAdapter{
            findNavController().navigate(R.id.DetailsFragment)
        }
    }

    private val detailsViewModel: DetailsViewModel by activityViewModels {
        object: ViewModelProvider.Factory{
            override fun <Q: ViewModel> create (modelClass: Class<Q>): Q{
                return DetailsViewModel(Network.serviceApi) as Q
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        // Inflate the layout for this fragment

        binding.detailsRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = detailsAdapter
        }

        detailsViewModel.details.observe(viewLifecycleOwner) { state ->
            when(state) {
                is DetailsUIState.LOADING -> {

                }
                is DetailsUIState.SUCCESS -> {
                    detailsAdapter.updateDetails(state.response)
                }
                is DetailsUIState.ERROR -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error occurred")
                        .setMessage(state.error.localizedMessage)
                        .setPositiveButton("RETRY") { dialog, _ ->
                            detailsViewModel.getAllDetails()
                            dialog.dismiss()
                        }
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        detailsViewModel.getAllDetails()

        return binding.root
    }
}


