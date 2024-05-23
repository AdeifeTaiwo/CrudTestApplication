package com.example.crudtestapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.crudtestapplication.R
import com.example.crudtestapplication.databinding.FragmentLogInBinding
import com.example.crudtestapplication.databinding.FragmentSgnUpBinding

class SgnUpFragment : Fragment() {

    private var _binding: FragmentSgnUpBinding? = null

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
        _binding = FragmentSgnUpBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignup.setOnClickListener {
            binding.btnSignup.setOnClickListener {
                if (binding.etEmail.text?.isNotEmpty() == true
                    && binding.etPass.text?.isNotEmpty() == true
                    && binding.etFirstName.text?.isNotEmpty() == true
                    && binding.etLastName.text?.isNotEmpty() == true
                ) {
                    findNavController().navigate(R.id.action_sgnUpFragment_to_jobSearchFragment)
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Input field cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SgnUpFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}