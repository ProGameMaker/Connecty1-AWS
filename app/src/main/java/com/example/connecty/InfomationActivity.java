package com.example.connecty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class InfomationActivity extends AppCompatActivity {

    ArrayList<Group> chooseGroup;
    Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);

        //set circular image
        ImageView iv = findViewById(R.id.iconView);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a5);

        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        mDrawable.setCircular(true);

        iv.setImageDrawable(mDrawable);

        chooseGroup = new ArrayList<>();
        Intent intent = getIntent();
        int n = (Integer) intent.getIntExtra("Size",0);
        for (int i = 0; i < n; i++) {
            String name = "Group" + String.valueOf(i);
            Group group = (Group) intent.getSerializableExtra(name);
            chooseGroup.add(group);
        }

        Rerandom(null);
    }

    public void Next(View view) {

        Intent intent = new Intent(this,OperationActivity.class);
        intent.putExtra("Group",group);

        startActivity(intent);
    }

    public void Call(View view) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + group.TelephoneNumber));
        //startActivity(intent);
        //Toast.makeText(this,"tel:"+group.TelephoneNumber,Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void Rerandom(View view) {

        //Set icon
        Random rand = new Random();
        int randNumber = rand.nextInt(chooseGroup.size());
        group = chooseGroup.get(randNumber);

        //Set icon
        ImageView iconView = (ImageView) findViewById(R.id.iconView);
        String name = "a" + String.valueOf(group.ImageID);
        int id = getResources().getIdentifier(name, "drawable", this.getPackageName());

        iconView.setImageResource(id);

        //Set background
        ImageView backgroundView = (ImageView) findViewById(R.id.backgroundPic);
        name = "b" + String.valueOf(group.ImageID);
        id = getResources().getIdentifier(name, "drawable", this.getPackageName());

        backgroundView.setImageResource(id);





        //Set infomation

        String info = chooseGroup.get(randNumber).Description;
        info += "\n";

        TextView infoView = (TextView) findViewById(R.id.infoView);
        infoView.setText(info);
        infoView.setMovementMethod(new ScrollingMovementMethod());

    }
}
