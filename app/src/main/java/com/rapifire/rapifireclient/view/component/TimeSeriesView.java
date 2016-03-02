package com.rapifire.rapifireclient.view.component;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.model.TimeSeriesModel;
import com.rapifire.rapifireclient.view.adapter.ViewWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TimeSeriesView extends RelativeLayout implements ViewWrapper.Binder<TimeSeriesModel> {

    @Bind(R.id.thing_name_text_view)
    TextView nameTextView;

    private boolean alreadyInflated = false;
    private TimeSeriesModel data;

    public TimeSeriesView(Context context) {
        super(context);
    }

    public static TimeSeriesView build(Context context) {
        TimeSeriesView instance = new TimeSeriesView(context);
        instance.onFinishInflate();
        return instance;
    }

    @Override
    public void onFinishInflate() {
        if (!alreadyInflated) {
            alreadyInflated = true;
            inflate(getContext(), R.layout.thing_item_view, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }

    @Override
    public void bind(TimeSeriesModel data) {
        this.data = data;

        //nameTextView.setText(data.name);
        //idTextView.setText(data.thingId);
    }

    //public ThingModel getData() {
    //    return data;
    //}
}
