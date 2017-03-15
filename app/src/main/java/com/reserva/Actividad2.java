package com.reserva;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Actividad2 extends Activity {

	// Se agregó la variable apertura para que el usuario pueda seleccionar si desea alguna clase
	//de aperitivos o aperturas especiales para su mesa el día de su reservación
	String nombre = "", fecha = "", hora = "", apertura="";
	int personas = 0;
	TextView title;
	TextView reservador;
	TextView numeroPersonas;
	TextView fechaReservcacion;
	Spinner listaAperturas;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad2);
		title=(TextView)findViewById(R.id.txtTitulo);
		title.setText("Datos de reservacion");
		listaAperturas=(Spinner)findViewById(R.id.aperturas);
		reservador = (TextView) findViewById(R.id.txtNombre);
		numeroPersonas  = (TextView) findViewById(R.id.txtNumPersonas);
		fechaReservcacion = (TextView) findViewById(R.id.txtFecha);

		initListenerLista();

		Bundle recibe =  this.getIntent().getExtras();

		nombre = recibe.getString("nombre");
		personas = recibe.getInt("personas");
		fecha = recibe.getString("fecha");
		hora = recibe.getString("hora");

		reservador.setText("Reserva a nombre de: "+nombre);
		numeroPersonas.setText("Asistentes: "+personas);
		fechaReservcacion.setText("Fecha: "+fecha+"  Hora:"+hora);
		AlertDialog.Builder aviso=new AlertDialog.Builder(this);

		aviso.setTitle("Importante");
		aviso.setMessage("¡Escoge una apertura específica para tu mesa!");
		aviso.setPositiveButton("Bien", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.cancel();
			}
		});
		aviso.create().show();

	}

	//Agrega el listener de item seleccionado al spinner de aperturas
	//y muestra el item que se haya seleccionado en un Toast
	private void initListenerLista()
	{
		try {
			listaAperturas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
					apertura = adapterView.getItemAtPosition(i).toString();
					Toast.makeText(adapterView.getContext(), apertura, Toast.LENGTH_LONG).show();
				}

				@Override
				public void onNothingSelected(AdapterView<?> adapterView) {
					apertura = ":c";
				}
			});
		}catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

    public void hacerOtraReserva(View v) {
        Intent envia = new Intent(this, MainActivity.class);
		Bundle datosApertura= new Bundle();
		datosApertura.putString("Apertura",apertura);//Datos de regreso a la actividad 1
		envia.putExtras(datosApertura);
        finish();
        startActivity(envia);
    }

}
