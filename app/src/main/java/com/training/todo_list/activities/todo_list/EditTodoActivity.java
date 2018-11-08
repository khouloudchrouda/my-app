package com.training.todo_list.activities.todo_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.training.todo_list.R;
import com.training.todo_list.model.models.Todo;

public class EditTodoActivity extends AppCompatActivity {
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        description= (EditText) findViewById(R.id.description);

        Todo todo= (Todo) getIntent().getSerializableExtra("TODO");
        description.setText(todo.description());

    }
}
