package sdu.kz.likvidator.data.network.game;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sdu.kz.likvidator.data.network.ParameterNames;
import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.network.base.User;

public interface GameService {
    @GET("getgame")
    Single<GetGameResponse> getGame(
                    @Header("Authorization") String auth
            );
    @POST("creategame")
    Single<GetGameResponse> createGame(
            @Header("Authorization") String auth,
            @Query(ParameterNames.TITLE) String title
    );
    @POST("deletegame")
    Single<BaseResponse> deleteGame(
            @Header("Authorization") String auth
    );
    @POST("joingame")
    Single<BaseResponse> joinGame(
            @Header("Authorization") String auth,
            @Query("code") String code);




    @POST("verifyuser")
    Single<VerifyUserResponse> verifyUser(
            @Header(ParameterNames.AUTHORIZATION) String auth,
            @Query(ParameterNames.EMAIL) String email);



    @POST("startgame")
    Single<BaseResponse> startGame(
            @Header(ParameterNames.AUTHORIZATION) String auth);
    @POST("finishgame")
    Single<BaseResponse> finishGame(
            @Header(ParameterNames.AUTHORIZATION) String auth);


    @GET("getdetails")
    Single<User> getProfile(
            @Header("Authorization") String auth
    );


}
