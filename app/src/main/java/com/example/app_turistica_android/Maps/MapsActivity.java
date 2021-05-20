package com.example.app_turistica_android.Maps;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.app_turistica_android.Pantallas.Chat;
import com.example.app_turistica_android.Pantallas.ListadoLugares;
import com.example.app_turistica_android.PerfilUsuario;
import com.example.app_turistica_android.R;
import com.example.app_turistica_android.utils.Lugares;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnTypeSatelite, btnTypeHybrid;
    private BottomNavigationView museos, parques, lugaresOcio, lugaresInteres;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    BottomNavigationView botonNavegacion;
    Toolbar toolbar;
    DatabaseReference myRef;
    private ArrayList<Marker> tmpRealTimeMarkers = new ArrayList<>();
    private ArrayList<Marker> realTimeMarkers = new ArrayList<>();
    private static  final String TAG_Maps= "Maps";
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        uid = getIntent().getExtras().getString("uid");
        instancias();
        acciones();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private void acciones() {
        btnTypeSatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        btnTypeHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });


        botonNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mapas) {
                    //Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
                    //startActivity(intent);
                }
                if (item.getItemId() == R.id.lugares) {
                    Intent intentLugares = new Intent(MapsActivity.this, ListadoLugares.class);
                    startActivity(intentLugares);
                }
                if (item.getItemId() == R.id.chat) {
                    Intent intentChat = new Intent(MapsActivity.this, Chat.class);
                    intentChat.putExtra("uid", uid);
                    startActivity(intentChat);
                }
                if (item.getItemId() == R.id.perfilusuario) {
                    Intent intent = new Intent(MapsActivity.this, PerfilUsuario.class);
                    intent.putExtra("uid", uid);
                    //Log.v("prueba",uid);
                    startActivity(intent);
                }

                return true;
            }
        });
    }


    private void instancias() {
        btnTypeSatelite = findViewById(R.id.btnSatelite);
        btnTypeHybrid = findViewById(R.id.btnHybrid);
        botonNavegacion = findViewById(R.id.navegacion);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_superior);
        museos = findViewById(R.id.museos);
        parques = findViewById(R.id.parques);
        lugaresOcio = findViewById(R.id.lugaresOcio);
        lugaresInteres = findViewById(R.id.lugaresInteres);
        myRef = FirebaseDatabase.getInstance().getReference();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.museos:
                        Toast.makeText(getApplicationContext(), "Mostrando museos", Toast.LENGTH_SHORT).show();
                        myRef.child("lugares").child("defecto").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (Marker marker : realTimeMarkers) {
                                    marker.remove();
                                }

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Lugares lugares = snapshot.getValue(Lugares.class);
                                    Double latitud = lugares.getLatitud();
                                    Double longitud = lugares.getLongitud();
                                    String nombre = lugares.getNombre();
                                    String tipo = lugares.getTipo();
                                    if(tipo.equals("Museo")){
                                        MarkerOptions markerOptions = new MarkerOptions();
                                        markerOptions.position(new LatLng(latitud, longitud));
                                        markerOptions.title(nombre);
                                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.lugares));
                                        tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));
                                    }

                                }
                                realTimeMarkers.clear();
                                realTimeMarkers.addAll(tmpRealTimeMarkers);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case R.id.parques:
                        Toast.makeText(getApplicationContext(), "Mostrando parques", Toast.LENGTH_SHORT).show();
                        myRef.child("lugares").child("defecto").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (Marker marker : realTimeMarkers) {
                                    marker.remove();
                                }

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Lugares lugares = snapshot.getValue(Lugares.class);
                                    Double latitud = lugares.getLatitud();
                                    Double longitud = lugares.getLongitud();
                                    String nombre = lugares.getNombre();
                                    String tipo = lugares.getTipo();
                                    if(tipo.equals("Parque")){
                                        MarkerOptions markerOptions = new MarkerOptions();
                                        markerOptions.position(new LatLng(latitud, longitud));
                                        markerOptions.title(nombre);
                                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.lugares));
                                        tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));
                                    }


                                }
                                realTimeMarkers.clear();
                                realTimeMarkers.addAll(tmpRealTimeMarkers);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case R.id.lugaresOcio:
                        Toast.makeText(getApplicationContext(), "Mostrando lugares de ocio", Toast.LENGTH_SHORT).show();
                        myRef.child("lugares").child("defecto").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (Marker marker : realTimeMarkers) {
                                    marker.remove();
                                }

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Lugares lugares = snapshot.getValue(Lugares.class);
                                    Double latitud = lugares.getLatitud();
                                    Double longitud = lugares.getLongitud();
                                    String nombre = lugares.getNombre();
                                    String tipo = lugares.getTipo();
                                    if(tipo.equals("Ocio")) {
                                        MarkerOptions markerOptions = new MarkerOptions();
                                        markerOptions.position(new LatLng(latitud, longitud));
                                        markerOptions.title(nombre);
                                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.lugares));
                                        tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));
                                    }

                                }
                                realTimeMarkers.clear();
                                realTimeMarkers.addAll(tmpRealTimeMarkers);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case R.id.lugaresInteres:
                        Toast.makeText(getApplicationContext(), "Mostrando lugares de interés", Toast.LENGTH_SHORT).show();
                        myRef.child("lugares").child("defecto").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (Marker marker : realTimeMarkers) {
                                    marker.remove();
                                }

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Lugares lugares = snapshot.getValue(Lugares.class);
                                    Double latitud = lugares.getLatitud();
                                    Double longitud = lugares.getLongitud();
                                    String nombre = lugares.getNombre();
                                    String tipo = lugares.getTipo();
                                    if(tipo.equals("Calle")){
                                        MarkerOptions markerOptions = new MarkerOptions();
                                        markerOptions.position(new LatLng(latitud, longitud));
                                        markerOptions.title(nombre);
                                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.lugares));
                                        tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));
                                    }

                                }
                                realTimeMarkers.clear();
                                realTimeMarkers.addAll(tmpRealTimeMarkers);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case R.id.todos:
                        Toast.makeText(getApplicationContext(), "Mostrando todos los lugares", Toast.LENGTH_SHORT).show();
                        myRef.child("lugares").child("defecto").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (Marker marker : realTimeMarkers) {
                                    marker.remove();
                                }

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Lugares lugares = snapshot.getValue(Lugares.class);
                                    Double latitud = lugares.getLatitud();
                                    Double longitud = lugares.getLongitud();
                                    String nombre = lugares.getNombre();
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(new LatLng(latitud, longitud));
                                    markerOptions.title(nombre);
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.lugares));
                                    tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));


                                }
                                realTimeMarkers.clear();
                                realTimeMarkers.addAll(tmpRealTimeMarkers);
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                }

                return true;
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setPadding(10, 10, 10, 185);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        myRef.child("lugares").child("defecto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (Marker marker : realTimeMarkers) {
                    marker.remove();
                }

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Lugares lugares = snapshot.getValue(Lugares.class);
                    Double latitud = lugares.getLatitud();
                    Double longitud = lugares.getLongitud();
                    String nombre = lugares.getNombre();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitud, longitud));
                    markerOptions.title(nombre);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.lugares));
                    tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));


                }
                realTimeMarkers.clear();
                realTimeMarkers.addAll(tmpRealTimeMarkers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error al cargar las ubicaciones", Toast.LENGTH_SHORT).show();
            }
        });


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() { //Creamos marcas FAV del usuario Click largo
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.fav)).anchor(0.0f, 1.0f).position(latLng));
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { //hacer click en ubicación del mapa
            @Override
            public boolean onMarkerClick(Marker marker) {
                //Intent intent = new Intent(MapsActivity.this, AdaptadorRecycler.class);
                //startActivity(intent);
                //Toast.makeText(getApplicationContext(), "Hemos pulsado una ubicación", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        miUbicacion();

    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);

        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Mi ubicación").icon(BitmapDescriptorFactory.fromResource(R.drawable.dot)));
        mMap.animateCamera(miUbicacion);

    }

    private void actUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
        Log.v("mapa", "localizacion ready");
        //Toast.makeText(getApplicationContext(), String.format("Mi ubicacion es %.2f %.2f", lat, lng), Toast.LENGTH_SHORT).show(); TOAST Ubicacion Coordenadas
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actUbicacion(location);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion2() {
        if (hayPermisoLocalizacion()) {

        } else {

            String[] permisos = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(MapsActivity.this,
                    permisos, 1);

        }
        Log.v("mapa", String.valueOf(hayPermisoLocalizacion()));
    }

    private void miUbicacion() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permisos = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(MapsActivity.this,
                    permisos, 1);
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locListener);
    }

    public boolean hayPermisoLocalizacion() {
        return (ActivityCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

}
