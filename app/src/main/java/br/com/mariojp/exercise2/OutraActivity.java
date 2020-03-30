package br.com.mariojp.exercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static br.com.mariojp.exercise2.MainActivity.NAME_BUFF_KEY;
import static br.com.mariojp.exercise2.MainActivity.SET_NAME_KEY;

public class OutraActivity extends AppCompatActivity {

    private Button btnConfirmar;
    private Button btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);

        EditText editText = findViewById(R.id.editText);
        editText.setText(getIntent().getStringExtra(NAME_BUFF_KEY));

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnConfirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                String newName = editText.getText().toString();

                getIntent().putExtra(SET_NAME_KEY, newName);

                setResult(RESULT_OK, getIntent());
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        }
        );
    }
}
