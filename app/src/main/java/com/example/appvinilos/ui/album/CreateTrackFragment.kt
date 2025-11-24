package com.example.appvinilos.ui.album

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appvinilos.VinylsApplication
import com.example.appvinilos.databinding.FragmentCreateTrackBinding
import com.example.appvinilos.viewmodels.CreateTrackViewModel
import com.example.appvinilos.viewmodels.ViewModelFactory
import java.util.regex.Pattern

class CreateTrackFragment : Fragment() {

    private var _binding: FragmentCreateTrackBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateTrackViewModel by viewModels {
        val app = requireActivity().application as VinylsApplication
        ViewModelFactory(app.albumRepository, app.artistRepository, app.collectorRepository)
    }

    private val args: CreateTrackFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Disable button initially
        binding.saveTrackButton.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateForm()
            }
        }

        binding.trackNameEditText.addTextChangedListener(textWatcher)
        binding.trackDurationEditText.addTextChangedListener(textWatcher)

        binding.saveTrackButton.setOnClickListener {
            if (validateDurationFormat()) {
                binding.saveTrackButton.isEnabled = false // Disable button to prevent double clicks
                val params = mapOf(
                    "name" to binding.trackNameEditText.text.toString(),
                    "duration" to binding.trackDurationEditText.text.toString()
                )
                viewModel.addTrackToAlbum(args.albumId, params)
            }
        }

        viewModel.creationStatus.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, "Track añadido con éxito", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Error al añadir el track. Revisa los datos.", Toast.LENGTH_LONG).show()
                binding.saveTrackButton.isEnabled = true // Re-enable button on failure
            }
        }
    }

    private fun validateForm() {
        val isFormValid = binding.trackNameEditText.text.toString().isNotBlank() &&
                binding.trackDurationEditText.text.toString().isNotBlank()
        binding.saveTrackButton.isEnabled = isFormValid
    }

    private fun validateDurationFormat(): Boolean {
        val duration = binding.trackDurationEditText.text.toString()
        val pattern = Pattern.compile("^\\d{1,2}:\\d{2}$") // Matches M:SS or MM:SS
        return if (pattern.matcher(duration).matches()) {
            binding.trackDurationInputLayout.error = null
            true
        } else {
            binding.trackDurationInputLayout.error = "El formato debe ser MM:SS"
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}