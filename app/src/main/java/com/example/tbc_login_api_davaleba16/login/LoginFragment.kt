package com.example.tbc_login_api_davaleba16.login

import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbc_login_api_davaleba16.BaseFragment
import com.example.tbc_login_api_davaleba16.databinding.FragmentLoginBinding
import com.example.tbc_login_api_davaleba16.model.User
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun setUp() {
        listeners()
        bindObservers()
    }

    private fun listeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                if (checkFields()) {
                    val user = User(editTxtEmail.text.toString(), editTxtPassword.text.toString())
                    viewModel.loginUser(user)
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        with(binding) {
            if (editTxtEmail.text!!.isEmpty()) {
                setError(editTxtEmail)
                return false
            } else if (editTxtPassword.text!!.isEmpty()) {
                setError(editTxtPassword)
                return false
            }
        }
        return true
    }

    private fun setError(editText: AppCompatEditText) {
        editText.error = "Please, Fill out the field"
    }

    private fun bindObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.myResponse.observe(this@LoginFragment) { response ->
                    if (response.isSuccessful){
                        Log.d("Response", response.code().toString())
                        Log.d("Response", response.body()!!.email)
                        Toast.makeText(context, "Logged In successfully", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }else{
                        d("Response", response.message())
                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}