package com.example.tbc_login_api_davaleba16.welcome

import androidx.navigation.fragment.findNavController
import com.example.tbc_login_api_davaleba16.BaseFragment
import com.example.tbc_login_api_davaleba16.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun setUp() {
        listeners()
    }

    private fun listeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
                findNavController().navigate(action)
            }
            btnRegister.setOnClickListener {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
        }
    }
}