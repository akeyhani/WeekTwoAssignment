package com.example.weektwoassignment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LogoutDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                // Send result back to the host (HomeFragment)
                setFragmentResult("logout_request", Bundle().apply { putBoolean("confirmed", true) })
            }
            .setNegativeButton("No") { _, _ ->
                // Send a negative result or just dismiss; no navigation
                setFragmentResult("logout_request", Bundle().apply { putBoolean("confirmed", false) })
            }
            .create()
    }
}