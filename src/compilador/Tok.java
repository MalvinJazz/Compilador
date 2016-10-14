/*
 * Copyright (C) 2016 cgarciarobles
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package compilador;

/**
 *
 * @author cgarciarobles
 */
public class Tok {
    String tipo;
    String token;
    String fila;
    String columna;

    public Tok(String tipo, String token, String fila, String columna) {
        this.tipo = tipo;
        this.token = token;
        this.fila = fila;
        this.columna = columna;
    }


    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFila() {
        return fila;
    }

    public String getColumna() {
        return columna;
    }
    
    
    
}
