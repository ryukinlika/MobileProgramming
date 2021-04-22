package id.ac.umn.week11_29240;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitConfig {
    @GET("posts")
    Call<ArrayList<PostModel>> getPosts();
}
