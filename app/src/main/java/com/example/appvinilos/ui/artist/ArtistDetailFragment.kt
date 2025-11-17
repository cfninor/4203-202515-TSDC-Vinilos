package com.example.appvinilos.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.appvinilos.VinylsApplication
import com.example.appvinilos.databinding.FragmentArtistDetailBinding
import com.example.appvinilos.viewmodels.ArtistDetailViewModel
import com.example.appvinilos.viewmodels.ViewModelFactory

class ArtistDetailFragment : Fragment() {

    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistDetailViewModel by viewModels {
        val app = requireActivity().application as VinylsApplication
        ViewModelFactory(app.albumRepository, app.artistRepository, app.collectorRepository)
    }
    private val args: ArtistDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.artist.observe(viewLifecycleOwner) { artist ->
            binding.artistImageDetail.load(artist.image)
            binding.artistNameDetail.text = artist.name
            binding.artistDescriptionDetail.text = artist.description

            artist.albums?.let {
                binding.artistAlbumsRecyclerView.adapter = DiscographyAdapter(it)
            }
        }

        viewModel.fetchArtistDetail(args.artistId, args.artistType)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}