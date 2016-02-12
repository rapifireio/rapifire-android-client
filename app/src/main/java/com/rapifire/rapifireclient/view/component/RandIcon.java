package com.rapifire.rapifireclient.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;

/**
 * Created by witek on 13.01.16.
 */
public class RandIcon extends TextView {

    public RandIcon(Context context) {
        super(context);
    }

    public RandIcon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RandIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        float scale = Math.min(((float)w) / 512f, ((float)h) / 512f);
        int hc = Math.abs(generateHashCode(this.getText()));

        int colorGroupIndex = hc % colors.length;
        String[] colorGroup = colors[colorGroupIndex];

        int colorIndex1 = hc % colorGroup.length;
        String c1 = colorGroup[colorIndex1];

        int colorIndex2 = (colorIndex1 + 9) % colorGroup.length;
        String c2 = colorGroup[colorIndex2];


        float strokeWidth1 = (180 + (((hc & 0xff00) >> 8) % 13)) * scale;
        float strokeWidth2 = (180 - (((hc & 0xff00) >> 8) % 13)) * scale;

        float qx1 = (((hc & 0xff000000) >> 24) % 29)*scale;
        float m = 51 + hc % + 359 ;
        int t = hc % 3;

        int z1, z2;

        float z1x1=-10, z1y1=-150, z1qx1=510, z1qy1=156, z1x2=-30, z1y2=610;
        float z2x1=602, z2y1=-150, z2qx1=290, z2qy1=206, z2x2=540, z2y2=525;

        switch(t) {
            case 0:
                z1x1=-10;
                z1y1=-150;
                z1qx1=510;
                z1qy1=156;
                z1x2=-30;
                z1y2=610;
                z2x1=602;
                z2y1=-150;
                z2qx1=100;
                z2qy1=216;
                z2x2=540;
                z2y2=525;
                break;
            case 1:
                z1x1=-10;
                z1y1=-15;
                z1qx1=250;
                z1qy1=156;
                z1x2=600;
                z1y2=610;
                z2x1=602;
                z2y1=-50;
                z2qx1=280;
                z2qy1=286;
                z2x2=-90;
                z2y2=625;
                break;
            case 2:
                z1x1=80;
                z1y1=-150;
                z1qx1=250;
                z1qy1=156;
                z1x2=80;
                z1y2=610;
                z2x1=402;
                z2y1=-100;
                z2qx1=110;
                z2qy1=286;
                z2x2=380;
                z2y2=625;
                break;
        }
        z1x1 *= scale;
        z1y1 *= scale;
        z1qx1 *= scale;
        z1qy1 *= scale;
        z1x2 *= scale;
        z1y2 *= scale;

        z2x1 *= scale;
        z2y1 *= scale;
        z2qx1 *= scale;
        z2qy1 *= scale;
        z2x2 *= scale;
        z2y2 *= scale;

        //z1 = s.path("M"+z1x1+","+z1y1+" Q"+(z1qx1 + qx1)+","+z1qy1+" "+(z1x2-qx1)+","+z1y2).attr({"fill" : "none", "stroke": c1, "strokeWidth": strokeWidth1});
        Path path1 = new Path();
        path1.moveTo(z1x1, z1y1);
        path1.quadTo(z1qx1 + qx1, z1qy1, z1x2 - qx1, z1y2);
        //z1.transform("rotate(" + m + " " + (256 * scale) + " " + (256 * scale) + ")");
        Matrix pathRotator = new Matrix();
        pathRotator.postRotate(m, 256 * scale, 256 * scale);
        path1.transform(pathRotator);

        //z2 = s.path("M"+z2x1+","+z2y1+" Q"+(z2qx1 + qx1)+","+z2qy1+" "+(z2x2+qx1)+","+z2y2).attr({"fill" : "none", "stroke": c2, "strokeWidth": strokeWidth2, "opacity" : 1.0, "mix-blend-mode": "overlay"});
        Path path2 = new Path();
        path2.moveTo(z2x1, z2y1);
        path2.quadTo(z2qx1 + qx1, z2qy1, z2x2 + qx1, z2y2);
        path2.transform(pathRotator);

