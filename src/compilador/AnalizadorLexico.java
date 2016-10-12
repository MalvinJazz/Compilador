/* The following code was generated by JFlex 1.6.1 */

package compilador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;



/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>src/compilador/basic.jflex</tt>
 */
class AnalizadorLexico {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int ExTipo = 2;
  public static final int ExIdentificador = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\10\1\13\1\15\1\15\1\14\22\0\1\11\1\0\1\6"+
    "\2\0\1\70\2\0\1\16\1\17\1\0\1\0\1\67\1\0\1\0"+
    "\1\12\1\2\11\1\1\7\1\5\1\0\1\66\3\0\1\42\1\47"+
    "\1\22\1\54\1\40\1\34\1\55\1\60\1\20\2\3\1\23\1\62"+
    "\1\21\1\35\1\64\1\3\1\25\1\50\1\41\1\24\1\46\4\3"+
    "\3\0\1\71\1\4\1\0\1\45\1\52\1\30\1\56\1\43\1\36"+
    "\1\57\1\61\1\26\1\3\1\72\1\31\1\63\1\27\1\37\1\65"+
    "\1\3\1\33\1\53\1\44\1\32\1\51\1\3\1\73\2\3\12\0"+
    "\1\15\u1fa2\0\1\15\1\15\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\1\2\10\1\11\1\12\35\3\1\13\1\14"+
    "\1\15\1\16\1\0\1\17\1\0\1\20\15\3\1\21"+
    "\16\3\1\22\2\3\1\22\6\3\1\0\76\3\1\22"+
    "\34\3\1\23\5\3\1\22\3\3\1\24\6\3\1\24"+
    "\1\25\1\26\33\3\1\24\1\26\3\3\1\27\25\3"+
    "\1\30\1\3\1\31\1\3\1\32\2\3\1\33\4\3"+
    "\1\34\3\3\1\33\1\35\4\3\1\35\2\3\1\36"+
    "\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[284];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\74\0\74\0\170\0\74\0\264\0\74\0\360"+
    "\0\74\0\74\0\u012c\0\u0168\0\74\0\u01a4\0\74\0\74"+
    "\0\u01e0\0\u021c\0\u0258\0\u0294\0\u02d0\0\u030c\0\u0348\0\u0384"+
    "\0\u03c0\0\u03fc\0\u0438\0\u0474\0\u04b0\0\u04ec\0\u0528\0\u0564"+
    "\0\u05a0\0\u05dc\0\u0618\0\u0654\0\u0690\0\u06cc\0\u0708\0\u0744"+
    "\0\u0780\0\u07bc\0\u07f8\0\u0834\0\u0870\0\74\0\74\0\74"+
    "\0\74\0\360\0\360\0\u08ac\0\u08e8\0\u0924\0\u0960\0\u099c"+
    "\0\u09d8\0\u0a14\0\u0a50\0\u0a8c\0\u0ac8\0\u0b04\0\u0b40\0\u0b7c"+
    "\0\u0bb8\0\u0bf4\0\264\0\u0c30\0\u0c6c\0\u0ca8\0\u0ce4\0\u0d20"+
    "\0\u0d5c\0\u0d98\0\u0dd4\0\u0e10\0\u0e4c\0\u0e88\0\u0ec4\0\u0f00"+
    "\0\u0f3c\0\u0f78\0\u0fb4\0\u0ff0\0\u102c\0\u1068\0\u10a4\0\u10e0"+
    "\0\u111c\0\u1158\0\u1194\0\u11d0\0\u120c\0\u1248\0\u1284\0\u12c0"+
    "\0\u12fc\0\u1338\0\u1374\0\u13b0\0\u13ec\0\u1428\0\u1464\0\u14a0"+
    "\0\u14dc\0\u1518\0\u1554\0\u1590\0\u15cc\0\u1608\0\u1644\0\u1680"+
    "\0\u16bc\0\u16f8\0\u1734\0\u1770\0\u17ac\0\u17e8\0\u1824\0\u1860"+
    "\0\u189c\0\u18d8\0\u1914\0\u1950\0\u198c\0\u19c8\0\u1a04\0\u1a40"+
    "\0\u1a7c\0\u1ab8\0\u1af4\0\u1b30\0\u1b6c\0\u1ba8\0\u1be4\0\u1c20"+
    "\0\u1c5c\0\u1c98\0\u1cd4\0\u1d10\0\u1d4c\0\u1d88\0\u1dc4\0\u1e00"+
    "\0\u1e3c\0\u1e78\0\u1eb4\0\u1ef0\0\u1f2c\0\u1f68\0\u1fa4\0\u1fe0"+
    "\0\u201c\0\u2058\0\u2094\0\u20d0\0\u210c\0\u2148\0\u2184\0\u21c0"+
    "\0\u21fc\0\u2238\0\u2274\0\u22b0\0\u22ec\0\u2328\0\u2364\0\u23a0"+
    "\0\u23dc\0\u2418\0\u2454\0\u2490\0\u24cc\0\u2508\0\u2544\0\u2580"+
    "\0\u25bc\0\u25f8\0\u2634\0\u2670\0\u26ac\0\u26e8\0\u2724\0\264"+
    "\0\u2760\0\u279c\0\u27d8\0\u2814\0\u2850\0\74\0\u288c\0\u28c8"+
    "\0\u2904\0\264\0\u2940\0\u297c\0\u29b8\0\u29f4\0\u2a30\0\u2a6c"+
    "\0\u2aa8\0\264\0\u2ae4\0\u2b20\0\u2b5c\0\u2b98\0\u2bd4\0\u2c10"+
    "\0\u2c4c\0\u2c88\0\u2cc4\0\u2d00\0\u2d3c\0\u2d78\0\u2db4\0\u2df0"+
    "\0\u2e2c\0\u2e68\0\u2ea4\0\u2ee0\0\u2f1c\0\u2f58\0\u2f94\0\u2fd0"+
    "\0\u300c\0\u3048\0\u3084\0\u30c0\0\u30fc\0\u3138\0\74\0\74"+
    "\0\u3174\0\u31b0\0\u31ec\0\264\0\u3228\0\u3264\0\u32a0\0\u32dc"+
    "\0\u3318\0\u3354\0\u3390\0\u33cc\0\u3408\0\u3444\0\u3480\0\u34bc"+
    "\0\u34f8\0\u3534\0\u3570\0\u35ac\0\u35e8\0\u3624\0\u3660\0\u369c"+
    "\0\u36d8\0\264\0\u3714\0\264\0\u3750\0\264\0\u378c\0\u37c8"+
    "\0\u3804\0\u3840\0\u387c\0\u38b8\0\u38f4\0\264\0\u3930\0\u396c"+
    "\0\u39a8\0\74\0\u39e4\0\u3a20\0\u3a5c\0\u3a98\0\u3ad4\0\74"+
    "\0\u3b10\0\u3b4c\0\264\0\264";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[284];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\3\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\0\1\17\1\20"+
    "\1\21\1\22\1\23\2\6\1\24\1\25\1\26\1\27"+
    "\2\6\1\30\1\31\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\6\1\41\1\42\1\43\1\44\1\45"+
    "\1\46\1\47\1\50\1\6\1\51\3\6\1\52\1\53"+
    "\1\54\1\55\1\56\1\57\1\60\1\61\2\6\75\0"+
    "\2\4\72\0\4\6\13\0\46\6\4\0\2\6\6\62"+
    "\1\63\4\62\3\0\56\62\11\0\1\64\74\0\1\65"+
    "\74\0\1\15\61\0\4\6\13\0\1\6\1\66\17\6"+
    "\1\67\24\6\4\0\2\6\1\0\4\6\13\0\4\6"+
    "\1\70\41\6\4\0\2\6\1\0\4\6\13\0\15\6"+
    "\1\71\4\6\1\72\23\6\4\0\2\6\1\0\4\6"+
    "\13\0\20\6\1\73\25\6\4\0\2\6\1\0\4\6"+
    "\13\0\7\6\1\74\14\6\1\75\21\6\4\0\2\6"+
    "\1\0\4\6\13\0\12\6\1\76\33\6\4\0\2\6"+
    "\1\0\4\6\13\0\17\6\1\77\5\6\1\100\20\6"+
    "\4\0\2\6\1\0\4\6\13\0\23\6\1\101\22\6"+
    "\4\0\2\6\1\0\4\6\13\0\4\6\1\102\41\6"+
    "\4\0\2\6\1\0\4\6\13\0\5\6\1\103\40\6"+
    "\4\0\2\6\1\0\4\6\13\0\12\6\1\104\33\6"+
    "\4\0\2\6\1\0\4\6\13\0\13\6\1\103\32\6"+
    "\4\0\2\6\1\0\4\6\13\0\1\6\1\105\26\6"+
    "\1\106\15\6\4\0\2\6\1\0\4\6\13\0\17\6"+
    "\1\107\26\6\4\0\2\6\1\0\4\6\13\0\1\6"+
    "\1\110\3\6\1\111\21\6\1\112\16\6\4\0\2\6"+
    "\1\0\4\6\13\0\7\6\1\113\23\6\1\114\12\6"+
    "\4\0\2\6\1\0\4\6\13\0\7\6\1\115\3\6"+
    "\1\116\16\6\1\117\13\6\4\0\2\6\1\0\4\6"+
    "\13\0\22\6\1\120\23\6\4\0\2\6\1\0\4\6"+
    "\13\0\15\6\1\121\30\6\4\0\2\6\1\0\4\6"+
    "\13\0\1\122\45\6\4\0\2\6\1\0\4\6\13\0"+
    "\25\6\1\123\20\6\4\0\2\6\1\0\4\6\13\0"+
    "\17\6\1\124\26\6\4\0\2\6\1\0\4\6\13\0"+
    "\6\6\1\125\37\6\4\0\2\6\1\0\4\6\13\0"+
    "\20\6\1\126\25\6\4\0\2\6\1\0\4\6\13\0"+
    "\23\6\1\127\22\6\4\0\2\6\1\0\4\6\13\0"+
    "\1\130\45\6\4\0\2\6\1\0\4\6\13\0\6\6"+
    "\1\131\37\6\4\0\2\6\1\0\4\6\13\0\22\6"+
    "\1\132\23\6\4\0\2\6\1\0\4\6\13\0\25\6"+
    "\1\133\20\6\4\0\2\6\11\0\1\134\62\0\13\65"+
    "\3\0\56\65\1\0\4\6\13\0\2\6\1\135\43\6"+
    "\4\0\2\6\1\0\4\6\13\0\20\6\1\136\25\6"+
    "\4\0\2\6\1\0\4\6\13\0\42\6\1\137\3\6"+
    "\4\0\2\6\1\0\4\6\13\0\34\6\1\140\11\6"+
    "\4\0\2\6\1\0\4\6\13\0\34\6\1\141\11\6"+
    "\4\0\2\6\1\0\4\6\13\0\21\6\1\142\24\6"+
    "\4\0\2\6\1\0\4\6\13\0\10\6\1\143\35\6"+
    "\4\0\2\6\1\0\4\6\13\0\23\6\1\144\22\6"+
    "\4\0\2\6\1\0\4\6\13\0\43\6\1\145\2\6"+
    "\4\0\2\6\1\0\4\6\13\0\36\6\1\146\7\6"+
    "\4\0\2\6\1\0\4\6\13\0\36\6\1\147\7\6"+
    "\4\0\2\6\1\0\4\6\13\0\24\6\1\150\21\6"+
    "\4\0\2\6\1\0\4\6\13\0\1\6\1\151\44\6"+
    "\4\0\2\6\1\0\4\6\13\0\7\6\1\152\36\6"+
    "\4\0\2\6\1\0\4\6\13\0\21\6\1\153\24\6"+
    "\4\0\2\6\1\0\4\6\13\0\2\6\1\154\43\6"+
    "\4\0\2\6\1\0\4\6\13\0\46\6\4\0\1\155"+
    "\1\6\1\0\4\6\13\0\34\6\1\103\11\6\4\0"+
    "\2\6\1\0\4\6\13\0\2\6\1\156\43\6\4\0"+
    "\2\6\1\0\4\6\13\0\5\6\1\157\40\6\4\0"+
    "\2\6\1\0\4\6\13\0\24\6\1\160\21\6\4\0"+
    "\2\6\1\0\4\6\13\0\10\6\1\161\35\6\4\0"+
    "\2\6\1\0\4\6\13\0\36\6\1\103\7\6\4\0"+
    "\2\6\1\0\4\6\13\0\10\6\1\162\35\6\4\0"+
    "\2\6\1\0\4\6\13\0\13\6\1\163\32\6\4\0"+
    "\2\6\1\0\4\6\13\0\5\6\1\164\40\6\4\0"+
    "\2\6\1\0\4\6\13\0\15\6\1\165\30\6\4\0"+
    "\2\6\1\0\4\6\13\0\1\6\1\166\44\6\4\0"+
    "\2\6\1\0\4\6\13\0\13\6\1\167\32\6\4\0"+
    "\2\6\1\0\4\6\13\0\17\6\1\170\26\6\4\0"+
    "\2\6\1\0\4\6\13\0\7\6\1\171\36\6\4\0"+
    "\2\6\1\0\4\6\13\0\2\6\1\172\25\6\1\173"+
    "\15\6\4\0\2\6\1\0\4\6\13\0\10\6\1\174"+
    "\22\6\1\175\12\6\4\0\2\6\1\0\4\6\13\0"+
    "\20\6\1\176\25\6\4\0\2\6\1\0\4\6\13\0"+
    "\23\6\1\177\22\6\4\0\2\6\1\0\4\6\13\0"+
    "\5\6\1\200\40\6\4\0\2\6\1\0\4\6\13\0"+
    "\13\6\1\201\32\6\4\0\2\6\11\0\1\12\63\0"+
    "\4\6\13\0\5\6\1\202\40\6\4\0\2\6\1\0"+
    "\4\6\13\0\5\6\1\203\40\6\4\0\2\6\1\0"+
    "\4\6\13\0\20\6\1\204\25\6\4\0\2\6\1\0"+
    "\4\6\13\0\1\205\45\6\4\0\2\6\1\0\4\6"+
    "\13\0\20\6\1\206\25\6\4\0\2\6\1\0\4\6"+
    "\13\0\15\6\1\207\30\6\4\0\2\6\1\0\4\6"+
    "\13\0\13\6\1\210\32\6\4\0\2\6\1\0\4\6"+
    "\13\0\13\6\1\211\32\6\4\0\2\6\1\0\4\6"+
    "\13\0\23\6\1\212\22\6\4\0\2\6\1\0\4\6"+
    "\13\0\6\6\1\213\37\6\4\0\2\6\1\0\4\6"+
    "\13\0\23\6\1\214\22\6\4\0\2\6\1\0\4\6"+
    "\13\0\17\6\1\215\26\6\4\0\2\6\1\0\4\6"+
    "\13\0\2\6\1\216\43\6\4\0\2\6\1\0\4\6"+
    "\13\0\10\6\1\217\35\6\4\0\2\6\1\0\4\6"+
    "\13\0\15\6\1\220\30\6\4\0\2\6\1\0\4\6"+
    "\13\0\5\6\1\221\40\6\4\0\2\6\1\0\4\6"+
    "\13\0\23\6\1\222\22\6\4\0\2\6\1\0\4\6"+
    "\13\0\40\6\1\223\5\6\4\0\2\6\1\0\4\6"+
    "\13\0\1\224\45\6\4\0\2\6\1\0\4\6\13\0"+
    "\17\6\1\225\26\6\4\0\2\6\1\0\4\6\13\0"+
    "\13\6\1\226\32\6\4\0\2\6\1\0\4\6\13\0"+
    "\41\6\1\227\4\6\4\0\2\6\1\0\4\6\13\0"+
    "\6\6\1\230\37\6\4\0\2\6\1\0\4\6\13\0"+
    "\1\231\45\6\4\0\2\6\1\0\4\6\13\0\3\6"+
    "\1\232\42\6\4\0\2\6\1\0\4\6\13\0\15\6"+
    "\1\233\30\6\4\0\2\6\1\0\4\6\13\0\6\6"+
    "\1\234\37\6\4\0\2\6\1\0\4\6\13\0\11\6"+
    "\1\235\34\6\4\0\2\6\1\0\4\6\13\0\17\6"+
    "\1\233\26\6\4\0\2\6\1\0\4\6\13\0\5\6"+
    "\1\236\40\6\4\0\2\6\1\0\4\6\13\0\34\6"+
    "\1\237\11\6\4\0\2\6\1\0\4\6\13\0\13\6"+
    "\1\240\32\6\4\0\2\6\1\0\4\6\13\0\36\6"+
    "\1\241\7\6\4\0\2\6\1\0\4\6\13\0\1\6"+
    "\1\242\44\6\4\0\2\6\1\0\4\6\13\0\7\6"+
    "\1\243\36\6\4\0\2\6\1\0\4\6\13\0\22\6"+
    "\1\244\23\6\4\0\2\6\1\0\4\6\13\0\25\6"+
    "\1\245\20\6\4\0\2\6\1\0\4\6\13\0\20\6"+
    "\1\246\25\6\4\0\2\6\1\0\4\6\13\0\22\6"+
    "\1\247\23\6\4\0\2\6\1\0\4\6\13\0\5\6"+
    "\1\250\40\6\4\0\2\6\1\0\4\6\13\0\35\6"+
    "\1\251\10\6\4\0\2\6\1\0\4\6\13\0\1\6"+
    "\1\252\44\6\4\0\2\6\1\0\4\6\13\0\5\6"+
    "\1\253\40\6\4\0\2\6\1\0\4\6\13\0\23\6"+
    "\1\254\22\6\4\0\2\6\1\0\4\6\13\0\25\6"+
    "\1\255\20\6\4\0\2\6\1\0\4\6\13\0\13\6"+
    "\1\256\32\6\4\0\2\6\1\0\4\6\13\0\37\6"+
    "\1\257\6\6\4\0\2\6\1\0\4\6\13\0\7\6"+
    "\1\260\36\6\4\0\2\6\1\0\4\6\13\0\13\6"+
    "\1\261\32\6\4\0\2\6\1\0\4\6\13\0\1\262"+
    "\45\6\4\0\2\6\1\0\4\6\13\0\6\6\1\263"+
    "\37\6\4\0\2\6\1\0\4\6\13\0\1\6\1\264"+
    "\44\6\4\0\2\6\1\0\4\6\13\0\1\265\45\6"+
    "\4\0\2\6\1\0\4\6\13\0\7\6\1\266\36\6"+
    "\4\0\2\6\1\0\4\6\13\0\1\267\45\6\4\0"+
    "\2\6\1\0\4\6\13\0\5\6\1\270\40\6\4\0"+
    "\2\6\1\0\4\6\13\0\7\6\1\271\36\6\4\0"+
    "\2\6\1\0\4\6\13\0\6\6\1\272\37\6\4\0"+
    "\2\6\1\0\4\6\13\0\6\6\1\273\37\6\4\0"+
    "\2\6\1\0\4\6\13\0\13\6\1\270\32\6\4\0"+
    "\2\6\1\0\4\6\13\0\22\6\1\274\23\6\4\0"+
    "\2\6\1\0\4\6\13\0\20\6\1\275\25\6\4\0"+
    "\2\6\1\0\4\6\2\0\1\276\10\0\46\6\4\0"+
    "\2\6\1\0\4\6\13\0\25\6\1\277\20\6\4\0"+
    "\2\6\1\0\4\6\13\0\23\6\1\300\22\6\4\0"+
    "\2\6\1\0\4\6\13\0\20\6\1\301\25\6\4\0"+
    "\2\6\1\0\4\6\13\0\20\6\1\302\25\6\4\0"+
    "\2\6\1\0\4\6\13\0\23\6\1\303\22\6\4\0"+
    "\2\6\1\0\4\6\13\0\23\6\1\302\22\6\4\0"+
    "\2\6\1\0\4\6\13\0\21\6\1\304\24\6\4\0"+
    "\2\6\1\0\4\6\13\0\24\6\1\305\21\6\4\0"+
    "\2\6\1\0\4\6\13\0\2\6\1\306\43\6\4\0"+
    "\2\6\1\0\4\6\13\0\10\6\1\307\35\6\4\0"+
    "\2\6\1\0\4\6\13\0\42\6\1\310\3\6\4\0"+
    "\2\6\1\0\4\6\13\0\5\6\1\311\40\6\4\0"+
    "\2\6\1\0\4\6\13\0\15\6\1\312\30\6\4\0"+
    "\2\6\1\0\4\6\13\0\15\6\1\313\30\6\4\0"+
    "\2\6\1\0\4\6\13\0\22\6\1\312\23\6\4\0"+
    "\2\6\1\0\4\6\13\0\1\6\1\314\44\6\4\0"+
    "\2\6\1\0\4\6\13\0\43\6\1\315\2\6\4\0"+
    "\2\6\1\0\4\6\13\0\13\6\1\311\32\6\4\0"+
    "\2\6\1\0\4\6\13\0\17\6\1\312\26\6\4\0"+
    "\2\6\1\0\4\6\13\0\17\6\1\313\26\6\4\0"+
    "\2\6\1\0\4\6\13\0\25\6\1\312\20\6\4\0"+
    "\2\6\1\0\4\6\13\0\7\6\1\316\36\6\4\0"+
    "\2\6\1\0\4\6\13\0\15\6\1\317\30\6\4\0"+
    "\2\6\1\0\4\6\13\0\17\6\1\320\26\6\4\0"+
    "\2\6\1\0\4\6\13\0\2\6\1\321\43\6\4\0"+
    "\2\6\1\0\4\6\13\0\27\6\1\322\16\6\4\0"+
    "\2\6\1\0\4\6\13\0\5\6\1\323\12\6\1\324"+
    "\7\6\1\325\3\6\1\326\5\6\1\327\3\6\4\0"+
    "\2\6\1\0\4\6\13\0\26\6\1\330\17\6\4\0"+
    "\2\6\1\0\4\6\13\0\10\6\1\331\35\6\4\0"+
    "\2\6\1\0\4\6\13\0\32\6\1\332\13\6\4\0"+
    "\2\6\1\0\4\6\13\0\31\6\1\333\14\6\4\0"+
    "\2\6\1\0\4\6\13\0\27\6\1\334\16\6\4\0"+
    "\2\6\1\0\4\6\13\0\22\6\1\335\23\6\4\0"+
    "\2\6\1\0\4\6\13\0\32\6\1\336\13\6\4\0"+
    "\2\6\1\0\4\6\13\0\25\6\1\337\20\6\4\0"+
    "\2\6\1\0\4\6\13\0\42\6\1\340\3\6\4\0"+
    "\2\6\1\0\4\6\13\0\43\6\1\341\2\6\4\0"+
    "\2\6\1\0\4\6\13\0\5\6\1\342\40\6\4\0"+
    "\2\6\1\0\4\6\13\0\13\6\1\343\32\6\4\0"+
    "\2\6\1\0\4\6\13\0\22\6\1\344\23\6\4\0"+
    "\2\6\1\0\4\6\13\0\25\6\1\345\20\6\4\0"+
    "\2\6\1\0\4\6\13\0\20\6\1\346\25\6\4\0"+
    "\2\6\1\0\4\6\2\0\1\347\10\0\46\6\4\0"+
    "\2\6\1\0\4\6\2\0\1\350\10\0\46\6\4\0"+
    "\2\6\1\0\4\6\13\0\22\6\1\351\23\6\4\0"+
    "\2\6\1\0\4\6\13\0\23\6\1\352\22\6\4\0"+
    "\2\6\1\0\4\6\13\0\25\6\1\353\20\6\4\0"+
    "\2\6\1\0\4\6\13\0\1\6\1\354\44\6\4\0"+
    "\2\6\1\0\4\6\13\0\7\6\1\354\36\6\4\0"+
    "\2\6\1\0\4\6\13\0\20\6\1\355\25\6\4\0"+
    "\2\6\1\0\4\6\13\0\1\356\45\6\4\0\2\6"+
    "\1\0\4\6\13\0\23\6\1\357\22\6\4\0\2\6"+
    "\1\0\4\6\13\0\46\6\4\0\1\6\1\360\1\0"+
    "\4\6\13\0\12\6\1\361\33\6\4\0\2\6\1\0"+
    "\4\6\13\0\6\6\1\362\37\6\4\0\2\6\1\0"+
    "\4\6\13\0\12\6\1\363\4\6\1\364\26\6\4\0"+
    "\2\6\1\0\4\6\13\0\15\6\1\365\30\6\4\0"+
    "\2\6\1\0\4\6\13\0\23\6\1\366\22\6\4\0"+
    "\2\6\1\0\4\6\13\0\6\6\1\367\37\6\4\0"+
    "\2\6\1\0\4\6\13\0\17\6\1\370\26\6\4\0"+
    "\2\6\1\0\4\6\13\0\3\6\1\371\42\6\4\0"+
    "\2\6\1\0\4\6\13\0\1\6\1\250\44\6\4\0"+
    "\2\6\1\0\4\6\13\0\11\6\1\372\34\6\4\0"+
    "\2\6\1\0\4\6\13\0\7\6\1\256\36\6\4\0"+
    "\2\6\1\0\4\6\13\0\20\6\1\373\25\6\4\0"+
    "\2\6\1\0\4\6\13\0\23\6\1\374\22\6\4\0"+
    "\2\6\1\0\4\6\13\0\22\6\1\375\23\6\4\0"+
    "\2\6\1\0\4\6\13\0\25\6\1\376\20\6\4\0"+
    "\2\6\1\0\4\6\13\0\34\6\1\377\11\6\4\0"+
    "\2\6\1\0\4\6\13\0\36\6\1\u0100\7\6\4\0"+
    "\2\6\1\0\4\6\13\0\1\6\1\u0101\44\6\4\0"+
    "\2\6\1\0\4\6\13\0\5\6\1\u0102\40\6\4\0"+
    "\2\6\1\0\4\6\13\0\7\6\1\u0103\36\6\4\0"+
    "\2\6\1\0\4\6\13\0\13\6\1\u0102\32\6\4\0"+
    "\2\6\1\0\4\6\13\0\30\6\1\233\15\6\4\0"+
    "\2\6\1\0\4\6\13\0\5\6\1\u0104\40\6\4\0"+
    "\2\6\1\0\4\6\13\0\33\6\1\u0105\12\6\4\0"+
    "\2\6\1\0\4\6\13\0\45\6\1\u0106\4\0\2\6"+
    "\1\0\4\6\13\0\43\6\1\u0107\2\6\4\0\2\6"+
    "\1\0\4\6\13\0\31\6\1\u0106\14\6\4\0\2\6"+
    "\1\0\4\6\13\0\11\6\1\u0108\34\6\4\0\2\6"+
    "\1\0\4\6\13\0\36\6\1\u0106\7\6\4\0\2\6"+
    "\1\0\4\6\13\0\30\6\1\u0109\15\6\4\0\2\6"+
    "\1\0\4\6\13\0\33\6\1\233\12\6\4\0\2\6"+
    "\1\0\4\6\13\0\13\6\1\u0104\32\6\4\0\2\6"+
    "\1\0\4\6\13\0\33\6\1\u0109\12\6\4\0\2\6"+
    "\1\0\4\6\13\0\20\6\1\u010a\25\6\4\0\2\6"+
    "\1\0\4\6\13\0\23\6\1\u010b\22\6\4\0\2\6"+
    "\1\0\4\6\13\0\1\6\1\u010c\44\6\4\0\2\6"+
    "\1\0\4\6\13\0\7\6\1\u010d\36\6\4\0\2\6"+
    "\1\0\4\6\13\0\30\6\1\302\15\6\4\0\2\6"+
    "\1\0\4\6\13\0\33\6\1\302\12\6\4\0\2\6"+
    "\1\0\4\6\13\0\22\6\1\u010e\23\6\4\0\2\6"+
    "\1\0\4\6\13\0\25\6\1\u010e\20\6\4\0\2\6"+
    "\1\0\4\6\13\0\21\6\1\u010f\24\6\4\0\2\6"+
    "\1\0\4\6\13\0\24\6\1\u0110\21\6\4\0\2\6"+
    "\1\0\4\6\13\0\24\6\1\u0107\21\6\4\0\2\6"+
    "\1\0\4\6\13\0\25\6\1\u0106\20\6\4\0\2\6"+
    "\1\0\4\6\13\0\24\6\1\u0111\21\6\4\0\2\6"+
    "\1\0\4\6\2\0\1\u0112\10\0\46\6\4\0\2\6"+
    "\1\0\4\6\13\0\30\6\1\u0113\15\6\4\0\2\6"+
    "\1\0\4\6\13\0\33\6\1\u0113\12\6\4\0\2\6"+
    "\1\0\4\6\13\0\21\6\1\u0114\24\6\4\0\2\6"+
    "\1\0\4\6\13\0\24\6\1\u0115\21\6\4\0\2\6"+
    "\1\0\4\6\13\0\22\6\1\u0116\23\6\4\0\2\6"+
    "\1\0\4\6\13\0\25\6\1\u0117\20\6\4\0\2\6"+
    "\1\0\4\6\13\0\6\6\1\u0106\37\6\4\0\2\6"+
    "\1\0\4\6\2\0\1\u0118\10\0\46\6\4\0\2\6"+
    "\1\0\4\6\13\0\22\6\1\u0119\23\6\4\0\2\6"+
    "\1\0\4\6\13\0\25\6\1\u011a\20\6\4\0\2\6"+
    "\1\0\4\6\13\0\5\6\1\u011b\40\6\4\0\2\6"+
    "\1\0\4\6\13\0\13\6\1\u011b\32\6\4\0\2\6"+
    "\1\0\4\6\13\0\5\6\1\u011c\40\6\4\0\2\6"+
    "\1\0\4\6\13\0\13\6\1\u011c\32\6\4\0\2\6";

