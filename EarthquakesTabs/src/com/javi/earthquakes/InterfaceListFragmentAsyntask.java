package com.javi.earthquakes;

import java.util.ArrayList;

public interface InterfaceListFragmentAsyntask {
	public void actualizarListadoTerremotos(Quakes q);
	public void actualizarListadoTerremotos(ArrayList<Quakes> lista);
	public void resetearActualizarLista(ArrayList<Quakes> result);
}
