package com.giantcroissant.android_7funs;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CookBookPhotoTeachingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CookBookPhotoTeachingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookBookPhotoTeachingFragment extends Fragment implements ObservableScrollView.Callback {


    private ObservableScrollView mScrollView;
    private ImageView mImageView;
    private FrameLayout mImageFrameLayout;
    private TextView ingredientContentText;
    private TextView sauceContentText;
    private TextView stepContentText;
    TextView lookCountView;
    TextView collectCountView;
    ImageButton collectCookBookButton;

    private LinearLayout mContentLinearLayout;
    private OnFragmentInteractionListener mListener;

    private View rootView;
    private CookBook cookBook;

    public CookBookPhotoTeachingFragment() {
    }

    public static final CookBookPhotoTeachingFragment newInstance(CookBook cookBook) {
        CookBookPhotoTeachingFragment cookBookPhotoTeachingFragment =  new CookBookPhotoTeachingFragment();
        cookBookPhotoTeachingFragment.cookBook = cookBook;

        return cookBookPhotoTeachingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cook_book_photo_teaching, container, false);

        getAllView();
        setData();
        setupScrollView();

        return rootView;
    }


    public void getAllView()
    {
        mScrollView = (ObservableScrollView) rootView.findViewById(R.id.notify_scroll_view);

        mImageView = (ImageView) rootView.findViewById(R.id.image_view);
        mImageFrameLayout = (FrameLayout) rootView.findViewById(R.id.image_frame_layout);

        mContentLinearLayout = (LinearLayout) rootView.findViewById(R.id.content_linear_layout);

        ingredientContentText = (TextView) rootView.findViewById(R.id.ingredient_content_text);
        sauceContentText = (TextView) rootView.findViewById(R.id.sauce_content_text);
        stepContentText = (TextView) rootView.findViewById(R.id.step_content_text);

        lookCountView = (TextView) rootView.findViewById(R.id.cookbook_view_count_text);
        collectCountView = (TextView) rootView.findViewById(R.id.collectedcount_text);
        collectCookBookButton = (ImageButton) rootView.findViewById(R.id.collectCookBookButton);
    }

    public void setData()
    {
        ingredientContentText.setText(cookBook.getIngredient());
        sauceContentText.setText(cookBook.getSauce());
        stepContentText.setText(cookBook.getStep());
        lookCountView.setText(String.valueOf(cookBook.getViewedPeopleCount()));
        collectCountView.setText(String.valueOf(cookBook.getCollectedPeopleCount()));
        if(cookBook.getIsCollected())
        {
            collectCookBookButton.setImageResource(R.drawable.heart);
        }
        else
        {
            collectCookBookButton.setImageResource(R.drawable.heart_outline);
        }
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


    private void setupScrollView() {
        mScrollView.setCallback(this);

        ViewTreeObserver viewTreeObserver = mScrollView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // get size
//                    int toolbarLinearLayoutHeight = mToolbarLinearLayout.getHeight();
                    int imageHeight = mImageView.getHeight();

                    // adjust image frame layout height
                    ViewGroup.LayoutParams layoutParams = mImageFrameLayout.getLayoutParams();
                    if (layoutParams.height != imageHeight) {
                        layoutParams.height = imageHeight;
                        mImageFrameLayout.setLayoutParams(layoutParams);
                    }

                    // adjust top margin of content linear layout
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) mContentLinearLayout.getLayoutParams();
                    if (marginLayoutParams.topMargin != + imageHeight) {            //toolbarLinearLayoutHeight + imageHeight) {
                        marginLayoutParams.topMargin =  + imageHeight;               //toolbarLinearLayoutHeight + imageHeight;
                        mContentLinearLayout.setLayoutParams(marginLayoutParams);
                    }

                    // call onScrollChange to update initial properties.
                    onScrollChanged(0, 0, 0, 0);
                }
            });
        }
    }

    @Override
    public void onScrollChanged(int left, int top, int oldLeft, int oldTop) {
        // get scroll y
        int scrollY = mScrollView.getScrollY();

        // translate image and toolbar
        ViewCompat.setTranslationY(mImageFrameLayout, scrollY * 0.0f);
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


}
