package com.giantcroissant.android_7funs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class CookBookPhotoTeachingFragment extends Fragment implements ObservableScrollView.Callback {

    private OnFragmentInteractionListener mListener;

    private ObservableScrollView mScrollView;
    private ImageView mImageView;
    private FrameLayout mImageFrameLayout;
    private TextView ingredientContentText;
    private TextView sauceContentText;
    private TextView stepContentText;
//    private LinearLayout mToolbarLinearLayout;

    private LinearLayout mContentLinearLayout;

    View rootView;

    public CookBookPhotoTeachingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cook_book_photo_teaching, container, false);
//        // set content view
//        setContentView(R.layout.fragment_cook_book_photo_teaching);

        // view matching
        mScrollView = (ObservableScrollView) rootView.findViewById(R.id.notify_scroll_view);

        mImageView = (ImageView) rootView.findViewById(R.id.image_view);
        mImageFrameLayout = (FrameLayout) rootView.findViewById(R.id.image_frame_layout);

        mContentLinearLayout = (LinearLayout) rootView.findViewById(R.id.content_linear_layout);

        ingredientContentText = (TextView) rootView.findViewById(R.id.ingredient_content_text);
        sauceContentText = (TextView) rootView.findViewById(R.id.sauce_content_text);
        stepContentText = (TextView) rootView.findViewById(R.id.step_content_text);
        
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbarLinearLayout = (LinearLayout) rootView.findViewById(R.id.toolbar_linear_layout);

        // more setup
        setupScrollView();
//        setupToolbar();
        return rootView;
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
                        marginLayoutParams.topMargin = + imageHeight;               //toolbarLinearLayoutHeight + imageHeight;
                        mContentLinearLayout.setLayoutParams(marginLayoutParams);
                    }

                    // call onScrollChange to update initial properties.
                    onScrollChanged(0, 0, 0, 0);
                }
            });
        }
    }

//    private void setupToolbar() {
//        // set ActionBar as Toolbar
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_accent));
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//    }

    @Override
    public void onScrollChanged(int left, int top, int oldLeft, int oldTop) {
        // get scroll y
        int scrollY = mScrollView.getScrollY();

        // choose appropriate y
        float newY = Math.max(mImageView.getHeight(), scrollY);

        // translate image and toolbar
//        ViewCompat.setTranslationY(mToolbarLinearLayout, newY);
        ViewCompat.setTranslationY(mImageFrameLayout, scrollY * 0.7f);
    }

}
