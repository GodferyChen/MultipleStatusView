package com.sample.chen.multiplestatusview

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import com.github.clans.fab.FloatingActionMenu
import com.sample.chen.library.MultipleStatusView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mMultipleStatusView: MultipleStatusView? = null
    private var mFloatingActionMenu: FloatingActionMenu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)

        mMultipleStatusView = findViewById(R.id.multiple_status_view) as MultipleStatusView?
        mFloatingActionMenu = findViewById(R.id.main_fab_menu) as FloatingActionMenu?
        val mLoadingFab = findViewById(R.id.main_fab_loading) as com.github.clans.fab.FloatingActionButton?
        val mEmptyFab = findViewById(R.id.main_fab_empty) as com.github.clans.fab.FloatingActionButton?
        val mErrorFab = findViewById(R.id.main_fab_error) as com.github.clans.fab.FloatingActionButton?
        val mNoNetworkFab = findViewById(R.id.main_fab_no_network) as com.github.clans.fab.FloatingActionButton?
        val mContentFab = findViewById(R.id.main_fab_content) as com.github.clans.fab.FloatingActionButton?
        mLoadingFab!!.setOnClickListener(this)
        mEmptyFab!!.setOnClickListener(this)
        mErrorFab!!.setOnClickListener(this)
        mNoNetworkFab!!.setOnClickListener(this)
        mContentFab!!.setOnClickListener(this)

        mMultipleStatusView!!.setOnActionListener(object : MultipleStatusView.OnActionListener {
            override fun onAction(view: View) {
                Toast.makeText(baseContext, "OnActionListener", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.main_fab_loading -> mMultipleStatusView!!.loading()
            R.id.main_fab_empty -> mMultipleStatusView!!.empty()
            R.id.main_fab_error -> mMultipleStatusView!!.custom(resources.getDrawable(R.mipmap.ic_error),
                    "error", "retry")
            R.id.main_fab_no_network -> mMultipleStatusView!!.custom(resources.getDrawable(R.mipmap.ic_no_network),
                    "no network", "retry")
            R.id.main_fab_content -> mMultipleStatusView!!.dismiss()
        }
        mFloatingActionMenu!!.toggle(false)
    }
}
