package kaan.template2023.di

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kaan.template2023.data.RetrofitProvider
import kaan.template2023.data.spacex.SpaceXAPI
import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val BASE_URL = "https://api.spacexdata.com/"

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            //.add(BigDecimalAdapter)
            .build()

    @Singleton
    @Provides
    fun provideMoshiConverter(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Singleton
    @IntoSet
    @Provides
    fun provideOkHttpProfilerInterceptor(): Interceptor = OkHttpProfilerInterceptor()

    @Singleton
    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): Retrofit = RetrofitProvider(
        BASE_URL,
        converterFactory,
        interceptors.toList()   // replace with listOf() to disable OkHttp debugging
    ).retrofit

    @Provides
    @Singleton
    fun provideSpaceXApi(retrofit: Retrofit): SpaceXAPI = retrofit.create(SpaceXAPI::class.java)
}