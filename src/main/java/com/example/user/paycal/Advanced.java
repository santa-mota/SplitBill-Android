package com.example.user.paycal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Advanced extends Activity implements View.OnClickListener{

    Button b1,b2;
    EditText tv2,tv3,tv4;
    String k1,k2,k3,k4;
    int sum,i,a,b,j,people,money,tax,discount,call,chk;
    String [] p,names;
    int [] arr1,arr2;
    int [] check;
    Intent k;
    Spinner drop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
        tv2=(EditText)findViewById(R.id.editText5);
        tv3=(EditText)findViewById(R.id.editText6);
        tv4=(EditText)findViewById(R.id.editText7);
        b1=(Button)findViewById(R.id.button3);
        b2=(Button)findViewById(R.id.button4);
        k=getIntent();
        people=k.getIntExtra("people",0);
        money=k.getIntExtra("money",0);
        tax=k.getIntExtra("tax",0);
        discount=k.getIntExtra("discount",0);
        call=k.getIntExtra("call",0);
        if(call!=2)
        {
            Toast.makeText(getApplicationContext(),"FATAL ERROR",Toast.LENGTH_LONG);
            finish();
        }
        p=new String[people];
        names=new String[people];
        arr1=new int[people];
        arr2=new int[people];
        check=new int[people];
        for(i=0;i<people;i++){check[i]=0;}
        for(i=0;i<people;i++)
        {
            p[i]=toString().valueOf(i+1);
        }
        drop=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,p);
        drop.setAdapter(adapter);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        drop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv2.setText(toString().valueOf(position+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advanced, menu);
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
            case R.id.button3 :
                k1=drop.getSelectedItem().toString();
                i=Integer.parseInt(k1);i--;
                k1=tv3.getText().toString();
                k3=tv4.getText().toString();
                k4=tv2.getText().toString();
                if( k3.isEmpty() || k4.isEmpty() || k3=="0")
                {
                    Toast.makeText(getApplicationContext(),"Fill the Fields Properly.",Toast.LENGTH_LONG).show();

                }
                else{
                if(k1.isEmpty()){k1="0";}
                a=Integer.parseInt(k1);
                arr1[i]=a;
                b=Integer.parseInt(k3);
                arr2[i]=b;
                names[i]=k4;
                check[i]=1;}
                break;
            case R.id.button4 :
                chk=1;
                k2="";
                for(i=0;i<people;i++)
                {
                    if(check[i]==0)
                    {
                        k2=k2+toString().valueOf(i+1)+",";chk=0;
                    }
                }
                if(chk==0){k2=k2.substring(0,k2.length()-1);}
                if(chk==0)
                {
                    Toast.makeText(getApplicationContext(),"Values of "+k2+" not filled yet",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Successfully Submitted",Toast.LENGTH_SHORT).show();
                    Bundle b=new Bundle();
                    b.putStringArray("names",names);
                    b.putIntArray("min",arr1);
                    b.putIntArray("max",arr2);
                    b.putInt("people",people);
                    b.putInt("money",money);
                    b.putInt("discount",discount);
                    b.putInt("tax",tax);
                    Intent z=new Intent(this,Direct.class);
                    z.putExtras(b);
                    z.putExtra("call",2);
                    startActivity(z);
                }
                break;
        }
    }
}