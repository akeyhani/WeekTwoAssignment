package com.example.weektwoassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.weektwoassignment.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(i, c, false); return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //  Open dialog
        binding.logoutButton.setOnClickListener {
            LogoutDialogFragment().show(parentFragmentManager, "logout_dialog")
        }
        val nav = findNavController()
        val loginEntry = nav.getBackStackEntry(R.id.loginFragment)

        loginEntry.savedStateHandle
            .getLiveData<Boolean>("logged_out")
            .observe(viewLifecycleOwner) { loggedOut ->
                if (loggedOut == true) {
                    Snackbar.make(binding.root, "Successfully logged out", Snackbar.LENGTH_SHORT).show()
                    // remove so it doesn't show again on rotation/re-entry
                    loginEntry.savedStateHandle.remove<Boolean>("logged_out")
                }
            }

        //  dialog reslut listener
        setFragmentResultListener("logout_request") { _, bundle ->
            val confirmed = bundle.getBoolean("confirmed", false)
            if (confirmed) {
        // Put a flag for LoginFragment to show the snackbar
                findNavController().previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("logged_out", true)
                findNavController().popBackStack(R.id.loginFragment, false)

                // back to login fragment
                findNavController().navigate(
                    R.id.loginFragment,
                    null,
                    navOptions {
                        popUpTo(R.id.homeFragment) { inclusive = true }
                    }
                )
            } }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}