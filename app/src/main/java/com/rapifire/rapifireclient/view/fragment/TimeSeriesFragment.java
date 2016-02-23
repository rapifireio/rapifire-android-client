package com.rapifire.rapifireclient.view.fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.ChartItemModel;
import com.rapifire.rapifireclient.mvp.presenter.TimeSeriesPresenter;
import com.rapifire.rapifireclient.mvp.view.TimeSeriesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ktomek on 09.12.15.
 */
public class TimeSeriesFragment extends Fragment implements TimeSeriesView {

    @Inject
    TimeSeriesPresenter mTimeSeriesPresenter;

    @Bind(R.id.line_chart)
    LineChart mLineChart;
    @Bind(R.id.pie_chart)
    PieChart mPieChart;

    public TimeSeriesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.timeseries_fragment, container, false);
            ButterKnife.bind(this, contentView);
        }
        mTimeSeriesPresenter.subscribe(this);
        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimeSeriesPresenter.loadTimeSeries();
    }

    @Override
    public void onDestroyView() {
        mTimeSeriesPresenter.unsubscribe(this);
        super.onDestroyView();
    }

    @Override
    public void showMessage(final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message).setTitle(getString(R.string.warning));
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showMessage(int stringId) {
        this.showMessage(getString(stringId));
    }
    
    @Override
    public void showProgress(boolean show) {
//        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setLineData(List<ChartItemModel> data) {
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            final ChartItemModel item = data.get(i);
            xVals.add(item.name);
            yVals.add(new Entry(Double.valueOf(item.value).floatValue(), i));
        }
        LineDataSet set1 = new LineDataSet(yVals, "Time Series");

        set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleSize(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setFillAlpha(65);
        set1.setFillColor(Color.BLACK);

        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the datasets

        LineData lineData = new LineData(xVals, dataSets);
        mPieChart.setVisibility(View.GONE);
        mLineChart.setVisibility(View.VISIBLE);
        mLineChart.setData(lineData);
        mLineChart.invalidate();
    }

    @Override
    public void setPieData(List<ChartItemModel> data) {
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            final ChartItemModel item = data.get(i);
            xVals.add(item.name);
            yVals.add(new Entry(Double.valueOf(item.value).floatValue(), i));
        }
        PieDataSet dataSet = new PieDataSet(yVals, "Time Series");

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        // create a data object with the datasets
        PieData pieData = new PieData(xVals, dataSet);

        mPieChart.setVisibility(View.VISIBLE);
        mLineChart.setVisibility(View.GONE);
        mPieChart.setData(pieData);
        mPieChart.invalidate();
    }
}
