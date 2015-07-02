package com.giantcroissant.android_7funs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CookBookVideoTeachingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CookBookVideoTeachingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookBookVideoTeachingFragment extends Fragment implements YouTubePlayer.OnInitializedListener {


    private CookBook cookBook;
    private View rootView;
    private YouTubeFragment youTubeFragment;
    private OnFragmentInteractionListener mListener;
    public String YOUTUBE_VIDEO_ID = "pKbac2kh0nM";

    public static CookBookVideoTeachingFragment newInstance(CookBook cookBook) {
        CookBookVideoTeachingFragment cookBookVideoTeachingFragment = new CookBookVideoTeachingFragment();
        cookBookVideoTeachingFragment.cookBook = cookBook;
        return cookBookVideoTeachingFragment;
    }

    public CookBookVideoTeachingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cook_book_video_teaching, container, false);

        YouTubePlayerSupportFragment youTubeFragment = new YouTubePlayerSupportFragment();
        youTubeFragment.initialize(Config.DEVELOPER_KEY, this);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.YOUTUBE_PLAYER, youTubeFragment);
        fragmentTransaction.commit();
        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onVideoTeachingButtonPressed(String string) {
        if (mListener != null) {
            mListener.onVideoTeachingFragmentInteraction(string);
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onVideoTeachingFragmentInteraction(String string);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getActivity(), "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }




}
