package com.example.recycle.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.irecycle.R
import com.example.irecycle.databinding.FragmentFirstBinding



class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding=DataBindingUtil.inflate(layoutInflater, R.layout.fragment_first, container, false)
        binding.HaveAnAccount.setOnClickListener {
            findNavController().navigate(R.id.logIn)
        }
        binding.SignUp.setOnClickListener {
            findNavController().navigate(R.id.signUp)
        }
        return binding.root
    }


}