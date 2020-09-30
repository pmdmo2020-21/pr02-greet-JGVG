package es.iessaladillo.pedrojoya.greet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.greet.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

}