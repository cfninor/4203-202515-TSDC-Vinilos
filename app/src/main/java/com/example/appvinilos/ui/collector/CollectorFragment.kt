package com.example.appvinilos.ui.collector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.appvinilos.VinylsApplication
import com.example.appvinilos.databinding.FragmentCollectorBinding
import com.example.appvinilos.viewmodels.CollectorViewModel
import com.example.appvinilos.viewmodels.ViewModelFactory

class CollectorFragment : Fragment() {

    private var _binding: FragmentCollectorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CollectorViewModel by viewModels {
        val app = requireActivity().application as VinylsApplication
        ViewModelFactory(app.albumRepository, app.artistRepository, app.collectorRepository)
    }
    private var collectorAdapter: CollectorAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectorAdapter = CollectorAdapter(emptyList())
        binding.collectorsRecyclerView.adapter = collectorAdapter

        viewModel.collectors.observe(viewLifecycleOwner) {
            collectorAdapter?.updateCollectors(it)
        }

        viewModel.fetchCollectors()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}