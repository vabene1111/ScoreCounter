package ua.napps.scorekeeper.log;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import ua.com.napps.scorekeeper.R;
import ua.napps.scorekeeper.settings.LocalSettings;
import ua.napps.scorekeeper.utils.Singleton;
import ua.napps.scorekeeper.utils.ViewUtil;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // get back button
        Toolbar toolbar = findViewById(R.id.toolbar_log_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // setup recycler view and adapter
        RecyclerView mRecyclerView = findViewById(R.id.rv_log_main);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        LogAdapter mAdapter = new LogAdapter(Singleton.getInstance().getLogEntries());
        mRecyclerView.setAdapter(mAdapter);

        Group emptyState = findViewById(R.id.g_empty_history);
        emptyState.setVisibility(mAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);

        boolean isDarkTheme = LocalSettings.isDarkTheme();
        if (!isDarkTheme) {
            ViewUtil.setLightStatusBar(this, Color.WHITE);
        } else {
            ViewUtil.clearLightStatusBar(this, Color.BLACK);
        }
        ViewUtil.setNavBarColor(this, !isDarkTheme);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
