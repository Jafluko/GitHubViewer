package com.example.githubviewer.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.githubviewer.R
import com.example.githubviewer.databinding.RepositoriesListFragmentBinding
import com.example.githubviewer.viewmodel.RepositoriesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesListFragment : Fragment(R.layout.repositories_list_fragment) {

    private lateinit var viewBinding: RepositoriesListFragmentBinding
    private val viewModel by viewModels<RepositoriesListViewModel>()
    companion object {
        fun newInstance() = RepositoriesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = RepositoriesListFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(RepositoriesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}