package com.example.githubviewer.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubviewer.R
import com.example.githubviewer.databinding.RepositoriesListFragmentBinding
import com.example.githubviewer.model.adapters.ReposAdapter
import com.example.githubviewer.viewmodel.RepositoriesListViewModel
import com.example.githubviewer.viewmodel.RepositoriesListViewModel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesListFragment : Fragment(R.layout.repositories_list_fragment) {

    private lateinit var viewBinding: RepositoriesListFragmentBinding
    private val viewModel by viewModels<RepositoriesListViewModel>()
    private val adapter: ReposAdapter = ReposAdapter()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    viewBinding.progressBar.visibility = View.VISIBLE
                    viewBinding.recyclerView.visibility = View.GONE
                }
                is State.Loaded -> {
                    adapter.setList(state.repos)
                    viewBinding.progressBar.visibility = View.GONE
                    viewBinding.recyclerView.visibility = View.VISIBLE
                }
                is State.Error -> {

                }
                is State.Empty -> {

                }
                else -> {}
            }
        }
    }

}