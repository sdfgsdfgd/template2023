package net.sdfgsdfg.luxr.di

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import net.sdfgsdfg.luxr.data.spacex.SpaceXAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    // region API
    private const val BASE_URL = "https://api.spacexdata.com/"

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
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
    ): Retrofit = with(Retrofit.Builder()) {
        baseUrl(BASE_URL)
        client(getClient(interceptors))
        addConverterFactory(converterFactory)
        build()
    }

    @Provides
    @Singleton
    fun provideSpaceXApi(retrofit: Retrofit): SpaceXAPI = retrofit.create(SpaceXAPI::class.java)
    // endregion

    // region Configs & Helpers
    private fun getClient(interceptors: Set<Interceptor>): OkHttpClient =
        with(OkHttpClient.Builder()) {
            connectTimeout(15000, TimeUnit.MILLISECONDS)
            readTimeout(15000, TimeUnit.MILLISECONDS)
            writeTimeout(15000, TimeUnit.MILLISECONDS)
            addInterceptors(interceptors)
            build()
        }

    private fun OkHttpClient.Builder.addInterceptors(interceptors: Set<Interceptor>) {
        interceptors.forEach { addInterceptor(it) }
    }
    // endregion
}
