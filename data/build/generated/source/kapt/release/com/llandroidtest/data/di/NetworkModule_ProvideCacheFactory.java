// Generated by Dagger (https://dagger.dev).
package com.llandroidtest.data.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import okhttp3.Cache;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NetworkModule_ProvideCacheFactory implements Factory<Cache> {
  private final Provider<Context> contextProvider;

  public NetworkModule_ProvideCacheFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public Cache get() {
    return provideCache(contextProvider.get());
  }

  public static NetworkModule_ProvideCacheFactory create(Provider<Context> contextProvider) {
    return new NetworkModule_ProvideCacheFactory(contextProvider);
  }

  public static Cache provideCache(Context context) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCache(context));
  }
}