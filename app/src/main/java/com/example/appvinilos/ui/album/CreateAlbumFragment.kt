package com.example.appvinilos.ui.album

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.appvinilos.VinylsApplication
import com.example.appvinilos.databinding.FragmentCreateAlbumBinding
import com.example.appvinilos.models.Genre
import com.example.appvinilos.models.RecordLabel
import com.example.appvinilos.viewmodels.CreateAlbumViewModel
import com.example.appvinilos.viewmodels.ViewModelFactory
import java.util.regex.Pattern

class CreateAlbumFragment : Fragment() {

    private var _binding: FragmentCreateAlbumBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateAlbumViewModel by viewModels {
        val app = requireActivity().application as VinylsApplication
        ViewModelFactory(app.albumRepository, app.artistRepository, app.collectorRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Disable button initially
        binding.saveButton.isEnabled = false

        // Populate dropdowns
        val genres = Genre.values().map { it.name }
        val genreAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, genres)
        binding.genreAutoComplete.setAdapter(genreAdapter)

        val recordLabels = RecordLabel.values().map { it.label }
        val recordLabelAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, recordLabels)
        binding.recordLabelAutoComplete.setAdapter(recordLabelAdapter)

        // Validation logic
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateForm()
            }
        }

        binding.nameEditText.addTextChangedListener(textWatcher)
        binding.coverEditText.addTextChangedListener(textWatcher)
        binding.releaseDateEditText.addTextChangedListener(textWatcher)
        binding.descriptionEditText.addTextChangedListener(textWatcher)
        binding.genreAutoComplete.addTextChangedListener(textWatcher)
        binding.recordLabelAutoComplete.addTextChangedListener(textWatcher)

        // Save button logic
        binding.saveButton.setOnClickListener {
            if (validateDateFormat()) {
                val releaseDate = binding.releaseDateEditText.text.toString()
                val formattedDate = "${releaseDate}T00:00:00.000Z"

                val params = mapOf(
                    "name" to binding.nameEditText.text.toString(),
                    "cover" to binding.coverEditText.text.toString(),
                    "releaseDate" to formattedDate,
                    "description" to binding.descriptionEditText.text.toString(),
                    "genre" to binding.genreAutoComplete.text.toString(),
                    "recordLabel" to binding.recordLabelAutoComplete.text.toString()
                )
                viewModel.createAlbum(params)
            }
        }

        // Observe creation status
        viewModel.creationStatus.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, "Álbum creado con éxito", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Error al crear el álbum", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm() {
        val isFormValid = binding.nameEditText.text.toString().isNotBlank() &&
                binding.coverEditText.text.toString().isNotBlank() &&
                binding.releaseDateEditText.text.toString().isNotBlank() &&
                binding.descriptionEditText.text.toString().isNotBlank() &&
                binding.genreAutoComplete.text.toString().isNotBlank() &&
                binding.recordLabelAutoComplete.text.toString().isNotBlank()
        binding.saveButton.isEnabled = isFormValid
    }

    private fun validateDateFormat(): Boolean {
        val date = binding.releaseDateEditText.text.toString()
        val pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$")
        val matcher = pattern.matcher(date)
        return if (matcher.matches()) {
            binding.releaseDateInputLayout.error = null
            true
        } else {
            binding.releaseDateInputLayout.error = "El formato debe ser YYYY-MM-DD"
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}