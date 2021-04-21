package id.ac.umn.week10b_29240;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Void> {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setMax(100);
    }
    public void startTask(View view){
        if(!LoaderManager.getInstance(this).hasRunningLoaders())
            LoaderManager.getInstance(this).initLoader(0,(Bundle) null,
                    this);
    }
    @NonNull
    @Override
    public Loader<Void> onCreateLoader(int id, @Nullable Bundle args) {
        AsyncTaskLoader<Void> asyncTaskLoader =
                new ContohLoader(this,(int)(Math.random()*50)+10,this);
        asyncTaskLoader.forceLoad();
        return asyncTaskLoader;
    }
    @Override
    public void onLoadFinished(@NonNull Loader<Void> loader, Void data)
    {
        LoaderManager.getInstance(this).destroyLoader(0);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Void> loader) {
    }
    static class ContohLoader extends AsyncTaskLoader<Void> {
        static WeakReference<MainActivity> mActivity;
        int mCounter = 0;
        public ContohLoader(@NonNull Context context, int n,
                            MainActivity main) {
            super(context);
            mCounter = n;
            mActivity = new WeakReference<MainActivity>(main);
        }
        @Nullable
        @Override
        public Void loadInBackground() {
            try {
                for(int i = 0; i <= mCounter; i++) {
                    Thread.sleep(200);
                    final int progress = ((int)((100 * i)/
                            (float) mCounter));
                    if (mActivity.get() != null) {
                        mActivity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(progress < 100) {
                                    mActivity.get().mProgressBar
                                            .setProgress(progress);
                                    mActivity.get().mTextView
                                            .setText("progress = " + progress + " percent");
                                }else {
                                    mActivity.get().mProgressBar
                                            .setProgress(100);
                                    mActivity.get().mTextView.
                                            setText("Done after " + mCounter * 200 +" millisecond...");
                                }
                            }
                        });
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}