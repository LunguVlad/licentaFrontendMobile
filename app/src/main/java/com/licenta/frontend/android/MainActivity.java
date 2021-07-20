package com.licenta.frontend.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.licenta.frontend.android.models.User;

public class MainActivity extends AppCompatActivity {

    private User user;

    private TextView textViewWelcome;
    private Button buttonPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = getIntent().getParcelableExtra("user");

        this.textViewWelcome = this.findViewById(R.id.textViewWelcome);
        this.textViewWelcome.setText("Bine ai venit, " + user.getFirstName()+ "!");



    }

    public void introducereConsumOnClick(View view) {
        Intent it = new Intent(this,IntroducereConsumActivity.class);
        it.putExtra("user",user);
        this.startActivity(it);
    }

    public void clickAvizier(View view) {
        Intent it = new Intent(this,AvizierActivity.class);
        it.putExtra("numarBloc",user.getNumarBloc());
        this.startActivity(it);
    }

    public void clickPlateste(View view){
        Intent it = new Intent(this,PaymentActivity.class);
        it.putExtra("user",user);
        this.startActivity(it);
    }
}