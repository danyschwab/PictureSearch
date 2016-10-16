package br.com.danyswork.picturesearch.request;


import java.util.Map;

import br.com.danyswork.picturesearch.model.Pictures;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

interface PixabayService {

    @GET("./")
    Call<Pictures> search(@QueryMap Map<String, String> options);
}
