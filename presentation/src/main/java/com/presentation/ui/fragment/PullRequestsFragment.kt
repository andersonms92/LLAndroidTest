package com.presentation.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.domain.model.PullRequest
import com.presentation.adapter.PullRequestsAdapter
import com.presentation.databinding.FragmentPullRequestsBinding
import com.domain.usecase.Resource
import com.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullRequestsFragment : Fragment() {

    private var _binding: FragmentPullRequestsBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var adapter: PullRequestsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPullRequestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        val args = PullRequestsFragmentArgs.fromBundle(requireArguments())
        sharedViewModel.getPullRequests(owner = args.owner, repo = args.repo)
        sharedViewModel.getPullRequestsClosed(owner = args.owner, repo = args.repo)

    }

    private fun setupRecyclerView() {
        adapter = PullRequestsAdapter(mutableListOf()) { url ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
        binding.rvPullRequests.adapter = adapter
        binding.rvPullRequests.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        sharedViewModel.pullRequests.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> showOpenPullRequests(resource.data)
                is Resource.Error -> showError()
            }
        }
        sharedViewModel.pullRequestsClosed.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> showClosedPullRequests(resource.data)
                is Resource.Error -> showClosedError()
            }
        }
    }

    private fun showLoading() {
        binding.progressBarLoading.visibility = View.VISIBLE
        binding.tvEmptyMessage.visibility = View.GONE
    }

    private fun showOpenPullRequests(pullRequests: List<PullRequest>) {
        binding.progressBarLoading.visibility = View.GONE
        adapter.submitList(pullRequests)
        binding.tvOpenedCount.text = "${pullRequests.size} opened"
        updateEmptyMessageVisibility()
    }

    private fun showError() {
        binding.progressBarLoading.visibility = View.GONE
        binding.tvOpenedCount.text = "0 opened"
        adapter.submitList(emptyList())
        updateEmptyMessageVisibility()
    }

    private fun showClosedPullRequests(pullRequests: List<PullRequest>) {
        binding.progressBarLoading.visibility = View.GONE
        binding.tvClosedCount.text = "/ ${pullRequests.size} closed"
    }

    private fun showClosedError() {
        binding.progressBarLoading.visibility = View.GONE
        binding.tvClosedCount.text = "/ 0 closed"
    }

    private fun updateEmptyMessageVisibility() {
        val openedCount = (sharedViewModel.pullRequests.value as? Resource.Success)?.data?.size ?: 0

        binding.tvEmptyMessage.visibility = if (openedCount == 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}