package compilador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java_cup.runtime.Symbol;


%%

%class AnalizadorLexico
%unicode
%line
%column
%char
%cup

%type String


%{
    /*
        Acá se declaran las variables a utilizar por la clase Analizador Lexico.
        
    */


    private static ArrayList<String> tokenList = new ArrayList<>();
    static ArrayList<Tok> tabla = new ArrayList<>();
    static ArrayList<String> compiladoSalida = new ArrayList<>();
    static String encabezado = "Primer proyecto de compiladores, Ingenieria en Informatica y sistemas, Universidad Rafael Landivar\ndesarrollado por los estudiantes Melvin Juarez y Carlos Garcia. Se utilizo JFlex para la generacion \nde dicho analizador. \n\n";

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
        out.write(encabezado);
        out.write("\t\t\t CODIGO COMPILADO \n\n");
        for (int i = 0; i < compiladoSalida.size(); i++) {
            out.write(compiladoSalida.get(i) + "\n");
        } 
        out.write("\t\t\t TABLA DE SIMBOLOS \n\n");
        for (Tok argumento: this.tabla)
        {
            out.write(argumento.getTipo() + "\t\t\t" + argumento.getToken() + "\t\t\t" + "[" + argumento.getFila() + "," + argumento.getColumna() + "]" + "\n");
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

ArchivoNombre = {Identificador}({KeyL}[a-zA-Z\-0-9]*{KeyR})?\*?\.{Identificador}


Decimal = [1-9][0-9]* | 0
Identificador = [a-zA-Z][a-zA-Z0-9_]*

Extension = "led"
Directiva = {Identificador}\.{Extension}


//Gramaticales
TokenPunto = "."
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
KeyL = "["
KeyR = "]"
MayorQ= ">"
MenorQ = "<"
IgualIgual = "=="
MayorIgual = ">=" 
MenorIgual = "<="
ConditionSym = {MayorQ} | {MenorQ} | {IgualIgual} | {MayorIgual} | {MenorIgual}

//Preprocesador
TokenInclude = "INCLUIR"|"incluir"

//Funciones y encabezados
TokenFunction = "FUNCION" | "funcion"
TokenReturn = "RETORNAR" | "retornar"
TokenEVariables = ("VARIABLES"|"variables")
TokenECodigo = ("CODIGO"|"codigo")
TokenEFile = ("ARCHIVOS"|"archivos")

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
TokenThen = ("ENTONCES"|"entonces")
TokenFor = "DESDE" | "desde"
TokenWhile = "MIENTRAS" | "mientras"
TokenIterar = ("ITERAR"|"iterar")
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
TokenOperacion =  {TokenMulti} | {TokenDiv} | {TokenMod} | {TokenExp}
TokenAritmetica = {TokenSuma} | {TokenResta}

TokenAns = "?"

//OperadoresEstadistica
TokenSu = "suma"
TokenR = "resta"
TokenPromedio = "promedio"
TokenModa = "moda"
TokenMedia = "media"
TokenMa = "mayor"
TokenMe = "menor"
TokenConcatenar = "concatenar"
TokenEstadistica = {TokenSu} | {TokenR} | {TokenPromedio} | {TokenModa} | {TokenMedia} | {TokenMa} | {TokenMe} | {TokenConcatenar} 


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
                        String compilado = "Declaracion de Funcion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.FUNCION,new token(yycolumn, yyline, yytext()));
                    }
{Decimal}           {
                        
                        tokenList.add("Number#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Numero : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TDECIMAL,new token(yycolumn, yyline, yytext()));
                    }
{TokenSout}         {
                        
                        tokenList.add("SystemPrint#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Salida de sistema: " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.SALIDA,new token(yycolumn, yyline, yytext()));
                    }
{TokenTipos}        {
                        
                        tokenList.add("Type#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Tipo de dato : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TIPODATO,new token(yycolumn, yyline, yytext()));
                    }
{TokenReturn}       {
                        
                        tokenList.add("Return#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Return : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.RETORNO,new token(yycolumn, yyline, yytext()));
                    }
{TokenEVariables}   {
                        
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Encabezado de variables : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.ENCVAR,new token(yycolumn, yyline, yytext()));
                    }
{TokenECodigo}      {
                        
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Encabezado de codigo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.ENCCOD,new token(yycolumn, yyline, yytext()));
                    }
{TokenEFile}        {
                        
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Encabezado de archivo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.ENCFILE,new token(yycolumn, yyline, yytext()));
                    }
{TokenPrint}        {
                        
                        tokenList.add("SystemPrint#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Escribir en pantalla : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TPRINT,new token(yycolumn, yyline, yytext()));
                    }
{TokenOpen}         {
                        
                        tokenList.add("Instruction#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Abrir archivo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TOPEN,new token(yycolumn, yyline, yytext()));
                    }
{TokenIf}           {
                        
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Condicion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TIF,new token(yycolumn, yyline, yytext()));
                    }
{TokenThen}         {
                        
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Condicion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TTHEN,new token(yycolumn, yyline, yytext()));
                    }
{TokenElse}         {
                        
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Condicion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TELSE,new token(yycolumn, yyline, yytext()));
                    }
{TokenFor}          {
                        
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Ciclo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TFOR,new token(yycolumn, yyline, yytext()));
                    }
{TokenWhile}        {
                        
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Ciclo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TWHILE,new token(yycolumn, yyline, yytext()));
                    }
{TokenIterar}       {
                        
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Ciclo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.ITERAR,new token(yycolumn, yyline, yytext()));
                    }
{TokenParaCada}     {
                        
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Foreach : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TFOREACH,new token(yycolumn, yyline, yytext()));
                    }
{TokenAnd}          {
                        
                        tokenList.add("LogicalOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Operador Logico : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TAND,new token(yycolumn, yyline, yytext()));
                    }
{TokenOr}           {
                        
                        tokenList.add("LogicalOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Operador Logico : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TOR,new token(yycolumn, yyline, yytext()));
                    }
{TokenAsignacion}   {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Asignacion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.ASIGNACION,new token(yycolumn, yyline, yytext()));
                    }
{TokenComas}        {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Comas : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.COMAS,new token(yycolumn, yyline, yytext()));
                    }
{TokenPunto}        {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Punto : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.PUNTO,new token(yycolumn, yyline, yytext()));
                    }
{TokenAritmetica}    {
                        
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Operacion basica aritmetica: " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.ARITMETICA,new token(yycolumn, yyline, yytext()));
                    }
{TokenOperacion}    {
                        
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Operacion basica : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.OPERACION,new token(yycolumn, yyline, yytext()));
                    }
{TokenMod}          {
                        
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Modulo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TMOD,new token(yycolumn, yyline, yytext()));
                    }
{TokenExp}          {
                        
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Exponente : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TEXP,new token(yycolumn, yyline, yytext()));
                    }
{TokenInc}          {
                        
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Incrementar en uno : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.INCREMENTAR,new token(yycolumn, yyline, yytext()));
                    }
{TokenDec}          {
                        
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Decrementar en uno : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.DECREMENTAR,new token(yycolumn, yyline, yytext()));
                    }
{ParenL}            {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Apertura de parentesis : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.PARENTESISI,new token(yycolumn, yyline, yytext()));
                    }
{ParenR}            {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Clausura de parentesis : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.PARENTESISD,new token(yycolumn, yyline, yytext()));
                    }


