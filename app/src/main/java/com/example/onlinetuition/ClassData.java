package com.example.onlinetuition;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/*public class ClassData extends AppCompatActivity {

    ListView myListView;

    ArrayList<String> myArrayList = new ArrayList<>();

    DatabaseReference mRef;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        ArrayAdapter<String> myArrayAdapter  = new ArrayAdapter<String>(ClassData.this, android.R.layout.simple_list_item_1, myArrayList);

        myListView = (ListView) findViewById(R.id.list_class);
        myListView.setAdapter(myArrayAdapter);

        mRef = FirebaseDatabase.getInstance().getReference();

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                myArrayList.add(value);
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}*/
