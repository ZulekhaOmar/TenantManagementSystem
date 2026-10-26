package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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

        updateDashboard(view)
    }

    override fun onResume() {
        super.onResume()
        view?.let { updateDashboard(it) }
    }

    private fun updateDashboard(view: View) {
        val tenantCount = TenantRepository.tenants.size
        val totalRent = TenantRepository.totalRent()

        view.findViewById<TextView>(R.id.tenantCountTextView).text =
            "Total Tenants: $tenantCount"

        view.findViewById<TextView>(R.id.totalRentTextView).text =
            "Total Rent: KSh ${"%.2f".format(totalRent)}"
    }
}