{KeyL}            {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Apertura de corchete : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.CORI,new token(yycolumn, yyline, yytext()));
                    }
{TokenInclude}            {
                        
                        tokenList.add("Include#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Preprocesador : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.TINCLUDE,new token(yycolumn, yyline, yytext()));
                    }
{KeyR}            {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Clausura de corchete : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.CORD,new token(yycolumn, yyline, yytext()));
                    }
{ConditionSym}      {
                        
                        tokenList.add("Conditional#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Condicional : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.CONDITIONSYM,new token(yycolumn, yyline, yytext()));
                    }
{MayorQ}            {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Simbolo mayor que : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.MAYQ,new token(yycolumn, yyline, yytext()));
                    }
{MenorQ}            {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Simbolo menor que : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.MENQ,new token(yycolumn, yyline, yytext()));
                    }
{IgualIgual}            {
                        
                        tokenList.add("Relacional#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Relacional : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.RELACIONAL,new token(yycolumn, yyline, yytext()));
                    }

{TokenFinLinea}     {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Fin de linea : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.FINLINEA,new token(yycolumn, yyline, yytext()));
                    }
{TokenComillas}     {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Comillas : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.COMILLAS,new token(yycolumn, yyline, yytext()));
                    }
{TokenDosPuntos}    {
                        
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Dos Puntos : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.DOSPUNTOS,new token(yycolumn, yyline, yytext()));
                    }

{TokenIdentacion}   {
                        
                        tokenList.add("Indention#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Identacion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.IDENTACION,new token(yycolumn, yyline, yytext()));
                    }
{TokenComentarios}  {
                        tokenList.add("Comment#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Comentarios : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenAns}          {
                        
                        tokenList.add("ForAll#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "ForAll : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.ANS,new token(yycolumn, yyline, yytext()));
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
                            if ("Type".equals(tabla.get(tabla.size()-1).getTipo()))
                            {
                                throw new Error("WARNING! Probably the object have been declarated previously: " + "\n"   
                                                + " TOKEN ERROR..: " + yytext() + " at: [" + yyline + "," + yycolumn + "]");
                            }
                            else
                            {
                                
                                tokenList.add("Identifier#" + yytext() + "#" + yyline + "#" + yycolumn );
                                addToTable();
                                String compilado = "Identificador : " + yytext();
                                compiladoSalida.add(compilado);
                                System.out.println(compilado);
                                return new Symbol(sym.ID,new token(yycolumn, yyline, yytext()));
                            }
                                               
                        }
                        else
                        {
                            
                            tokenList.add("Identifier#" + yytext() + "#" + yyline + "#" + yycolumn );
                            addToTable();
                            String compilado = "Identificador : " + yytext();
                            compiladoSalida.add(compilado);
                            System.out.println(compilado);
                            return new Symbol(sym.ID,new token(yycolumn, yyline, yytext()));
                        }
                    }
{TokenEstadistica}         {
                        
                        tokenList.add("Estadistica#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Estadistica : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.ESTADISTICA,new token(yycolumn, yyline, yytext()));
                    }
{Directiva}         {
                        
                        tokenList.add("Directiva#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Directiva : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.DIRECTIVA,new token(yycolumn, yyline, yytext()));
                    }
{ArchivoNombre}     {
                        
                        tokenList.add("File#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Archivo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        return new Symbol(sym.NAMEARCHIVO,new token(yycolumn, yyline, yytext()));
                        
                    }

\r|\n|\r\n          {return new Symbol(sym.SALTOLINEA,new token(yycolumn, yyline, yytext()));}
{TokenEspacio}      {}
.                   {
                          throw new Error("A lexical error, it could be an invalid or unacceptable character by the language at: " + yyline + "," + yycolumn + " ..: " + yytext());  
                          
                    }
