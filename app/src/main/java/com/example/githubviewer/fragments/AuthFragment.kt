package com.example.githubviewer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubviewer.MainActivity
import com.example.githubviewer.R
import com.example.githubviewer.databinding.AuthFragmentBinding
import com.example.githubviewer.model.KeyValueStorage
import com.example.githubviewer.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.auth_fragment) {

    private lateinit var viewBinding: AuthFragmentBinding
    private val viewModel by viewModels<AuthViewModel>()

    @Inject
    lateinit var store: KeyValueStorage

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            AuthFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar?.hide()
        store.authToken = "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"
        viewBinding.btnAuth.setOnClickListener { viewModel.onSignButtonPressed(/*viewBinding.tokenTextInput.*/) }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*viewModel.state.observe(viewLifecycleOwner, { state ->

        })*/

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.actions.collect { action ->
                when (action) {
                    is AuthViewModel.Action.ShowError ->
                        Toast.makeText(requireContext(), action.message, Toast.LENGTH_LONG).show()
                    is AuthViewModel.Action.RouteToMain -> navigateToRepoList()
                }
            }
        }
    }

    private fun navigateToRepoList() {
        val action = AuthFragmentDirections.actionAuthorization()
        this.findNavController().navigate(action)
    }
}