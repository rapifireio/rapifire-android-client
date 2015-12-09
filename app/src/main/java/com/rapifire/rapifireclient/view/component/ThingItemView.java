package com.rapifire.rapifireclient.view.component;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.view.adapter.ViewWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ktomek on 05.12.15.
 */
public class ThingItemView extends RelativeLayout implements ViewWrapper.Binder<ThingModel> {

    @Bind(R.id.name_text_view)
    TextView nameTextView;

    @Bind(R.id.id_text_view)
    TextView idTextView;

    private boolean alreadyInflated = false;
    private ThingModel data;

    public ThingItemView(Context context) {
        super(context);
    }

    public static ThingItemView build(Context context) {
        ThingItemView instance = new ThingItemView(context);
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
    public void bind(ThingModel data) {
        this.data = data;

        nameTextView.setText(data.name);
        idTextView.setText(data.thingId);
    }

    public ThingModel getData() {
        return data;
    }
}
