package com.example.appvinilos.ui.artist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.appvinilos.VinylsApplication
import com.example.appvinilos.databinding.FragmentArtistBinding
import com.example.appvinilos.viewmodels.ArtistViewModel
import com.example.appvinilos.viewmodels.ViewModelFactory

class ArtistFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistViewModel by viewModels {
        val app = requireActivity().application as VinylsApplication
        ViewModelFactory(app.albumRepository, app.artistRepository, app.collectorRepository)
    }
    private var artistAdapter: ArtistAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistAdapter = ArtistAdapter(emptyList())
        binding.artistsRecyclerView.adapter = artistAdapter

        viewModel.artists.observe(viewLifecycleOwner) {
            artistAdapter?.updateArtists(it)
        }

        binding.searchEditTextArtists.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchArtists(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        viewModel.fetchArtists()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}