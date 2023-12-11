package com.example.tbc_login_api_davaleba16.register

import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbc_login_api_davaleba16.BaseFragment
import com.example.tbc_login_api_davaleba16.databinding.FragmentRegisterBinding
import com.example.tbc_login_api_davaleba16.model.User
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun setUp() {
        listeners()
        bindObservers()
    }

    private fun listeners() {
        with(binding) {
            btnRegister.setOnClickListener {
                if (checkFields()) {
                    val user = User(editTxtEmail.text.toString(), editTxtPassword.text.toString())
                    viewModel.registerUser(user)
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        with(binding) {
            if (editTxtEmail.text!!.isEmpty()) {
                setError(editTxtEmail)
                return false
            } else if (editTxtUsername.text!!.isEmpty()) {
                setError(editTxtUsername)
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
                viewModel.myResponse.observe(this@RegisterFragment) { response ->
                    if (response.isSuccessful){
                        d("Response", response.code().toString())
                        d("Response", response.body()!!.email)
                        Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }else{
                        Toast.makeText(context, "Something went wrong!\n${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}