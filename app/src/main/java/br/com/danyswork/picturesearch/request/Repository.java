package br.com.danyswork.picturesearch.request;

import br.com.danyswork.picturesearch.util.Constants;

public class Repository {

    private static URLBuilder getURLBase() {
        return new URLBuilder(Constants.BASE_URL);
    }

}
