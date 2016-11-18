
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Thu Nov 17 22:03:13 CST 2016
//----------------------------------------------------

package compilador;

import java_cup.runtime.Symbol;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Thu Nov 17 22:03:13 CST 2016
  */
public class AnalizadorSintactico extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public AnalizadorSintactico() {super();}

  /** Constructor which sets the default scanner. */
  public AnalizadorSintactico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalizadorSintactico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\013\000\002\002\004\000\002\002\004\000\002\002" +
    "\003\000\002\005\002\000\002\004\007\000\002\006\002" +
    "\000\002\004\006\000\002\003\003\000\002\003\003\000" +
    "\002\003\005\000\002\003\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\024\000\006\011\004\012\006\001\002\000\004\007" +
    "\015\001\002\000\010\002\uffff\011\uffff\012\uffff\001\002" +
    "\000\004\011\012\001\002\000\010\002\011\011\004\012" +
    "\006\001\002\000\010\002\000\011\000\012\000\001\002" +
    "\000\004\002\001\001\002\000\004\006\ufffc\001\002\000" +
    "\004\006\014\001\002\000\010\002\ufffb\011\ufffb\012\ufffb" +
    "\001\002\000\006\010\020\011\016\001\002\000\010\004" +
    "\ufff9\005\ufff9\006\ufff9\001\002\000\010\004\023\005\021" +
    "\006\ufffe\001\002\000\010\004\ufffa\005\ufffa\006\ufffa\001" +
    "\002\000\006\010\020\011\016\001\002\000\004\006\025" +
    "\001\002\000\006\010\020\011\016\001\002\000\010\004" +
    "\ufff8\005\ufff8\006\ufff8\001\002\000\010\002\ufffd\011\ufffd" +
    "\012\ufffd\001\002\000\010\004\ufff7\005\ufff7\006\ufff7\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\024\000\006\002\006\004\004\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\004\007" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\006" +
    "\012\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\003\016\001\001\000\002\001\001\000\004\005\021\001" +
    "\001\000\002\001\001\000\004\003\025\001\001\000\002" +
    "\001\001\000\004\003\023\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalizadorSintactico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalizadorSintactico$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$AnalizadorSintactico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


  /** User initialization code. */
  public void user_init() throws java.lang.Exception
    {
 
    arbolSintactico = new Arbol();

    }


    
    public Arbol arbolSintactico;

    @Override
    public void syntax_error(Symbol sy) {
        token t=(token)sy.value;
        done_parsing();
        report_error("Error sintáctico cerca de \""+ t.getCadena()+"\" ["+t.getRow()+" : "+t.getCol()+"]",null);
        
    }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$AnalizadorSintactico$actions {



  private final AnalizadorSintactico parser;

  /** Constructor */
  CUP$AnalizadorSintactico$actions(AnalizadorSintactico parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$AnalizadorSintactico$do_action(
    int                        CUP$AnalizadorSintactico$act_num,
    java_cup.runtime.lr_parser CUP$AnalizadorSintactico$parser,
    java.util.Stack            CUP$AnalizadorSintactico$stack,
    int                        CUP$AnalizadorSintactico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalizadorSintactico$result;

      /* select the action based on the action number */
      switch (CUP$AnalizadorSintactico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // expr ::= expr RESTA expr 
            {
              Nodo RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Nodo l = (Nodo)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int rleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int rright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Nodo r = (Nodo)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		 
            Nodo raiz = new Nodo(Nodo.TIPO_OPERADOR, Nodo.OP_RESTA);
            raiz.agregarHijo(l);
            raiz.agregarHijo(r);
            RESULT=raiz;
         
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expr",1, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // expr ::= expr SUMA expr 
            {
              Nodo RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Nodo l = (Nodo)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int rleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int rright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Nodo r = (Nodo)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		 
            Nodo raiz = new Nodo(Nodo.TIPO_OPERADOR, Nodo.OP_SUMA);
            raiz.agregarHijo(l);
            raiz.agregarHijo(r);
            RESULT=raiz; 
        
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expr",1, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // expr ::= ID 
            {
              Nodo RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		token id = (token)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		 RESULT=new Nodo(Nodo.TIPO_IDENTIFICADOR, id.getCadena()); 
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expr",1, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // expr ::= DECIMAL 
            {
              Nodo RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		token d = (token)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		 RESULT=new Nodo(d.getEntero()); 
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expr",1, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // expr_part ::= DECLARA ID NT$1 PCOMA 
            {
              Nodo RESULT =null;
              // propagate RESULT from NT$1
                RESULT = (Nodo) ((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		token id = (token)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expr_part",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-3)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // NT$1 ::= 
            {
              Nodo RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		token id = (token)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;

                Nodo declaracion = new Nodo(Nodo.TIPO_DECLARACION,5);
                Nodo identificador = new Nodo(Nodo.TIPO_IDENTIFICADOR, id.getCadena());
                declaracion.agregarHijo(identificador);
                RESULT=declaracion;
            
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("NT$1",4, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // expr_part ::= ID IGUAL expr NT$0 PCOMA 
            {
              Nodo RESULT =null;
              // propagate RESULT from NT$0
                RESULT = (Nodo) ((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).right;
		token id = (token)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Nodo e = (Nodo)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expr_part",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-4)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // NT$0 ::= 
            {
              Nodo RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		token id = (token)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Nodo e = (Nodo)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;

                Nodo expresion = new Nodo(Nodo.TIPO_EXPRESION,0);
                Nodo identificador = new Nodo(Nodo.TIPO_IDENTIFICADOR, id.getCadena());
                Nodo igual = new Nodo(Nodo.TIPO_OPERADOR, Nodo.OP_IGUAL);
                expresion.agregarHijo(identificador);
                expresion.agregarHijo(igual);
                expresion.agregarHijo(e);
                RESULT=expresion;
            
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("NT$0",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // expr_list ::= expr_part 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Nodo e = (Nodo)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
                parser.arbolSintactico.agregarHijo(e);
            
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expr_list",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // expr_list ::= expr_list expr_part 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Nodo e = (Nodo)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		 
                parser.arbolSintactico.agregarHijo(e);
            
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("expr_list",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= expr_list EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		RESULT = start_val;
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$AnalizadorSintactico$parser.done_parsing();
          return CUP$AnalizadorSintactico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

