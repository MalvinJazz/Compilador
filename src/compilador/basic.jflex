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
TokenOperacion = {TokenSuma} | {TokenResta} | {TokenMulti} | {TokenDiv} | {TokenMod} | {TokenExp}


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
                        return new Symbol(sym.FUNCION,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Function#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Declaracion de Funcion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{Decimal}           {
                        return new Symbol(sym.TDECIMAL,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Number#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Numero : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenSout}         {
                        return new Symbol(sym.SALIDA,new token(yycolumn, yyline, yytext()));
                        tokenList.add("SystemPrint#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Salida de sistema: " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenTipos}        {
                        return new Symbol(sym.TIPODATO,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Type#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Tipo de dato : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenReturn}       {
                        return new Symbol(sym.RETORNO,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Return#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Return : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenEVariables}   {
                        return new Symbol(sym.ENCVAR,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Encabezado de variables : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenECodigo}      {
                        return new Symbol(sym.ENCCOD,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Encabezado de codigo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenEFile}        {
                        return new Symbol(sym.ENCFILE,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Header#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Encabezado de archivo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenPrint}        {
                        return new Symbol(sym.TPRINT,new token(yycolumn, yyline, yytext()));
                        tokenList.add("SystemPrint#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Escribir en pantalla : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenOpen}         {
                        return new Symbol(sym.TOPEN,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Instruction#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Abrir archivo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenIf}           {
                        return new Symbol(sym.TIF,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Condicion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenThen}         {
                        return new Symbol(sym.TTHEN,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Condicion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenElse}         {
                        return new Symbol(sym.TELSE,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Condicion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenFor}          {
                        return new Symbol(sym.TFOR,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Ciclo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenWhile}        {
                        return new Symbol(sym.TWHILE,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Ciclo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenIterar}       {
                        return new Symbol(sym.ITERAR,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Ciclo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenParaCada}     {
                        return new Symbol(sym.TFOREACH,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Statement#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Foreach : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenAnd}          {
                        return new Symbol(sym.TAND,new token(yycolumn, yyline, yytext()));
                        tokenList.add("LogicalOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Operador Logico : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenOr}           {
                        return new Symbol(sym.TOR,new token(yycolumn, yyline, yytext()));
                        tokenList.add("LogicalOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Operador Logico : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenAsignacion}   {
                        return new Symbol(sym.ASIGNACION,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Asignacion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenComas}        {
                        return new Symbol(sym.COMAS,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Comas : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenPunto}        {
                        return new Symbol(sym.PUNTO,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Punto : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenOperacion}    {
                        return new Symbol(sym.OPERACION,new token(yycolumn, yyline, yytext()));
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Operacion basica : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenMod}          {
                        return new Symbol(sym.TMOD,new token(yycolumn, yyline, yytext()));
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Modulo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenExp}          {
                        return new Symbol(sym.TEXP,new token(yycolumn, yyline, yytext()));
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Exponente : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenInc}          {
                        return new Symbol(sym.INCREMENTAR,new token(yycolumn, yyline, yytext()));
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Incrementar en uno : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenDec}          {
                        return new Symbol(sym.DECREMENTAR,new token(yycolumn, yyline, yytext()));
                        tokenList.add("ArithmeticOperator#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Decrementar en uno : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{ParenL}            {
                        return new Symbol(sym.PARENTESISI,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Apertura de parentesis : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{ParenR}            {
                        return new Symbol(sym.PARENTESISD,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Clausura de parentesis : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }


{KeyL}            {
                        return new Symbol(sym.CORI,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Apertura de corchete : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{KeyR}            {
                        return new Symbol(sym.CORD,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Clausura de corchete : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{ConditionSym}      {
                        return new Symbol(sym.CONDITIONSYM,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Conditional#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Condicional : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{MayorQ}            {
                        return new Symbol(sym.MAYQ,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Simbolo mayor que : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{MenorQ}            {
                        return new Symbol(sym.MENQ,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Simbolo menor que : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{IgualIgual}            {
                        return new Symbol(sym.RELACIONAL,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Relacional#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Relacional : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }

{TokenFinLinea}     {
                        return new Symbol(sym.FINLINEA,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Fin de linea : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenComillas}     {
                        return new Symbol(sym.COMILLAS,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Comillas : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenDosPuntos}    {
                        return new Symbol(sym.DOSPUNTOS,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Symbol#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Dos Puntos : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }

{TokenIdentacion}   {
                        return new Symbol(sym.IDENTACION,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Indention#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Identacion : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenComentarios}  {
                        tokenList.add("Comment#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Comentarios : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{TokenAns}          {
                        return new Symbol(sym.ANS,new token(yycolumn, yyline, yytext()));
                        tokenList.add("ForAll#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "ForAll : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
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
                                return new Symbol(sym.ID,new token(yycolumn, yyline, yytext()));
                                tokenList.add("Identifier#" + yytext() + "#" + yyline + "#" + yycolumn );
                                addToTable();
                                String compilado = "Identificador : " + yytext();
                                compiladoSalida.add(compilado);
                                System.out.println(compilado);
                            }
                                               
                        }
                        else
                        {
                            return new Symbol(sym.ID,new token(yycolumn, yyline, yytext()));
                            tokenList.add("Identifier#" + yytext() + "#" + yyline + "#" + yycolumn );
                            addToTable();
                            String compilado = "Identificador : " + yytext();
                            compiladoSalida.add(compilado);
                            System.out.println(compilado);
                        }
                    }
{TokenEstadistica}         {
                        return new Symbol(sym.ESTADISTICA,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Estadistica#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Estadistica : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{Directiva}         {
                        return new Symbol(sym.DIRECTIVA,new token(yycolumn, yyline, yytext()));
                        tokenList.add("Directiva#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Directiva : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                    }
{ArchivoNombre}     {
                        return new Symbol(sym.NAMEARCHIVO,new token(yycolumn, yyline, yytext()));
                        tokenList.add("File#" + yytext() + "#" + yyline + "#" + yycolumn );
                        addToTable();
                        String compilado = "Archivo : " + yytext();
                        compiladoSalida.add(compilado);
                        System.out.println(compilado);
                        
                    }

\r|\n|\r\n          {}
{TokenEspacio}      {}
.                   {
                          throw new Error("A lexical error, it could be an invalid or unacceptable character by the language at: " + yyline + "," + yycolumn + " ..: " + yytext());  
                          
                    }
