package binar.academy.chapter4topic4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import binar.academy.chapter4topic4.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val sharedDataRegister = "userRegister"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences(sharedDataRegister, Context.MODE_PRIVATE)
        editor = sharedPref.edit()

        // Button sign up
        binding.btnRegister.setOnClickListener {
            signUp()
        }

        // Option sign in
        binding.tvSignin.setOnClickListener {
            optionLogin()
        }
    }

    // Method for user sign up
    fun signUp() {
        val fullname = binding.etUserfullname.text.toString()
        val username = binding.etUserreg.text.toString()
        val pass = binding.etPassreg.text.toString()
        val confirm = binding.etConfirmreg.text.toString()

        when {
            pass.equals(confirm) -> {
                editor.putString("fullname", fullname)
                editor.putString("username", username)
                editor.putString("password", pass)
                editor.apply()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            else -> {
                Toast.makeText(requireContext(), "The password confirmation doesn't match with password !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // It will redirect to Login Fragment
    private fun optionLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
}