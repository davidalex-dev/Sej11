package com.uc.sej11.view.Fragments.MateriFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.model.Materi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MateriFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MateriFragment extends Fragment {

    private MateriViewModel materiViewModel;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;
    private MateriAdapter materiAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MateriFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MateriFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MateriFragment newInstance(String param1, String param2) {
        MateriFragment fragment = new MateriFragment();
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
        return inflater.inflate(R.layout.fragment_materi, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_materi);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        materiViewModel = new ViewModelProvider(getActivity()).get(MateriViewModel.class);
        materiViewModel.init(helper.getAccessToken());
        materiViewModel.getData();
        materiViewModel.getResultData().observe(getActivity(), showData);

        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }




    List<Materi.Data> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Materi> showData = new Observer<Materi>() {
        @Override
        public void onChanged(Materi materi) {
            results = materi.getData();
            materiAdapter = new MateriAdapter(getActivity());
            materiAdapter.setDataList(results);
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(materiAdapter);

        }
    };

}