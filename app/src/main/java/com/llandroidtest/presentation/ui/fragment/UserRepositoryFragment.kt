package com.llandroidtest.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llandroidtest.R
import com.llandroidtest.presentation.adapter.UserRepositoryAdapter
import com.llandroidtest.databinding.FragmentUserRepositoryBinding
import com.llandroidtest.presentation.viewmodel.Resource
import com.llandroidtest.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRepositoryFragment : Fragment() {

    private var _binding: FragmentUserRepositoryBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var adapter: UserRepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        lifecycle.addObserver(FragmentLifecycleObserver {
            observeViewModel()
        })

        if (sharedViewModel.allRepositories.isEmpty()) {
            sharedViewModel.getRepositories(query = "language:java", page = 1)
        } else {
             adapter.updateData(sharedViewModel.allRepositories)
        }
    }

    private fun setupRecyclerView() {
        adapter = UserRepositoryAdapter(emptyList()) { repository ->
            val action = UserRepositoryFragmentDirections
                .actionUserRepositoryToPullRequests(
                    owner = repository.owner.login,
                    repo = repository.name
                )
            findNavController().navigate(action)
        }
        binding.recyclerViewUserRepositories.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUserRepositories.layoutManager = layoutManager

        binding.recyclerViewUserRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0 && !sharedViewModel.isLoading) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 5) {
                        sharedViewModel.getRepositories(query = "language:java", page = sharedViewModel.currentPage)
                    }
                }
            }
        })
    }

    private fun observeViewModel() {
        viewLifecycleOwnerLiveData.observe(viewLifecycleOwner) { viewLifecycleOwner ->
            sharedViewModel.repositories.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        binding.progressBarLoading.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.progressBarLoading.visibility = View.GONE
                        val repositories = resource.data
                        adapter.updateData(repositories)
                    }

                    is Resource.Error -> {
                        binding.progressBarLoading.visibility = View.GONE
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}