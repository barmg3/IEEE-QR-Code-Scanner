package com.nour.ieeeevent.ui.cameraX

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.nour.ieeeevent.databinding.FragmentCameraXBinding
import com.nour.ieeeevent.ui.home.HomeViewModel
import com.nour.ieeeevent.util.QRCodeConverter.getId
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CameraXFragment : Fragment() {

    private lateinit var binding: FragmentCameraXBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private val options =
        BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE).build()
    private val barcodeScanner = BarcodeScanning.getClient(options)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraXBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCamera()
    }

    private fun startCamera() {
        val cameraController = LifecycleCameraController(requireContext())
        cameraController.bindToLifecycle(viewLifecycleOwner)
        binding.viewFinder.controller = cameraController
        setAnalysisImageUseCase(cameraController)
    }

    private fun setAnalysisImageUseCase(cameraController: CameraController) {
        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(requireContext()),
            MlKitAnalyzer(
                listOf(barcodeScanner), COORDINATE_SYSTEM_VIEW_REFERENCED,
                ContextCompat.getMainExecutor(requireContext()), ::onMlKitAnalyzerResult
            )
        )
    }


    private fun onMlKitAnalyzerResult(result: MlKitAnalyzer.Result?) {
        val id = result?.getValue(barcodeScanner)?.firstOrNull()?.getId()
        if (id != null) {
            viewModel.getAttenderDataById(id)
            goBackToLastFragment()
        }
    }

    private fun goBackToLastFragment() {
        try {
            findNavController().popBackStack()
        } catch (e: Exception) {
        }

    }
}