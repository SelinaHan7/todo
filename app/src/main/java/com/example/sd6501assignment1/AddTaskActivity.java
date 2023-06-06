package com.example.sd6501assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sd6501assignment1.Adapter.ToDoAdapter;
import com.example.sd6501assignment1.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AddTaskActivity extends AppCompatActivity implements DialogCloseListener{

    private DatabaseHandler db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    EditText title;
    private ToDoModel tdModel;

    private List<ToDoModel> taskList;
    private ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("AddTaskActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
        imageView = findViewById(R.id.logout_task);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AddTaskActivity.this, LoginActivity.class));
//            }
//        });

        title = findViewById(R.id.newTaskText);
        Button subButton = findViewById(R.id.newTaskButton);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = title.getText().toString();
                db = new DatabaseHandler(AddTaskActivity.this);
                tdModel = new ToDoModel();
                tdModel.setTask(t);
                tdModel.setStatus(0);
                Log.d("SUBMIT ONCLICK", "onClick: "+ tdModel);
                db.insertTask(tdModel);
            }
        });

        Objects.requireNonNull(getSupportActionBar()).hide();

        db = new DatabaseHandler(this);
        db.openDatabase();
        Log.e("AddTaskActivity", "setAdapter -1");

        setContentView(R.layout.activity_add_task);
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);

        Log.e("AddTaskActivity", "setAdapter 0");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        tasksRecyclerView.setLayoutManager(layoutManager);

        Log.e("AddTaskActivity", "setAdapter");
        tasksAdapter = new ToDoAdapter(db,AddTaskActivity.this);

        tasksAdapter.notifyDataSetChanged();
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab_task);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    public ToDoAdapter getTaskAdapter(){
        return this.tasksAdapter;
    }
    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }

}