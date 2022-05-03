package com.example.githubviewer.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.githubviewer.R
import com.example.githubviewer.databinding.DetailInfoFragmentBinding
import com.example.githubviewer.databinding.RepositoriesListFragmentBinding
import com.example.githubviewer.viewmodel.DetailInfoViewModel
import com.example.githubviewer.viewmodel.DetailInfoViewModel.*
import dagger.hilt.android.AndroidEntryPoint
import io.noties.markwon.Markwon

@AndroidEntryPoint
class DetailInfoFragment : Fragment(R.layout.detail_info_fragment) {

    private lateinit var viewBinding: DetailInfoFragmentBinding
    private val viewModel by viewModels<DetailInfoViewModel>()
    private val args: DetailInfoFragmentArgs by navArgs()

    private var markdown: Markwon? = null

    companion object {
        fun newInstance() = DetailInfoFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        markdown = Markwon.create(requireContext())
        viewBinding = DetailInfoFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {

                }
                is State.Loaded -> {
                    with(viewBinding) {
                        //link
                        tvLink.text = state.githubRepo.htmlUrl.toString()

                        //license
                        //viewBinding.tvLicense.text = state.githubRepo.license!!.name

                        //stats
                        tvForks.text = state.githubRepo.forks.toString()
                        tvStars.text = state.githubRepo.stargazers.toString()
                        tvWatchers.text = state.githubRepo.watchers.toString()

                        when (state.readmeState) {
                            is ReadmeState.Loading -> {

                            }
                            is ReadmeState.Loaded -> {
                                markdown?.setMarkdown(readMe, state.readmeState.markdown)
                            }
                            is ReadmeState.Empty -> {

                            }
                            is ReadmeState.Error -> {

                            }
                        }
                    }
                }
                is State.Error -> {

                }
                else -> {}
            }
        }

        Log.d("eeee", args.repoId.toString() + " " + args.repoName)
        viewModel.setInfo(args.repoId, args.repoName)
    }

}