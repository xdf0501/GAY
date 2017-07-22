package com.gay;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.View.Main_addFragment;
import com.View.Main_messageFragment;
import com.View.Main_mineFragment;
import com.View.Main_pageFragment;
import com.View.Main_shopFragment;
import com.View.MessageFragment;
import com.appbase.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements Main_addFragment.CallBackValue{
    private DrawerLayout drawerLayout_main;
    private RelativeLayout relativeLayout_main;
    private ArrayList<String> returnedData;
    public View tabLayout1;
    public View tabLayout2;
    public View tabLayout3;
    public View tabLayout4;
    public View tabLayout5;
    List<Fragment> fragments;
    public  Main_addFragment main_addFragment;
    public Main_pageFragment main_pageFragment;
    ;
    public TextView tab1Textview;
    public TextView tab2Textview;
    public TextView tab3Textview;
    private String panduan="0";
    int currentIndex=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            setContentView(R.layout.activity_main);


            drawerLayout_main =(DrawerLayout) findViewById(R.id.drawerLayout_main);
            tabLayout1 = findViewById(R.id.tabLayout1);
            tabLayout2 = findViewById(R.id.tabLayout2);
            tabLayout3 = findViewById(R.id.tabLayout3);
            tabLayout4= findViewById(R.id.tabLayout4);
            tabLayout5= findViewById(R.id.tabLayout5);



            if(tabLayout1 !=null){
                tabLayout1.setOnClickListener(viewClick);
                tabLayout1.setBackgroundResource(R.color.title);
                tabLayout2.setBackgroundResource(R.color.white);
                tabLayout3.setBackgroundResource(R.mipmap.add_01);
                tabLayout4.setBackgroundResource(R.color.white);
                tabLayout5.setBackgroundResource(R.color.white);;

            }
            if(tabLayout2 !=null){
                tabLayout2.setOnClickListener(viewClick);

            }
            if(tabLayout3 !=null){
                tabLayout3.setOnClickListener(viewClick);

            }
            if(tabLayout4 !=null){
                tabLayout4.setOnClickListener(viewClick);

            }
            if(tabLayout5 !=null){
                tabLayout5.setOnClickListener(viewClick);

            }
            showTab(0);
            onShowFragment(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public View.OnClickListener viewClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(view == tabLayout1){
                showTab(0);
                onShowFragment(0);
                tabLayout1.setBackgroundResource(R.color.title);
                tabLayout2.setBackgroundResource(R.color.white);
                tabLayout3.setBackgroundResource(R.mipmap.add_01);
                tabLayout4.setBackgroundResource(R.color.white);
                tabLayout5.setBackgroundResource(R.color.white);
            }
            if(view == tabLayout2){
                showTab(1);
                onShowFragment(1);
                tabLayout1.setBackgroundResource(R.color.white);
                tabLayout2.setBackgroundResource(R.color.title);
                tabLayout3.setBackgroundResource(R.mipmap.add_01);
                tabLayout4.setBackgroundResource(R.color.white);
                tabLayout5.setBackgroundResource(R.color.white);
            }
            if(view == tabLayout3){
                showTab(2);
                onShowFragment(2);
                tabLayout1.setBackgroundResource(R.color.white);
                tabLayout2.setBackgroundResource(R.color.white);
                tabLayout3.setBackgroundResource(R.mipmap.add_02);
                tabLayout4.setBackgroundResource(R.color.white);
                tabLayout5.setBackgroundResource(R.color.white);
            }
            if(view == tabLayout4){
                showTab(3);
                onShowFragment(3);
                tabLayout1.setBackgroundResource(R.color.white);
                tabLayout2.setBackgroundResource(R.color.white);
                tabLayout3.setBackgroundResource(R.mipmap.add_01);
                tabLayout4.setBackgroundResource(R.color.title);
                tabLayout5.setBackgroundResource(R.color.white);
            }
            if(view == tabLayout5){
                showTab(4);
                onShowFragment(4);
                tabLayout1.setBackgroundResource(R.color.white);
                tabLayout2.setBackgroundResource(R.color.white);
                tabLayout3.setBackgroundResource(R.mipmap.add_01);
                tabLayout4.setBackgroundResource(R.color.white);
                tabLayout5.setBackgroundResource(R.color.title);
            }
        }
    };


    public int getCurrentIndex(){
        return currentIndex;
    }
    public synchronized void showTab(int index){
        if (index<0){
            index=0;
        }
        if (index>4){
            index=4;
        }
        currentIndex = index;
        if (tabLayout1 !=null) {
            if(currentIndex ==0){
//                tab1Textview.setTextColor(getResources().getColor(Res.color.tab_text_color1));

            }else{
//                tab1Textview.setTextColor(getResources().getColor(Res.color.tab_text_color0));

            }
        }
        if (tabLayout2 !=null) {
            if(currentIndex ==1){
//                tab2Textview.setTextColor(getResources().getColor(Res.color.tab_text_color1));

            }else{
//                tab2Textview.setTextColor(getResources().getColor(Res.color.tab_text_color0));

            }
        }
        if (tabLayout3 !=null) {
            if(currentIndex ==2){
//                tab3Textview.setTextColor(getResources().getColor(Res.color.tab_text_color1));

            }else{
//                tab3Textview.setTextColor(getResources().getColor(Res.color.tab_text_color0));

            }
        }
        if (tabLayout4 !=null) {
            if(currentIndex ==3){
//                tab3Textview.setTextColor(getResources().getColor(Res.color.tab_text_color1));

            }else{
//                tab3Textview.setTextColor(getResources().getColor(Res.color.tab_text_color0));

            }
        }
        if (tabLayout5 !=null) {
            if(currentIndex ==4){
//                tab3Textview.setTextColor(getResources().getColor(Res.color.tab_text_color1));

            }else{
//                tab3Textview.setTextColor(getResources().getColor(Res.color.tab_text_color0));

            }
        }
    }

    public synchronized void onShowFragment(int newtab) {
            FragmentManager fm =null;
            FragmentTransaction ft=null;
            if (newtab<=4) {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                 fragments = fm.getFragments();
                if (fragments != null) {
                    for (Fragment frag : fragments) {
                        if(frag!=null) {
                            ft.hide(frag);
                        }
                    }
                }
            }
            if (fm!=null&&ft!=null) {
                String tabId = String.valueOf(newtab);
                Fragment fg = null;
                switch (newtab) {
                    case 0:
                        fg = fm.findFragmentByTag(tabId);
                        if (fg == null) {
                            fg = new Main_pageFragment();
                            ft.add(R.id.main_pager, fg, tabId);
                            //checkDepartment(fg);
                        } else {
                            //ft.attach(fg);
                            ft.show(fg);
                        }
                        break;
                    case 1:
                        fg = fm.findFragmentByTag(tabId);
                        if (fg == null) {
                            fg = new MessageFragment();
                            ft.add(R.id.main_pager, fg, tabId);
                            //checkDepartment(fg);
                        } else {
                            //ft.attach(fg);
                            ft.show(fg);
                        }

                        break;
                    case 2:
                        fg = fm.findFragmentByTag(tabId);
                        if (fg == null) {
                            fg = new Main_addFragment();
                            ft.add(R.id.main_pager, fg, tabId);
                            //checkDepartment(fg);
                        } else {
                            //ft.attach(fg);
                            ft.show(fg);
                        }
                        break;
                    case 3:
                        fg = fm.findFragmentByTag(tabId);
                        if (fg == null) {
                            fg = new Main_shopFragment();
                            ft.add(R.id.main_pager, fg, tabId);
                            //checkDepartment(fg);
                        } else {
                            //ft.attach(fg);
                            ft.show(fg);
                        }
                        break;
                    case 4:
                        fg = fm.findFragmentByTag(tabId);
                        if (fg == null) {
                            fg = new Main_mineFragment();
                            ft.add(R.id.main_pager, fg, tabId);
                            //checkDepartment(fg);
                        } else {
                            //ft.attach(fg);
                            ft.show(fg);
                        }
                        break;
                    default:
                        break;
                }
                if (ft != null) {
                    ft.commit();
                }
            }
    }

    @Override
    public void SendMessageValue(String strValue) {

    }


//    @Override
//    public void SendMessageValue(String strValue) {
//        panduan=strValue;
//    }

//    private void callLogin(){
//        Intent intent = new Intent(getActivity(),LoginActivity.class);
//        getActivity().startActivityForResult(intent,ActivityRequestCode.requestCode_login);
//    }

}
