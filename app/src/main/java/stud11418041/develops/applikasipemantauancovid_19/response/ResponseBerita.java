package stud11418041.develops.applikasipemantauancovid_19.response;

import java.util.List;

import stud11418041.develops.applikasipemantauancovid_19.model.Berita;

public class ResponseBerita {
    private String error;
    private List<Berita> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Berita> getData() {
        return data;
    }

    public void setData(List<Berita> data) {
        this.data = data;
    }
}
