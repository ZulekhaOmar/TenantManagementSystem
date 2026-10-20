package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.addTenantButton).setOnClickListener {
            findNavController().navigate(R.id.addTenantFragment)
        }

        view.findViewById<Button>(R.id.viewTenantsButton).setOnClickListener {
            findNavController().navigate(R.id.viewTenantsFragment)
        }
    }
}