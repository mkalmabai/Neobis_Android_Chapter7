package com.example.lorby.ui.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lorby.R
import com.example.lorby.databinding.FragmentMailAuthBinding
import com.example.lorby.databinding.LetterAlertDialogBinding
import com.example.lorby.utils.Data

class MailAuthFragment : Fragment() {
    private lateinit var  binding: FragmentMailAuthBinding
    private val args: MailAuthFragmentArgs by navArgs()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentMailAuthBinding.inflate(inflater,container,false)
        return binding.root
    }
    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.authText.text = getString(R.string.sendingLetter, args.mail)

        if (requireActivity().intent.action == Intent.ACTION_VIEW) {
            val data: Uri? = requireActivity().intent?.data
            Data.token = data?.getQueryParameter("token").toString()
            if (Data.token!=null){
//                Toast.makeText(requireContext(), Data.token, Toast.LENGTH_SHORT).show()
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://164.90.185.42:8081/auth/activate?token=${Data.token}"))
                startActivity(webIntent)
                findNavController().navigate(R.id.action_mailAuthFragment_to_mainFragment)
            }else{
                Toast.makeText(requireContext(), "NULL", Toast.LENGTH_SHORT).show()
            }
            }
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.notArrivedLetter.setOnClickListener{
         callDialogLetter()
        }
    }
    private fun callDialogLetter() {
        val dialogBinding = LetterAlertDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())
        dialogBinding.textAlert.text = getString(R.string.alertDialogLetter, args.mail)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        dialogBinding.buttonClose.setOnClickListener {
            dialog.dismiss()
        }
    }

}