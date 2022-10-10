package com.example.notesfirebase.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesfirebase.R
import com.example.notesfirebase.auth.LoginActivity
import com.example.notesfirebase.databinding.FragmentProfileBinding
import com.example.notesfirebase.model.UserModel
import com.example.notesfirebase.utlis.Config
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding



class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        Config.showDialog(requireContext())
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.phoneNumber!!)
            .get()
            .addOnSuccessListener {
                if (it.exists()){
                    val data = it.getValue(UserModel::class.java)

                    binding.name.setText(data!!.name.toString())
                    binding.email.setText(data.email.toString())

                    Config.hideDialog()
                }
            }

        binding.logout.setOnClickListener{

            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_logout)
            bottomSheet.setCancelable(true)
            val yes = bottomSheet.findViewById<View>(R.id.tvLogoutYes)
            val no = bottomSheet.findViewById<View>(R.id.tvLogoutNo)

            yes?.setOnClickListener {
//                logout()
                bottomSheet.dismiss()
            }
            no?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()

        }

        return binding.root
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()

        return inflater.inflate(R.layout.fragment_blank, container, false)

    }
}