package stud11418041.develops.applikasipemantauancovid_19;

import retrofit2.Call;
import retrofit2.http.GET;
import stud11418041.develops.applikasipemantauancovid_19.response.ResponseBerita;

public interface APIBerita {

    @GET("amancovid/berita")
    Call<ResponseBerita> getBerita();
}
