package com.lalp.sist.controle_de_medidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;
import com.lalp.sist.controle_de_medidas.databinding.ActMenuDeTelasBinding;

public class MenuDeTelas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private ActMenuDeTelasBinding binding;

    @Override
    public void setContentView(View view) {
        drawerLayout =(DrawerLayout) getLayoutInflater().inflate(R.layout.act_menu_de_telas, null);
        FrameLayout container = drawerLayout.findViewById(R.id.fragment_container);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

       //drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.nav_treino:
                startActivity(new Intent(this, Treinos.class));
                overridePendingTransition(0,0);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;

            case R.id.nav_add_medidas:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                startActivity(new Intent(this, AddMedidas.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_lista_medidas:
                startActivity(new Intent(this, ListaDeMedidas.class));
                overridePendingTransition(0,0);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
                break;

            case R.id.nav_comp_medidas:
                startActivity(new Intent(this, ComparativoMedidas.class));
                overridePendingTransition(0,0);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
                break;

            case R.id.nav_list_treino:
                startActivity(new Intent(this, ListaDeUsuarios.class));
                overridePendingTransition(0,0);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
                break;

            case R.id.nav_logout:
                finishAffinity();
                break;
            case R.id.nav_download:
                linkParaDownload();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    public void linkParaDownload() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setData(Uri.parse("mailto:"));
        intent.setType("text/text");
        intent.putExtra(Intent.EXTRA_TEXT, "Baixe o app List&Treine e adicione sua ficha de treino e suas medidas, acesse o link da Play Store: https://bit.ly/listetreine" );

        startActivity(intent);
    }

   @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void allocationActivityTitle(String titleString){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }
    }
}