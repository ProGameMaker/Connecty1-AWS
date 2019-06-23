package com.example.connecty;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, PopupMenu.OnMenuItemClickListener {

    private GoogleMap mMap;
    public MarkerOptions markerOptions;
    public final Context context = this;
    public String languagle;
    ArrayList<Group> arrayList;


    double x = 10.7271046;
    double y = 106.7180038;
    String Username="dtnhan";
    String Password="123456789";
    String tm1;
    String tm2;
    Group tm3;
    int check=0;
    Group thisUser;
    List<Group> listGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView button = findViewById(R.id.languageButton);
        registerForContextMenu(button);

        thisUser=LoginLocal(Username,Password);

        if(thisUser==null){
            //Toast.makeText(MapsActivity.this,"OK OK",Toast.LENGTH_SHORT).show();
        }
        else{
            //set listener cho user do khi co thay doi
            final DatabaseReference setListenThisUser = FirebaseDatabase.getInstance().getReference().child("User").child("Local").child(thisUser.ID);
            setListenThisUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    thisUser.ID =(dataSnapshot.child("ID").getValue().toString());
                    thisUser.Description=(dataSnapshot.child("Description").getValue().toString());
                    thisUser.y=(Float.parseFloat(dataSnapshot.child("y").getValue().toString()));
                    thisUser.x=(Float.parseFloat(dataSnapshot.child("x").getValue().toString()));
                    thisUser.ImageID=(Integer.parseInt( dataSnapshot.child("ImageID").getValue().toString()));
                    thisUser.Language=(dataSnapshot.child("Language").getValue().toString());
                    thisUser.Level=(Integer.parseInt(dataSnapshot.child("Level").getValue().toString()));
                    thisUser.Username=(dataSnapshot.child("Username").getValue().toString());
                    thisUser.Pass=(dataSnapshot.child("Pass").getValue().toString());
                    thisUser.TelephoneNumber=(dataSnapshot.child("TelephoneNumber").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        SetData();
        Getlocal();

    }
    void SetAutoComplete() {

        if (!Places.isInitialized()) {
            Places.initialize(this, getString(R.string.google_maps_key));
        }

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //Toast.makeText(MapsActivity.this,"ChoosePlace",Toast.LENGTH_SHORT).show();
                LatLng pos = place.getLatLng();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.

            }
        });


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(x,y);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Your position"));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .zoom(17)
                .target(sydney)
                .build();
        final CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(cu);

        SetAutoComplete();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                markerOptions = new MarkerOptions().position(latLng);
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.addMarker(markerOptions);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .zoom(17)
                        .target(latLng)
                        .build();
                CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cameraPosition);
                mMap.animateCamera(cu);

                                 // Creates a CameraPosition from the builder
            }
        });



        //View view = inflater.inflate(R.layout.fragment_list_of_group, container, false);

    }
    public ArrayList<Group> FindListOfData() {
        //set list of data
        return arrayList;
    }
    public void ChooseUser(View view) {

        Intent intent = new Intent(MapsActivity.this,InfomationActivity.class);

        intent.putExtra("Size",listGroup.size());
        for (int i = 0; i < listGroup.size(); i++) {
            String extra = "Group" + String.valueOf(i);
            intent.putExtra(extra,listGroup.get(i));
        }

        startActivity(intent);
    }
    void SetData() {

        //mock data
        /*arrayList = new ArrayList<>();
        arrayList.add(new Group("1751109","English","I’ve been working as an administrative assistant for three years. At my current job in the finance department of a midsize company, I handle scheduling, meeting and travel planning for four executives and 20 staff members. I also help prepare correspondence, presentations and reports.","0981895949",1));
        arrayList.add(new Group("1751084","English","I’m known for being a detail-oriented, well-organized team player. I never miss deadlines, I’m a good communicator and I can juggle multiple tasks at once. In my performance reviews, my supervisor always notes that he appreciates my professionalism and enthusiasm for the job.","037 5994 743",2));
        arrayList.add(new Group("1751108","English","With this experience under my belt, I’m looking for an opportunity to take the next step in my career. I’m hoping to do so in an organization like yours that works to improve the environment, which is something I’m passionate about.","0909254721",3));
        arrayList.add(new Group("1751083","English","Be concise. Don’t take up too much time with your response. You don’t have to tell the hiring manager every single thing that makes you a good fit for the position. Just give a few important details that will spark their interest in learning more about you, and you’ll get the interview off to a strong start.","0356506802",4));
        arrayList.add(new Group("1710032","Japanese","And some job seekers simply summarize their resume, going point-by-point through their work experience and education history.","0949150989",5));

        arrayList.add(new Group("1751109","English","I’ve been working as an administrative assistant for three years. At my current job in the finance department of a midsize company, I handle scheduling, meeting and travel planning for four executives and 20 staff members. I also help prepare correspondence, presentations and reports.","0981895949",1));
        arrayList.add(new Group("1751084","English","I’m known for being a detail-oriented, well-organized team player. I never miss deadlines, I’m a good communicator and I can juggle multiple tasks at once. In my performance reviews, my supervisor always notes that he appreciates my professionalism and enthusiasm for the job.","037 5994 743",2));
        arrayList.add(new Group("1751108","English","With this experience under my belt, I’m looking for an opportunity to take the next step in my career. I’m hoping to do so in an organization like yours that works to improve the environment, which is something I’m passionate about.","0909254721",3));
        arrayList.add(new Group("1751083","English","Be concise. Don’t take up too much time with your response. You don’t have to tell the hiring manager every single thing that makes you a good fit for the position. Just give a few important details that will spark their interest in learning more about you, and you’ll get the interview off to a strong start.","0356506802",4));
        arrayList.add(new Group("1710032","Japanese","And some job seekers simply summarize their resume, going point-by-point through their work experience and education history.","0949150989",5));*/

//        AddLocal("English","Toi thich an chay, yeu mau hong",10.3f,106.3f,"01675994743",30,6,"dtnhan","123456789");
    }
    public Group AddLocal(String Language,String Description, float presentPosx, float getPresentPosy, String TelephoneNumber, int Level, int ImageID, String User, String Pass){
        tm1=User;
        tm2=Pass;
        check=0;
        DatabaseReference add = FirebaseDatabase.getInstance().getReference();
        add.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot local : dataSnapshot.getChildren())
                {
                    //Toast.makeText(getApplicationContext(),"Nhan",Toast.LENGTH_SHORT).show();
                    if(local.child("User").child("Local").child("Username").exists() && local.child("User").child("Local").child("Username").getValue().toString().equals(tm1) && local.child("User").child("Local").child("Pass").exists() && local.child("User").child("Local").child("Pass").getValue().toString().equals(tm2))
                    {
                        //Toast.makeText(getApplicationContext(),"User and Pass have exist",Toast.LENGTH_SHORT).show();
                        check=1;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (check == 1) return null;
        Group g=new Group("",Language,Description,presentPosx,getPresentPosy,TelephoneNumber,Level,ImageID,User,Pass);
        String key=add.child("User").child("Local").push().getKey();
        g.ID=key;
        add.child("User").child("Local").child(key).setValue(g);
        return g;
    }
    public Group LoginLocal(String User, String Pass){

        tm1=User;
        tm2=Pass;
        tm3=new Group();
        check=0;

        DatabaseReference Local = FirebaseDatabase.getInstance().getReference();
        Local.child("User").child("Local").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot local : dataSnapshot.getChildren())
                {
                    //Toast.makeText(MapsActivity.this,"OK",Toast.LENGTH_SHORT).show();
                    String a=local.child("Username").getValue().toString();
                    String b=local.child("Pass").getValue().toString();
                    if(local.child("Username").exists() && local.child("Username").getValue().toString().equals(tm1) && local.child("Pass").exists() && local.child("Pass").getValue().toString().equals(tm2))
                    {
                        tm3.ID= (local.child("ID").getValue().toString());
                        tm3.Description= ( local.child("Description").getValue().toString());
                        tm3.y=  (Float.parseFloat(local.child("y").getValue().toString()));
                        tm3.x=(Float.parseFloat(local.child("x").getValue().toString()));
                        tm3.ImageID=(Integer.parseInt( local.child("ImageID").getValue().toString()));
                        tm3.Language =(local.child("Language").getValue().toString());
                        tm3.Level= (Integer.parseInt(local.child("Level").getValue().toString()));
                        tm3.Username =(local.child("Username").getValue().toString());
                        tm3.Pass =(local.child("Pass").getValue().toString());
                        tm3.TelephoneNumber =(local.child("TelephoneNumber").getValue().toString());
                        check=1;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(check==1) {
            //Toast.makeText(getApplicationContext(),"Login succesess",Toast.LENGTH_SHORT).show();
            return tm3;
        }
        else{//Toast.makeText(getApplicationContext(),"User is not exist",Toast.LENGTH_SHORT).show();
        return null;}
    }
    public void Getlocal() {
        listGroup=new ArrayList<>();

        DatabaseReference showList = FirebaseDatabase.getInstance().getReference().child("User").child("Local");
        showList.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot local : dataSnapshot.getChildren()) {
                    //Toast.makeText(MapsActivity.this,"THis Happend tm3",Toast.LENGTH_SHORT).show();
                    Group tm3=new Group();
                    tm3.ID =(local.child("ID").getValue().toString());
                    tm3.Description=(local.child("Description").getValue().toString());
                    tm3.y=(Float.parseFloat(local.child("y").getValue().toString()));
                    tm3.x=(Float.parseFloat(local.child("x").getValue().toString()));
                    tm3.ImageID=(Integer.parseInt(local.child("ImageID").getValue().toString()));
                    tm3.Language=(local.child("Language").getValue().toString());
                    tm3.Level=(Integer.parseInt(local.child("Level").getValue().toString()));
                    tm3.Username=(local.child("Username").getValue().toString());
                    tm3.Pass=(local.child("Pass").getValue().toString());
                    tm3.TelephoneNumber=(local.child("TelephoneNumber").getValue().toString());
                    listGroup.add(tm3);

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void languageShow(View view) {

        PopupMenu popup = new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.language_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.English:
                //Toast.makeText(this, "Option 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Vietnamese:
                //Toast.makeText(this, "Option 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }


    }

    void findRelate(double s[]) {
        int numpoints = 1000;
        int dim = 300;
        KDTree kdt = new KDTree(numpoints);
        File file = new File("C:\\Users\\pankaj\\Desktop\\vector.txt");
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String readLine = "";
            while ((readLine = in.readLine()) != null) {
                //đọc vào 300 chiều
                double x[] = new double[dim];
                for (int i = 0; i < dim; i++) {
                    x[i] = 0; //////xử lí chuỗi
                }
                kdt.add(x);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException e) {
        }
        KDNode kdn = kdt.find_nearest(s);
        System.out.println("(" + kdn.x[0] + " , " + kdn.x[1] + ")");
    }
}
