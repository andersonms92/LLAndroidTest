package com.llandroidtest.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llandroidtest.R
import com.llandroidtest.presentation.adapter.UserRepositoryAdapter
import com.llandroidtest.data.model.RepositoryResponse
import com.llandroidtest.presentation.viewmodel.Resource
import com.llandroidtest.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class UserRepositoryFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel

    //    private lateinit var adapter: RepositoryAdapter
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_repository, container, false)
        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]

//        adapter = RepositoryAdapter()

//        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.getRepositories("language:Kotlin", 1)
        }

        viewModel.repositories.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> Timber.tag("Sucesso").d("${resource.data.data}")
//                    adapter.submitList(resource.data.data)
                is Resource.Loading -> Timber.tag("Loading").d("Continuo carregando")
//                    showLoading()
                is Resource.Error -> Timber.tag("Erro Carregamento").d("Erro no carregamento")
//                    showError(resource.message)
            }
        }

//        viewModel.getRepositories("language:Kotlin", currentPage)

//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (!recyclerView.canScrollVertically(1)) { // Verifica se chegou no fim
//                    currentPage++
//                    viewModel.getRepositories("language:Kotlin", currentPage)
//                }
//            }
//        })
//
//        return inflater.inflate(R.layout.fragment_repository, container, false)
//    }
        return view
    }
}

//class UserRepositoryFragment : Fragment() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: UserRepositoryAdapter
//    private val sharedViewModel: SharedViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_user_repository, container, false)
//        recyclerView = view.findViewById(R.id.recyclerViewUserRepositories)
//
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        adapter = UserRepositoryAdapter(repositories) { repository ->
//            val action = UserRepositoryFragmentDirections
//                .actionUserRepositoryToPullRequests(repository.repositoryName)
//            this.view?.let { findNavController(it).navigate(action) }
//        }
//
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = adapter
//    }
//}