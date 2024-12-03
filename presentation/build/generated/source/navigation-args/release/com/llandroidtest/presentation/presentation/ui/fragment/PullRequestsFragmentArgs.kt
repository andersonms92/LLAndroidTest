package com.llandroidtest.presentation.presentation.ui.fragment

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class PullRequestsFragmentArgs(
  public val owner: String,
  public val repo: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("owner", this.owner)
    result.putString("repo", this.repo)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("owner", this.owner)
    result.set("repo", this.repo)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): PullRequestsFragmentArgs {
      bundle.setClassLoader(PullRequestsFragmentArgs::class.java.classLoader)
      val __owner : String?
      if (bundle.containsKey("owner")) {
        __owner = bundle.getString("owner")
        if (__owner == null) {
          throw IllegalArgumentException("Argument \"owner\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"owner\" is missing and does not have an android:defaultValue")
      }
      val __repo : String?
      if (bundle.containsKey("repo")) {
        __repo = bundle.getString("repo")
        if (__repo == null) {
          throw IllegalArgumentException("Argument \"repo\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"repo\" is missing and does not have an android:defaultValue")
      }
      return PullRequestsFragmentArgs(__owner, __repo)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): PullRequestsFragmentArgs {
      val __owner : String?
      if (savedStateHandle.contains("owner")) {
        __owner = savedStateHandle["owner"]
        if (__owner == null) {
          throw IllegalArgumentException("Argument \"owner\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"owner\" is missing and does not have an android:defaultValue")
      }
      val __repo : String?
      if (savedStateHandle.contains("repo")) {
        __repo = savedStateHandle["repo"]
        if (__repo == null) {
          throw IllegalArgumentException("Argument \"repo\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"repo\" is missing and does not have an android:defaultValue")
      }
      return PullRequestsFragmentArgs(__owner, __repo)
    }
  }
}
