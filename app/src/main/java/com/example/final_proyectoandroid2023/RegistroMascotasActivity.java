package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.final_proyectoandroid2023.db.DataBaseMascotas;
import com.example.final_proyectoandroid2023.db.DataBaseUsuarios;
import com.google.android.material.snackbar.Snackbar;

public class RegistroMascotasActivity extends AppCompatActivity {
    EditText etNombreMascota, etDescripcionMascota, etRaza;
    RadioGroup rgEspecieMascota;
    RadioButton rbPerro,rbGato;
    RadioGroup rgEdadMascota;
    RadioButton rbMenosSeisMeses,rbSeisMesesUnAnno,rbUnAnnoCincoAnno,rbMasCincoAnno;
    RadioGroup rgSexoMascota;
    RadioButton rbMacho,rbHembra;
    RadioGroup rgTamanoMascota;
    RadioButton rbPequeno,rbMediano,rbGrande;
    RadioGroup rgVacunaDiaMascota;
    RadioButton rbSiVacunaDia,rbNoVacunaDia;
    RadioGroup rgSaludMascota;
    RadioButton rbSiSano,rbNoSano;
    Button btnGuardarMascota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascotas);

        etNombreMascota = (EditText) findViewById(R.id.etNombreMascota);
        etDescripcionMascota = (EditText) findViewById(R.id.etDescripcionMascota);
        etRaza = (EditText) findViewById(R.id.etRazaMascota);

        rgEspecieMascota = (RadioGroup) findViewById(R.id.rgEspecieMascota);
        rbPerro = (RadioButton) findViewById(R.id.rbPerro);
        rbGato = (RadioButton) findViewById(R.id.rbGato);

        rgEdadMascota = (RadioGroup) findViewById(R.id.rgEdadMascota);
        rbMenosSeisMeses = (RadioButton) findViewById(R.id.rbMenosSeisMeses);
        rbSeisMesesUnAnno = (RadioButton) findViewById(R.id.rbSeisMesesUnAnno);
        rbUnAnnoCincoAnno = (RadioButton) findViewById(R.id.rbUnAnnoCincoAnno);
        rbMasCincoAnno = (RadioButton) findViewById(R.id.rbMasCincoAnno);

        rgSexoMascota = (RadioGroup) findViewById(R.id.rgSexoMascota);
        rbMacho = (RadioButton) findViewById(R.id.rbMacho);
        rbHembra = (RadioButton) findViewById(R.id.rbHembra);

        rgTamanoMascota = (RadioGroup) findViewById(R.id.rgTamanoMascota);
        rbPequeno = (RadioButton) findViewById(R.id.rbPequeno);
        rbMediano = (RadioButton) findViewById(R.id.rbMediano);
        rbGrande = (RadioButton) findViewById(R.id.rbGrande);

        rgVacunaDiaMascota = (RadioGroup) findViewById(R.id.rgVacunaDiaMascota);
        rbSiVacunaDia = (RadioButton) findViewById(R.id.rbSiVacunaDia);
        rbNoVacunaDia = (RadioButton) findViewById(R.id.rbNoVacunaDia);

        rgSaludMascota = (RadioGroup) findViewById(R.id.rgSaludMascota);
        rbSiSano = (RadioButton) findViewById(R.id.rbSiSano);
        rbNoSano = (RadioButton) findViewById(R.id.rbNoSano);

        btnGuardarMascota = (Button) findViewById(R.id.btnGuardarMascota);

        btnGuardarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseUsuarios dbUsuarios = new DataBaseUsuarios(RegistroMascotasActivity.this);
                DataBaseMascotas dbmascotas = new DataBaseMascotas(RegistroMascotasActivity.this);

                long idDueno;
                ContentValues contentValuesMascota = new ContentValues();

                SharedPreferences myShared = getSharedPreferences("misDatos",MODE_PRIVATE);
                String correo = myShared.getString("correo","No se encontro correo");

                if(dbmascotas.obtenerIdDuenoPorCorreo(correo) == -2){
                    // Agregar datos de usuario a duenos, si dueno no exista
                    String[] usuario = dbUsuarios.obtenerUsuarioPorCorreo(correo);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("nombre",usuario[0]); // Nombre
                    contentValues.put("correo",usuario[1]); // Correo
                    contentValues.put("telefono",usuario[2]); // Telefono

                    idDueno = dbmascotas.agregarDueno(contentValues);
                }else{
                    // entregar idDueno si existe
                    idDueno = dbmascotas.obtenerIdDuenoPorCorreo(correo);
                }

                // Agregar datos de la mascota a la DB
                String nombre = etNombreMascota.getText().toString();
                String descripcion = etDescripcionMascota.getText().toString();
                String raza = etRaza.getText().toString();
                if(!nombre.isEmpty()){
                    if(!descripcion.isEmpty()){
                        if(rbPerro.isChecked() || rbGato.isChecked()){
                            if(!raza.isEmpty()){
                                if(rbMenosSeisMeses.isChecked() || rbSeisMesesUnAnno.isChecked() || rbUnAnnoCincoAnno.isChecked() || rbMasCincoAnno.isChecked()){
                                    if(rbMacho.isChecked() || rbHembra.isChecked()){
                                        if(rbPequeno.isChecked() || rbMediano.isChecked() || rbGrande.isChecked()){
                                            if(rbSiVacunaDia.isChecked() || rbNoVacunaDia.isChecked()){
                                                if(rbSiSano.isChecked() || rbNoSano.isChecked()){
                                                    // Se agrega datos de la mascota a la BD
                                                    contentValuesMascota.put("id_dueno",idDueno);
                                                    contentValuesMascota.put("nombre_mascota",nombre);
                                                    contentValuesMascota.put("descripcion",descripcion);
                                                    String especie = "";
                                                    if(rbPerro.isChecked()){
                                                        especie = "Perro";
                                                    }else{
                                                        especie = "Gato";
                                                    }
                                                    contentValuesMascota.put("especie",especie);
                                                    contentValuesMascota.put("raza",raza);
                                                    String edad ="";
                                                    if(rbSeisMesesUnAnno.isChecked()){
                                                        edad = "Menor a 6 meses";
                                                    }else if(rbSeisMesesUnAnno.isChecked()){
                                                        edad = "6 meses - 1";
                                                    }else if(rbUnAnnoCincoAnno.isChecked()){
                                                        edad = "1 - 5";
                                                    }else{
                                                        edad = "+5";
                                                    }
                                                    contentValuesMascota.put("edad",edad);
                                                    String sexo = "";
                                                    if(rbMacho.isChecked()){
                                                        sexo = "Macho";
                                                    }else{
                                                        sexo = "Hembra";
                                                    }
                                                    contentValuesMascota.put("sexo",sexo);
                                                    String tamano = "";
                                                    if(rbPequeno.isChecked()){
                                                        tamano = "Pequeno";
                                                    }else if(rbMediano.isChecked()){
                                                        tamano = "Mediano";
                                                    }else{
                                                        tamano = "Grande";
                                                    }
                                                    contentValuesMascota.put("tamano",tamano);
                                                    String vacuna = "";
                                                    if(rbSiVacunaDia.isChecked()){
                                                        vacuna = "Si";
                                                    }else{
                                                        vacuna = "No";
                                                    }
                                                    contentValuesMascota.put("vacuna_dia",vacuna);
                                                    String sano = "";
                                                    if(rbSiSano.isChecked()){
                                                        sano = "Si";
                                                    }else{
                                                        sano = "No";
                                                    }
                                                    contentValuesMascota.put("sano",sano);

                                                    dbmascotas.agregarMascota(contentValuesMascota,idDueno);
                                                    Snackbar.make(v,"Mascota ingresada",Snackbar.LENGTH_LONG);
                                                    etNombreMascota.setText("");
                                                    etDescripcionMascota.setText("");
                                                    etRaza.setText("");
                                                    // Deseleccionar radioButton
                                                    deseleccionarRadioButtons(rgEspecieMascota);
                                                    deseleccionarRadioButtons(rgEdadMascota);
                                                    deseleccionarRadioButtons(rgSexoMascota);
                                                    deseleccionarRadioButtons(rgTamanoMascota);
                                                    deseleccionarRadioButtons(rgVacunaDiaMascota);
                                                    deseleccionarRadioButtons(rgSaludMascota);

                                                }else{
                                                    Snackbar.make(v,"Debe chequear si esta sana la mascota",Snackbar.LENGTH_LONG).show();
                                                }
                                            }else{
                                                Snackbar.make(v,"Debe chequear si la mascota esta sana",Snackbar.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Snackbar.make(v,"Debe chequear un tamaño",Snackbar.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Snackbar.make(v,"Debe chequear una sexo",Snackbar.LENGTH_LONG).show();
                                    }
                                }else{
                                    Snackbar.make(v,"Debe chequear una edad",Snackbar.LENGTH_LONG).show();
                                }
                            }else{
                                Snackbar.make(v,"Raza no puede estar vacio",Snackbar.LENGTH_LONG).show();
                            }
                        }else{
                            Snackbar.make(v,"Debe chequear Perro o Gato",Snackbar.LENGTH_LONG).show();
                        }

                    }else{
                        Snackbar.make(v,"Descripcion no puede estar vacio",Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(v,"Nombre no puede estar vacio",Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void deseleccionarRadioButtons(RadioGroup radioGroup) {
        int childCount = radioGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = radioGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                ((RadioButton) view).setChecked(false);
            }
        }
    }
}