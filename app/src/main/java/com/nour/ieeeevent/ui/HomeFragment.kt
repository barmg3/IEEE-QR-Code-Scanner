package com.nour.ieeeevent.ui

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.nour.ieeeevent.data.modeles.Attender
import com.nour.ieeeevent.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.IOException


class HomeFragment : Fragment(),QRCodeResult {

    lateinit var binding : FragmentHomeBinding
    private lateinit var cameraRegister : ActivityResultLauncher<Intent>
    private lateinit var uriImage : Uri
    private  val viewModel : HomeViewModel by sharedViewModel ()
    private lateinit var  qrCodeScanner : QRCodeScanner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentHomeBinding.inflate(inflater)
        qrCodeScanner=QRCodeScanner(requireContext(),this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraRegister =registerForActivityResult(ActivityResultContracts.StartActivityForResult(),this::galleryResult)
        binding.scannerButton.setOnClickListener(this::openCamera)
    }

    private fun openCamera(view: View){
        cameraRegister.launch(getCameraIntent())
        viewModel.clearDataFromUi()
    }

    private fun getCameraIntent():Intent{
        uriImage = requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())!!
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uriImage)
        return intent
    }

    private fun galleryResult(activityResult: ActivityResult) = try{
        qrCodeScanner.getTextQrCodes(uriImage)
    }catch (e : IOException){}

    override fun getQRCodeResult(attender: Attender) {
         viewModel.getAttenderFromDB(attender)
    }

}


