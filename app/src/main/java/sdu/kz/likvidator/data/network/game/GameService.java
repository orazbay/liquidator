package sdu.kz.likvidator.data.network.game;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sdu.kz.likvidator.data.network.ParameterNames;

public interface GameService {
    @GET("getgame")
    Single<GetGameResponse> getGame(
                    @Header("Authorization") String auth
            );
    @POST("creategame")
    Single<CreateGameResponse> createGame(
            @Header("Authorization") String auth,
            @Query(ParameterNames.TITLE) String title
    );
}
