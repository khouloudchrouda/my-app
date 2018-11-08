package com.training.todo_list.activities.todo_list;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.managers.TodoTypeManager;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ActivityTodoList extends ListActivity {
    private List<Todo> todoList;
    private TodoManager todoManager;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_list);
        listView= (ListView) findViewById(android.R.id.list);

        TodoType tTypeEmergency = new TodoType("Emergency", "#b94a48", UUID.randomUUID());
        TodoType tTypeBeforeYesterday = new TodoType("Before yesterday", "#f89406", UUID.randomUUID());
        TodoType tTypeNormal = new TodoType("Normal", "#3a87ad", UUID.randomUUID());
        Todo tTodo1 = new Todo("Buy milk and eggs",
                new Date(1420106400000L), tTypeNormal.id(), true, UUID.randomUUID());
        Todo tTodo2 = new Todo("Call Superman to repair the internet",
                new Date(1443693600000L), tTypeEmergency.id(), false, UUID.randomUUID());

        TodoTypeManager tTodoTypeManager = new TodoTypeManager();
        TodoManager tTodoManager = new TodoManager();
        tTodoTypeManager.save(tTypeEmergency);
        tTodoTypeManager.save(tTypeBeforeYesterday);
        tTodoTypeManager.save(tTypeNormal);
        tTodoManager.save(tTodo1);
        tTodoManager.save(tTodo2);


        ArrayAdapter<Todo> adapter = new ArrayAdapter<Todo>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tTodoManager.all());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Todo todo= (Todo) listView.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), EditTodoActivity.class);
                intent.putExtra("TODO", todo);
                startActivity(intent);
                //startActivity(new Intent(ActivityTodoList.this,EditTodoActivity.class));
            }
        });




    }

    private void showDetailsPopUp(Todo todo) {

      startActivity(new Intent(this,EditTodoActivity.class));
    }


    public void askAddTodo(View pView) {
       // todo item

        Toast.makeText(this, "Ask add todo", Toast.LENGTH_SHORT).show();
    }


    public void askSurprise(View pView) {
        AlertDialog.Builder tBuilder = new AlertDialog.Builder(this);
        tBuilder.setTitle(R.string.dialog_title_surprise);
        tBuilder.setMessage(R.string.dialog_message_surprise);
        tBuilder.setPositiveButton(R.string.confirm, null);
        tBuilder.show();
    }
}

