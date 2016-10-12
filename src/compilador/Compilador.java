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
import java.util.Stack;

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
        FileReader archivo;
        Stack<String> argumentos = new Stack<>();
        
        try {
            
            for(int i = args.length - 1; i>-1; i--){
                argumentos.push(args[i]);
            }
            
            if(!argumentos.isEmpty()){
                
                if(argumentos.pop().equalsIgnoreCase("compilar")){
                    
                    if(!argumentos.isEmpty()){
                        String pop = argumentos.pop();
                    
                        if(pop.toLowerCase().endsWith(".led")){

                            archivo = new FileReader(pop);
                            AnalizadorLexico analizadorLexico = new AnalizadorLexico(archivo);
                            analizadorLexico.yylex();
                            archivo.close();

                        }else
                            System.out.println("El archivo no es valido.");
                    }else
                        System.out.println("No se envio ningun archivo.");
                }else
                    System.out.println("No se entiende la instruccion");
                
            }else
                System.out.println("No se enviaron instrucciones.");
            
            //Se usará "código fuente" como archivo de entrada, para tomarlo como lenguaje a tratar en el compilador.
            //archivo = new FileReader("src" + File.separator + "compilador" + File.separator + "codigofuente.txt");
            //Para llamar a Analizador Léxico se utilizará 'analizadorLexico

            
        } catch (IOException e) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
}
