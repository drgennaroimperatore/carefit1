package com.example.game_.other01_app.Utility;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class CompendiumActivitiesAutoComplete extends AutoCompleteTextView {
    public CompendiumActivitiesAutoComplete(Context context) {
        super(context);
    }

    public CompendiumActivitiesAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompendiumActivitiesAutoComplete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && getFilter()!=null) {
            performFiltering(getText(), 0);
        }
    }
}