        //s.circle(20*scale+qx1, 266*scale+qx1, 50*scale).transform("rotate("+m+" "+(281*scale)+" "+(282*scale)+")").attr({fill: c1, stroke: c2, strokeWidth: 30*scale, opacity: 1.0});
        Path circle = new Path();
        circle.addCircle(20 * scale + qx1, 266 * scale + qx1, 50 * scale, Path.Direction.CW);
        Matrix mMatrix = new Matrix();
        mMatrix.postRotate(m, 281 * scale, 282 * scale);
        circle.transform(mMatrix);

        Paint strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);

        strokePaint.setStrokeWidth(strokeWidth1);
        strokePaint.setColor(Color.parseColor(c1));
        canvas.drawPath(path1, strokePaint);

        strokePaint.setStrokeWidth(strokeWidth2);
        strokePaint.setColor(Color.parseColor(c2));
        canvas.drawPath(path2, strokePaint);

        Paint fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.parseColor(c1));
        canvas.drawPath(circle, fillPaint);

        strokePaint.setStrokeWidth(30 * scale);
        strokePaint.setColor(Color.parseColor(c2));
        canvas.drawPath(circle, strokePaint);
    }

    private int generateHashCode(CharSequence str) {
        long hash = 30723761;
        int chr = 0;
        if (str.length() == 0) {
            return 0;
        }

        for (int i = 0; i < str.length(); i++) {
            chr   = str.charAt(i);
            hash  = ((hash << 5) - hash) + chr;
        }

        return (int)hash;
    };

    private String[][] colors = {
            {
                    "#FF8A65",
                    "#FF7043",
                    "#FFB74D",
                    "#FFA726",
                    "#FFD54F",
                    "#FFCA28",
                    "#FFF176",
                    "#FFEE58",
                    "#DCE775",
                    "#D4E157",
                    "#AED581",
                    "#9CCC65",
                    "#81C784",
                    "#66BB6A"
            },

            {
                    "#4DB6AC",
                    "#26A69A",
                    "#B2EBF2",
                    "#80DEEA",
                    "#4DD0E1",
                    "#26C6DA",
                    "#B3E5FC",
                    "#81D4FA",
                    "#4FC3F7",
                    "#29B6F6",
                    "#BBDEFB",
                    "#90CAF9",
                    "#64B5F6",
                    "#42A5F5"
            },

            {
                    "#C5CAE9",
                    "#9FA8DA",
                    "#7986CB",
                    "#5C6BC0",
                    "#D1C4E9",
                    "#B39DDB",
                    "#9575CD",
                    "#7E57C2",
                    "#E1BEE7",
                    "#CE93D8",
                    "#BA68C8",
                    "#AB47BC",
                    "#F8BBD0",
                    "#F48FB1",
                    "#F06292"
            },
            {
                    "#BBDEFB",
                    "#90CAF9",
                    "#64B5F6",
                    "#42A5F5",
                    "#9FA8DA",
                    "#7986CB",
                    "#5C6BC0",
                    "#3F51B5",
                    "#D1C4E9",
                    "#B39DDB",
                    "#9575CD",
                    "#7E57C2"
            },
            {
                    "#D1C4E9",
                    "#B39DDB",
                    "#9575CD",
                    "#7E57C2",
                    "#E1BEE7",
                    "#CE93D8",
                    "#BA68C8",
                    "#AB47BC",
                    "#F8BBD0",
                    "#F48FB1",
                    "#F06292"
            },
            {
                    "#E91E63",
                    "#9C27B0",
                    "#673AB7",
                    "#3F51B5",
                    "#2196F3",
                    "#03A9F4"
            },
            {
                    "#FFEB3B",
                    "#AFB42B",
                    "#9E9D24",
                    "#FBC02D",
                    "#F9A825",
                    "#FFA000",
                    "#FF8F00",
                    "#FF6F00"
            },
            {
                    "#795548",
                    "#FF5722",
                    "#FFA726",
                    "#FFB74D",
                    "#BF360C"
            },
            {
                    "#78909C",
                    "#546E7A",
                    "#BF360C",
                    "#FF5722",
                    "#FFD54F"
            },
            {
                    "#78909C",
                    "#B0BEC5",
                    "#FF5722",
                    "#f44336"
            },
            {
                    "#43A047",
                    "#689F38",
                    "#7CB342",
                    "#689F38",
                    "#C0CA33",
                    "#AFB42B",
                    "#FFEB3B",
                    "#FDD835",
                    "#FFC107",
                    "#FFB300",
                    "#FF9800",
                    "#FB8C00"
            }
    };
}
