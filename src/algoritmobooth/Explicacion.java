package algoritmobooth;

public class Explicacion {
    String tipoOP,OP;
    String Q, Q1,A,M,ultimoQ;
    String Res="NAN";
    public String initStr(String[] rowAt) {
        A = rowAt[1];
        Q = rowAt[2];
        Q1 = rowAt[3];
        M = rowAt[4];
        Res = rowAt[6];
        ultimoQ = Q.substring(Q.length()-1);
        return determinarOP(rowAt[5]);
    }
    
    private String setTextDesp() {
        String primeroA=A.substring(0,1);
        String sinAsin=A.substring(1,A.length()-1);
        String ultimoA= A.substring(A.length()-1);
        String AsinUltimo=A.substring(0,A.length()-2);
        String Qsin=Q.substring(0,Q.length()-1);
        String QsinUltimo=Q.substring(0,Q.length()-2); 
        String algo = "<table style=\"width: 100%;\" border=\"1\">";
        String tabla = String.format(
                "<center><h1> %s  </h1></center><br>"
                + "<p> Texto </p> %s"+
        "<tbody><tr>"+
         " <td style=\"height: 19px; text-align: center;\">A</td>"+
         " <td style=\"text-align: center;\">Q</td>"+
         " <td style=\"text-align: center;\">Q-1</td>"+
        "</tr>"+
        "<tr>"+
          "<td style=\"text-align: center; width: 538.667px;\"><FONT color=\"green\">%s</font>%s<FONT color=\"red\">%s</font></td>"+
          "<td style=\"text-align: center; width: 538.667px;\">%s<FONT color=\"blue\">%s</font></td>"+
          "<td style=\"text-align: center; width: 200px;\"> %s </td>"+
        "</tr>"+
        "<tr>"+
          "<td style=\"text-align: center;\"><FONT color=\"green\">%s</font><FONT color=\"green\">%s</font>%s</td>"+
          "<td style=\"text-align: center;\"><FONT color=\"red\">%s</font>%s</td>"+
          "<td style=\"text-align: center;\"><FONT color=\"blue\">%s</font></td>"+
        "</tr></tbody> </table>",
       tipoOP, algo,primeroA, sinAsin, ultimoA, Qsin, ultimoQ, Q1, primeroA,primeroA,AsinUltimo, ultimoA,QsinUltimo, ultimoQ);
        return (tabla);
    }
    private String setTextInit() {
        String s = String.format("<center><h1> %s</h1></center><br>", tipoOP);
        ImplAlgoritmo a = new ImplAlgoritmo();
        int decM = a.binToDec(M), decQ = a.binToDec(Q);
        String binM = a.decABinS(decM),binQ = a.decABinS(decQ);
        
        String cuerpo = String.format("<div style=\"text-align: center;\">Dados los números %s y %s, es necesario:<br>"+
      "<br>"+
      "1. Convertirlos a binario complemento a dos<br>"+
      "<br>"+
      "%s = %s<br>"+
      "%s = %s<br>"+
      "<br>"+
      "2. Igualar ambos al mismo número de bits<br>"+
      "<br>"+
      "%s = %s<br>"+
      "%s = %s<br>"+
      "<br>"+
      "3. Inicializar a A en %s (Asignarle tantos ceros como dígitos tenga M)<br>"+
      "<br>"+                    //           |         |         |      |      | 
      "4. Inicializar Q1 en 0</div>",decM,decQ,decM,binM,decQ,binQ,binM,M,binQ,Q, A);
        return (s+cuerpo);
    }
    private String setText() {
        
        String s = String.format("<center><h1> %s</h1></center><br>"
                + "<center><p>Ya que el último dígito de <b>Q</b> (%s) y de <b>Q-1</b> (%s) <br>"
                + "es <b>%s</b> y <b>%s</b> respectivamente,<br>"
                + "es necesario <b>%s</b> a <b>A</b> y a <b>M</b></p><br><br></center>", tipoOP, Q,Q1, ultimoQ,Q1,tipoOP);
        
        String q = String.format("<center>&nbsp;%s</center><center>%s%s</center><center>_____________</center><center>&nbsp;%s</center>",A,OP,M,Res);
        return (s+q);
    }
    
    private String determinarOP(String l) {
        if(l.equals("desplazamiento"))
        {
            tipoOP="Desplazamiento";
            OP="";
            return setTextDesp();
        }else if (l.equals("A<~A+M"))
        {
            tipoOP="Sumar";
            OP="+";
            return setText();
        }else if (l.equals("A<~A-M"))
        {
            tipoOP="Restar";
            OP="-";
            return setText();
        }
        else if (l.equals("Inicial"))
        {
            tipoOP="Inicial";
            OP="init";
            return setTextInit();
        }
        return "";
    }
}
