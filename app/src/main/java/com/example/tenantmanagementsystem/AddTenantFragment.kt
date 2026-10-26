package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AddTenantFragment : Fragment(R.layout.fragment_add_tenant) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tenantNameEditText =
            view.findViewById<EditText>(R.id.tenantNameEditText)

        val phoneEditText =
            view.findViewById<EditText>(R.id.phoneEditText)

        val rentEditText =
            view.findViewById<EditText>(R.id.rentEditText)

        val saveButton =
            view.findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val name = tenantNameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val rentText = rentEditText.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || rentText.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all tenant details",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val rent = rentText.toDoubleOrNull()

            if (rent == null || rent < 0) {
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid rent amount",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val tenant = Tenant(
                name = name,
                phone = phone,
                rent = rentText
            )

            TenantRepository.addTenant(tenant)

            Toast.makeText(
                requireContext(),
                "Tenant saved successfully!",
                Toast.LENGTH_SHORT
            ).show()

            val action =
                AddTenantFragmentDirections
                    .actionAddTenantFragmentToViewTenantsFragment(tenant)

            findNavController().navigate(action)
        }
    }
}