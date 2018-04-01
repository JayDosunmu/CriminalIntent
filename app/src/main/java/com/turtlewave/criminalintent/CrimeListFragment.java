package com.turtlewave.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by TJsMac on 3/30/18.
 */

public class CrimeListFragment extends Fragment {

    public static final int DEFAULT_TYPE = 0;
    public static final int POLICE_REQUIRED_TYPE = 1;

    private RecyclerView crimeRecyclerView;
    private CrimeAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        crimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        adapter = new CrimeAdapter(crimes);
        crimeRecyclerView.setAdapter(adapter);
    }

    private class CrimeAdapter extends RecyclerView.Adapter<BaseCrimeHolder> {

        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        @NonNull
        @Override
        public BaseCrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            if (viewType == POLICE_REQUIRED_TYPE) {
                return new PoliceRequiredHolder(layoutInflater, parent);
            } else {
                return new CrimeHolder(layoutInflater, parent);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull BaseCrimeHolder holder, int position) {
            Crime crime = crimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (crimes.get(position).isPoliceRequired()) {
                return POLICE_REQUIRED_TYPE;
            }
            return super.getItemViewType(position);
        }
    }

    private abstract class BaseCrimeHolder extends RecyclerView.ViewHolder {

        private Crime crime;

        public BaseCrimeHolder(LayoutInflater inflater, ViewGroup parent, int layout) {
            super(inflater.inflate(layout, parent, false));
        }

        public void bind(Crime crime) {
            this.crime = crime;
        }
    }

    private class CrimeHolder extends BaseCrimeHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView dateTextView;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.list_item_crime);

            titleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            dateTextView = (TextView) itemView.findViewById(R.id.crime_date);

            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(Crime crime) {
            this.crime = crime;
            titleTextView.setText(crime.getTitle());
            dateTextView.setText(crime.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(
                    getActivity(),
                    crime.getTitle() + " clicked!",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private class PoliceRequiredHolder extends BaseCrimeHolder implements View.OnClickListener{

        private TextView titleTextView;
        private TextView dateTextView;
        private Crime crime;
        private Button contactPoliceButton;

        public PoliceRequiredHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.list_item_police_required_crime);

            titleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            dateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            contactPoliceButton = (Button) itemView.findViewById(R.id.contact_police_button);
            contactPoliceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(
                            getActivity(),
                            R.string.police_contacted,
                            Toast.LENGTH_LONG
                    ).show();
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(Crime crime) {
            this.crime = crime;
            titleTextView.setText(crime.getTitle());
            dateTextView.setText(crime.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(
                    getActivity(),
                    crime.getTitle() + " clicked!",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
