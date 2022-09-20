package binar.academy.chapter4topic4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import binar.academy.chapter4topic4.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreLog: SharedPreferences
    private lateinit var sharedPreReg: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val sharedDataLogin = "userLogin"
    private val sharedDataRegister = "userRegister"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // It will fetch by default the related activity
        sharedPreLog = requireActivity().getSharedPreferences(sharedDataLogin, Context.MODE_PRIVATE)
        sharedPreReg = requireActivity().getSharedPreferences(sharedDataRegister, Context.MODE_PRIVATE)
        editor = sharedPreLog.edit()

        // Recall data full name to show on Home Fragment
        binding.tvNameout.text = sharedPreReg.getString("fullname", "")

        // Button logout
        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    // Method for clear recent editor data
    private fun logout() {
        editor.clear()
        editor.apply()
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }
}