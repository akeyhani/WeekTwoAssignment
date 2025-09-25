package com.example.weektwoassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weektwoassignment.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginButton.setOnClickListener {
            val u = binding.usernameInput.text?.toString().orEmpty()
            val p = binding.passwordInput.text?.toString().orEmpty()

            val userOk = u.isNotBlank()
            val passOk = p.isNotBlank()

            binding.usernameLayout.error = if (!userOk) "Required" else null
            binding.passwordLayout.error = if (!passOk) "Required" else null

            if (userOk && passOk) {
                val nav = findNavController()
                if (nav.currentDestination?.id == R.id.loginFragment) {
                    nav.navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }

        val nav = findNavController()
        val loginEntry = nav.getBackStackEntry(R.id.loginFragment)
        loginEntry.savedStateHandle
            .getLiveData<Boolean>("logged_out")
            .observe(viewLifecycleOwner) { loggedOut ->
                if (loggedOut == true) {
                    Snackbar.make(binding.root, "Successfully logged out", Snackbar.LENGTH_SHORT).show()
                    loginEntry.savedStateHandle.remove<Boolean>("logged_out")
                }
            }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}