package me.mateusz.tictacboom2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlarmDetails extends AppCompatActivity {

    public static final String EXTRA_ID =
            "me.mateusz.tictacboom2.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "me.mateusz.tictacboom2.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "me.mateusz.tictacboom2.EXTRA_DESCRIPTION";

    private EditText newTitle;
    private EditText newDescription;
    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_details);

        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);
        newTitle = findViewById(R.id.new_title);
        newDescription = findViewById(R.id.new_description);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(newTitle.getText().toString().trim().isEmpty() || newDescription.getText().toString().trim().isEmpty()){
                    Toast.makeText(AlarmDetails.this, "These places can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_TITLE, newTitle.getText().toString());
                    intent.putExtra(EXTRA_DESCRIPTION, newDescription.getText().toString());

                    int id = getIntent().getIntExtra(EXTRA_ID, -1);
                    if(id != -1){
                        intent.putExtra(EXTRA_ID, id);
                    }

                    setResult(2 , intent);
                    finish();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_TITLE, newTitle.getText().toString());
                intent.putExtra(EXTRA_DESCRIPTION, newDescription.getText().toString());
                int id = getIntent().getIntExtra(EXTRA_ID, -1);
                if(id != -1){
                    intent.putExtra(EXTRA_ID, id);
                }


                setResult(3, intent);
                finish();
            }
        });
    }
}
