package com.example.gabi.busk_13_09;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import Fragments.Fragment_Horarios;
import Fragments.Fragment_Main_Onibus;
import Fragments.Fragment_Mensagens;
import Fragments.Fragment_mapa;
import POJO.ObjPonto;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String id_onibus;
    ArrayList<ObjPonto> lista=null;
    String nome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Intent intent = getIntent();
        id_onibus = intent.getStringExtra("id_onibus");
        nome = intent.getStringExtra("nome");
        this.setTitle(nome);
        FragmentManager fm = getFragmentManager();
        // Handle navigation view item clicks here.

        Bundle bundle = new Bundle();
        bundle.putString("idOnibus", id_onibus );
        Fragment_Main_Onibus fragInfo = new Fragment_Main_Onibus();
        fragInfo.setArguments(bundle);
        fm.beginTransaction().replace(R.id.content_frame2, fragInfo).commit();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.itemHome) {
            FragmentManager fm = getFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString("idOnibus", id_onibus );
            Fragment_Main_Onibus fragInfo = new Fragment_Main_Onibus();
            fragInfo.setArguments(bundle);

            fm.beginTransaction().replace(R.id.content_frame2, fragInfo).commit();
        }

        if (id == R.id.itemHorariosSegunda) {
            FragmentManager fm = getFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString("idOnibus", String.valueOf(id_onibus));
            bundle.putString("idDia", String.valueOf(0));
            Fragment_Horarios fragInfo = new Fragment_Horarios();
            fragInfo.setArguments(bundle);
            fm.beginTransaction().replace(R.id.content_frame2, fragInfo).commit();
        }
        if (id == R.id.itemVoltar) {
            finish();
        }
        if (id == R.id.itemPontos) {


            FragmentManager fm = getFragmentManager();

            Bundle bundle = new Bundle();
            bundle.putString("idOnibus", String.valueOf(id_onibus));
            Fragment_mapa fragInfo = new Fragment_mapa();
            fragInfo.setArguments(bundle);
            fm.beginTransaction().replace(R.id.content_frame2, fragInfo).commit();
        }
        if (id == R.id.itemMensagens) {
            FragmentManager fm = getFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString("idOnibus", String.valueOf(id_onibus));
            Fragment_Mensagens fragInfo = new Fragment_Mensagens();
            fragInfo.setArguments(bundle);
            fm.beginTransaction().replace(R.id.content_frame2, fragInfo).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
