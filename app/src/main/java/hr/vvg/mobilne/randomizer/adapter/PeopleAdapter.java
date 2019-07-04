package hr.vvg.mobilne.randomizer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import hr.vvg.mobilne.randomizer.R;
import hr.vvg.mobilne.randomizer.model.People;
import hr.vvg.mobilne.randomizer.model.Result;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private People peoples = null;
    private Context context;

    public PeopleAdapter(People peoples, Context context) {
        this.context = context;
        this.peoples = peoples;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView personName;
        private TextView personEmail;
        private ImageView personPicture;


        public ViewHolder(View view) {
            super(view);
            personName = view.findViewById(R.id.person_name);
            personEmail = view.findViewById(R.id.person_email);
            personPicture = view.findViewById(R.id.personImageView);
        }
    }

    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.people_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleAdapter.ViewHolder viewHolder, final int i) {

        Result person = peoples.getResults().get(i);
        final String personName = person.getName().getFirst().toUpperCase() + " " + person.getName().getLast().toUpperCase();
        final String personEmail = person.getEmail();
        final String personPicture = person.getPicture().getMedium();

        viewHolder.personName.setText(personName);
        viewHolder.personEmail.setText("Kontakt:\n" + personEmail);

        Glide.with(context)
                .load(personPicture)
                //.placeholder(R.drawable.logo)
                .into(viewHolder.personPicture);

    }

    @Override
    public int getItemCount() {
        return peoples.getResults().size();
    }


}
