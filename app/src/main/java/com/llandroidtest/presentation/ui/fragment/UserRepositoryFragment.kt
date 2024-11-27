package com.llandroidtest.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llandroidtest.R
import com.llandroidtest.presentation.adapter.UserRepositoryAdapter
import com.llandroidtest.data.model.RepositoryResponse
import com.llandroidtest.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserRepositoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserRepositoryAdapter
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_repository, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewUserRepositories)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            sharedViewModel.repositoryList.collect { repositories ->
            }

            sharedViewModel.isLoading.collect { isLoading ->
            }

            sharedViewModel.getRepositories(query = "Kotlin", page = 1)
        }

//        adapter = UserRepositoryAdapter(repositories) { repository ->
//            val action = UserRepositoryFragmentDirections
//                .actionUserRepositoryToPullRequests(repository.repositoryName)
//            this.view?.let { findNavController(it).navigate(action) }
//        }

//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = adapter
    }
}