package com.example.lorby.ui.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lorby.R
import com.example.lorby.databinding.FragmentMailAuthBinding
import com.example.lorby.databinding.FragmentRegisterBinding
import com.example.lorby.model.Data

class MailAuthFragment : Fragment() {
    private lateinit var  binding: FragmentMailAuthBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentMailAuthBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notArrivedLetter.setOnClickListener {

            val data: Uri? = requireActivity().intent?.data
            Data.token = data?.getQueryParameter("token").toString()
            if (Data.token!=null){
                Toast.makeText(requireContext(), "TOKEN not NULL", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "not token", Toast.LENGTH_SHORT).show()
            }
//            findNavController().navigate(R.id.action_mailAuthFragment_to_mainFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}