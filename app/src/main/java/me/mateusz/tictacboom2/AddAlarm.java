package me.mateusz.tictacboom2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

public class AddAlarm extends AppCompatActivity {

    public static final String EXTRA_ID =
            "me.mateusz.tictacboom2.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "me.mateusz.tictacboom2.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "me.mateusz.tictacboom2.EXTRA_DESCRIPTION";

    public static final String EXTRA_TIME =
            "me.mateusz.tictacboom2.EXTRA_TIME";

    public static final String EXTRA_HOUR =
            "me.mateusz.tictacboom2.EXTRA_HOUR";
    public static final String EXTRA_MINUTE =
            "me.mateusz.tictacboom2.EXTRA_MINUTE";
    public static final String EXTRA_SECOND =
            "me.mateusz.tictacboom2.EXTRA_SECOND";

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button acceptButton;
    private NumberPicker numberPickerHour, numberPickerMinute, numberPickerSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        titleEditText = findViewById(R.id.title_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);
        acceptButton = findViewById(R.id.accept_button);
        numberPickerHour = findViewById(R.id.number_picker_hour);
        numberPickerMinute = findViewById(R.id.number_picker_minute);
        numberPickerSecond = findViewById(R.id.number_picker_second);

        numberPickerHour.setMinValue(1);
        numberPickerHour.setMaxValue(24);

        numberPickerMinute.setMinValue(0);
        numberPickerMinute.setMaxValue(59);

        numberPickerSecond.setMinValue(0);
        numberPickerSecond.setMaxValue(59);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = numberPickerHour.getValue();
                int minute = numberPickerMinute.getValue();
                int second = numberPickerSecond.getValue();

                Intent intent = new Intent();
                intent.putExtra(EXTRA_TITLE, titleEditText.getText().toString());
                intent.putExtra(EXTRA_DESCRIPTION, descriptionEditText.getText().toString());
                intent.putExtra(EXTRA_HOUR, hour);
                intent.putExtra(EXTRA_MINUTE, minute);
                intent.putExtra(EXTRA_SECOND,second);

                setResult(1,intent);
                finish();
            }
        });

    }
}
