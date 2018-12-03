package com.example.user.architecturecomponent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.user.architecturecomponent.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "com.example.user.architecturecomponent.EXTRA_DESCRIPTION";

    public static final String EXTRA_PRIORITY =
            "com.example.user.architecturecomponent.EXTRA_PRIORITY";

    public static final String EXTRA_ID =
            "com.example.user.architecturecomponent.EXTRA_ID";


    private EditText editTextTitle;
    private EditText editTextDesc;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);

        editTextDesc = findViewById(R.id.edit_text_desc);
        numberPicker = findViewById(R.id.number_picker_priority);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Title");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDesc.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }
        else{
            setTitle("Add Note");
        }

    }


    private void saveNote(){

        String title = editTextTitle.getText().toString();
        String description = editTextDesc.getText().toString();

        int priority = numberPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent(AddNoteActivity.this, MainActivity.class);

        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1)
            data.putExtra(EXTRA_ID, id);
        setResult(RESULT_OK,data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
