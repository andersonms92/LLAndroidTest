package com.llandroidtest.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llandroidtest.R
import com.llandroidtest.presentation.adapter.UserRepositoryAdapter
import com.llandroidtest.domain.model.UserRepository

class UserRepositoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserRepositoryAdapter

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

        val repositories = listOf(
            UserRepository(
                userPhotoUrl = "https://via.placeholder.com/64",
                userName = "chaoslune",
                name = "Anderson Matos",
                repositoryName = "Luiza Labs Android Test",
                repositoryDescription = "A test repository for Android development.",
                forksCount = 120,
                likesCount = 300
            ),
            UserRepository(
                userPhotoUrl = "https://via.placeholder.com/64",
                userName = "johndoe",
                name = "John Doe",
                repositoryName = "Awesome Kotlin Project",
                repositoryDescription = "An amazing project built with Kotlin.",
                forksCount = 75,
                likesCount = 450
            )
        )

        adapter = UserRepositoryAdapter(repositories) { repository ->
            val action = UserRepositoryFragmentDirections
                .actionUserRepositoryToPullRequests(repository.repositoryName)
            this.view?.let { findNavController(it).navigate(action) }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}