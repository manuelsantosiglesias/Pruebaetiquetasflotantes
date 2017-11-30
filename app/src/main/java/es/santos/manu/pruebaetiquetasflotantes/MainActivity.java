package es.santos.manu.pruebaetiquetasflotantes;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText tieEmail;
    private TextInputEditText tiePass;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.action_validate), Snackbar.LENGTH_LONG).show();
                validate(view);
            }
        });

        // Text input y textedit del email
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tieEmail = (TextInputEditText) findViewById(R.id.tieEmail);
        // Text input y textedit del pass
        tilPass = (TextInputLayout) findViewById(R.id.tilPass);
        tiePass = (TextInputEditText) findViewById(R.id.tiePass);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void validate(View view) {
        String mailError = null;
        String passError = null;

        // Comprobación de email
        if (TextUtils.isEmpty(tieEmail.getText())) {
            mailError = getString(R.string.mandatory);
        }

        toggleTextInputLayoutError(tilEmail, mailError);

        // Comprobación de password
        if (TextUtils.isEmpty(tiePass.getText())) {
            passError = getString(R.string.mandatory);
        }
        toggleTextInputLayoutError(tilPass, passError);


        clearFocus();
    }

    private static void toggleTextInputLayoutError(TextInputLayout til, String msg) {
        til.setError(msg);
        if (msg == null) {
            til.setErrorEnabled(false);
        } else {
            til.setErrorEnabled(true);
        }

    }

    private void clearFocus(){
        View view = this.getCurrentFocus();
        if(view!=null&& view instanceof EditText){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            view.clearFocus();
        }
    }
}
