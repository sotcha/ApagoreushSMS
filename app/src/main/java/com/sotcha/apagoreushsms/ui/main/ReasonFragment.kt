package com.sotcha.apagoreushsms.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sotcha.apagoreushsms.BuildConfig
import com.sotcha.apagoreushsms.R
import com.sotcha.apagoreushsms.databinding.ItemReasonBinding
import com.sotcha.apagoreushsms.databinding.ReasonFragmentBinding
import com.sotcha.apagoreushsms.model.Reason
import com.sotcha.apagoreushsms.model.User

class ReasonFragment : Fragment() {
    companion object {
        fun newInstance() = ReasonFragment()
    }

    var listener: ReasonFragmentListener? = null

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ReasonFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ReasonFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = MainViewModelFactory(activity!!)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.load(activity!!)

        binding.topText.visibility = if (BuildConfig.SHOW_MORE_INFO) View.VISIBLE else View.GONE

        initReasons(binding.reasonsWrapper)
        initUser(viewModel.user)
    }

    private fun initUser(user: User) {
        binding.userName.text = user.name
        binding.userAddress.text = user.address
    }

    private fun initReasons(parent: ViewGroup) {
        val inflater = LayoutInflater.from(activity)
        for (reason in viewModel.reasons) {
            val view = inflater.inflate(R.layout.item_reason, parent, false)
            renderReason(view, reason)
            parent.addView(view)
        }
    }

    private fun renderReason(view: View, reason: Reason) {
        val binding = ItemReasonBinding.bind(view)
        binding.reasonButton.text = "${reason.id}.  ${reason.smallDescription}"
        binding.reasonButton.setOnClickListener { onClickReason(reason) }

        binding.description.text = reason.description
        binding.description.visibility = View.GONE
    }

    private fun onClickReason(reason: Reason) {
        listener?.onSendSms("${reason.id} ${viewModel.user.name} ${viewModel.user.address}")
    }

    fun toggleInfo() {
        binding.reasonsWrapper.children.forEach { child ->
            val description = child.findViewById<TextView>(R.id.description)
            description.visibility =
                if (description.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }


    interface ReasonFragmentListener {
        fun onSendSms(text: String)
    }

}
