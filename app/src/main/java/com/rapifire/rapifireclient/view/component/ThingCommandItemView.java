package com.rapifire.rapifireclient.view.component;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.domain.model.ProductCommandType;
import com.rapifire.rapifireclient.view.adapter.ViewWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ThingCommandItemView extends RelativeLayout implements ViewWrapper.Binder<ProductCommandModel> {

    @Bind(R.id.command_name_text_view)
    TextView nameTextView;

    @Bind(R.id.command_visibility_text_view)
    TextView visibilityTextView;

    @Bind(R.id.command_payload_text_view)
    TextView payloadTextView;

    @Bind(R.id.command_text_icon)
    TextView textIcon;

    @Bind(R.id.command_binary_icon)
    TextView binaryIconIcon;

    private boolean alreadyInflated = false;
    private ProductCommandModel data;

    public ThingCommandItemView(Context context) {
        super(context);
    }

    public static ThingCommandItemView build(Context context) {
        ThingCommandItemView instance = new ThingCommandItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    @Override
    public void onFinishInflate() {
        if (!alreadyInflated) {
            alreadyInflated = true;
            inflate(getContext(), R.layout.thing_command_item_view, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }

    @Override
    public void bind(ProductCommandModel data) {
        this.data = data;

        textIcon.setVisibility(View.GONE);
        binaryIconIcon.setVisibility(View.GONE);

        if(ProductCommandType.TEXT.equals(data.getType())) {
            textIcon.setVisibility(View.VISIBLE);
        } else {
            binaryIconIcon.setVisibility(View.VISIBLE);
        }


        nameTextView.setText(data.getName());
        visibilityTextView.setText(data.getVisibility());
        payloadTextView.setText(data.getPayload());
    }

    public ProductCommandModel getData() {
        return data;
    }
}
