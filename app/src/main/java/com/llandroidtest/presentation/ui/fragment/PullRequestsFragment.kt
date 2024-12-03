package com.llandroidtest.presentation.ui.fragment

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
import com.llandroidtest.R
import com.llandroidtest.databinding.FragmentPullRequestsBinding
import com.llandroidtest.databinding.FragmentUserRepositoryBinding
import com.llandroidtest.presentation.adapter.PullRequestsAdapter
import com.llandroidtest.presentation.viewmodel.Resource
import com.llandroidtest.presentation.viewmodel.SharedViewModel
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
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val pullRequests = resource.data
                    adapter.submitList(pullRequests)
                    "${pullRequests.size} opened".also { binding.tvOpenedCount.text = it }
                 }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        sharedViewModel.pullRequestsClosed.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBarLoading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBarLoading.visibility = View.GONE
                    val pullRequests = resource.data.size
                    "/ $pullRequests closed".also { binding.tvClosedCount.text = it }
                }
                is Resource.Error -> {
                    binding.progressBarLoading.visibility = View.GONE
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        sharedViewModel.resetPagination()
//    }

}