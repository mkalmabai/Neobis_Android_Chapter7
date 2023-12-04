package com.example.lorby.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lorby.R
import com.example.lorby.databinding.FragmentMailAuthBinding
import com.example.lorby.databinding.FragmentRegisterBinding

class MailAuthFragment : Fragment() {
    private lateinit var  binding: FragmentMailAuthBinding
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
            findNavController().navigate(R.id.action_mailAuthFragment_to_mainFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}