package com.nour.ieeeevent.ui.sheetName

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.nour.ieeeevent.R
import com.nour.ieeeevent.data.Repository
import com.nour.ieeeevent.databinding.FragmentSheetNameBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel



class SheetNameFragment : Fragment() {

     lateinit var binding : FragmentSheetNameBinding
      val viewModel : SheetNameViewModel by sharedViewModel ()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSheetNameBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel.sheetNameIsTrue.observe(viewLifecycleOwner,this::observeSheetNameIsTrue)

    }

    private fun observeSheetNameIsTrue(isTrue : Boolean){
        if(isTrue){
            downloadAllAttenderToLocalDB()
            goToHomeFragment() }
        else showToastNotCorrectName()
    }

    private fun downloadAllAttenderToLocalDB() {
        val repository : Repository by inject()
        val sheetName = binding.sheetName.text.toString()
        repository.getAllAttender(sheetName)
    }


    private fun goToHomeFragment(){
        findNavController().navigate(R.id.action_sheetNameFragment_to_homeFragment)
    }

    private fun showToastNotCorrectName(){
        Toast.makeText(requireContext(),"Please Enter correct sheet name", Toast.LENGTH_LONG).show()
    }

}