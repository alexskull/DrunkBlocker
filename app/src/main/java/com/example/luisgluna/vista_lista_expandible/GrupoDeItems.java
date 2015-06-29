package com.example.luisgluna.vista_lista_expandible;

import java.util.ArrayList;
import java.util.List;

public class GrupoDeItems {
    public String string;
    public final List<String> children = new ArrayList<String>();
    public GrupoDeItems(String string) {
        this.string = string;
    }
}