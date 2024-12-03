package com.llandroidtest.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

    private val binding by lazy { FragmentUserRepositoryBinding.bind(requireView()) }
    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var adapter: UserRepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_repository, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        if (savedInstanceState == null) {
            sharedViewModel.getRepositories(query = "language:java", page = 1)
        }
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.getRepositories(query = "language:java", page = 1)
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

                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 5) {
                        sharedViewModel.getRepositories(query = "language:java")
                    }
                }
            }
        })
    }

    private fun observeViewModel() {
        sharedViewModel.repositories.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val repositories = resource.data
                    adapter.updateData(repositories)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}