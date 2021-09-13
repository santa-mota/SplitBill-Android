package com.example.user.paycal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    Intent i,i1;
    int p=0;
    Animation anim;
    EditText tv1, tv2, tv3, tv4;
    Button b1, b2,b3;
    int a=0,res=0,p1=0,len=0,temp=0;
    String s;
    char ch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (EditText) findViewById(R.id.editText1);
        tv2 = (EditText) findViewById(R.id.editText);
        tv3 = (EditText) findViewById(R.id.editText2);
        tv4 = (EditText) findViewById(R.id.editText3);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button11);
        anim= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        i=new Intent(this,Advanced.class);
        i1=new Intent(this,Direct.class);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button11:
                a=0;res=0;p1=0;
                s=tv1.getText().toString();
                s=s+"+";
                for(len=0;len<s.length();len++)
                {
                    ch=s.charAt(len);
                    if(ch=='+' || ch=='-' || ch=='*' || ch=='/')
                    {
                        if(p1==0)
                        {
                            res=a;a=0;p1=(int)ch;
                        }
                        else
                        {
                            calc();p1=(int)ch;
                        }
                    }
                    else
                    {
                        a=a*10+((int)ch-48);
                    }
                }
                tv1.setText(toString().valueOf(res));
                break;
            case R.id.button2:
            Toast.makeText(getApplicationContext(), "Wait", Toast.LENGTH_SHORT).show();
            p=2;
                if(p==2)
                {
                    String s=tv1.getText().toString();
                    i.putExtra("call",p);
                    i.putExtra("money",Integer.parseInt(s));
                    s=tv4.getText().toString();
                    i.putExtra("people",Integer.parseInt(s));
                    s=tv2.getText().toString();
                    i.putExtra("tax",Integer.parseInt(s));
                    s=tv3.getText().toString();
                    i.putExtra("discount",Integer.parseInt(s));
                    startActivity(i);
                }
            break;
            case R.id.button:
            AlertDialog.Builder build = new AlertDialog.Builder(this);
            build.setTitle("Are You Sure?");
            build.setMessage("This option doesn't allow you to distinguish te prices paid by people.\nContinue?");
            build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Calculating", Toast.LENGTH_SHORT).show();
                    p=1;
                    s=tv1.getText().toString();
                    i1.putExtra("call",p);
                    i1.putExtra("money",Integer.parseInt(s));
                    s=tv4.getText().toString();
                    i1.putExtra("people",Integer.parseInt(s));
                    s=tv2.getText().toString();
                    i1.putExtra("tax",Integer.parseInt(s));
                    s=tv3.getText().toString();
                    i1.putExtra("discount",Integer.parseInt(s));
                    startActivity(i1);
                }
            });
            build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Click the other Button for advanced ettings", Toast.LENGTH_SHORT).show();
                    b2.setAnimation(anim);
                }
            });
                AlertDialog ad=build.create();
                ad.show();
                if(p==1)
                {
                    finish();
                }
                break;
        }
    }

    public void calc()
    {
        if(p1==(int)'+'){res=res+a;a=0;return;}
        else if(p1==(int)'-'){res=res-a;a=0;return;}
        else if(p1==(int)'*'){res=res*a;a=0;return;}
        else if(p1==(int)'/'){res=res/a;a=0;return;}
    }
}