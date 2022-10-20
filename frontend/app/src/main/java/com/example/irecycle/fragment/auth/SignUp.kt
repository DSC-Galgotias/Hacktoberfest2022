package com.example.recycle.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.irecycle.R
import com.example.irecycle.databinding.FragmentSignUpBinding


class SignUp : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    companion object
    {
        lateinit var phoneNumber:String
        lateinit var profilename:String
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.GetotpSignUp.setOnClickListener {
            phoneNumber=binding.SignUpMobileNo.text.toString()
            var mobileNo=binding.SignUpMobileNo.text.toString()
            var name=binding.SignUpNameOtp.text.toString()
            profilename=name
            if(mobileNo.length<10)
            {
                binding.MobileNoSignUp.helperText="Please Enter Valid Mobile Number"
                binding.textInputLayout.helperText=""
            }
            else if(name.isEmpty())
            {
                binding.textInputLayout.helperText="Please Enter The Name"
                binding.MobileNoSignUp.helperText=""
            }
            else
            {
                findNavController().navigate(R.id.otpFragment)
            }


        }
        return binding.root
    }


}