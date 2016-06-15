package com.sample.chen.multiplestatusview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.sample.chen.library.MultipleStatusView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MultipleStatusView mMultipleStatusView;
    private FloatingActionMenu mFloatingActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMultipleStatusView = (MultipleStatusView) findViewById(R.id.multiple_status_view);
        mFloatingActionMenu = (FloatingActionMenu) findViewById(R.id.main_fab_menu);
        com.github.clans.fab.FloatingActionButton mLoadingFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.main_fab_loading);
        com.github.clans.fab.FloatingActionButton mEmptyFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.main_fab_empty);
        com.github.clans.fab.FloatingActionButton mErrorFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.main_fab_error);
        com.github.clans.fab.FloatingActionButton mNoNetworkFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.main_fab_no_network);
        com.github.clans.fab.FloatingActionButton mContentFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.main_fab_content);
        mLoadingFab.setOnClickListener(this);
        mEmptyFab.setOnClickListener(this);
        mErrorFab.setOnClickListener(this);
        mNoNetworkFab.setOnClickListener(this);
        mContentFab.setOnClickListener(this);

        mMultipleStatusView.setOnActionListener(new MultipleStatusView.OnActionListener() {
            @Override
            public void onAction(View view) {
                Toast.makeText(getBaseContext(), "OnActionListener", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_fab_loading:
                mMultipleStatusView.loading();
                break;
            case R.id.main_fab_empty:
                mMultipleStatusView.empty();
                break;
            case R.id.main_fab_error:
                mMultipleStatusView.custom(getResources().getDrawable(R.mipmap.ic_error),
                        "error","retry");
                break;
            case R.id.main_fab_no_network:
                mMultipleStatusView.custom(getResources().getDrawable(R.mipmap.ic_no_network),
                        "no network","retry");
                break;
            case R.id.main_fab_content:
                mMultipleStatusView.dismiss();
                break;
        }
        mFloatingActionMenu.toggle(false);
    }
}
