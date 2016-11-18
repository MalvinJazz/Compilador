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
import excepciones.DatoInvalido;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

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
                throw new ArchivoInvalido("El archivo "+ruta+" es invalido.");
        }
        
        this.archivo_excel = new HSSFWorkbook(
                new FileInputStream(new File(ruta)));
        this.ruta = ruta;
    }
    
    private String[] get_todas_las_filas(int c){
        
        Sheet primera_hoja = this.archivo_excel.getSheetAt(0);
        
        String[] filas = new String[primera_hoja.getLastRowNum()];
        
        Iterator<Row> iterador_fila = primera_hoja.rowIterator();
        
        int i = 0;
        while(iterador_fila.hasNext()){
            
            Row row = iterador_fila.next();
            
            filas[0] = row.getCell(c).getStringCellValue();
            i++;
            
        }
        
        return filas;
        
    }
    
    private String[] get_todas_las_columnas(int f){
        
        Sheet primera_hoja = this.archivo_excel.getSheetAt(0);
        Row fila = primera_hoja.getRow(f);
        
        String[] columnas = new String[fila.getLastCellNum()];
        
        int i = 0;
        Iterator<Cell> iterador_celda = fila.cellIterator();
        
        while(iterador_celda.hasNext()){
            
            columnas[i] = iterador_celda.next().getStringCellValue();
            i++;
            
        }
        
        return columnas;
        
    }
    
    
    public <Any>Any get_celda(String f, String c){
        
        if(f.equals("?"))
            return (Any) get_todas_las_filas(Integer.parseInt(c));
        else if(c.equals("?"))
            return (Any) get_todas_las_columnas(Integer.parseInt(f));
        
        int fila = Integer.parseInt(f);
        int columna = Integer.parseInt(c);
        
        Sheet primera_hoja = this.archivo_excel.getSheetAt(0);
        
        Row row = primera_hoja.getRow(fila);
        Cell cell = row.getCell(columna);
        
        switch(cell.getCellType()){
            
            case Cell.CELL_TYPE_NUMERIC:
                return ((Any)(Float)(float)cell.getNumericCellValue());
            case Cell.CELL_TYPE_BOOLEAN:
                return ((Any)(Boolean)cell.getBooleanCellValue());
            default:
                return ((Any)(String)cell.getStringCellValue());
            
        }
        
    }
    
    public <Any>Any get_celda(int hoja, int fila, int columna){
        
        Sheet thoja = this.archivo_excel.getSheetAt(hoja);
        
        Row row = thoja.getRow(fila);
        Cell cell = row.getCell(columna);
        
        switch(cell.getCellType()){
            
            case Cell.CELL_TYPE_NUMERIC:
                return ((Any)(Float)(float)cell.getNumericCellValue());
            case Cell.CELL_TYPE_BOOLEAN:
                return ((Any)(Boolean)cell.getBooleanCellValue());
            default:
                return ((Any)(String)cell.getStringCellValue());
            
        }
        
    }
    
    public float get_suma(int fila, int columna) throws DatoInvalido{
        
        float suma = 0;
        
        Iterator<Sheet> iterador_hoja = this.archivo_excel.iterator();
        
        while(iterador_hoja.hasNext()){
            
            Sheet hoja = iterador_hoja.next();
            
            Row row = hoja.getRow(fila);
            Cell cell = row.getCell(columna);
            
            if(cell.getCellType()!=Cell.CELL_TYPE_NUMERIC)
                throw new DatoInvalido(
                            "El archivo "+this.ruta+
                            " tiene un dato no numerico en la celda "+
                            fila+", "+columna+" de la hoja "+hoja.getSheetName()
                        );
            
            suma += cell.getNumericCellValue();
        }
        
        return suma;
    }
    
    public float get_resta(int fila, int columna) throws DatoInvalido{
        
        float resta = 0;
        
        Iterator<Sheet> iterador_hoja = this.archivo_excel.iterator();
        
        while(iterador_hoja.hasNext()){
            
            Sheet hoja = iterador_hoja.next();
            
            Row row = hoja.getRow(fila);
            Cell cell = row.getCell(columna);
            
            if(cell.getCellType()!=Cell.CELL_TYPE_NUMERIC)
                throw new DatoInvalido(
                            "El archivo "+this.ruta+
                            " tiene un dato no numerico en la celda "+
                            fila+", "+columna+" de la hoja "+hoja.getSheetName()
                        );
            
            resta -= cell.getNumericCellValue();
        }
        
        return resta;
        
    }
    
    public float get_promedio(int fila, int columna) throws DatoInvalido{
        
        float suma = 0;
        
        Iterator<Sheet> iterador_hoja = this.archivo_excel.iterator();
        
        while(iterador_hoja.hasNext()){
            
            Sheet hoja = iterador_hoja.next();
            
            Row row = hoja.getRow(fila);
            Cell cell = row.getCell(columna);
            
            if(cell.getCellType()!=Cell.CELL_TYPE_NUMERIC)
                throw new DatoInvalido(
                            "El archivo "+this.ruta+
                            " tiene un dato no numerico en la celda "+
                            fila+", "+columna+" de la hoja "+hoja.getSheetName()
                        );
            
            suma += cell.getNumericCellValue();
        }
        
        return suma/this.archivo_excel.getNumberOfSheets();
        
    }
    
    public float get_moda(int fila, int columna) throws DatoInvalido{
        
        float moda = 0;
        float[] repeticiones = new float[this.archivo_excel.getNumberOfSheets()];
        
        Iterator<Sheet> iterador_hoja = this.archivo_excel.iterator();
        
        int a = 0;
        while(iterador_hoja.hasNext()){
            
            Sheet hoja = iterador_hoja.next();
            
            Row row = hoja.getRow(fila);
            Cell cell = row.getCell(columna);
            
            if(cell.getCellType()!=Cell.CELL_TYPE_NUMERIC)
                throw new DatoInvalido(
                            "El archivo "+this.ruta+
                            " tiene un dato no numerico en la celda "+
                            fila+", "+columna+" de la hoja "+hoja.getSheetName()
                        );
            
            repeticiones[a] = (float)cell.getNumericCellValue();
            a++;
        }
        
        int maximaVecesQueSeRepite = 0;
        
        for(int i=0; i<repeticiones.length; i++){
            int vecesQueSeRepite = 0;
            for(int j=0; j<repeticiones.length; j++){
                if(repeticiones[i] == repeticiones[j])
                vecesQueSeRepite++;
            }
            if(vecesQueSeRepite > maximaVecesQueSeRepite){
                moda = repeticiones[i];
                maximaVecesQueSeRepite = vecesQueSeRepite;
            }
        }
        
        return moda;
        
    }
    
    public float get_max(int fila, int columna) throws DatoInvalido{
        
        float[] numeros = new float[this.archivo_excel.getNumberOfSheets()];
        
        Iterator<Sheet> iterador_hoja = this.archivo_excel.iterator();
        
        int a = 0;
        while(iterador_hoja.hasNext()){
            
            Sheet hoja = iterador_hoja.next();
            
            Row row = hoja.getRow(fila);
            Cell cell = row.getCell(columna);
            
            if(cell.getCellType()!=Cell.CELL_TYPE_NUMERIC)
                throw new DatoInvalido(
                            "El archivo "+this.ruta+
                            " tiene un dato no numerico en la celda "+
                            fila+", "+columna+" de la hoja "+hoja.getSheetName()
                        );
            
            numeros[a] = (float)cell.getNumericCellValue();
            a++;
            
        }
        
        Arrays.sort(numeros);
        
        return numeros[0];
        
    }
    
    public float get_min(int fila, int columna) throws DatoInvalido{
        
        float[] numeros = new float[this.archivo_excel.getNumberOfSheets()];
        
        Iterator<Sheet> iterador_hoja = this.archivo_excel.iterator();
        
        int a = 0;
        while(iterador_hoja.hasNext()){
            
            Sheet hoja = iterador_hoja.next();
            
            Row row = hoja.getRow(fila);
            Cell cell = row.getCell(columna);
            
            if(cell.getCellType()!=Cell.CELL_TYPE_NUMERIC)
                throw new DatoInvalido(
                            "El archivo "+this.ruta+
                            " tiene un dato no numerico en la celda "+
                            fila+", "+columna+" de la hoja "+hoja.getSheetName()
                        );
            
            numeros[a] = (float)cell.getNumericCellValue();
            a++;
            
        }
        
        Arrays.sort(numeros);
        
        return numeros[numeros.length - 1];
        
    }
    
    public String concatenar(int fila, int columna){
        
        String salida = "";
        
        Iterator<Sheet> iterador_hoja = this.archivo_excel.iterator();
 
        while(iterador_hoja.hasNext()){
            
            Sheet hoja = iterador_hoja.next();
            
            Row row = hoja.getRow(fila);
            Cell cell = row.getCell(columna);
            
            salida += "["+cell.getRowIndex()+", "+cell.getColumnIndex()+"] -> "
                        +cell.getStringCellValue()+", "; 
            
        }
        
        return salida;
        
    }
    
}
