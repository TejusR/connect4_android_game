package com.example.connect4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class restart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart);
        Button b=findViewById(R.id.button);
        TextView t=findViewById(R.id.textView);
        int w=getIntent().getIntExtra("winner",0);
        t.setText("player "+w+" wins");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent res=new Intent(getApplicationContext(),homeactivity.class);
                startActivity(res);
            }
        });
    }
}
