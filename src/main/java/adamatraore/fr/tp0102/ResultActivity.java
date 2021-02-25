package adamatraore.fr.tp0102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    //TODO 7: Créer une variable TextView txvPreviousResult
    TextView txvPreviousResult;

    SharedPreferences sharedPreferences;

    String key = "clé";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //TODO 8: instancier notre view txvPreviousResult
        txvPreviousResult = findViewById(R.id.txv_previous_result);

        sharedPreferences = this.getSharedPreferences("result", Context.MODE_PRIVATE);

        updateUI();

    }

    private void updateUI() {
        //TODO 9: Récuperer le contenu du SharedPreference et l'afficher dans la TextView txvPreviousResult
        String resultSharedPreference = sharedPreferences.getString(key, "pas de résultat");
        txvPreviousResult.setText("Résultat précédent : " + resultSharedPreference);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
    }
}