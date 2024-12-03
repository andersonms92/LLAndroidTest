package com.llandroidtest.presentation.presentation.ui.fragment

import android.os.Bundle
import androidx.navigation.NavDirections
import com.llandroidtest.presentation.R
import kotlin.Int
import kotlin.String

public class UserRepositoryFragmentDirections private constructor() {
  private data class ActionUserRepositoryToPullRequests(
    public val owner: String,
    public val repo: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_userRepository_to_pullRequests

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("owner", this.owner)
        result.putString("repo", this.repo)
        return result
      }
  }

  public companion object {
    public fun actionUserRepositoryToPullRequests(owner: String, repo: String): NavDirections =
        ActionUserRepositoryToPullRequests(owner, repo)
  }
}
