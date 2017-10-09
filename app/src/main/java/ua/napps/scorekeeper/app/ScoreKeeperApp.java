package ua.napps.scorekeeper.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import timber.log.Timber;
import ua.com.napps.scorekeeper.BuildConfig;
import ua.napps.scorekeeper.counters.DatabaseHolder;

public class ScoreKeeperApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
    DatabaseHolder.init(this);
  }
}
