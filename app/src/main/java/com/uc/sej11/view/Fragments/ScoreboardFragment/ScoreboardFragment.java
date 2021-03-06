package com.uc.sej11.view.Fragments.ScoreboardFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.model.UserLevel;
import com.uc.sej11.view.Fragments.MateriFragment.MateriViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreboardFragment extends Fragment {

    private ScoreboardViewModel scoreboardViewModel;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;
    private ScoreboardAdapter scoreboardAdapter;

    private MateriViewModel materiViewModel;

    private static final String TAG = "ScoreboardFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScoreboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreboardFragment newInstance(String param1, String param2) {
        ScoreboardFragment fragment = new ScoreboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scoreboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_scoreboard);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        scoreboardViewModel = new ViewModelProvider(getActivity()).get(ScoreboardViewModel.class);
        scoreboardViewModel.init(helper.getAccessToken());
        scoreboardViewModel.getAll_level();
        scoreboardViewModel.getResultData().observe(getActivity(), showLevel);

        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }

    List<UserLevel.AllLevel> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<UserLevel> showLevel = new Observer<UserLevel>() {
        @Override
        public void onChanged(UserLevel userLevel) {
            Log.d(TAG, "Hello from showLevel");
            results = userLevel.getAll_level();
            scoreboardAdapter = new ScoreboardAdapter(getActivity());
            scoreboardAdapter.setDataList(results);
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(scoreboardAdapter);
        }
    };


}