package com.aluracursos.screenmatch.calculos;

public class FiltroDeRecomendaciones {
    public void filtro (Clasificacion clasificacion){
        if (clasificacion.getClasificacion()>= 4){
            System.out.println("Muy Bien evaluado en el momento");
        } else if (clasificacion.getClasificacion()>=2) {
            System.out.println("Popular en el momento");
        }else{
            System.out.println("Colocalo en tu lista para verlo despues");
        }
    }
}
