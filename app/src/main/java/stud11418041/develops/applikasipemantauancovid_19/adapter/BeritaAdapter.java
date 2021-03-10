package stud11418041.develops.applikasipemantauancovid_19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import stud11418041.develops.applikasipemantauancovid_19.R;
import stud11418041.develops.applikasipemantauancovid_19.model.Berita;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.GridItemViewHolder> {

    private List<Berita> data;
    private Context context;

    public BeritaAdapter(Context context, List beritas) {
        this.context = context;
        this.data = beritas;
    }

    public class GridItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvWaktu, tvIsi;

        public GridItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_Judul);
            tvWaktu = itemView.findViewById(R.id.tv_waktu);
            tvIsi = itemView.findViewById(R.id.tv_isi);

        }
    }

    @NonNull
    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.berita_item, parent, false);
        return new GridItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        final Berita berita = data.get(position);

        holder.tvJudul.setText(berita.getJudul());
        holder.tvWaktu.setText(berita.getWaktu());
        holder.tvIsi.setText(berita.getIsi());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
