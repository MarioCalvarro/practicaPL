
//----------------------------------------------------
// The following code was generated by CUP v0.11b beta 20140220
// Fri Mar 15 12:16:50 CET 2024
//----------------------------------------------------

package asint;

import java_cup.runtime.*;
import alex.AnalizadorLexicoTiny;
import alex.UnidadLexica;
import alex.TokenValue;
import errors.GestionErroresTiny;
import java_cup.runtime.ComplexSymbolFactory.Location;

/** CUP v0.11b beta 20140220 generated parser.
  * @version Fri Mar 15 12:16:50 CET 2024
  */
public class AnalizadorSintacticoTiny extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public AnalizadorSintacticoTiny() {super();}

  /** Constructor which sets the default scanner. */
  public AnalizadorSintacticoTiny(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalizadorSintacticoTiny(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\026\000\002\002\005\000\002\002\004\000\002\002" +
    "\005\000\002\003\004\000\002\003\002\000\002\004\005" +
    "\000\002\004\003\000\002\004\003\000\002\005\005\000" +
    "\002\005\004\000\002\006\005\000\002\006\003\000\002" +
    "\007\005\000\002\007\003\000\002\010\003\000\002\010" +
    "\003\000\002\010\003\000\002\010\005\000\002\011\003" +
    "\000\002\011\003\000\002\012\003\000\002\012\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\042\000\004\005\005\001\002\000\004\002\044\001" +
    "\002\000\014\003\010\006\014\007\012\010\011\017\013" +
    "\001\002\000\022\002\ufff6\004\ufff6\011\ufff6\012\ufff6\013" +
    "\026\014\025\016\ufff6\020\ufff6\001\002\000\012\002\ufffd" +
    "\004\030\011\020\012\022\001\002\000\006\002\ufffd\004" +
    "\030\001\002\000\022\002\ufff2\004\ufff2\011\ufff2\012\ufff2" +
    "\013\ufff2\014\ufff2\016\ufff2\020\ufff2\001\002\000\022\002" +
    "\ufff3\004\ufff3\011\ufff3\012\ufff3\013\ufff3\014\ufff3\016\ufff3" +
    "\020\ufff3\001\002\000\012\006\014\007\012\010\011\017" +
    "\013\001\002\000\022\002\ufff1\004\ufff1\011\ufff1\012\ufff1" +
    "\013\ufff1\014\ufff1\016\ufff1\020\ufff1\001\002\000\022\002" +
    "\ufff4\004\ufff4\011\ufff4\012\ufff4\013\ufff4\014\ufff4\016\ufff4" +
    "\020\ufff4\001\002\000\010\011\020\012\022\020\017\001" +
    "\002\000\022\002\ufff0\004\ufff0\011\ufff0\012\ufff0\013\ufff0" +
    "\014\ufff0\016\ufff0\020\ufff0\001\002\000\012\006\uffef\007" +
    "\uffef\010\uffef\017\uffef\001\002\000\012\006\014\007\012" +
    "\010\011\017\013\001\002\000\012\006\uffee\007\uffee\010" +
    "\uffee\017\uffee\001\002\000\022\002\ufff7\004\ufff7\011\ufff7" +
    "\012\ufff7\013\026\014\025\016\ufff7\020\ufff7\001\002\000" +
    "\012\006\014\007\012\010\011\017\013\001\002\000\012" +
    "\006\uffec\007\uffec\010\uffec\017\uffec\001\002\000\012\006" +
    "\uffed\007\uffed\010\uffed\017\uffed\001\002\000\022\002\ufff5" +
    "\004\ufff5\011\ufff5\012\ufff5\013\ufff5\014\ufff5\016\ufff5\020" +
    "\ufff5\001\002\000\006\003\033\006\035\001\002\000\004" +
    "\002\uffff\001\002\000\006\002\ufffe\016\041\001\002\000" +
    "\006\002\ufffa\016\ufffa\001\002\000\006\002\ufffb\016\ufffb" +
    "\001\002\000\006\003\036\015\037\001\002\000\006\002" +
    "\ufff8\016\ufff8\001\002\000\012\006\014\007\012\010\011" +
    "\017\013\001\002\000\012\002\ufff9\011\020\012\022\016" +
    "\ufff9\001\002\000\004\006\035\001\002\000\006\002\ufffc" +
    "\016\ufffc\001\002\000\004\002\001\001\002\000\004\002" +
    "\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\042\000\004\002\003\001\001\000\002\001\001\000" +
    "\010\006\006\007\005\010\014\001\001\000\004\012\023" +
    "\001\001\000\006\003\042\011\020\001\001\000\004\003" +
    "\030\001\001\000\002\001\001\000\002\001\001\000\010" +
    "\006\015\007\005\010\014\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\011\020\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\007\022\010\014\001\001\000" +
    "\002\001\001\000\004\012\023\001\001\000\004\010\026" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\006\004\031\005\033\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\010\006\037\007\005" +
    "\010\014\001\001\000\004\011\020\001\001\000\004\005" +
    "\041\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalizadorSintacticoTiny$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalizadorSintacticoTiny$actions(this);
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
    return action_obj.CUP$AnalizadorSintacticoTiny$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


  /** User initialization code. */
  public void user_init() throws java.lang.Exception
    {
 
   errores = new GestionErroresTiny();
   AnalizadorLexicoTiny alex = (AnalizadorLexicoTiny)getScanner();
   alex.fijaGestionErrores(errores);

    }

  /** Scan to get the next Symbol. */
  public java_cup.runtime.Symbol scan()
    throws java.lang.Exception
    {
 return getScanner().next_token(); 
    }

 
   private GestionErroresTiny errores;
   public void syntax_error(Symbol unidadLexica) {
     errores.errorSintactico((UnidadLexica)unidadLexica);
   }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$AnalizadorSintacticoTiny$actions {
  private final AnalizadorSintacticoTiny parser;

  /** Constructor */
  CUP$AnalizadorSintacticoTiny$actions(AnalizadorSintacticoTiny parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$AnalizadorSintacticoTiny$do_action_part00000000(
    int                        CUP$AnalizadorSintacticoTiny$act_num,
    java_cup.runtime.lr_parser CUP$AnalizadorSintacticoTiny$parser,
    java.util.Stack            CUP$AnalizadorSintacticoTiny$stack,
    int                        CUP$AnalizadorSintacticoTiny$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalizadorSintacticoTiny$result;

      /* select the action based on the action number */
      switch (CUP$AnalizadorSintacticoTiny$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // S ::= EVALUA E0 Ds 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("S",0, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= S EOF 
            {
              Object RESULT =null;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintacticoTiny$stack.elementAt(CUP$AnalizadorSintacticoTiny$top-1)).value;
		RESULT = start_val;
              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("$START",0, RESULT);
            }
          /* ACCEPT */
          CUP$AnalizadorSintacticoTiny$parser.done_parsing();
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // S ::= EVALUA error Ds 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("S",0, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Ds ::= DONDE LDs 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("Ds",1, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Ds ::= 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("Ds",1, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // LDs ::= LDs COMA D 
            {
              Object RESULT =null;
		TokenValue c = (TokenValue)((java_cup.runtime.Symbol) CUP$AnalizadorSintacticoTiny$stack.elementAt(CUP$AnalizadorSintacticoTiny$top-1)).value;
		 System.out.println(" instrucción correcta. Linea " + c.fila);
              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("LDs",2, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // LDs ::= D 
            {
              Object RESULT =null;
		 System.out.println(" instrucción correcta");
              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("LDs",2, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // LDs ::= error 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("LDs",2, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // D ::= IDEN IGUAL E0 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("D",3, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // D ::= IDEN error 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("D",3, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // E0 ::= E0 OP0 E1 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("E0",4, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // E0 ::= E1 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("E0",4, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // E1 ::= E1 OP1 E2 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("E1",5, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // E1 ::= E2 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("E1",5, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // E2 ::= ENT 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("E2",6, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // E2 ::= REAL 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("E2",6, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // E2 ::= IDEN 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("E2",6, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // E2 ::= PAP E0 PCIERRE 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("E2",6, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // OP0 ::= MAS 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("OP0",7, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // OP0 ::= MENOS 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("OP0",7, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // OP1 ::= POR 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("OP1",8, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // OP1 ::= DIV 
            {
              Object RESULT =null;

              CUP$AnalizadorSintacticoTiny$result = parser.getSymbolFactory().newSymbol("OP1",8, RESULT);
            }
          return CUP$AnalizadorSintacticoTiny$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$AnalizadorSintacticoTiny$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$AnalizadorSintacticoTiny$do_action(
    int                        CUP$AnalizadorSintacticoTiny$act_num,
    java_cup.runtime.lr_parser CUP$AnalizadorSintacticoTiny$parser,
    java.util.Stack            CUP$AnalizadorSintacticoTiny$stack,
    int                        CUP$AnalizadorSintacticoTiny$top)
    throws java.lang.Exception
    {
              return CUP$AnalizadorSintacticoTiny$do_action_part00000000(
                               CUP$AnalizadorSintacticoTiny$act_num,
                               CUP$AnalizadorSintacticoTiny$parser,
                               CUP$AnalizadorSintacticoTiny$stack,
                               CUP$AnalizadorSintacticoTiny$top);
    }
}

