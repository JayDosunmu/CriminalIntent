package com.turtlewave.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by TJsMac on 3/30/18.
 */

public class CrimeListFragment extends Fragment {

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

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleTextView;
        private TextView dateTextView;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));

            titleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            dateTextView = (TextView) itemView.findViewById(R.id.crime_date);

            itemView.setOnClickListener(this);
        }

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

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = crimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }
    }
}
