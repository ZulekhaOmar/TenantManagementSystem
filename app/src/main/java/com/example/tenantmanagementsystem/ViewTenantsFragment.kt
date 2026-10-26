package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import android.content.Intent
import android.net.Uri
import android.widget.Button

class ViewTenantsFragment : Fragment(R.layout.fragment_view_tenants) {

    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allTenantsTextView =
            view.findViewById<TextView>(R.id.allTenantsTextView)

        val tenants = TenantRepository.tenants
        val newTenant = args.newTenant

        val callButton = view.findViewById<Button>(R.id.callButton)

        if (newTenant != null) {
            callButton.visibility = View.VISIBLE

            callButton.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${newTenant.phone}")
                )
                startActivity(intent)
            }
        } else {
            callButton.visibility = View.GONE
        }

        allTenantsTextView.text = if (tenants.isEmpty()) {
            "No tenants have been added yet."
        } else {
            tenants.joinToString(
                separator = "\n\n--------------------\n\n"
            ) { tenant -> tenant.summary() }
        }

        if (newTenant != null) {
            allTenantsTextView.append(
                "\n\nNewly added tenant:\n${newTenant.summary()}"
            )
        }
    }
}