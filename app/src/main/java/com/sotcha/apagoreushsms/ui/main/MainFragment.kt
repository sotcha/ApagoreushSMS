package com.sotcha.apagoreushsms.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sotcha.apagoreushsms.databinding.MainFragmentBinding
import com.sotcha.apagoreushsms.model.User

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    var listener: MainFragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        binding.confirmButton.setOnClickListener(this@MainFragment::onSubmitClick)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        loadData()
    }


    private fun loadData() {
        activity?.let {
            viewModel.load(it)

            binding.nameEditText.setText(viewModel.user.name)
            binding.addressEditText.setText(viewModel.user.address)
        }
    }

    private fun resetErrorForm() {
        binding.nameEditTextLayout.isErrorEnabled = false
        binding.addressEditTextLayout.isErrorEnabled = false
    }

    private fun validate(): Boolean {
        resetErrorForm()

        var result = true
        if (binding.nameEditText.text.toString().trim().isEmpty()) {
            binding.nameEditTextLayout.error = "Λείπει το όνομα"
            result = false
        }
        if (binding.addressEditText.text.toString().trim().isEmpty()) {
            binding.addressEditTextLayout.error = "Λείπει η διεύθυνση"
            result = false
        }

        return result
    }


    private fun saveUser() {
        viewModel.user = User(
            binding.nameEditText.text.toString().trim(),
            binding.addressEditText.text.toString().trim()
        )

        activity?.let { viewModel.save(it) }
    }

    private fun onSubmitClick(it: View) {
        if (validate()) {
            activity?.let { saveUser() }
            binding.nameEditText.clearFocus()
            binding.addressEditText.clearFocus()

            listener?.goToReasons(viewModel.user)
        }
    }

    interface MainFragmentListener {
        fun goToReasons(user: User)
    }


}
