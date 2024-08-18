package t.hkb.designhoroscope.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import t.hkb.designhoroscope.R;
import t.hkb.designhoroscope.JSONApiResponse.fetchData;

public class Tomorrow extends Fragment {

    private String star;
    TextView discriptionView,compatibilityView,colorView,moodView,luckyNumView,luckyTimeView,andText;
    ProgressBar progressBar;
    ImageView imageFirst,imageSecond;
    android.support.v7.widget.Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.tabsfragment, container, false);

        discriptionView =(TextView) view.findViewById(R.id.todayView);
//        andText =(TextView) view.findViewById(R.id.andText);
        compatibilityView  =(TextView) view.findViewById(R.id.compatibilityView);
        progressBar =(ProgressBar) view.findViewById(R.id.progressBar1);
        colorView =(TextView) view.findViewById(R.id.colorView);
        moodView =(TextView) view.findViewById(R.id.moodView);
        luckyNumView =(TextView) view.findViewById(R.id.luckyNumView);
        luckyTimeView =(TextView) view.findViewById(R.id.luckyTimeView);
        imageFirst = (ImageView)view.findViewById(R.id.imageFirst);
        imageSecond = (ImageView)view.findViewById(R.id.imageSecond);
        star = getActivity().getIntent().getExtras().getString("message");

        progressBar.setVisibility(View.VISIBLE);
        fetchData fetchData = new fetchData(star,"tomorrow",discriptionView,compatibilityView,colorView,moodView,luckyNumView,luckyTimeView, imageFirst,imageSecond, progressBar);
        fetchData.execute();
        return view;

    }
}
