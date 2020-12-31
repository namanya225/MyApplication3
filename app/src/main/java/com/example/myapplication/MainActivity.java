package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.*;

public class MainActivity extends AppCompatActivity {

    private MyReceiver receive=new MyReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            int number = intent.getIntExtra("Battery Level", 0);
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.ProgBar);
            progressBar.setProgress(number);
            TextView T = (TextView) findViewById(R.id.textV);
            T.setText("Your Batery is " + Integer.toString(number) + "%");

        }
};
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting,menu);
        return true;
    }
    public void myalarm(View b){
        EditText etText=findViewById(R.id.txtalm);
        int p=Integer.parseInt(etText.getText().toString());

        Intent intent=new Intent(getApplicationContext(),MyReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this.getApplicationContext(),0,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(p*1000),pendingIntent);
        Toast.makeText(this,"alarm set in"+ p +"seconds",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(receive, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        Button music =(Button)findViewById(R.id.button3);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,Main2Activity.class);
               startActivity(intent);

            }
        } );
        Button members=(Button)findViewById(R.id.but);
        members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, unitlist.class);
                startActivity(intent);
            }
        });
Button m =(Button)findViewById(R.id.but1);
        m.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this, recycle.class);
        startActivity(intent);
    }
});

    }
    public void sendMessage(View view) {
        EditText message = (EditText)findViewById(R.id.message);
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        intent.putExtra("MESSAGE", message.getText().toString());
        startActivity(intent);
        message.setText("");
        }



//for intenting the activites in the menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(this,Main4Activity.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(this, recycle.class));
                return true;
            case R.id.call:
               try {
                   int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
                   if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                       if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                           Toast.makeText(this,"this intends to call",Toast.LENGTH_LONG).show();
                       }
                       else {
                           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);

                       }
                   }
                 Intent contact =new Intent(Intent.ACTION_CALL, Uri.parse("tel:0704068371"));
                   startActivity(contact);
               }
               catch (Exception e){
                   e.printStackTrace();
               }
                return true;
            case R.id.email:
                Intent intent2=new Intent(Intent.ACTION_SEND);
                intent2.setData(Uri.parse("mailto:"));
                String to []={"bryntu9@gmail.com","markruhinda@gmail.com","elinahnabasitu@gmail.com"};
                intent2.putExtra(Intent.EXTRA_EMAIL,to);
                intent2.putExtra(Intent.EXTRA_SUBJECT,"GREETINGS");
                intent2.putExtra(Intent.EXTRA_TEXT,"nice corona holiday");
                intent2.setType("message/rfc822");
                startActivity(intent2);
                return true;

default:
          return super.onOptionsItemSelected(item) ;
        }

    }

    public void viewInformation(View view) {
    }
}
