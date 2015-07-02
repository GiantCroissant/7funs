package com.giantcroissant.android_7funs;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class YouTubeFragment extends YouTubePlayerSupportFragment implements YouTubePlayer.OnInitializedListener {

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static YouTubeFragment newInstance() {
        YouTubeFragment fragment = new YouTubeFragment();
        fragment.initialize(Config.DEVELOPER_KEY, fragment);
        return fragment;
    }

    public void init(){
//        initialize(Config.DEVELOPER_KEY, this);
    }

    public YouTubeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_you_tube, container, false);
//        init();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onYouTubeButtonPressed(String string) {
        if (mListener != null) {
            mListener.onYouTubeFragmentInteraction(string);
        }

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onYouTubeFragmentInteraction(String string);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo("nCgQDjiotG0");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getActivity(), "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }



}

