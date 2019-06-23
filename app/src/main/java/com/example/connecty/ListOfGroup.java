package com.example.connecty;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ListOfGroup extends Fragment {

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    void GetData() {

    }



    void SetData() {




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_of_group, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listOfGroupView);
        ArrayList<Group> arrayList = new ArrayList<>();
        /*arrayList.add(new Group("1751109","Nguyen Minh Tri","Male","20","English","I’ve been working as an administrative assistant for three years. At my current job in the finance department of a midsize company, I handle scheduling, meeting and travel planning for four executives and 20 staff members. I also help prepare correspondence, presentations and reports.","0981895949",1));
        arrayList.add(new Group("1751084","Do Tri Nhan","Male","20","English","I’m known for being a detail-oriented, well-organized team player. I never miss deadlines, I’m a good communicator and I can juggle multiple tasks at once. In my performance reviews, my supervisor always notes that he appreciates my professionalism and enthusiasm for the job.","037 5994 743",2));
        arrayList.add(new Group("1751108","Ho Minh Tri","Male","20","English","With this experience under my belt, I’m looking for an opportunity to take the next step in my career. I’m hoping to do so in an organization like yours that works to improve the environment, which is something I’m passionate about.","0909254721",3));
        arrayList.add(new Group("1751083","Pham Minh Tuan","Male","20","English","Be concise. Don’t take up too much time with your response. You don’t have to tell the hiring manager every single thing that makes you a good fit for the position. Just give a few important details that will spark their interest in learning more about you, and you’ll get the interview off to a strong start.","0356506802",4));
        arrayList.add(new Group("1710032","Ngo Van Phat","Male","20","Japaness","And some job seekers simply summarize their resume, going point-by-point through their work experience and education history.","0949150989",5));

        arrayList.add(new Group("1751109","Nguyen Minh Tri","Male","20","English","I’ve been working as an administrative assistant for three years. At my current job in the finance department of a midsize company, I handle scheduling, meeting and travel planning for four executives and 20 staff members. I also help prepare correspondence, presentations and reports.","0981895949",1));
        arrayList.add(new Group("1751084","Do Tri Nhan","Male","20","English","I’m known for being a detail-oriented, well-organized team player. I never miss deadlines, I’m a good communicator and I can juggle multiple tasks at once. In my performance reviews, my supervisor always notes that he appreciates my professionalism and enthusiasm for the job.","037 5994 743",2));
        arrayList.add(new Group("1751108","Ho Minh Tri","Male","20","English","With this experience under my belt, I’m looking for an opportunity to take the next step in my career. I’m hoping to do so in an organization like yours that works to improve the environment, which is something I’m passionate about.","0909254721",3));
        arrayList.add(new Group("1751083","Pham Minh Tuan","Male","20","English","Be concise. Don’t take up too much time with your response. You don’t have to tell the hiring manager every single thing that makes you a good fit for the position. Just give a few important details that will spark their interest in learning more about you, and you’ll get the interview off to a strong start.","0356506802",4));
        arrayList.add(new Group("1710032","Ngo Van Phat","Male","20","Japaness","And some job seekers simply summarize their resume, going point-by-point through their work experience and education history.","0949150989",5));
        */

        ListOfGroupAdapter listOfGroupAdapter = new ListOfGroupAdapter(getContext(),arrayList,getActivity());
        recyclerView.setAdapter(listOfGroupAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        view.bringToFront();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_of_group, container, false);
        return view;
    }


}
