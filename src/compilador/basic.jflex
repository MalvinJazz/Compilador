package compilador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;


%%

%class AnalizadorLexico
%unicode
%line
%column

%type String


%{
    /*
        Acá se declaran las variables a utilizar por la clase Analizador Lexico.
        
    */
    private ArrayList<String> tokenList;


    /*
        Acá van las funciones a utilizas dentro de la clase AnalizadorLexico
    */
    private void writeOutputFile() throws IOException {
        String filename = "file.out";
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
        for (String s:this.tokenList) {
            System.out.println(s);
            out.write(s + "\n");
        }
        out.close();
    }
%}


%eof{
    try {
        this.writeOutputFile();
    } catch (IOException ex) {
        Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
    }
    /*
        Aca deben de ir todas las llamadas a funciones desde clase Compilador
        luego de la ejecucion del Analizador y antes de cerrar el programa.
    */
    System.exit(0);
%eof}


%init{
    this.tokenList = new ArrayList();
%init}


Decimal = [1-9][0-9]* | 0
Identificador = [a-zA-Z][a-zA-Z0-9_]*


//Gramaticales
TokenFinLinea = ";"
TokenComillas = "\""
TokenDosPuntos = ":"
TokenIdentacion = [\t]|[ ]{4}

//Externos
TokenComentarios = "//".*
TokenEspacio = " "
TokenSout = {TokenComillas}.*{TokenComillas}


//Agrupadores
ParenL = "("
ParenR = ")"


//Preprocesador
TokenInclude = "INCLUIR"|"incluir"

//Funciones y encabezados
TokenFunction = "FUNCION" | "funcion"
TokenReturn = "RETORNAR" | "retornar"
TokenEVariables = ("VARIABLES"|"variables"){TokenDosPuntos}?
TokenECodigo = ("CODIGO"|"codigo"){TokenDosPuntos}?
TokenEFile = ("ARCHIVOS"|"archivos"){TokenDosPuntos}?

//Tipos de datos
TokenInt = "NUMERO" | "numero"
TokenString = "CADENA" | "cadena"
TokenBool = "BOOLEANO" | "booleano"
TokenNull = "NULL" | "null"
TokenTipos = {TokenInt} | {TokenString} | {TokenBool}

//Instrucciones
TokenPrint = "ESCRIBIR" | "escribir"
TokenOpen = "ABRIR" | "abrir"

//Sentencias
TokenIf = "SI" | "si"
TokenElse = ("SINO"|"sino"){TokenDosPuntos}?
TokenThen = ("ENTONCES"|"entonces"){TokenDosPuntos}?
TokenFor = "DESDE" | "desde"
TokenWhile = "MIENTRAS" | "mientras"
TokenIterar = ("ITERAR"|"iterar"){TokenDosPuntos}?
TokenParaCada = "PARACADA" | "paracada"

//Operadores Logicos
TokenAnd = "AND" | "and"
TokenOr = "OR" | "or"

//Operadores Basicos
TokenAsignacion = "="
TokenComas = ","

//Operadores Aritmeticos
TokenSuma = "+"
TokenResta = "-"
TokenMulti = "*"
TokenDiv = "/"
TokenMod = "%"
TokenExp = "^"
TokenInc = "INCREMENTAR" | "incrementar"
TokenDec = "DECREMENTAR" | "decrementar"
TokenOperacion = TokenSuma | TokenResta | TokenMulti | TokenDiv | TokenMod | TokenExp



//Todos los estados tienen el prefijo Ex de ser Excluyentes y solamente E al ser incluyentes
//Estado para reconocer la declaracion de una funcion
%xstate ExTipo 
//Estado para identificador
%xstate ExIdentificador 

%%

{TokenFunction}     {
                        System.out.println("Declaracion de Funcion : " + yytext());
                    }
{Decimal}           {
                        System.out.println("Numero : " + yytext());
                    }
{TokenSout}         {
                        System.out.println("Salida de sistema: " + yytext());
                    }
{TokenTipos}        {
                        System.out.println("Tipo de dato : " + yytext());
                    }
{TokenReturn}       {
                        System.out.println("Return : " + yytext());
                    }
{TokenEVariables}   {
                        System.out.println("Encabezado de variables : " + yytext());
                    }
{TokenECodigo}      {
                        System.out.println("Encabezado de codigo : " + yytext());
                    }
{TokenEFile}        {
                        System.out.println("Encabezado de archivo : " + yytext());
                    }
{TokenPrint}        {
                        System.out.println("Escribir en pantalla : " + yytext());
                    }
{TokenOpen}         {
                        System.out.println("Abrir archivo : " + yytext());
                    }
{TokenIf}           {
                        System.out.println("Condicion : " + yytext());
                    }
{TokenThen}         {
                        System.out.println("Condicion : " + yytext());
                    }
{TokenElse}         {
                        System.out.println("Condicion : " + yytext());
                    }
{TokenFor}          {
                        System.out.println("Ciclo : " + yytext());
                    }
{TokenWhile}        {
                        System.out.println("Ciclo : " + yytext());
                    }
{TokenIterar}       {
                        System.out.println("Ciclo : " + yytext());
                    }
{TokenParaCada}     {
                        System.out.println("Foreach : " + yytext());
                    }
{TokenAnd}          {
                        System.out.println("Operador Logico : " + yytext());
                    }
{TokenOr}           {
                        System.out.println("Operador Logico : " + yytext());
                    }
{TokenAsignacion}   {
                        System.out.println("Asignacion : " + yytext());
                    }
{TokenComas}        {
                        System.out.println("Comas : " + yytext());
                    }
{TokenOperacion}    {
                        System.out.println("Operacion basica : " + yytext());
                    }
{TokenMod}          {
                        System.out.println("Modulo : " + yytext());
                    }
{TokenExp}          {
                        System.out.println("Exponente : " + yytext());
                    }
{TokenInc}          {
                        System.out.println("Incrementar en uno : " + yytext());
                    }
{TokenDec}          {
                        System.out.println("Decrementar en uno : " + yytext());
                    }
{ParenL}            {
                        System.out.println("Apertura de parentesis : " + yytext());
                    }
{ParenR}            {
                        System.out.println("Clausura de parentesis : " + yytext());
                    }
{TokenFinLinea}     {
                        System.out.println("Fin de linea : " + yytext());
                    }
{TokenComillas}     {
                        System.out.println("Comillas : " + yytext());
                    }
{TokenDosPuntos}     {
                        System.out.println("Dos Puntos : " + yytext());
                    }

{TokenIdentacion}   {
                        System.out.println("Identacion : " + yytext());
                    }
{TokenComentarios}  {
                        System.out.println("Comentarios : " + yytext());
                    }
{Identificador}     {
                        System.out.println("Identificador : " + yytext());
                    }
\r|\n|\r\n          {}
{TokenEspacio}      {}
.                   {
                        System.err.println("A lexical error, it could be an invalid or unacceptable character by the language at: " + yyline + "," + yycolumn + " ..: " + yytext());
                    }
