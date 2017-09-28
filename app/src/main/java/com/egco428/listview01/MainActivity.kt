package com.egco428.listview01

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_main.view.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_listView.adapter = myCustomAdapter(this)
        main_listView.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as String
            Log.d("Result","Item : $item at position $position")
            val intent = Intent(this,DetailActivity::class.java)

            intent.putExtra("name","${view.name_textView.text}")
            intent.putExtra("weapon","${view.position_textView.text}")

            startActivity(intent)
        }
    }


    private  class myCustomAdapter(context: Context):BaseAdapter(){
        private val mContext: Context
        private val names = arrayListOf<String>("Pharah","Winston","Genji","Dva","Reinhardt")
        private val heros_weapon = arrayListOf<String>("Rocket Launcher","Tesla Cannon","Shuriken and Heavenly Sword","Fusion Cannon","Hammer Down !")
        init {
            mContext = context
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return names.get(position)
        }

        override fun getCount(): Int {
            return names.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
//            val textView = TextView(mContext)
//            textView.text = "Show the message"
//            return textView
            val whiteColor = Color.WHITE
            val grayColor = Color.GRAY
            val rowMain: View
            if (convertView == null){
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)
                rowMain = layoutInflator.inflate(R.layout.row_main, viewGroup,false)
                val viewHolder = ViewHolder(rowMain.name_textView,rowMain.position_textView,rowMain.iconImage)
                rowMain.tag = viewHolder
            }
            else {
                rowMain = convertView
            }
            val viewHolder = rowMain.tag as ViewHolder

            viewHolder.nameTextView.text = names.get(position)

            viewHolder.positionTextView.text = "Weapon: ${heros_weapon.get(position)}"

            val resID = rowMain.resources.getIdentifier(names.get(position).toLowerCase(),"drawable","com.egco428.listview01")
            viewHolder.rowImageView.setImageResource(resID)
            if(position%2 == 0) {
                rowMain.setBackgroundColor(Color.GRAY)
            }
            else {
                rowMain.setBackgroundColor(Color.WHITE)
            }
            return rowMain
        }
        private class ViewHolder(val nameTextView: TextView,val positionTextView: TextView,val rowImageView: ImageView){

        }
    }
}
