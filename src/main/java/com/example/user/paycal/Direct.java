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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Direct extends Activity  implements View.OnClickListener{

    Spinner drop;
    String tmp;
    Button b1,b2,b3,b4,b5;
    TextView tv1,tv2,tv3,tv4;
    int remfive=0,remten=0;
    int call,people,money,tax,j,discount,rem,temp,chk=0;
    int []exact,rfive,rten,min,max;
    String [] name;
    Intent i;
    Bundle b;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct);
        i=getIntent();
        call=i.getIntExtra("call", 0);
        b1=(Button)findViewById(R.id.button9);
        b2=(Button)findViewById(R.id.button5);
        b3=(Button)findViewById(R.id.button6);
        b4=(Button)findViewById(R.id.button7);
        b5=(Button)findViewById(R.id.button8);
        tv1=(TextView)findViewById(R.id.editText4);
        tv2=(TextView)findViewById(R.id.editText8);
        tv3=(TextView)findViewById(R.id.editText9);
        tv4=(TextView)findViewById(R.id.editText10);
        drop=(Spinner)findViewById(R.id.spinner);


        if(call==1)
        {
            people=i.getIntExtra("people",0);
            money=i.getIntExtra("money",0);
            tax=i.getIntExtra("tax",0);
            discount=i.getIntExtra("discount",0);
            money=(int)Math.ceil(money - ((discount / 100) * money));
            money=(int)Math.ceil(money + ((tax / 100) * money));
            exact=new int[people];
            rfive=new int[people];
            rten=new int[people];
            name=new String[people];
            for(j=0;j<people;j++)
            {
                name[j]=toString().valueOf(j+1);
            }
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,name);
            drop.setAdapter(adapter);
            for(j=0;j<people;j++)
            {
                exact[j]=(int)(money/people);
            }
            rem=money-(int)(Math.floor(money / people))*people;
            tv1.setText("1");
            tv2.setText(toString().valueOf(exact[0]));
            tv3.setText(toString().valueOf(money));
            tv4.setText(toString().valueOf(rem));
            chk=1;
            rfive=new int[people];
            rten=new int[people];
            roundfive();
            roundten();
        }
        else if(call==2)
        {
            b=i.getExtras();
            people=b.getInt("people");
            money=b.getInt("money");
            discount=b.getInt("discount");
            tax=b.getInt("tax");
            money=(int)Math.ceil(money - ((discount / 100) * money));
            money=(int)Math.ceil(money + ((tax / 100) * money));
            name=new String[people];
            min=new int[people];
            max=new int[people];
            rfive=new int[people];
            rten=new int[people];
            exact=new int[people];
            name=b.getStringArray("names");
            min=b.getIntArray("min");
            max=b.getIntArray("max");
            roundexact();
            roundfive();
            roundten();
            tv1.setText(name[0]);
            tv2.setText(toString().valueOf(exact[0]));
            tv3.setText(toString().valueOf(money));
            tv4.setText(toString().valueOf(rem));
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,name);
            drop.setAdapter(adapter);
            chk=1;

        }
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        drop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(call==1) {
                    temp = Integer.parseInt(drop.getSelectedItem().toString());
                    tv1.setText(toString().valueOf(position+1));
                    if (chk == 1) {
                        tv2.setText(toString().valueOf(exact[position]));
                    } else if (chk == 5) {
                        tv2.setText(toString().valueOf(rfive[position]));
                    } else if (chk == 10) {
                        tv2.setText(toString().valueOf(rten[position]));
                    }
                }
                else if(call==2)
                {
                    tv1.setText(name[position]);
                    if (chk == 1) {
                        tv2.setText(toString().valueOf(exact[position]));
                    } else if (chk == 5) {
                        tv2.setText(toString().valueOf(rfive[position]));
                    } else if (chk == 10) {
                        tv2.setText(toString().valueOf(rten[position]));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_direct, menu);
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
            case R.id.button9:
                if(call==1)
                {
                    temp=Integer.parseInt(tv1.getText().toString());
                    temp--;
                    temp=(++temp)%people;
                    tv1.setText(toString().valueOf(temp+1));
                    if(chk==1){tv2.setText(toString().valueOf(exact[temp]));}
                    else if(chk==5){tv2.setText(toString().valueOf(rfive[temp]));}
                    else if(chk==10){tv2.setText(toString().valueOf(rten[temp]));}
                }
                else if(call==2)
                {
                    tmp=tv1.getText().toString();
                    for(j=0;j<people;j++) {
                        if (name[j].compareToIgnoreCase(tmp) == 0) {
                            break;
                        }
                    }
                    if(j==people-1){j=0;}
                        else{j++;}

                     tv1.setText(name[j]);
                    if(chk==1){tv2.setText(toString().valueOf(exact[j]));}
                    else if(chk==5){tv2.setText(toString().valueOf(rfive[j]));}
                    else if(chk==10){tv2.setText(toString().valueOf(rten[j]));}
                }
                break;
            case R.id.button5:
                if(call==1 && chk!=5)
                {
                    temp=Integer.parseInt(tv1.getText().toString());
                    temp--;
                    tv2.setText(toString().valueOf(rfive[temp]));
                    tv4.setText(toString().valueOf(remfive));
                }
                else if(call==2 && chk!=5)
                {
                    tmp=tv1.getText().toString();
                    for(j=0;j<people;j++)
                    {
                        if(name[j]==tmp){break;}
                    }
                    tv2.setText(toString().valueOf(rfive[j]));
                    tv4.setText(toString().valueOf(remfive));
                }
                chk=5;
                break;
            case R.id.button6:
                if(call==1 && chk!=10)
                {
                    temp=Integer.parseInt(tv1.getText().toString());
                    temp--;
                    tv2.setText(toString().valueOf(rten[temp]));
                    tv4.setText(toString().valueOf(remten));
                }
                else if(call==2 && chk!=10)
                {
                    tmp=tv1.getText().toString();
                    for(j=0;j<people;j++)
                    {
                        if(name[j]==tmp){break;}
                    }
                    tv2.setText(toString().valueOf(rten[j]));
                    tv4.setText(toString().valueOf(remten));
                }
                chk=10;
                break;
            case R.id.button7:
                if(call==1 && chk!=1)
                {
                    temp=Integer.parseInt(tv1.getText().toString());
                    temp--;
                    tv2.setText(toString().valueOf(exact[temp]));
                    tv4.setText(toString().valueOf(rem));
                }
                else if(call==2 && chk!=1)
                {
                    tmp=tv1.getText().toString();
                    for(j=0;j<people;j++)
                    {
                        if(name[j]==tmp){break;}
                    }
                    tv2.setText(toString().valueOf(exact[j]));
                    tv4.setText(toString().valueOf(rem));
                }
                chk=1;
                break;
            case R.id.button8:
                Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT);
                finish();
                break;
        }
    }

    public void roundexact()
    {
        for(j=0;j<people;j++)
        {
            exact[j]=money/people;
        }
        rem=money-(int)(Math.floor(money/people))*people;

        for(j=0;j<people;j++)
        {
            if(exact[j]>max[j])
            {
                rem+=exact[j]-max[j];exact[j]=max[j];
            }
        }
        j=0;
        temp=0;
        while(rem!=0)
        {
            if(exact[j]<max[j])
            {
                exact[j]++;rem--;temp=0;
            }
            else{temp++;}
            j=(++j)%people;
            if(temp==people+1){break;}
        }
        return ;
    }



    public void roundfive()
    {
        for(j=0;j<people;j++)
        {
            rfive[j]=exact[j]-exact[j]%5;
            remfive+=exact[j]%5;
        }
        if(remfive<5){return;}
        if(call==1)
        {
            j=0;
            while(remfive>=5)
            {
                rfive[j]+=5;
                remfive-=5;
                j=(++j)%people;
            }
        }
        else if(call==2)
        {
            temp=0;
            j=0;
            while(remfive>=5)
            {
                if((max[j]-rfive[j])>=5)
                {
                    rfive[j]+=5;
                    remfive-=5;
                    temp=0;
                }
                else{temp++;}
                j=(++j)%people;
                if(temp>=people+1){break;}
            }
        }
        return;
    }


    public void roundten()
    {
        for(j=0;j<people;j++)
        {
            rten[j]=exact[j]-exact[j]%10;
            remten+=exact[j]%10;
        }
        if(remten<=10){return;}
        if(call==1)
        {
            j=0;
            while(remten>=10)
            {
                rten[j]+=10;
                remten-=10;
            }
            j=(++j)%people;
        }
        else if(call==2)
        {
            temp=0;j=0;
            while(remten>=10)
            {
                if((max[j]-rten[j])>=10)
                {
                    rten[j]+=10;
                    remten-=10;
                    temp=0;
                }
                else{temp++;}
                j=(++j)%people;
                if(temp==people+1){break;}
            }
        }
        return;
    }
}