package com.example.appvinilos.ui.album

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appvinilos.R
import com.example.appvinilos.VinylsApplication
import com.example.appvinilos.databinding.FragmentAlbumBinding
import com.example.appvinilos.models.Album
import com.example.appvinilos.viewmodels.AlbumViewModel
import com.example.appvinilos.viewmodels.ViewModelFactory

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumViewModel by viewModels {
        val app = requireActivity().application as VinylsApplication
        ViewModelFactory(app.albumRepository, app.artistRepository, app.collectorRepository)
    }
    private var albumAdapter: AlbumAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Define click listeners
        val onAlbumClicked: (Album) -> Unit = { album ->
            val action = AlbumFragmentDirections.actionNavigationAlbumsToAlbumDetailFragment(album.id)
            findNavController().navigate(action)
        }
        val onAddButtonClicked: () -> Unit = {
            findNavController().navigate(R.id.action_navigation_albums_to_createAlbumFragment)
        }

        albumAdapter = AlbumAdapter(emptyList(), onAlbumClicked, onAddButtonClicked)
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.recyclerAlbums.layoutManager = gridLayoutManager
        binding.recyclerAlbums.adapter = albumAdapter

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == albumAdapter?.itemCount?.minus(1)) 2 else 1
            }
        }

        binding.addButton.setOnClickListener {
            onAddButtonClicked()
        }

        viewModel.albums.observe(viewLifecycleOwner) {
            albumAdapter?.updateAlbums(it)
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchAlbums(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        viewModel.fetchAlbums()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}