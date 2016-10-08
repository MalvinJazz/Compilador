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


Decimal = [1-9][0-9]*
Identificador = [a-zA-Z][a-zA-Z0-9_]*


//Preprocesador
TokenInclude = "INCLUIR"

//Funciones y encabezados
TokenFunction = "FUNCION"
TokenReturn = "RETORNAR"
TokenEVariables = "VARIABLES"
TokenECodigo = "CODIGO"
TokenEFile = "ARCHIVOS"

//Tipos de datos
TokenInt = "NUMERO"
TokenString = "CADENA"
TokenBool = "BOOLEANO"
TokenNull = "NULL"
TokenTipos = {TokenInt} | {TokenString} | {TokenBool}

//Instrucciones
TokenPrint = "ESCRIBIR"
TokenOpen = "ABRIR"

//Sentencias
TokenIf = "SI"
TokenElse = "SINO"
TokenThen = "ENTONCES"
TokenFor = "DESDE"
TokenWhile = "MIENTRAS"
TokenIterar = "ITERAR"

//Operadores Logicos
TokenAnd = "AND"
TokenOr = "OR"

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
TokenInc = "INCREMENTAR"
TokenDec = "DECREMENTAR"

//Agrupadores
ParenL = "("
ParenR = ")"

//Gramaticales
TokenFinLinea = ";"
TokenComillas = "\""
TokenDosPuntos = ":"
TokenIdentacion = [\t]|[ ]{4}

//Externos
TokenComentarios = "//".*
TokenEspacio = " "


//Todos los estados tienen el prefijo Ex de ser Excluyentes y solamente E al ser incluyentes
//Estado para reconocer la declaracion de una funcion
%xstate ExTipo 
//Estado para identificador
%xstate ExIdentificador 

%%

{TokenFunction}     {
                        System.out.println("Declaracion de Funcion : " + yytext());
                    }
{TokenTipos}        {
                        System.out.println("Tipo de dato : " + yytext());
                    }
{Identificador}     {
                        System.out.println("Identificador : " + yytext());
                    }
\r|\n|\r\n          {}
{TokenEspacio}      {}
.                   {
                        System.out.println("caracter no valido:[" + yyline + "," + yycolumn + "]" + yytext());
                    }