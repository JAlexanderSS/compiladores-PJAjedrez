
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package org.compiladores;

import org.compiladores.semantica.Semantica;
import java_cup.runtime.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return ParserSym.class;
}

  /** Default constructor. */
  @Deprecated
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\046\000\002\002\004\000\002\017\003\000\002\015" +
    "\004\000\002\015\004\000\002\015\004\000\002\012\003" +
    "\000\002\012\003\000\002\012\003\000\002\012\003\000" +
    "\002\012\003\000\002\012\003\000\002\012\003\000\002" +
    "\012\003\000\002\012\003\000\002\012\003\000\002\013" +
    "\004\000\002\014\004\000\002\021\003\000\002\021\003" +
    "\000\002\021\003\000\002\021\003\000\002\021\003\000" +
    "\002\021\003\000\002\022\004\000\002\020\004\000\002" +
    "\020\003\000\002\023\003\000\002\002\005\000\002\003" +
    "\005\000\002\004\007\000\002\005\005\000\002\011\003" +
    "\000\002\011\002\000\002\006\006\000\002\007\006\000" +
    "\002\010\006\000\002\016\003\000\002\016\002" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\065\000\024\005\004\006\031\007\030\010\014\011" +
    "\023\012\015\013\011\017\021\020\017\001\002\000\006" +
    "\004\053\014\064\001\002\000\006\002\uffdc\023\036\001" +
    "\002\000\010\004\046\005\050\014\045\001\002\000\004" +
    "\002\044\001\002\000\012\002\ufff6\021\ufff6\022\ufff6\023" +
    "\ufff6\001\002\000\020\002\uffeb\004\uffeb\005\uffeb\014\uffeb" +
    "\021\uffeb\022\uffeb\023\uffeb\001\002\000\012\002\ufff7\021" +
    "\ufff7\022\ufff7\023\ufff7\001\002\000\012\002\ufff5\021\ufff5" +
    "\022\ufff5\023\ufff5\001\002\000\020\002\uffee\004\uffee\005" +
    "\uffee\014\uffee\021\uffee\022\uffee\023\uffee\001\002\000\020" +
    "\002\uffec\004\uffec\005\uffec\014\uffec\021\uffec\022\uffec\023" +
    "\uffec\001\002\000\012\002\ufffa\021\ufffa\022\ufffa\023\ufffa" +
    "\001\002\000\012\002\ufff4\021\ufff4\022\ufff4\023\ufff4\001" +
    "\002\000\014\002\ufffc\015\042\021\ufffc\022\ufffc\023\ufffc" +
    "\001\002\000\012\002\ufff3\021\ufff3\022\ufff3\023\ufff3\001" +
    "\002\000\012\002\ufff9\021\ufff9\022\ufff9\023\ufff9\001\002" +
    "\000\020\002\uffed\004\uffed\005\uffed\014\uffed\021\uffed\022" +
    "\uffed\023\uffed\001\002\000\012\002\ufff8\021\ufff8\022\ufff8" +
    "\023\ufff8\001\002\000\004\002\000\001\002\000\012\002" +
    "\uffdc\021\041\022\040\023\036\001\002\000\012\002\ufffb" +
    "\021\ufffb\022\ufffb\023\ufffb\001\002\000\020\002\uffef\004" +
    "\uffef\005\uffef\014\uffef\021\uffef\022\uffef\023\uffef\001\002" +
    "\000\020\002\ufff0\004\ufff0\005\ufff0\014\ufff0\021\ufff0\022" +
    "\ufff0\023\ufff0\001\002\000\014\002\uffe8\015\uffe8\021\uffe8" +
    "\022\uffe8\023\uffe8\001\002\000\006\002\uffdc\023\036\001" +
    "\002\000\014\002\uffe7\015\uffe7\021\uffe7\022\uffe7\023\uffe7" +
    "\001\002\000\004\002\ufffe\001\002\000\004\002\uffdd\001" +
    "\002\000\004\002\uffff\001\002\000\006\002\ufff2\023\ufff2" +
    "\001\002\000\006\002\ufff1\023\ufff1\001\002\000\016\006" +
    "\031\007\030\010\014\011\023\012\015\013\011\001\002" +
    "\000\012\002\uffe3\021\uffe3\022\uffe3\023\uffe3\001\002\000" +
    "\004\002\001\001\002\000\004\005\055\001\002\000\006" +
    "\005\uffe1\014\052\001\002\000\020\002\uffe9\005\uffe1\014" +
    "\052\015\uffe9\021\uffe9\022\uffe9\023\uffe9\001\002\000\010" +
    "\004\053\005\uffe1\014\052\001\002\000\004\005\055\001" +
    "\002\000\004\005\uffe2\001\002\000\020\002\uffea\005\uffea" +
    "\014\uffea\015\uffea\021\uffea\022\uffea\023\uffea\001\002\000" +
    "\012\002\uffe0\021\uffe0\022\uffe0\023\uffe0\001\002\000\004" +
    "\004\053\001\002\000\004\005\055\001\002\000\012\002" +
    "\uffde\021\uffde\022\uffde\023\uffde\001\002\000\004\005\055" +
    "\001\002\000\012\002\uffdf\021\uffdf\022\uffdf\023\uffdf\001" +
    "\002\000\012\002\uffe6\021\uffe6\022\uffe6\023\uffe6\001\002" +
    "\000\004\002\ufffd\001\002\000\004\005\055\001\002\000" +
    "\014\002\uffe5\015\066\021\uffe5\022\uffe5\023\uffe5\001\002" +
    "\000\004\016\067\001\002\000\012\002\uffe4\021\uffe4\022" +
    "\uffe4\023\uffe4\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\065\000\042\002\026\003\015\004\021\005\023\006" +
    "\011\007\007\010\012\012\025\013\032\014\004\015\024" +
    "\017\006\020\017\021\005\022\033\023\031\001\001\000" +
    "\002\001\001\000\004\016\062\001\001\000\004\022\046" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\016" +
    "\036\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\016\034\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\021" +
    "\042\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\022\061\001\001\000\004\011\057\001\001\000\004\011" +
    "\055\001\001\000\004\011\050\001\001\000\004\022\053" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\022\056\001\001\000\002" +
    "\001\001\000\004\022\060\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\022\064\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
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
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Parser$actions {
  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= entrada EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // entrada ::= movimientofinal 
            {
              Object RESULT =null;
		 RESULT = Semantica.prueba(); Semantica.valoresObtenidos();  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("entrada",13, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // movimientofinal ::= movimiento comentario 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimientofinal",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // movimientofinal ::= jque comentario 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimientofinal",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // movimientofinal ::= jquemate comentario 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimientofinal",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // movimiento ::= desplazamiento 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // movimiento ::= captura 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // movimiento ::= cdp 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // movimiento ::= cdpap 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // movimiento ::= pdp 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // movimiento ::= dpc 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // movimiento ::= dpf 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // movimiento ::= dpa 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // movimiento ::= ENROQUECORTO 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // movimiento ::= ENROQUELARGO 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("movimiento",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // jque ::= movimiento JAQUE 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("jque",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // jquemate ::= movimiento JAQUEMATE 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("jquemate",10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // pieza ::= REY 
            {
              Object RESULT =null;
		 Semantica.tipoPieza("Rey");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("pieza",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // pieza ::= DAMA 
            {
              Object RESULT =null;
		 Semantica.tipoPieza("Dama");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("pieza",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // pieza ::= ALFIL 
            {
              Object RESULT =null;
		 Semantica.tipoPieza("Alfil");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("pieza",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // pieza ::= CABALLO 
            {
              Object RESULT =null;
		 Semantica.tipoPieza("Caballo");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("pieza",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // pieza ::= TORRE 
            {
              Object RESULT =null;
		 Semantica.tipoPieza("Torre");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("pieza",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // pieza ::= PEON 
            {
              Object RESULT =null;
		 Semantica.tipoPieza("Peon");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("pieza",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // casilla ::= COLUMNA FILA 
            {
              Object RESULT =null;
		int colleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int colright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object col = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int filleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int filright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object fil = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 Semantica.casilla((String) col, (String) fil); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("casilla",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // desplazamiento ::= pieza casilla 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("desplazamiento",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // desplazamiento ::= desplazamientopeon 
            {
              Object RESULT =null;
		 Semantica.tipoPieza("Peon");
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("desplazamiento",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // desplazamientopeon ::= casilla 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("desplazamientopeon",17, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // captura ::= pieza CAPTURA casilla 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("captura",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // cdp ::= COLUMNA CAPTURA casilla 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cdp",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // cdpap ::= COLUMNA CAPTURA casilla PROMOCION CAP 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cdpap",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // pdp ::= desplazamiento PROMOCION pieza 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("pdp",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // cca ::= CAPTURA 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cca",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // cca ::= 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cca",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // dpc ::= pieza COLUMNA cca casilla 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("dpc",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // dpf ::= pieza FILA cca casilla 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("dpf",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // dpa ::= pieza casilla cca casilla 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("dpa",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // comentario ::= COMENTARIO 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("comentario",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // comentario ::= 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("comentario",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
