package com.example.recycle.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.irecycle.R
import com.example.irecycle.databinding.FragmentLogInBinding


class LogIn : Fragment() {
    lateinit var binding: FragmentLogInBinding
    companion object
    {
        var phoneNumber=""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false)
        binding.getOtpLogin.setOnClickListener {
            phoneNumber=binding.logInMobileNo.text.toString()
            if(binding.logInMobileNo.text?.length == 10){
                findNavController().navigate(R.id.otpFragment)
            }
            else{
                binding.textInputLayout.helperText = "Enter valid phone number"
            }
        }
        return binding.root
    }
    private fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun onResume() {
        super.onResume()

    }

}