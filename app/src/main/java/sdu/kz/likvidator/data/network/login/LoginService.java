package sdu.kz.likvidator.data.network.login;

import org.json.JSONObject;

import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by orazbay on 4/4/18.
 */


public interface LoginService {
    @POST("login")
    Single<LoginResponse> login(
                                @Query("email") String email,
                                @Query("password") String password
            );
    @POST("register")
    Single<LoginResponse> register(@Query("name") String name,
                                @Query("surname") String surname,
                                @Query("email") String email,
                                @Query("password") String password,
                                @Query("c_password") String c_password//tupoi Kurmabak
    );

}
