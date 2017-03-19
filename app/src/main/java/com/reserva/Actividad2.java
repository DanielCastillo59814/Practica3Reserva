package com.reserva;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class Actividad2 extends Activity {

	// Se agregó la variable apertura para que el usuario pueda seleccionar si desea alguna clase
	//de aperitivos o aperturas especiales para su mesa el día de su reservación
	String nombre = "", fecha = "", hora = "", apertura="";
	int personas = 0;
	TextView title;
	TextView reservador;
	TextView numeroPersonas;
	TextView fechaReservcacion;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad2);
		title=(TextView)findViewById(R.id.txtTitulo);
		title.setText("Datos de reservacion");
		reservador = (TextView) findViewById(R.id.txtNombre);
		numeroPersonas  = (TextView) findViewById(R.id.txtNumPersonas);
		fechaReservcacion = (TextView) findViewById(R.id.txtFecha);

		Bundle recibe =  this.getIntent().getExtras();

		nombre = recibe.getString("nombre");
		personas = recibe.getInt("personas");
		fecha = recibe.getString("fecha");
		hora = recibe.getString("hora");

		reservador.setText("Reserva a nombre de: "+nombre);
		numeroPersonas.setText("Asistentes: "+personas);
		fechaReservcacion.setText("Fecha: "+fecha+"  Hora:"+hora);

	}

	//Agrega el listener de item seleccionado al spinner de aperturas
	//y muestra el item que se haya seleccionado en un Toast

    public void hacerOtraReserva(View v) {
        Intent envia = new Intent(this, MainActivity.class);
		Bundle datosApertura= new Bundle();
		datosApertura.putString("Apertura",apertura);//Datos de regreso a la actividad 1
		envia.putExtras(datosApertura);
        finish();
        startActivity(envia);
    }

    //Metodo para abrir mapa
    //con las cordenadas de las Tortas Sur 12 (Agricola Oriental)

    public void abreMapa(View o){
        final String LATITUD = "19.397103";
        final String LONGITUD = "-99.07194090000002";
        try{
			String uri = "geo:" + LATITUD + "," + LONGITUD;
			Intent intento = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intento);
        }catch(Exception e){
			Toast.makeText(this, "Algo salio mal: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

	}

	//Meotod para abrir la aplicacion telefono
    //con cierto numero de telefono a marcar

	public void marcaNumero(View o){
        final String NUMERO = "55 5558 1455";
        String uri = "tel:" + NUMERO.trim();
        Intent intento = new Intent(Intent.ACTION_DIAL,Uri.parse(uri));
        try{
            startActivity(intento);
        }catch(Exception e){
            Toast.makeText(this, "Algo salio mal: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
	}

	//Metodo para enviar correo
    //a cierta direccion de correo

	public void enviaCorreo(View o){
        final String CORREO = "tortasgigantesur12@gmail.com";
        Intent intento = new Intent(Intent.ACTION_SEND);
		intento.setType("text/plain");
		Bundle recipiente = new Bundle();
		recipiente.putStringArray(Intent.EXTRA_EMAIL,new String[]{CORREO});
		recipiente.putString(Intent.EXTRA_SUBJECT,"Asunto");
		recipiente.putString(Intent.EXTRA_TEXT,"Texto");
        intento.putExtras(recipiente);
        try{
            startActivity(intento);
        }catch(Exception e){
            Toast.makeText(this, "Algo salio mal: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
	}

	//Metodo para abrir el navegador
    //con la pagina web indicada

	public void abrirPagina(View o){
        final String PAGINA = "http://tortasgigantessur12.com/";
        Intent intento = new Intent(Intent.ACTION_VIEW, Uri.parse(PAGINA));
        try{
            startActivity(intento);
        }catch(Exception e){
            Toast.makeText(this, "Algo salio mal: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    //Metodo para abrir la aplicacion Camara

	public void tomarFoto(View o){
		Intent intento = new Intent("android.media.action.IMAGE_CAPTURE");
		try{
			startActivity(intento);
		}catch(Exception e){
			Toast.makeText(this, "Algo salio mal: " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}
