package com.egco428.listview01

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val bundle = intent.extras
        val name = bundle.getString("name")
        detailTextView.text = name
        val resID = resources.getIdentifier(name.toLowerCase(),"drawable","com.egco428.listview01")
        textView2.text = bundle.getString("weapon")
        imageView.setImageResource(resID)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item!!.itemId
        if(id == R.id.home){
            finish()
        }
    }
}
