package com.example.seng405_mt_201928015;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//Çektiğimiz dataların gözüktüğü activtydir.
// Kullanıcının sayfa numarasına bağlı olarak API'den veri alır ve RecyclerView ile görüntüler.
public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<DataClass> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        int pageNumber = intent.getIntExtra("page_number", 1);

        String url = "https://reqres.in/api/users?page=" + pageNumber;

        userList = new ArrayList<>();
        adapter = new Adapter(this, userList);
        recyclerView.setAdapter(adapter);


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray usersArray = response.getJSONArray("data");
                            for (int i = 0; i < usersArray.length(); i++) {
                                JSONObject userObject = usersArray.getJSONObject(i);
                                String firstName = userObject.getString("first_name");
                                String lastName = userObject.getString("last_name");
                                String email = userObject.getString("email");
                                String avatarUrl = userObject.getString("avatar");
                                DataClass user = new DataClass(firstName, lastName, email, avatarUrl);
                                userList.add(user);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(request);

        //Burada arama yaptıuktan sonra ilk ekrana geri dönebilmesi için bir back button ekledim
        //Buna tıklandığında FirstActivty'ye geri dönüyor
        Button backButton = findViewById(R.id.goback_firstactivty);
        backButton.setOnClickListener(v -> {
            //Bu activityi bitirip first activitye geri dönüyoruz
            finish();
        });
    }
}
