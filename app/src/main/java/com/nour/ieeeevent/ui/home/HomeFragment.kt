package com.nour.ieeeevent.ui.home

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nour.ieeeevent.R
import com.nour.ieeeevent.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var permissionRegister: ActivityResultLauncher<String>
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.scannerButton.setOnClickListener(this::openCamera)
        permissionRegister = registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
            this::permissionResult
        )
        binding.changeSheet.setOnClickListener { showDialog() }
        viewModel.uiState.observe(viewLifecycleOwner) {
            it.errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openCamera(view: View) {
        permissionRegister.launch(Manifest.permission.CAMERA)
    }

    private fun permissionResult(grant: Boolean) {
        if (grant)
            openCameraX()
        else
            showToastPermissionDenied()
    }

    private fun showToastPermissionDenied() {
        Toast.makeText(requireContext(), getText(R.string.permissionDenied), Toast.LENGTH_LONG)
            .show()
    }

    private fun openCameraX() {
        viewModel.clearData()
        findNavController().navigate(R.id.action_homeFragment_to_cameraXFragment)
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(requireContext().getString(R.string.delete_old_data))
            .setPositiveButton("Yes") { dialog, which ->
                viewModel.deleteAllAttenders()
                goToSheetDataFragment()
            }
            .setNegativeButton("No") { dialog, which ->
                goToSheetDataFragment()
            }
            .show()
    }

    private fun goToSheetDataFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_sheetDataFragment)
    }


}


