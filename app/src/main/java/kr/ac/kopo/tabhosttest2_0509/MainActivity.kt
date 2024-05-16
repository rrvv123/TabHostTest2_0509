package kr.ac.kopo.tabhosttest2_0509

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), ActionBar.TabListener {

    lateinit var tab1 : ActionBar.Tab
    lateinit var tab2 : ActionBar.Tab
    lateinit var tab3 : ActionBar.Tab
    var arrFrags = arrayOfNulls<MyFragment>(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bar = this.supportActionBar
        bar!!.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        tab1 = bar.newTab()
        tab1.text = "FirstTab"
        tab1.setTabListener(this)
        bar.addTab(tab1)

        tab2 = bar.newTab()
        tab2.text = "SecondTab"
        tab2.setTabListener(this)
        bar.addTab(tab2)

        tab3 = bar.newTab()
        tab3.text = "ThirdTab"
        tab3.setTabListener(this)
        bar.addTab(tab3)
    }

    override fun onTabSelected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        var fragment : MyFragment? = null

        if(arrFrags[tab!!.position] == null){
            fragment = MyFragment()
            val data = Bundle()
            data.putString("tabName", tab.text.toString())
            fragment.arguments = data
            arrFrags[tab.position] = fragment
        }
        else{
            fragment = arrFrags[tab.position]
        }

        ft!!.replace(android.R.id.content, fragment!!)
    }

    override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {

    }

    override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {

    }

// 탭을 선택하면 변경되는 Content로 Fragment를 사용
    class MyFragment : Fragment(){
        var tabName : String? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            var data = arguments
            tabName = data!!.getString("tabName")
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
            var linear = LinearLayout(super.getActivity())
            linear.layoutParams = params
            linear.orientation = LinearLayout.VERTICAL

            if(tabName === "FirstTab")
                linear.setBackgroundColor(Color.DKGRAY)
            if(tabName === "SecondTab")
                linear.setBackgroundColor(Color.WHITE)
            if(tabName === "ThirdTab")
                linear.setBackgroundColor(Color.CYAN)

            return linear
        }
    }
}