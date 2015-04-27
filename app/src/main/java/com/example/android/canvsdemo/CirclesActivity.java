package com.example.android.canvsdemo;

/**
 * Created by kingHenry on 4/17/15.
 */

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.CycleInterpolator;
import 	android.animation.AnimatorListenerAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class CirclesActivity extends Fragment {
    private OnFragmentInteractionListener mListener;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    // Views
    ImageView ivDrawable;
    ImageView ivDrawable2;
    ImageView ivDrawable3;
    ImageView ivDrawable4;
    Button btStyle1;
    Button btStyle2;
    Button btStyle3;
    Button btStyle4;

    CircularProgressDrawable drawable1,drawable2,drawable3,drawable4;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currentAnimation1 != null) {
                currentAnimation1.cancel();
            }
            switch (v.getId()) {

                case R.id.bt_style_3:
                    currentAnimation1 = prepareStyle3Animation(drawable1);
                    currentAnimation2 = prepareStyle2Animation(drawable2);
                    currentAnimation3 = prepareStyle1Animation(drawable3);
                    currentAnimation4 = prepareStyle4Animation(drawable4);
                    break;

                default:
                    currentAnimation1 = prepareStyle3Animation(drawable1);
                    break;

            }
            currentAnimation1.start();
            currentAnimation2.start();
            currentAnimation3.start();
            currentAnimation4.start();
        }
    };

    Animator currentAnimation1;
    Animator currentAnimation2;
    Animator currentAnimation3;
    Animator currentAnimation4;
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_circles);

    }*/
public static CirclesActivity newInstance(String param1, String param2){
    CirclesActivity fragment = new CirclesActivity();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
}


public CirclesActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        //LinearLayout llLayout=(LinearLayout)inflater.inflate(R.layout.activity_circles, container, false);


        // Replace LinearLayout by the type of the root element of the layout you're trying to load
       LinearLayout llLayout=(LinearLayout)inflater.inflate(R.layout.activity_circles, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here

        // Content of previous onCreate() here
        // ...
        ivDrawable = (ImageView) llLayout.findViewById(R.id.iv_drawable);
        ivDrawable2 = (ImageView) llLayout.findViewById(R.id.iv_drawable2);
        ivDrawable3 = (ImageView) llLayout.findViewById(R.id.iv_drawable3);
        ivDrawable4 = (ImageView) llLayout.findViewById(R.id.iv_drawable4);
        //btStyle2 = (Button) findViewById(R.id.bt_style_2);
        btStyle3 = (Button) llLayout.findViewById(R.id.bt_style_3);
        //btStyle4 = (Button) findViewById(R.id.bt_style_4);

        drawable1 = new CircularProgressDrawable.Builder()
                .setRingWidth(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(getResources().getColor(android.R.color.darker_gray))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(getResources().getColor(android.R.color.holo_blue_light))
                .create();
        ivDrawable.setImageDrawable(drawable1);
        drawable2 = new CircularProgressDrawable.Builder()
                .setRingWidth(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(getResources().getColor(android.R.color.darker_gray))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(getResources().getColor(android.R.color.holo_blue_light))
                .create();
        ivDrawable2.setImageDrawable(drawable2);
        drawable3 = new CircularProgressDrawable.Builder()
                .setRingWidth(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(getResources().getColor(android.R.color.darker_gray))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(getResources().getColor(android.R.color.holo_blue_light))
                .create();
        ivDrawable3.setImageDrawable(drawable3);
        drawable4 = new CircularProgressDrawable.Builder()
                .setRingWidth(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(getResources().getColor(android.R.color.darker_gray))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(getResources().getColor(android.R.color.holo_blue_light))
                .create();
        ivDrawable4.setImageDrawable(drawable4);
        hookUpListeners();
        // Don't use this method, it's handled by inflater.inflate() above :
        // setContentView(R.layout.activity_layout);

        // The FragmentActivity doesn't contain the layout directly so we must use our instance of     LinearLayout :

        // Instead of :
        // findViewById(R.id.someGuiElement);
        return llLayout; // We must return the loaded Layout
        // Don't use this method, it's handled by inflater.inflate() above :
        // setContentView(R.layout.activity_layout);

        // The FragmentActivity doesn't contain the layout directly so we must use our instance of     LinearLayout :

        // Instead of :
        // findViewById(R.id.someGuiElement);

    }
    public void setUp(int fragmentId, ImageView ivDrawable){


    }


/*
    @Override
    public void onResume() {
        super.onResume();

    }

    */

    private void hookUpListeners() {
        ivDrawable.setOnClickListener(listener);
        ivDrawable2.setOnClickListener(listener);
        ivDrawable3.setOnClickListener(listener);
        ivDrawable4.setOnClickListener(listener);

        btStyle3.setOnClickListener(listener);


    }

    /**
     * This animation was intended to keep a pressed state of the Drawable
     *
     * @return Animation
     */
    private Animator preparePressedAnimation(CircularProgressDrawable drawable) {
        Animator animation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
                drawable.getCircleScale(), 0.65f);
        animation.setDuration(120);
        return animation;
    }

    /**
     * This animation will make a pulse effect to the inner circle
     *
     * @return Animation
     */
    private Animator preparePulseAnimation(CircularProgressDrawable drawable) {
        AnimatorSet animation = new AnimatorSet();

        Animator firstBounce = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
                drawable.getCircleScale(), 0.88f);
        firstBounce.setDuration(300);
        firstBounce.setInterpolator(new CycleInterpolator(1));
        Animator secondBounce = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
                0.75f, 0.83f);
        secondBounce.setDuration(300);
        secondBounce.setInterpolator(new CycleInterpolator(1));
        Animator thirdBounce = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
                0.75f, 0.80f);
        thirdBounce.setDuration(300);
        thirdBounce.setInterpolator(new CycleInterpolator(1));

        animation.playSequentially(firstBounce, secondBounce, thirdBounce);
        return animation;
    }

    /**
     * Style 1 animation will simulate a indeterminate loading while taking advantage of the inner
     * circle to provide a progress sense
     *
     * @return Animation

    private Animator prepareStyle1Animation() {
    AnimatorSet animation = new AnimatorSet();

    final Animator indeterminateAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0, 3600);
    indeterminateAnimation.setDuration(3600);

    Animator innerCircleAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0f, 0.75f);
    innerCircleAnimation.setDuration(3600);
    innerCircleAnimation.addListener(new AnimatorListenerAdapter() {
    @Override
    public void onAnimationStart(Animator animation) {
    drawable.setIndeterminate(true);
    }

    @Override
    public void onAnimationEnd(Animator animation) {
    indeterminateAnimation.end();
    drawable.setIndeterminate(false);
    drawable.setProgress(0);
    }
    });

    animation.playTogether(innerCircleAnimation, indeterminateAnimation);
    return animation;
    }
     */
    /**
     * Style 2 animation will fill the outer ring while applying a color effect from red to green
     *
     * @return Animation
     */
    private Animator prepareStyle2Animation(CircularProgressDrawable drawable) {
        AnimatorSet animation = new AnimatorSet();

        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY,
                0f, .12f);
        progressAnimation.setDuration(1600);
        progressAnimation.setInterpolator(new OvershootInterpolator());


        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_green_light));
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(1600);

        animation.playTogether(progressAnimation, colorAnimator);
        return animation;
    }

    /**
     * Style 3 animation will turn a 3/4 animation with Anticipate/Overshoot interpolation to a
     * blank waiting - like state, wait for 2 seconds then return to the original state
     *
     * @return Animation
     */

    private Animator prepareStyle1Animation(CircularProgressDrawable drawable) {
        AnimatorSet animation = new AnimatorSet();

        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY,
                0f, .80f);
        progressAnimation.setDuration(1600);
        progressAnimation.setInterpolator(new OvershootInterpolator());


        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_green_light));
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(1600);

        animation.playTogether(progressAnimation, colorAnimator);
        return animation;
    }
    private Animator prepareStyle3Animation(CircularProgressDrawable drawable) {
        AnimatorSet animation = new AnimatorSet();

        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY,
                0f, .20f);
        progressAnimation.setDuration(1600);
        progressAnimation.setInterpolator(new OvershootInterpolator());


        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_green_light));
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(1600);

        animation.playTogether(progressAnimation, colorAnimator);
        return animation;
    }

    private Animator prepareStyle4Animation(CircularProgressDrawable drawable) {
        AnimatorSet animation = new AnimatorSet();

        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY,
                0f, .66f);
        progressAnimation.setDuration(1600);
        progressAnimation.setInterpolator(new OvershootInterpolator());


        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_green_light));
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(1600);

        animation.playTogether(progressAnimation, colorAnimator);
        return animation;
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
        public void onFragmentInteraction(Uri uri);
    }
}

