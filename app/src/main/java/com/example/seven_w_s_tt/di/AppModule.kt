package com.example.seven_w_s_tt.di

import com.example.seven_w_s_tt.data.remote.ApiService
import com.example.seven_w_s_tt.data.remote.common.AuthInterceptor
import com.example.seven_w_s_tt.data.remote.common.AuthPreferences
import com.example.seven_w_s_tt.data.repository.AuthRepositoryImpl
import com.example.seven_w_s_tt.data.repository.MapRepositoryImpl
import com.example.seven_w_s_tt.data.repository.MenuRepositoryImpl
import com.example.seven_w_s_tt.domain.repository.AuthRepository
import com.example.seven_w_s_tt.domain.repository.MapRepository
import com.example.seven_w_s_tt.domain.repository.MenuRepository
import com.example.seven_w_s_tt.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

     @Provides
     @Singleton
     fun provideLoggingInterceptor(): HttpLoggingInterceptor {
          return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
     }

     @Provides
     @Singleton
     fun provideOkHttpClient(
          logger: HttpLoggingInterceptor,
          authInterceptor: AuthInterceptor
     ): OkHttpClient {
          val okHttpClient = OkHttpClient.Builder()
          okHttpClient.addInterceptor(logger)
          okHttpClient.addInterceptor(authInterceptor)
          okHttpClient.callTimeout(30, TimeUnit.SECONDS)
          okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
          okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
          okHttpClient.readTimeout(30, TimeUnit.SECONDS)
          val okHttp = okHttpClient.build()
          return okHttp
     }

     @Provides
     @Singleton
     fun provideConvectorFactory(): GsonConverterFactory {
          return GsonConverterFactory.create()
     }

     @Provides
     @Singleton
     fun provideRetrofit(
          converterFactory: GsonConverterFactory,
          okHttpClient: OkHttpClient
     ): Retrofit {
          return Retrofit.Builder()
               .baseUrl(Constants.BASE_URL)
               .addConverterFactory(converterFactory)
               .client(okHttpClient)
               .build()
     }

     @Provides
     @Singleton
     fun provideApiService(retrofit: Retrofit): ApiService {
          return retrofit.create(ApiService::class.java)
     }

     @Provides
     @Singleton
     fun provideAuthRepository(
          apiService: ApiService,
          authPreferences: AuthPreferences
     ): AuthRepository {
          return AuthRepositoryImpl(
               authApiService = apiService,
               authPreferences = authPreferences
          )
     }

     @Provides
     @Singleton
     fun provideMapRepository(
          apiService: ApiService,
     ): MapRepository {
          return MapRepositoryImpl(
               apiService = apiService,
          )
     }

     @Provides
     @Singleton
     fun provideMMenuRepository(
          apiService: ApiService,
     ): MenuRepository {
          return MenuRepositoryImpl(
               apiService = apiService,
          )
     }
}


