package com.llandroidtest.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llandroidtest.R
import com.llandroidtest.domain.model.PullRequestModel
import com.llandroidtest.presentation.adapter.PullRequestsAdapter

class PullRequestsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PullRequestsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pull_requests, container, false)

        recyclerView = view.findViewById(R.id.rv_pull_requests)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pullRequestModels = listOf(
            PullRequestModel(
                title = "Pull Request 1",
                description = "Description for PR 1",
                username = "chaoslune",
                fullName = "Anderson de Matos"
            ),
            PullRequestModel(
                title = "Pull Request 2",
                description = "Description for PR 2",
                username = "hammerfall",
                fullName = "Robson de Matos"
            )
        )

        adapter = PullRequestsAdapter(pullRequestModels)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}