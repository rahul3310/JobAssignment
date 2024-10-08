package process.com.jobassignment.network

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import process.com.jobassignment.api.ApiServices
import process.com.jobassignment.localdb.JobDao
import process.com.jobassignment.localdb.JobRoomDataBase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


const val REQUEST_TIMEOUT = 30L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule @Inject constructor() {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor())

        return builder.build()

    }

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://testapi.getlokalapp.com")

    }

    @Singleton
    @Provides
    fun providesApiService(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): ApiServices {
        return retrofitBuilder.client(okHttpClient).build().create(ApiServices::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): JobRoomDataBase {
        return Room.databaseBuilder(
            context,
            JobRoomDataBase::class.java,
            name = "job_details"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideJobDao(database: JobRoomDataBase): JobDao {
        return database.jobDetailsDao()
    }

}