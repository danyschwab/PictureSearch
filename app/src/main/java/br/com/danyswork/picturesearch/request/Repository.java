package br.com.danyswork.picturesearch.request;

import br.com.danyswork.picturesearch.BuildConfig;
import br.com.danyswork.picturesearch.util.Constants;

public class Repository {

    private static URLBuilder getURLBase() {
        return new URLBuilder(Constants.BASE_URL);
    }

    public void search(String query /*, ResponseListener*/){
        String url = getSearchURL(query);
    }

    private String getSearchURL(String query) {
        URLBuilder urlBuilder = getURLBase();
        urlBuilder.addQueryParameter(Constants.KEY, BuildConfig.API_KEY);
        urlBuilder.addQueryParameter(Constants.QUERY, query);
        urlBuilder.addQueryParameter(Constants.IMAGE_TYPE, Constants.PHOTO);
        return null;
    }
}
