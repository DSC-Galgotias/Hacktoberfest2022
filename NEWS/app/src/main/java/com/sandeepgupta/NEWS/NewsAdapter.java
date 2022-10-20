package com.sandeepgupta.NEWS;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class NewsAdapter extends ArrayAdapter<News> {
    TextToSpeech textToSpeech;
    public NewsAdapter(Context context, ArrayList<News> androidFlavors) {

        super(context, 0, androidFlavors);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }


        News currentnews = getItem(position);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = currentnews.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                getContext().startActivity(i);
            }
        });

        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        ImageButton speechButton = (ImageButton)listItemView.findViewById(R.id.speech);
        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak(currentnews.getTitle(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });




        // Find the TextView in the list_item.xml layout with the ID version_name
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);

        URL urlToImage;
        try {
            urlToImage = new URL(currentnews.getUrlToImage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            urlToImage=null;
        }
        if(urlToImage==null)
       {
           image.setVisibility(View.GONE);
       }
        else Picasso.get().load(urlToImage.toString()).into(image);



        TextView title = (TextView) listItemView.findViewById(R.id.title);


        title.setText(currentnews.getTitle());



// Create an English-German translator:
//        TranslatorOptions options =
//                new TranslatorOptions.Builder()
//                        .setSourceLanguage(TranslateLanguage.ENGLISH)
//                        .setTargetLanguage(TranslateLanguage.HINDI)
//                        .build();
//        final Translator englishGermanTranslator =
//                Translation.getClient(options);
//
//        DownloadConditions conditions = new DownloadConditions.Builder()
//                .requireWifi()
//                .build();
//        englishGermanTranslator.downloadModelIfNeeded(conditions)
//                .addOnSuccessListener(
//                        (OnSuccessListener) v -> {
//                            // Model downloaded successfully. Okay to start translating.
//                            // (Set a flag, unhide the translation UI, etc.)
//                            englishGermanTranslator.translate(currentnews.getTitle())
//                                    .addOnSuccessListener(
//                                            (OnSuccessListener) translatedText -> {
//                                                // Translation successful.
//                                                title.setText(translatedText.toString());
//                                            })
//                                    .addOnFailureListener(
//                                            new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception e) {
//                                                    // Error.
//                                                    // ...
//                                                    title.setText(e.toString());
//                                                }
//                                            });
//                        })
//                .addOnFailureListener(
//                        new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Model couldn’t be downloaded or other internal error.
//                                // ...
//                                title.setText("failure");
//                            }
//                        });
//
//




        String datetime = currentnews.getPublishedAt();
        String[] array = datetime.split("T");
        String date = array[0];
        String time = array[1].substring(0,5);
        TextView dateVIEW = (TextView) listItemView.findViewById(R.id.date);
        dateVIEW.setText(date);

        TextView timeView = (TextView)listItemView.findViewById(R.id.time);
        timeView.setText(time);

        TextView description = (TextView)listItemView.findViewById(R.id.description);
        description.setText(currentnews.getDescription());
        TextView readMore = (TextView)listItemView.findViewById(R.id.readMore);
        readMore.setOnClickListener(v -> {
            if(description.getVisibility() == View.GONE){
                description.setVisibility(View.VISIBLE);
            }
            else{
                description.setVisibility(View.GONE);
            }

        });

        return listItemView;
    }

}
