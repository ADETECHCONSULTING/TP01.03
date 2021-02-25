package adamatraore.fr.tp0102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Variables
    int randomA;
    int randomB;

    TextView txvCalcul;
    EditText edtCalcul;
    Button btnCalcul;
    Button btnPreviousResult;

    String key = "clé";

    //TODO 1: Créer une variable de type SharedPreference
    SharedPreferences sharedPreferences;

    //Cycle de vie
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvCalcul = findViewById(R.id.txv_calcul);
        edtCalcul = findViewById(R.id.edt_calcul);
        btnCalcul = findViewById(R.id.btn_calcul);
        btnPreviousResult = findViewById(R.id.btn_previous_result);

        generateRandomCalcul();

        //TODO 2: Valoriser la variable de type sharedpreference en créant une instance de SharedPreference
        sharedPreferences = this.getSharedPreferences("result", Context.MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        txvCalcul.setText(randomA + " + " + randomB);

        btnCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isRightAnswer = calcul();

                if (isRightAnswer) {
                    Snackbar
                            .make(findViewById(R.id.myConstraintLayout), "bonne réponse", Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    Snackbar
                            .make(findViewById(R.id.myConstraintLayout), "mauvaise réponse", Snackbar.LENGTH_SHORT)
                            .show();
                }

                //TODO 3: Sauvegarder le contenu de l'edittext dans le SharedPreference
                String contenu = edtCalcul.getText().toString();
                saveInSharedPreference(contenu);

                generateRandomCalcul();
                edtCalcul.setText("");
                txvCalcul.setText(randomA + " + " + randomB);
            }
        });

        btnPreviousResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Passer dans l'activity ResultActivity
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    //Methodes
    private void generateRandomCalcul() {
        randomA = new Random().nextInt(1000);
        randomB = new Random().nextInt(1000);
    }

    private boolean calcul() {
        int inputResult = Integer.parseInt(edtCalcul.getText().toString());

        if (inputResult == (randomA + randomB)) {
            return true;
        } else {
            return false;
        }
    }

    private void saveInSharedPreference(String value) {
        sharedPreferences
                .edit()
                .putString(key, value)
                .apply();
    }


}