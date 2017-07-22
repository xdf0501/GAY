package com.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.appbase.BaseFragment;
import com.gay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 狄飞 on 2017/6/23.
 */

public class MessageFragment extends BaseFragment {
    private Context context;//MainActivity的context
    private String[] project={"科目","语文","数学","英语"};
    private List<String> projectall=new ArrayList<String>();
    private  ArrayAdapter projectAdapter;
    View conuntView;//View
    private Spinner discipline;
    private String message;
    private TextView name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        conuntView = View.inflate(getActivity(), R.layout.activitymessage, null);
        conuntView.setLayoutParams(params);
        discipline=(Spinner)conuntView.findViewById(R.id.spinner);
        name=(TextView)conuntView.findViewById(R.id.project);
        run();
        return conuntView;

    }
    private void run()
    {
        for(int i=0;i<project.length;i++){
            projectall.add(project[i]);
        }
//        projectAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,projectall);
//        projectAdapter.setDropDownViewResource(R.layout.spinner_item);
                projectAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,projectall)
                {

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view =convertView.inflate(getContext(),R.layout.spinner_item,null);
                        TextView label = (TextView) view.findViewById(R.id.spinner_item_label);
                        label.setText(projectall.get(position));
                        return view;
                    }

                };
//        android.R.layout.simple_spinner_dropdown_item
        discipline.setAdapter(projectAdapter);
        //单击第一个下拉按钮时，显示选择的值。
        discipline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                message= (String) discipline.getAdapter().getItem((int) id);
                if(message.equals("数学"))
                {
                    name.setText("数学");
                }
                else if(message.equals("语文"))
                {
                    name.setText("语文");
                }
                else if(message.equals("英语"))
                {
                    name.setText("英语");
                }else if(message.equals("科目"))
                {
                    name.setText("科目");
                }

//                setTitle(discipline);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

}
