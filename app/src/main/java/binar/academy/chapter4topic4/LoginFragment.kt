package binar.academy.chapter4topic4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import binar.academy.chapter4topic4.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPrefRegis: SharedPreferences
    private lateinit var sharedPrefLogin: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val sharedNameRegis = "userRegister"
    private val sharedNameLogin = "userLogin"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // It will fetch by default the related activity
        sharedPrefRegis = requireActivity().getSharedPreferences(sharedNameRegis, 0)
        sharedPrefLogin = requireActivity().getSharedPreferences(sharedNameLogin, 0)
        editor = sharedPrefLogin.edit()

        // Button sign in
        binding.btnLogin.setOnClickListener {
            signIn()
        }

        // Option sign up
        binding.tvRegister.setOnClickListener {
            optionRegister()
        }
    }

    // Method for user sign in
    private fun signIn() {
        val userLog = binding.etUserlog.text.toString()
        val passLog = binding.etPasslog.text.toString()
        val userReg = sharedPrefRegis.getString("username", "")
        val passReg = sharedPrefRegis.getString("password", "")

        when {
            userReg == "" && passReg == "" -> {
                Toast.makeText(requireContext(), "You're not registered !", Toast.LENGTH_SHORT).show()
            }
            userLog == userReg && passLog == passReg -> {
                editor.putString("username", userLog)
                editor.putString("password", passLog)
                editor.apply()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else -> {
                Toast.makeText(requireContext(), "The username or password is incorrect !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // It will redirect to Register Fragment
    private fun optionRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}