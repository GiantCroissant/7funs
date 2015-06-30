package com.giantcroissant.android_7funs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class CookBookPhotoTeachingFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public CookBookPhotoTeachingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cook_book_photo_teaching, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onPhotoTeachingButtonPressed(String string) {
        if (mListener != null) {
            mListener.onPhotoTeachingFragmentInteraction(string);
        }
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
        public void onPhotoTeachingFragmentInteraction(String string);
    }
}
