package stud11418041.develops.applikasipemantauancovid_19;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTClient {
    private static APIBerita REST_CLIENT;
    private static final String BASE_URL="http://192.168.43.4:8080/";
    static {
        setupRestClient();
    }

    private RESTClient() {
    }

    public static APIBerita get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        REST_CLIENT = retrofit.create(APIBerita.class);
    }
}