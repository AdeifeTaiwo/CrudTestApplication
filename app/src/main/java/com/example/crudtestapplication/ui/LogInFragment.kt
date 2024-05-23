package com.example.crudtestapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.crudtestapplication.R
import com.example.crudtestapplication.databinding.FragmentFirstBinding
import com.example.crudtestapplication.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            if (binding.etEmail.text?.isNotEmpty() == true
                && binding.etPass.text?.isNotEmpty() == true
            ) {
                findNavController().navigate(R.id.action_logInFragment_to_jobSearchFragment)
            }
            else {
                Toast.makeText(requireActivity(), "Input field cannot be empty", Toast.LENGTH_SHORT).show();
            }
        }

        binding.llSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_logInFragment_to_sgnUpFragment)
        }
    }

    companion object {

    }
}