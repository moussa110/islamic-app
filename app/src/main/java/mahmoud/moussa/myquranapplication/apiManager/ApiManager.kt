package mahmoud.moussa.myquranapplication.apiManager

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiManager {



        private var retrofit:Retrofit?=null;

        private fun getInstance():Retrofit{
            val interceptor = HttpLoggingInterceptor(object:HttpLoggingInterceptor.Logger{
                override fun log(message: String) {
                    Log.e("api",message);
                }
            })
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            if (retrofit ==null){
                 retrofit =Retrofit.Builder().
                    baseUrl("http://api.mp3quran.net/")
                     .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            }
            return retrofit
                ?: Retrofit.Builder().
                        baseUrl("http://api.mp3quran.net/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }

        fun getWebServices(): WebServices {
            return getInstance()
                .create(WebServices::class.java);
        }

}