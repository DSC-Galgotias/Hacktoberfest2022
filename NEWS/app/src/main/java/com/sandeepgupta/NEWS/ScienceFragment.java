package com.sandeepgupta.NEWS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ScienceFragment extends Fragment {
    private static final String URL =
            "https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=35eced8c13cd4021917025285f9c55c4";
    public NewsAdapter adapter;
    public ListView covidListView;
    TextView loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_fragment, container, false);


        loading = (TextView)rootView.findViewById(R.id.loading);

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
                                loading.setVisibility(View.GONE);
                                covidListView.setAdapter(adapter);
                            }
//                            covidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                String url = adapter.getItem(position).getUrl();
//                                Intent i = new Intent(Intent.ACTION_VIEW);
//                                i.setData(Uri.parse(url));
//                                startActivity(i);
//
//
//                                }
//                            });

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


return rootView;
    }



}


