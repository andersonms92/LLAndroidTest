package com.llandroidtest.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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

        sharedViewModel.getRepositories(query = "language:kotlin", page = 1)
    }

    private fun setupRecyclerView() {
        adapter = UserRepositoryAdapter(emptyList()) { repository ->
            Toast.makeText(requireContext(), "Selecionado: ${repository.name}", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerViewUserRepositories.adapter = adapter
        binding.recyclerViewUserRepositories.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        sharedViewModel.repositories.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val repositories = resource.data?.items ?: emptyList()
                    adapter.updateData(repositories)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}