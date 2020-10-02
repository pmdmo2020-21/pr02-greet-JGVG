package es.iessaladillo.pedrojoya.greet;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.greet.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        b = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setupViews();
    }

    //  Start values and functionality are initialized.
    private void setupViews() {

        //Values
        b.rdb01.setChecked(true);
        b.textProgress.setText(getString(R.string.progress_bar_text, b.progressBar.getProgress(), b.progressBar.getMax()));

        //Funcionality
        b.swtPremium.setOnCheckedChangeListener((buttonView, isChecked) -> { InterleavingPremium(isChecked); });
        b.rdgTreatment.setOnCheckedChangeListener((group, checkedId) -> UpdateImage(checkedId));
        b.clickButton.setOnClickListener(v -> IncrementIfNotPremium());

    }

    //  1. Each time the premium is activated the progress is restarted and the progress bar disappears.
    //  2. When we return to the non-premium, the progress bar reappears.
    private void InterleavingPremium(boolean isChecked) {

        if (isChecked) {
            b.progressBar.setProgress(0);
            b.textProgress.setText(getString(R.string.progress_bar_text, b.progressBar.getProgress(), b.progressBar.getMax()));
            b.progressBar.setVisibility(View.GONE);
            b.textProgress.setVisibility(View.GONE);
        }else{
            b.progressBar.setVisibility(View.VISIBLE);
            b.textProgress.setVisibility(View.VISIBLE);
        }

    }

    //  1. Check if you are premium or not.
    //      1.1. If you are not premium, the usage counter increases to its limit and then displays a warning message.
    //      1.2. If it is premium, the counter disappears.
    private void IncrementIfNotPremium() {

        int counter = b.progressBar.getProgress();

        if(!b.txtName.getText().toString().equals("") && !b.txtSirname.getText().toString().equals("")){
            if(!b.swtPremium.isChecked()){
                if(counter >= b.progressBar.getMax()){
                    b.textGreet.setText(R.string.buy_premium_text);
                }else{
                    ShowGreet();
                    counter++;
                    b.progressBar.setProgress(counter);
                    b.textProgress.setText(getString(R.string.progress_bar_text, counter, b.progressBar.getMax()));
                }
            }else{
                ShowGreet();
            }
        }

    }

    //  When I press the button I collect the information given (name, surname and treatment) and I launch a greeting.
    private void ShowGreet() {

        RadioButton seleccted_rb = findViewById(b.rdgTreatment.getCheckedRadioButtonId());

        String treatment = seleccted_rb.getText().toString();
        String name = b.txtName.getText().toString();
        String surname = b.txtSirname.getText().toString();

        if(b.chkPolitely.isChecked()) {
            b.textGreet.setText(getString(R.string.menssage_politely_text,treatment,name,surname));
        }else{
            b.textGreet.setText(getString(R.string.menssage_colloquially_text,name,surname));
        }

    }

    //  Updates the image when the selected radiobutton is changed.
    private void UpdateImage(int checkedId) {

        if(checkedId == b.rdb01.getId()){
            b.imgPhoto.setImageResource(R.drawable.ic_mr);
        }
        else if(checkedId == b.rdb02.getId()){
            b.imgPhoto.setImageResource(R.drawable.ic_mrs);
        }else{
            b.imgPhoto.setImageResource(R.drawable.ic_ms);
        }

    }

}