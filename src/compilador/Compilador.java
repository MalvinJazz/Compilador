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

import java.util.Arrays;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MalvinJazz, CGarciaRobles
 * 
 * 
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(Arrays.toString(args));
        FileReader archivo;
        try {
            //Se usará "código fuente" como archivo de entrada, para tomarlo como lenguaje a tratar en el compilador.
            archivo = new FileReader("src" + File.separator + "compilador" + File.separator + "codigofuente.txt");
            //Para llamar a Analizador Léxico se utilizará 'analizadorLexico
            AnalizadorLexico analizadorLexico = new AnalizadorLexico(archivo);
            analizadorLexico.yylex();
            archivo.close();
            
        } catch (IOException e) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
}
