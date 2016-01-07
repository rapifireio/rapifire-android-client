package com.rapifire.rapifireclient.view.component;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.view.adapter.ViewWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ThingLatestDataItemView extends RelativeLayout implements ViewWrapper.Binder<LatestTimeSeriesModel> {

    @Bind(R.id.series_name_text_view)
    TextView nameTextView;

    @Bind(R.id.series_timestamp_text_view)
    TextView timestampTextView;

    @Bind(R.id.series_value_text_view)
    TextView valueTextView;

    private boolean alreadyInflated = false;
    private LatestTimeSeriesModel data;

    public ThingLatestDataItemView(Context context) {
        super(context);
    }

    public static ThingLatestDataItemView build(Context context) {
        ThingLatestDataItemView instance = new ThingLatestDataItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    @Override
    public void onFinishInflate() {
        if (!alreadyInflated) {
            alreadyInflated = true;
            inflate(getContext(), R.layout.thing_latest_data_item_view, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }

    @Override
    public void bind(LatestTimeSeriesModel data) {
        this.data = data;

        nameTextView.setText(data.getName());
        timestampTextView.setText(String.valueOf(data.getDataTimeMillis()));
        valueTextView.setText(data.getValueAsString());
    }

    public LatestTimeSeriesModel getLatestTimeSeriesModel() {
        return data;
    }
}
