package com.sotcha.apagoreushsms.ui.main

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sotcha.apagoreushsms.R
import com.sotcha.apagoreushsms.databinding.MainFragmentBinding
import com.sotcha.apagoreushsms.model.User
import com.sotcha.apagoreushsms.ui.SimpleDialog

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
        binding.helpButton.setOnClickListener(this@MainFragment::onHelpClick)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.devName.text =
                Html.fromHtml(resources.getString(R.string.dev_name), Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.devName.text = Html.fromHtml(resources.getString(R.string.dev_name))
        }




        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = MainViewModelFactory(activity!!)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

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
            binding.nameEditTextLayout.error = "Λείπει το ονοματεπώνυμο"
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

    private fun onSubmitClick(v: View) {
        if (validate()) {
            activity?.let { saveUser() }
            binding.nameEditText.clearFocus()
            binding.addressEditText.clearFocus()

            listener?.goToReasons(viewModel.user)
        }
    }

    private fun onHelpClick(v: View) {
        SimpleDialog(activity!!).show(R.string.info_dialog_title, R.string.info_dialog_body)
    }


    interface MainFragmentListener {
        fun goToReasons(user: User)
    }

}
