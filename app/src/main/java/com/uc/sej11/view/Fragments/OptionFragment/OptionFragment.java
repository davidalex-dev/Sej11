package com.uc.sej11.view.Fragments.OptionFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.model.User;
import com.uc.sej11.view.Activities.AboutActivity;
import com.uc.sej11.view.Activities.LoginActivity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionFragment extends Fragment {
    TextView txt_name, txt_email, txt_school, txt_city, txt_birthyear;
    Button btn_logout, btn_change, btn_about;
    ImageView avatar;

    private OptionViewModel optionViewModel;
    private SharedPreferenceHelper helper;
    private static final String TAG = "OptionFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OptionFragment newInstance(String param1, String param2) {
        OptionFragment fragment = new OptionFragment();
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
        return inflater.inflate(R.layout.fragment_option, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        optionViewModel = new ViewModelProvider(getActivity()).get(OptionViewModel.class);

        optionViewModel.init(helper.getAccessToken());

        optionViewModel.getUser();
        optionViewModel.getResultUser().observe(getActivity(), showUser);

        btn_logout = view.findViewById(R.id.button_option_logout);
        btn_about = view.findViewById(R.id.button_option_about);
        txt_name = view.findViewById(R.id.textView_option_name);
        txt_email = view.findViewById(R.id.textView_option_email);
        txt_school = view.findViewById(R.id.textView_option_school);
        txt_city = view.findViewById(R.id.textView_option_city);
        txt_birthyear = view.findViewById(R.id.textView_option_birthyear);
        avatar = view.findViewById(R.id.imageView_profile_avatar);

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AboutActivity.class);
                startActivity(i);
            }
        });



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                Toast.makeText(getActivity(), "Logged out.", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        btn_logout.setOnClickListener(view1 -> {

            optionViewModel.logout().observe(requireActivity(), s -> {

                if(!s.isEmpty()){
                    helper.clearPref();

                    //end main activity and go to login activity
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getActivity(), "Logged out.", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }

            });

        });
    }

    private Observer<User> showUser = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            txt_name.setText(user.getName());
            txt_email.setText(user.getEmail());
            txt_birthyear.setText(user.getBirthyear());
            txt_city.setText(user.getCity());
            txt_school.setText(user.getSchool());

            txt_name.setVisibility(View.VISIBLE);
            txt_email.setVisibility(View.VISIBLE);
            txt_birthyear.setVisibility(View.VISIBLE);
            txt_city.setVisibility(View.VISIBLE);
            txt_school.setVisibility(View.VISIBLE);
            avatar.setVisibility(View.VISIBLE);

        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }

}