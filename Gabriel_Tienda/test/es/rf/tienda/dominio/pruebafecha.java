package es.rf.tienda.dominio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class pruebafecha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(df.format(Calendar.getInstance().getTime()));

	}

}
