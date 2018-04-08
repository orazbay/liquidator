package sdu.kz.likvidator.data.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sdu.kz.likvidator.data.network.game.GameService;
import sdu.kz.likvidator.data.network.login.LoginService;

/**
 * Created by orazbay on 4/4/18.
 */

public class RetrofitHelper {
    public static String BASE_URL="http://35.187.6.231/api/";

    private static Retrofit retrofit;



    private static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit=createRetrofit();

            return retrofit;
        }
        return retrofit;
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            final Request original = chain.request();

            final Request.Builder requestBuilder = original.newBuilder()
                    .header("Content-Type","application/x-www-form-urlencoded");

            final Request request = requestBuilder.build();

            long t1 = System.nanoTime();
            Log.e("request",String.format("%s | %s",
                    request.url(),request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();



//            return response.newBuilder().build();
            ResponseBody body = response.body();
            String bodyString = body.string();
            MediaType contentType = body.contentType();

            Log.e("response",String.format("Received %s from %s in %.1fms%n",bodyString,request.url(), (t2 - t1) / 1e6d));
            return response.newBuilder().body(ResponseBody.create(contentType, bodyString)).build();

        });

        return httpClient.build();
    }






    public static LoginService getLoginService(){
        return getRetrofit().create(LoginService.class);
    }
    public static GameService getGameService(){
        return getRetrofit().create(GameService.class);
    }


}
