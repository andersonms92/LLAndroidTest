// Generated by view binder compiler. Do not edit!
package com.llandroidtest.presentation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.llandroidtest.presentation.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentPullRequestsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout headerLayout;

  @NonNull
  public final RecyclerView rvPullRequests;

  @NonNull
  public final TextView tvClosedCount;

  @NonNull
  public final TextView tvOpenedCount;

  private FragmentPullRequestsBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout headerLayout, @NonNull RecyclerView rvPullRequests,
      @NonNull TextView tvClosedCount, @NonNull TextView tvOpenedCount) {
    this.rootView = rootView;
    this.headerLayout = headerLayout;
    this.rvPullRequests = rvPullRequests;
    this.tvClosedCount = tvClosedCount;
    this.tvOpenedCount = tvOpenedCount;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentPullRequestsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentPullRequestsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_pull_requests, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentPullRequestsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.header_layout;
      LinearLayout headerLayout = ViewBindings.findChildViewById(rootView, id);
      if (headerLayout == null) {
        break missingId;
      }

      id = R.id.rv_pull_requests;
      RecyclerView rvPullRequests = ViewBindings.findChildViewById(rootView, id);
      if (rvPullRequests == null) {
        break missingId;
      }

      id = R.id.tv_closed_count;
      TextView tvClosedCount = ViewBindings.findChildViewById(rootView, id);
      if (tvClosedCount == null) {
        break missingId;
      }

      id = R.id.tv_opened_count;
      TextView tvOpenedCount = ViewBindings.findChildViewById(rootView, id);
      if (tvOpenedCount == null) {
        break missingId;
      }

      return new FragmentPullRequestsBinding((ConstraintLayout) rootView, headerLayout,
          rvPullRequests, tvClosedCount, tvOpenedCount);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
