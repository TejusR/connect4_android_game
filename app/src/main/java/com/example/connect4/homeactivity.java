package com.example.connect4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class homeactivity extends AppCompatActivity {
    int row=6;
    int col=7,player=0,turn=0;
    float r,width,height;
    ImageView iv;
    int vacancy[][];
    Canvas canvas;
    Bitmap bitmap;
    Paint p1,p2,base;
    ImageButton b;
    Intent go;
    int x[][]=new int[50][2];
    int y[][]=new int[50][2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        go=new Intent(getApplicationContext(),restart.class);
        b=findViewById(R.id.imageButton2);
        iv=findViewById(R.id.imageView2);
        vacancy=new int[row+3][col+3];
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height=size.y;
        r=(float)((width/col)*0.5);
        bitmap=Bitmap.createBitmap((int)width,(int)height, Bitmap.Config.ARGB_8888);
        canvas=new Canvas(bitmap);
        p1=new Paint();
        p2=new Paint();
        base=new Paint();
        base.setStyle(Paint.Style.FILL);
        p1.setStyle(Paint.Style.FILL);
        p2.setStyle(Paint.Style.FILL);
        base.setColor(Color.WHITE);
        base.setTextSize(80);
        p1.setColor(Color.RED);
        p2.setColor(Color.YELLOW);
        for(int i=0;i<50;i++)
        {
            x[i][0]=100;
            x[i][1]=100;
        }
        for(int i=0;i<50;i++)
        {
            y[i][0]=100;
            y[i][1]=100;
        }
        for (int i = 0; i < row+3; i++)
        {
            for (int j = 0; j < col+3; j++)
            {
                vacancy[i][j]=0;
            }
        }
       //iv.setImageBitmap(bitmap);
       draw();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player==0)
                {
                    turn--;
                    vacancy[y[turn][1]][x[turn][1]]=0;
                    player=1;
                    x[turn][1]=100;
                    y[turn][1]=100;
                    draw();
                }
                else
                {
                    vacancy[y[turn][0]][x[turn][0]]=0;
                    player=0;
                    x[turn][0]=100;
                    y[turn][0]=100;
                    draw();
                }
            }
        });
    }
    public void draw() {
        canvas.drawColor(Color.rgb(0,0,255));
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                canvas.drawCircle((2 * j + 1) * r, height - (2 * i + 1) * r - 20, r, base);
            }
        }
        for (int i = 0; i <turn; i++) {
            canvas.drawCircle((2 * x[i][0] + 1) * r, height - (2 * y[i][0] + 1) * r - 20, r, p1);
            canvas.drawCircle((2 * x[i][1] + 1) * r, height - (2 * y[i][1] + 1) * r - 20, r, p2);
        }
            if (player == 1) {
                canvas.drawCircle((2 * x[turn][0] + 1) * r, height - (2 * y[turn][0] + 1) * r - 20, r, p1);
            } else {
                canvas.drawCircle((2 * x[turn][0] + 1) * r, height - (2 * y[turn][0] + 1) * r - 20, r, p1);
                canvas.drawCircle((2 * x[turn][1] + 1) * r, height - (2 * y[turn][1] + 1) * r - 20, r, p2);
            }

        //canvas.drawText("player " + player + " turn", 100, 100, base);
        iv.setImageBitmap(bitmap);
    }
    public void check()
    {
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(vacancy[i][j]==1)
                {
                    if(vacancy[i+1][j]==1&&vacancy[i+2][j]==1&&vacancy[i+3][j]==1)
                    {
                        go.putExtra("winner",1);
                        startActivity(go);
                    }
                    if(vacancy[i][j+1]==1&&vacancy[i][j+2]==1&&vacancy[i][j+3]==1)
                    {
                        go.putExtra("winner",1);
                        startActivity(go);
                    }
                    if(vacancy[i+1][j+1]==1&&vacancy[i+2][j+2]==1&&vacancy[i+3][j+3]==1)
                    {
                        go.putExtra("winner",1);
                        startActivity(go);
                    }
                }
                if(vacancy[i][j]==2)
                {
                    if(vacancy[i+1][j]==2&&vacancy[i+2][j]==2&&vacancy[i+3][j]==2)
                    {
                        go.putExtra("winner",2);
                        startActivity(go);
                    }
                    if(vacancy[i][j+1]==2&&vacancy[i][j+2]==2&&vacancy[i][j+3]==2)
                    {
                        go.putExtra("winner",2);
                        startActivity(go);
                    }
                    if(vacancy[i+1][j+1]==2&&vacancy[i+2][j+2]==2&&vacancy[i+2][j+2]==2)
                    {
                        go.putExtra("winner",2);
                        startActivity(go);
                    }
                }
            }
        }
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            for (int i = 0; i < col; i++) {
                if ((int)event.getX() > (2 * i + 1) * r && (int)event.getX() < (2 * i + 3) * r) {

                    x[turn][player] = i;
                    for (int j = 0; j < row; j++) {
                        if (vacancy[j][i] == 0) {
                            y[turn][player] = j;
                            if(player==0)
                              vacancy[j][i] = 1;
                            else
                                vacancy[j][i]=2;
                            break;
                        }
                    }
                    if (player == 0) {
                        player = 1;
                    } else {
                        player = 0;
                        turn++;
                    }
                }
            }
            draw();
            check();
        }
        return true;
    }

}
