package br.com.mariojp.exercise2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String NAME_BUFF_KEY = "br.com.mariojp.exercise2.NAME_BUFF";
    public static final String SET_NAME_KEY = "br.com.mariojp.exercise2.SET_NAME";

    static final int CHANGE_NAME_REQUEST = 1;

    private TextView textView;
    private Button btnTrocar;

    private String currentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnTrocar = findViewById(R.id.btnTrocar);

        setFirstMessage(savedInstanceState);

        btnTrocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OutraActivity.class);
                intent.putExtra(NAME_BUFF_KEY, currentName);
                startActivityForResult(intent, CHANGE_NAME_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CHANGE_NAME_REQUEST){
            if(resultCode == RESULT_OK){
                String newName = null;

                if(data != null){
                    newName = data.getStringExtra(SET_NAME_KEY);
                }

                if(newName != null){
                    updateCurrentName(newName);
                }
            } else if(resultCode == RESULT_CANCELED) {
                updateCurrentName("");
            }
            setMessageWithCurrentName();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(NAME_BUFF_KEY, currentName);
        super.onSaveInstanceState(outState);
    }

    /**
     * Set the name in message
     *
     * @return void
     */
    private void setMessageWithCurrentName() {
        String format = getString(R.string.format_message);
        String name = "";
        if(!currentName.isEmpty()){
            name = ", " + currentName;
        }

        textView.setText(String.format(format, name));
    }

    /**
     * Defines the first message of application
     *
     * @param savedInstanceState
     * @return void
     */
    private void setFirstMessage(Bundle savedInstanceState) {
        String savedName = "";
        if(savedInstanceState != null) {
            savedName = savedInstanceState.getString(NAME_BUFF_KEY, "");
        }
        updateCurrentName(savedName);
        setMessageWithCurrentName();
    }

    private void updateCurrentName(String name){
        currentName = name;
    }
}
