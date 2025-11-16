package com.example.appvinilos.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.appvinilos.VinylsApplication
import com.example.appvinilos.databinding.FragmentAlbumDetailBinding
import com.example.appvinilos.viewmodels.AlbumDetailViewModel
import com.example.appvinilos.viewmodels.ViewModelFactory
import com.google.android.material.appbar.AppBarLayout
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs

class AlbumDetailFragment : Fragment() {

    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumDetailViewModel by viewModels {
        val app = requireActivity().application as VinylsApplication
        ViewModelFactory(app.albumRepository, app.artistRepository, app.collectorRepository)
    }
    private val args: AlbumDetailFragmentArgs by navArgs()
    private var trackAdapter: TrackAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDetail.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        viewModel.album.observe(viewLifecycleOwner) { album ->
            val dateFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(album.releaseDate)
            val performerName = album.performers.firstOrNull()?.name ?: ""

            binding.albumCoverDetail.load(album.cover)
            binding.albumNameDetail.text = album.name
            binding.performerNameDetail.text = performerName
            binding.albumInfoDetail.text = "$formattedDate - ${album.genre}"

            setupToolbarTitle(album.name)

            binding.tracksTitle.visibility = View.VISIBLE
            val gridLayoutManager = GridLayoutManager(context, 2)
            binding.tracksRecyclerView.layoutManager = gridLayoutManager
            trackAdapter = TrackAdapter(album.tracks ?: emptyList(), album.cover)
            binding.tracksRecyclerView.adapter = trackAdapter

            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == trackAdapter?.itemCount?.minus(1)) 2 else 1
                }
            }
        }

        viewModel.fetchAlbumDetail(args.albumId)
    }

    private fun setupToolbarTitle(albumName: String) {
        var isTitleShown = false
        binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val shouldShowTitle = abs(verticalOffset) - appBarLayout.totalScrollRange == 0
            if (isTitleShown != shouldShowTitle) {
                binding.collapsingToolbar.title = if (shouldShowTitle) albumName else " "
                isTitleShown = shouldShowTitle
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}