/*
 * Copyright (C) 2016 MalvinJazz
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
package lector_excel;

import excepciones.ArchivoInvalido;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author MalvinJazz
 */
public class Libro {
    
    private final String[] tipos = {"ods", "xls", "xlsx"};
    
    private String ruta;
    private Workbook archivo_excel = null;

    public Libro(String ruta) throws IOException, ArchivoInvalido {
        
        for (String tipo : tipos) {
            if(!ruta.toLowerCase().endsWith(tipo))
                throw new ArchivoInvalido("Se ha ingresado un archivo invalido.");
        }
        
        this.archivo_excel = new HSSFWorkbook(
                new FileInputStream(new File(ruta)));
        this.ruta = ruta;
    }
    
}