  private static int [] zzUnpackTrans() {
    int [] result = new int[15240];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\10\1\11\1\1\1\11\1\1\1\11\1\1"+
    "\2\11\2\1\1\11\1\1\2\11\35\1\4\11\1\0"+
    "\1\1\1\0\47\1\1\0\141\1\1\11\50\1\2\11"+
    "\51\1\1\11\5\1\1\11\4\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[284];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
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


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  AnalizadorLexico(java.io.Reader in) {
      this.tokenList = new ArrayList();
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 202) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
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

    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public String yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { System.err.println("A lexical error, it could be an invalid or unacceptable character by the language at: " + yyline + "," + yycolumn + " ..: " + yytext());
            }
          case 32: break;
          case 2: 
            { System.out.println("Numero : " + yytext());
            }
          case 33: break;
          case 3: 
            { System.out.println("Identificador : " + yytext());
            }
          case 34: break;
          case 4: 
            { System.out.println("Fin de linea : " + yytext());
            }
          case 35: break;
          case 5: 
            { System.out.println("Comillas : " + yytext());
            }
          case 36: break;
          case 6: 
            { System.out.println("Dos Puntos : " + yytext());
            }
          case 37: break;
          case 7: 
            { System.out.println("Identacion : " + yytext());
            }
          case 38: break;
          case 8: 
            { 
            }
          case 39: break;
          case 9: 
            { System.out.println("Apertura de parentesis : " + yytext());
            }
          case 40: break;
          case 10: 
            { System.out.println("Clausura de parentesis : " + yytext());
            }
          case 41: break;
          case 11: 
            { System.out.println("Asignacion : " + yytext());
            }
          case 42: break;
          case 12: 
            { System.out.println("Comas : " + yytext());
            }
          case 43: break;
          case 13: 
            { System.out.println("Modulo : " + yytext());
            }
          case 44: break;
          case 14: 
            { System.out.println("Exponente : " + yytext());
            }
          case 45: break;
          case 15: 
            { System.out.println("Salida de sistema: " + yytext());
            }
          case 46: break;
          case 16: 
            { System.out.println("Comentarios : " + yytext());
            }
          case 47: break;
          case 17: 
            { System.out.println("Operador Logico : " + yytext());
            }
          case 48: break;
          case 18: 
            { System.out.println("Condicion : " + yytext());
            }
          case 49: break;
          case 19: 
            { System.out.println("Abrir archivo : " + yytext());
            }
          case 50: break;
          case 20: 
            { System.out.println("Ciclo : " + yytext());
            }
          case 51: break;
          case 21: 
            { System.out.println("Tipo de dato : " + yytext());
            }
          case 52: break;
          case 22: 
            { System.out.println("Encabezado de codigo : " + yytext());
            }
          case 53: break;
          case 23: 
            { System.out.println("Declaracion de Funcion : " + yytext());
            }
          case 54: break;
          case 24: 
            { System.out.println("Return : " + yytext());
            }
          case 55: break;
          case 25: 
            { System.out.println("Escribir en pantalla : " + yytext());
            }
          case 56: break;
          case 26: 
            { System.out.println("Operacion basica : " + yytext());
            }
          case 57: break;
          case 27: 
            { System.out.println("Encabezado de archivo : " + yytext());
            }
          case 58: break;
          case 28: 
            { System.out.println("Foreach : " + yytext());
            }
          case 59: break;
          case 29: 
            { System.out.println("Encabezado de variables : " + yytext());
            }
          case 60: break;
          case 30: 
            { System.out.println("Incrementar en uno : " + yytext());
            }
          case 61: break;
          case 31: 
            { System.out.println("Decrementar en uno : " + yytext());
            }
          case 62: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
