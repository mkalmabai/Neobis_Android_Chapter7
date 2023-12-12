package com.example.lorby.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lorby.R
import com.example.lorby.databinding.FragmentMailAuthBinding
import com.example.lorby.databinding.FragmentMainBinding
import com.example.lorby.databinding.ReturnAlertDialogBinding

class MainFragment : Fragment() {
    private val args: MainFragmentArgs by navArgs()
    private lateinit var binding:FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.welcome.text = args.title
        binding.returnButton.setOnClickListener {
            callDialog()
        }
    }
    private fun callDialog() {
        val dialogBinding = ReturnAlertDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        dialogBinding.buttonNo.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.buttonYes.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_mainFragment_to_splashScreenFragment)
        }
    }
}