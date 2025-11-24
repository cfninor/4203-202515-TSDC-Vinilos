package com.example.appvinilos.ui.collector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appvinilos.VinylsApplication
import com.example.appvinilos.databinding.FragmentCollectorDetailBinding
import com.example.appvinilos.ui.artist.DiscographyAdapter
import com.example.appvinilos.viewmodels.CollectorDetailViewModel
import com.example.appvinilos.viewmodels.ViewModelFactory

class CollectorDetailFragment : Fragment() {

    private var _binding: FragmentCollectorDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CollectorDetailViewModel by viewModels {
        val app = requireActivity().application as VinylsApplication
        ViewModelFactory(app.albumRepository, app.artistRepository, app.collectorRepository)
    }

    private val args: CollectorDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorDetailBinding.inflate(inflater, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe collector details for name, email, etc.
        viewModel.collector.observe(viewLifecycleOwner) { collector ->
            binding.collectorNameDetail.text = collector.name
            binding.collectorTelephone.text = collector.telephone
            binding.collectorEmail.text = collector.email

            collector.favoritePerformers?.let {
                binding.favoriteArtistsRecyclerView.adapter = FavoriteArtistAdapter(it) { artist ->
                    val artistType = if (artist.creationDate != null) "band" else "musician"
                    val action = CollectorDetailFragmentDirections.actionCollectorDetailFragmentToArtistDetailFragment(
                        artistId = artist.id,
                        artistType = artistType
                    )
                    findNavController().navigate(action)
                }
            }
        }

        // Observe the new albums list for the discography
        viewModel.albums.observe(viewLifecycleOwner) { albums ->
            binding.collectorAlbumsRecyclerView.adapter = DiscographyAdapter(albums) { album ->
                val action = CollectorDetailFragmentDirections.actionCollectorDetailFragmentToAlbumDetailFragment(album.id)
                findNavController().navigate(action)
            }
        }

        viewModel.fetchCollectorDetail(args.collectorId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}