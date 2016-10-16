package br.com.danyswork.picturesearch.request;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.danyswork.picturesearch.BuildConfig;
import br.com.danyswork.picturesearch.model.Pictures;
import br.com.danyswork.picturesearch.util.Constants;
import br.com.danyswork.picturesearch.util.Utils;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private Context mContext;
    private PixabayService mService;
    private Call<Pictures> mSearch;

    public Repository(Context context) {

        this.mContext = context;

        //setup cache
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);


        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(mInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(PixabayService.class);
    }

    private Map<String, String> createQueryParans(String query) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEY, BuildConfig.API_KEY);
        map.put(Constants.QUERY, query);
        map.put(Constants.IMAGE_TYPE, Constants.PHOTO);
        return map;
    }

    public void search(String query, Callback<Pictures> callback) {
        Map<String, String> options = createQueryParans(query);
        mSearch = mService.search(options);
        mSearch.enqueue(callback);
    }

    public void cancelSearch() {
        if (mSearch != null) {
            mSearch.cancel();
        }
    }

    private final Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (Utils.isNetworkAvailable(mContext)) {
                int maxAge = 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };
}
