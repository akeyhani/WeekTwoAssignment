package com.example.weektwoassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weektwoassignment.databinding.FragmentLoginBinding
import androidx.navigation.fragment.findNavController
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
            if (u.isBlank() || p.isBlank()) {
                binding.usernameLayout.error = if (u.isBlank()) "Required" else null
                binding.passwordLayout.error = if (p.isBlank()) "Required" else null
            } else {
                binding.usernameLayout.error = null
                binding.passwordLayout.error = null
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        // listen to the logout flag
        val handle = findNavController().currentBackStackEntry?.savedStateHandle
        handle?.getLiveData<Boolean>("logged_out")?.observe(viewLifecycleOwner) { loggedOut ->
            if (loggedOut == true) {
                Snackbar.make(binding.root, "Successfully logged out", Snackbar.LENGTH_SHORT).show()
                handle.remove<Boolean>("logged_out")
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}