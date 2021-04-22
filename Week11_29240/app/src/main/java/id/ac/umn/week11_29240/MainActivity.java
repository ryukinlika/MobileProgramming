package id.ac.umn.week11_29240;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//https://jsonplaceholder.typicode.com/posts
public class MainActivity extends AppCompatActivity {
    RecyclerView rcView;
    PostAdapter adapter;
    protected static String server_url = "https://jsonplaceholder.typicode.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcView = findViewById(R.id.rvPost);
        adapter = new PostAdapter(this);
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitConfig api = retrofit.create(RetrofitConfig.class);
        Call<ArrayList<PostModel>> call = api.getPosts();

        ConnectivityManager connManager = (ConnectivityManager) getSystemService((this.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = null;
        if (connManager != null) {
            networkInfo = connManager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()){
            // no need to create own thread, just use enqueue
            call.enqueue(new Callback<ArrayList<PostModel>>() {
                @Override
                public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(MainActivity.this,
                                response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    ArrayList<PostModel> posts = response.body();

                    adapter.setPosts(posts);
//                    for(PostModel post: posts){
//
//                    }

                }

                @Override
                public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this,
                    "No Connection Available", Toast.LENGTH_LONG).show();
        }
    }
}