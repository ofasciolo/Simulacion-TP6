package org.simulacion.Eventos;

import org.simulacion.Models.Simulacion;

import java.util.ArrayList;

public class Actualizacion extends Evento {

    public Actualizacion(double instante) {
        super(instante);
    }

    @Override
    public void determinarEvento() {
        // Creo que es el metodo mas complicado, 
        // tiene que elegir un criterio para actualizar los tickets
        // recorrer todos los tickets e ir sacando unos de una cola y metiendolos en otra
        // Por ejemplo: si el tiempo entre que el ticket fue creado y el instante actual es mayor al tiempo de resolucion estimado, se cambia de cola
    }

    @Override
    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        Actualizacion nuevActualizacion = new Actualizacion(getInstante() + Simulacion.getInstance().getTiempoDeActualizacion());
        return new ArrayList<Evento>() {{
            add(nuevActualizacion);
        }};
    }

    @Override
    public ArrayList<Evento> eventosFuturosCondicionados() {
        return new ArrayList<Evento>();
    }
    
}
