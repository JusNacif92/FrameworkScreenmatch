package com.aluracursos.screenmatch.model;

public enum Categoria {
    ACCION ("Action","Acción"),
    ROMANCE ("Romance","Romance"),
    COMEDIA ("Comedy", "Comedia"),
    DRAMA ("Drama","Drama"),
    CRIMEN ("Crime","Crimen");

    private String categoriaOmdb;
    private String generoEspanol;

    Categoria (String categoriaOmdb, String generoEspanol) {
        this.categoriaOmdb = categoriaOmdb;
        this.generoEspanol = generoEspanol;
    }

    public static Categoria fromString (String text){
        for(Categoria categora : Categoria.values()){
            if (categora.categoriaOmdb.equalsIgnoreCase(text)){
                return categora;
            }
        }

        throw new IllegalArgumentException("No se encontro la categorioa: "+text);
    }

    public static Categoria fromEspanol (String text){
        for(Categoria categora : Categoria.values()){
            if (categora.generoEspanol.equalsIgnoreCase(text)){
                return categora;
            }
        }

        throw new IllegalArgumentException("No se encontro el genero: "+text);
    }
}
