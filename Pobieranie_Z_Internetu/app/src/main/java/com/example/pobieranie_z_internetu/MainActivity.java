package com.example.pobieranie_z_internetu;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout container;
    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        newsList = new ArrayList<>();

        fetchNews();
    }

    private void fetchNews() {
        String url = "http://json.itmargen.com/5TR/";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject newsObject = response.getJSONObject(i);
                                String title = newsObject.getString("title");
                                String description = newsObject.getString("description");
                                String date = newsObject.getString("date");
                                String author = newsObject.getString("author");
                                String content = newsObject.getString("content");
                                newsList.add(new News(title, author, date, description, content));
                            }
                            displayNews();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Błąd przetwarzania danych", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Błąd pobierania danych", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void displayNews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (News news : newsList) {
            View newsView = inflater.inflate(R.layout.item_news, container, false);

            TextView textTitle = newsView.findViewById(R.id.textTitle);
            TextView textDescription = newsView.findViewById(R.id.textDescription);
            TextView textDate = newsView.findViewById(R.id.textDate);
            TextView textAuthor = newsView.findViewById(R.id.textAuthor);
            TextView textContent = newsView.findViewById(R.id.textContent);

            textTitle.setText(news.getTitle());
            textDescription.setText(news.getDescription());
            textDate.setText("Date: " + news.getDate());
            textAuthor.setText("Author: " + news.getAuthor());
            textContent.setText("Content: " + news.getContent());

            container.addView(newsView);
        }
    }
}

class News {
    private String title;
    private String description;
    private String date;
    private String author;
    private String content;

    public News(String title, String description, String date,  String author, String content) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.description = description;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getAuthor() { return author; }
    public String getContent() { return  content; }
}