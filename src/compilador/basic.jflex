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


    private static ArrayList<String> tokenList = new ArrayList<>();
    static ArrayList<Tok> tabla = new ArrayList<>();

        /* Obtenemos el listado de Tokens para armar, por aparte, una tabla de
        *   simbolos mas facil de recorrer para una clasificacion adecuada, de 
        *   la misma forma, para poder corroborar que los identificadores no se
        *   repitan.
        */



    /*
        Acá van las funciones a utilizas dentro de la clase AnalizadorLexico
    */
    private void writeOutputFile() throws IOException {
        String filename = "file.out";
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
        for (String s:this.tokenList) {
            out.write(s + "\n");
        }
        out.close();
    }

    public static ArrayList<String> getTokenList (){
        return tokenList;
    }


    public static void addToTable(){
        String[] parts = tokenList.get(tokenList.size() - 1).split("#");
        Tok aux = new Tok(parts[0], parts[1], parts[2], parts[3]);
        tabla.add(aux);
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
InicioComentario = [/][*]
FinComentario = [^*]*[*][/]
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
TokenOperacion = {TokenSuma} | {TokenResta} | {TokenMulti} | {TokenDiv} | {TokenMod} | {TokenExp}


%xstates ExTipo 
%xstates ExIdentificador 
%xstate C

%%
{InicioComentario}  {
                     System.out.println("Inicio comentario largo:\n");
                     yybegin(C);
                    }

<C> {FinComentario} {
    System.out.println(yytext());
    System.out.println("Fin comentario largo.");
    yybegin(YYINITIAL);
}


{TokenFunction}     {
                        tokenList.add("Function#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Declaracion de Funcion : " + yytext());
                    }
{Decimal}           {
                        tokenList.add("Number#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Numero : " + yytext());
                    }
{TokenSout}         {
                        tokenList.add("SystemPrint#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Salida de sistema: " + yytext());
                    }
{TokenTipos}        {
                        tokenList.add("Type#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Tipo de dato : " + yytext());
                    }
{TokenReturn}       {
                        tokenList.add("Return#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Return : " + yytext());
                    }
{TokenEVariables}   {
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Encabezado de variables : " + yytext());
                    }
{TokenECodigo}      {
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Encabezado de codigo : " + yytext());
                    }
{TokenEFile}        {
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Encabezado de archivo : " + yytext());
                    }
{TokenPrint}        {
                        tokenList.add("SystemPrint#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Escribir en pantalla : " + yytext());
                    }
{TokenOpen}         {
                        tokenList.add("Instruction#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Abrir archivo : " + yytext());
                    }
{TokenIf}           {
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Condicion : " + yytext());
                    }
{TokenThen}         {
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Condicion : " + yytext());
                    }
{TokenElse}         {
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Condicion : " + yytext());
                    }
{TokenFor}          {
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Ciclo : " + yytext());
                    }
{TokenWhile}        {
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Ciclo : " + yytext());
                    }
{TokenIterar}       {
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Ciclo : " + yytext());
                    }
{TokenParaCada}     {
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Foreach : " + yytext());
                    }
{TokenAnd}          {
                        tokenList.add("LogicalOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Operador Logico : " + yytext());
                    }
{TokenOr}           {
                        tokenList.add("LogicalOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Operador Logico : " + yytext());
                    }
{TokenAsignacion}   {
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Asignacion : " + yytext());
                    }
{TokenComas}        {
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Comas : " + yytext());
                    }
{TokenOperacion}    {
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Operacion basica : " + yytext());
                    }
{TokenMod}          {
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Modulo : " + yytext());
                    }
{TokenExp}          {
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Exponente : " + yytext());
                    }
{TokenInc}          {
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Incrementar en uno : " + yytext());
                    }
{TokenDec}          {
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Decrementar en uno : " + yytext());
                    }
{ParenL}            {
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Apertura de parentesis : " + yytext());
                    }
{ParenR}            {
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Clausura de parentesis : " + yytext());
                    }
{TokenFinLinea}     {
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Fin de linea : " + yytext());
                    }
{TokenComillas}     {
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Comillas : " + yytext());
                    }
{TokenDosPuntos}    {
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Dos Puntos : " + yytext());
                    }

{TokenIdentacion}   {
                        tokenList.add("Indention#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Identacion : " + yytext());
                    }
{TokenComentarios}  {
                        tokenList.add("Comment#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        System.out.println("Comentarios : " + yytext());
                    }
{Identificador}     {
                        boolean x = false;
                        for (int i = 0; i < tabla.size(); i++) {
                            if (yytext().equals(tabla.get(i).getToken()))
                            {
                                x = true;
                            }
                        }
                        if (x == true)
                        {
                            int cont = tabla.size();
                            boolean bandera = false;
                            while ((bandera == false) && (cont>0))
                            {
                                
                            }
                            System.out.println("WARNING! Probably the object have been declarated previously: " + yytext());
                            tokenList.add("Identifier#" + yytext() + "#" + yyline + "#" + yycolumn );
                            addToTable();
                            System.out.println("Identificador : " + yytext());
                        }
                        else
                        {
                            tokenList.add("Identifier#" + yytext() + "#" + yyline + "#" + yycolumn );
                            addToTable();
                            System.out.println("Identificador : " + yytext());
                        }
                    }
\r|\n|\r\n          {}
{TokenEspacio}      {}
.                   {
                          throw new Error("A lexical error, it could be an invalid or unacceptable character by the language at: " + yyline + "," + yycolumn + " ..: " + yytext());  
                          
                    }
