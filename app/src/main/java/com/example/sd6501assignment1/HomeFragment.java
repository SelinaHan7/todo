package com.example.sd6501assignment1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sd6501assignment1.Adapter.ToDoAdapter;
import com.example.sd6501assignment1.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ToDoAdapter toDoAdapter;
    List<ToDoModel> taskList;
    public interface RecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        DatabaseHandler db = new DatabaseHandler(getActivity());
//        db.openDatabase();

        startActivity(new Intent(getActivity(), AddTaskActivity.class));
//
//        List<ToDoModel> list =  db.getAllTasks();
////        for (int i=0; i< list.size(); i++){
////            Log.d("show list", "onCreateView: " + list);
////
////        }
//
//        // Initialize taskList and taskAdapter
//        taskList = new ArrayList<>();
//
//        //ToDoAdapter taskAdapter = ((MenuActivity)getActivity()).getTaskAdapter();
//        toDoAdapter = ((MenuActivity)getActivity()).getTaskAdapter();
//
//        // Initialize RecyclerView
//        recyclerView = view.findViewById(R.id.tasksRecyclerViewHome);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Log.e("HomeFragment", "setAdapter");
//        recyclerView.setAdapter(toDoAdapter);
//
//        // ...

        return view;
    }

    // ...

//    @Override
//    public void onTaskDelete(int position) {
//        // Delete the task at the given position from the taskList
//        taskList.remove(position);
//
//        // Notify the adapter that an item has been removed
//        toDoAdapter.notifyItemRemoved(position);
//    }
//
//    @Override
//    public void onTaskUpdate(int position) {
//        // Handle the update task logic, e.g., launch an UpdateTaskActivity
//        // and pass the necessary data to it
//    }
}








//package com.example.sd6501assignment1;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
////import com.example.sd6501assignment1.Model.ToDoModel;
//import com.example.sd6501assignment1.Adapter.ToDoAdapter;
//import com.google.android.gms.tasks.Task;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HomeFragment extends Fragment {
//
//    RecyclerView recyclerView;
//    ToDoAdapter toDoAdapter;
//    List<Task> taskList;
//    private View view;
//
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        DatabaseHandler db;
//
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//       // Initialize RecyclerView
//            recyclerView = view.findViewById(R.id.tasksRecyclerView);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//            db = new DatabaseHandler(getActivity());
//            db.openDatabase();
//
//            // Initialize taskList and taskAdapter
//            taskList = new ArrayList<>();
//            toDoAdapter = new ToDoAdapter(db, AddTaskActivity.this);
//
//            // Set the adapter to the RecyclerView
//            recyclerView.setAdapter(toDoAdapter);
//
//            // ...
//
//            return view;
//        }
//
//        // ...
//
//        @Override
//        public void deleteTask(int position) {
//            // Delete the task at the given position from the taskList
//            taskList.remove(position);
//
//            // Notify the adapter that an item has been removed
//            toDoAdapter.notifyItemRemoved(position);
//        }
//
//        @Override
//        public void updateTask(int position) {
//            // Handle the update task logic, e.g., launch an UpdateTaskActivity
//            // and pass the necessary data to it
//        }
//
//    public void replace(FragmentManager supportFragmentManager, String tag) {
//    }
//
//
//    }