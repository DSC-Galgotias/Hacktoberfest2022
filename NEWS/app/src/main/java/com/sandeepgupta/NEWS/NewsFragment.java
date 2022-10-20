package com.sandeepgupta.NEWS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sandeepgupta.NEWS.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsFragment extends Fragment {
    private static final String URL =
            "https://newsapi.org/v2/top-headlines?country=in&sortBy=publishedAt&apiKey=35eced8c13cd4021917025285f9c55c4";
    public NewsAdapter adapter;
    public ListView covidListView;
    TextView loadingHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        EditText searchText = (EditText)rootView.findViewById(R.id.searchText);
        Button searchButton = (Button)rootView.findViewById(R.id.searchButton);
        loadingHome = (TextView)rootView.findViewById(R.id.loadingHome);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = searchText.getText().toString().trim().toLowerCase();

                if(search.equalsIgnoreCase("")||search==null) {

                }
                else{


                    loadingHome.setVisibility(View.VISIBLE);
                    adapter.clear();
                String url = "https://newsapi.org/v2/everything?q=".concat(search).concat("&sortBy=publishedAt&sortBy=relevance&apiKey=35eced8c13cd4021917025285f9c55c4");
                RequestQueue queue = Volley.newRequestQueue(getContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET,url , null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                ArrayList<News> news = new ArrayList<>();


                                JSONArray jsonArray = response.optJSONArray("articles");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject list = jsonArray.optJSONObject(i);
                                    String title = list.optString("title");
                                    String description = list.optString("description");
                                    String url = list.optString("url");
                                    String urlToImage = list.optString("urlToImage");
                                    String publishedAt = list.optString("publishedAt");

                                    news.add(new News(title, description, url, urlToImage, publishedAt));

                                }
                                if (getActivity()!=null) {

                                    adapter = new NewsAdapter(getContext(), news);
                                    covidListView = (ListView) getView().findViewById(R.id.list);
                                    loadingHome.setVisibility(View.GONE);
                                    covidListView.setAdapter(adapter);
                                }
//                        covidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//                            }
//                        });

                            }

                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
//                        TextView statusTextView = getView().findViewById(R.id.status);
//                        statusTextView.setText("error response");

                            }
                        }){

                    /**
                     * Passing some request headers
                     */
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        //headers.put("Content-Type", "application/json");
                        headers.put("User-Agent","Mozilla/5.0");
                        return headers;
                    }};

                queue.add(jsonObjectRequest);
            }}
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<News> news = new ArrayList<>();


                        JSONArray jsonArray = response.optJSONArray("articles");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject list = jsonArray.optJSONObject(i);
                            String title = list.optString("title");
                            String description = list.optString("description");
                            String url = list.optString("url");
                            String urlToImage = list.optString("urlToImage");
                            String publishedAt = list.optString("publishedAt");

                            news.add(new News(title, description, url, urlToImage, publishedAt));

                        }
                        if (getActivity()!=null) {

                            adapter = new NewsAdapter(getContext(), news);
                            covidListView = (ListView) getView().findViewById(R.id.list);
                            loadingHome.setVisibility(View.GONE);
                            covidListView.setAdapter(adapter);
                        }
//                        covidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//                            }
//                        });

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
//                        TextView statusTextView = getView().findViewById(R.id.status);
//                        statusTextView.setText("error response");
                    }
                }){

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("User-Agent","Mozilla/5.0");
                return headers;
            }};

        queue.add(jsonObjectRequest);



                return rootView ;

    }




}
