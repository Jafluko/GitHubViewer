package com.example.githubviewer.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.githubviewer.R
import com.example.githubviewer.databinding.DetailInfoFragmentBinding
import com.example.githubviewer.databinding.RepositoriesListFragmentBinding
import com.example.githubviewer.viewmodel.DetailInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailInfoFragment : Fragment(R.layout.detail_info_fragment) {

    private lateinit var viewBinding: DetailInfoFragmentBinding
    private val viewModel by viewModels<DetailInfoViewModel>()

    companion object {
        fun newInstance() = DetailInfoFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DetailInfoFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(DetailInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}