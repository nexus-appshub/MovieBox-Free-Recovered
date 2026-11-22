package com.moviebox.recovered;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private LinearLayout content;
    private final String[] tabs = {"Home", "NovelHub", "ShortTV", "Downloads", "Me"};
    private final String[] categories = {
        "Football", "Trending", "Movie", "Nollywood", "TV",
        "ShortTV", "TV Channel", "Anime", "Kids", "Education",
        "Music", "Free Novels", "Game", "FightZone"
    };

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(255, 255, 255));
        getWindow().setNavigationBarColor(Color.rgb(255, 255, 255));
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        );
        showHome();
    }

    private TextView text(String value, int size, boolean bold) {
        TextView v = new TextView(this);
        v.setText(value);
        v.setTextSize(size);
        v.setTextColor(Color.rgb(28, 28, 32));
        if (bold) v.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        v.setPadding(20, 14, 20, 14);
        return v;
    }

    private void showHome() {
        ScrollView scroll = new ScrollView(this);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.WHITE);
        scroll.addView(root);

        TextView brand = text("MovieBox", 26, true);
        root.addView(brand);
        TextView notice = text(
            "Recovery build shell\nThis is a clean, buildable Android scaffold. Original MovieBox screens, APIs and playback have not yet been recovered.",
            14, false
        );
        notice.setBackgroundColor(Color.rgb(255, 246, 235));
        root.addView(notice);
        root.addView(text("Explore", 20, true));

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        root.addView(content);
        for (String category : categories) {
            Button b = new Button(this);
            b.setText(category);
            b.setAllCaps(false);
            b.setOnClickListener(v -> {
                content.removeAllViews();
                content.addView(text(category, 22, true));
                content.addView(text("This section is a placeholder until the protected app code and authorized API behavior are recovered.", 15, false));
                Button back = new Button(this);
                back.setText("Back to Home");
                back.setOnClickListener(x -> showHome());
                content.addView(back);
            });
            content.addView(b);
        }

        LinearLayout nav = new LinearLayout(this);
        nav.setGravity(Gravity.CENTER);
        nav.setBackgroundColor(Color.rgb(250, 250, 250));
        for (String tab : tabs) {
            Button b = new Button(this);
            b.setText(tab);
            b.setTextSize(11);
            b.setAllCaps(false);
            b.setOnClickListener(v -> {
                content.removeAllViews();
                content.addView(text(tab, 22, true));
                content.addView(text("Navigation placeholder — original feature implementation is not yet available.", 15, false));
            });
            nav.addView(b, new LinearLayout.LayoutParams(0, -2, 1));
        }

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));
        page.addView(nav);
        setContentView(page);
    }
}
