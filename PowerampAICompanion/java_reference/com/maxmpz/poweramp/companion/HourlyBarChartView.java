package com.maxmpz.poweramp.companion;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: HourlyBarChartView.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fJ\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0014R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/maxmpz/poweramp/companion/HourlyBarChartView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "barPaint", "Landroid/graphics/Paint;", "labelPaint", "highlightPaint", "data", "", "maxVal", "colorPrimary", "colorSurface", "colorOnSurface", "setData", "", "hours", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class HourlyBarChartView extends View {
    private final Paint barPaint;
    private int colorOnSurface;
    private int colorPrimary;
    private int colorSurface;
    private int[] data;
    private final Paint highlightPaint;
    private final Paint labelPaint;
    private int maxVal;

    public /* synthetic */ HourlyBarChartView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public HourlyBarChartView(Context context) {
        this(context, null, 0, 6, null);
    }

    public HourlyBarChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 4, null);
    }

    public HourlyBarChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        this.barPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(22.0f);
        this.labelPaint = paint2;
        Paint paint3 = new Paint(1);
        paint3.setStyle(Paint.Style.FILL);
        this.highlightPaint = paint3;
        this.data = new int[24];
        this.maxVal = 1;
        this.colorPrimary = -10354450;
        this.colorSurface = -4475905;
        this.colorOnSurface = ViewCompat.MEASURED_STATE_MASK;
        TypedArray ta = context.obtainStyledAttributes(new int[]{com.google.android.material.R.attr.colorPrimary, com.google.android.material.R.attr.colorPrimaryContainer, android.R.attr.textColorPrimary});
        this.colorPrimary = ta.getColor(0, this.colorPrimary);
        this.colorSurface = ta.getColor(1, this.colorSurface);
        this.colorOnSurface = ta.getColor(2, this.colorOnSurface);
        ta.recycle();
        this.barPaint.setColor(this.colorSurface);
        this.highlightPaint.setColor(this.colorPrimary);
        this.labelPaint.setColor((this.colorOnSurface & ViewCompat.MEASURED_SIZE_MASK) | (-1728053248));
    }

    public final void setData(int[] hours) {
        int[] copyOf = Arrays.copyOf(hours, 24);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
        this.data = copyOf;
        this.maxVal = RangesKt.coerceAtLeast(ArraysKt.maxOrThrow(this.data), 1);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float topPad;
        float botPad;
        super.onDraw(canvas);
        float w = getWidth();
        float h = getHeight();
        float topPad2 = 0.05f * h;
        float botPad2 = 0.18f * h;
        float chartH = (h - topPad2) - botPad2;
        float barW = w / 24.0f;
        float gap = 0.15f * barW;
        float peak = RangesKt.coerceAtLeast(ArraysKt.maxOrThrow(this.data), 1.0f);
        int i = 0;
        while (i < 24) {
            float barH = (this.data[i] / peak) * chartH * 0.85f;
            float left = (i * barW) + gap;
            float right = ((i + 1) * barW) - gap;
            float top = (topPad2 + chartH) - barH;
            float bottom = topPad2 + chartH;
            float w2 = w;
            boolean isPeak = false;
            boolean isNight = i < 6 || i >= 22;
            boolean isNight2 = isNight;
            float h2 = h;
            if (this.data[i] == ArraysKt.maxOrThrow(this.data) && this.data[i] > 0) {
                isPeak = true;
            }
            Paint paint = isPeak ? this.highlightPaint : this.barPaint;
            canvas.drawRoundRect(new RectF(left, top, right, bottom), gap, gap, paint);
            if (i % 3 == 0) {
                this.labelPaint.setAlpha(isNight2 ? 100 : 160);
                topPad = topPad2;
                botPad = botPad2;
                canvas.drawText(i + "h", ((right - left) / 2) + left, h2 - 4.0f, this.labelPaint);
            } else {
                topPad = topPad2;
                botPad = botPad2;
            }
            i++;
            w = w2;
            botPad2 = botPad;
            h = h2;
            topPad2 = topPad;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(w, RangesKt.coerceAtLeast((int) (w / 3.5f), 140));
    }
}
