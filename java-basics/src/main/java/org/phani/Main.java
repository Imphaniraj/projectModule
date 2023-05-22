package org.phani;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String [] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
        var api = retrofit.create(JSONPlaceholderAPI.class);
        if (args.length == 0) {
            System.out.println(Lib.getGreeting());
        } else {
            if (args[0].equals("example")) {
                var httpLib = new HttpLib();
                System.out.println(httpLib.getExampleDotCom());
            }
            if (args[0].equals("posts")) {
                api.getPosts().execute().body().forEach(post -> {
                    System.out.println(post.getTitle());
                });
            }

            if (args[0].equals("photos")) {
                api.getPhotos().execute().body().forEach(photo -> {
                    System.out.println(photo.getUrl());
                });
            }
        }
    }
}
