package algoritmobooth;

public class Explicacion {
    private String explicacion;
    public String initStr(String[] rowAt) {
        A = rowAt[1];
        Q = rowAt[2];
        Q1 = rowAt[3];
        M = rowAt[4];
        Res = rowAt[6];
        ultimoQ = Q.substring(Q.length()-1);
        return determinarOP(rowAt[5]);
    }
    private String determinarOP(String l) {
        if(l.equals("desplazamiento"))
        {
            tipoOP="Desplazamiento";
            setTextDesp();
        }else if (l.equals("A<~A+M"))
        {
            tipoOP="Sumar";
            OP="+";
            setTextSumDif();
        }else if (l.equals("A<~A-M"))
        {
            tipoOP="Restar";
            OP="-";
            setTextSumDif();
        }
        else if (l.equals("Inicial"))
        {
            tipoOP="Inicial";
            setTextInit();
        }
        
        return explicacion;
    }
    
    private void setTextDesp() {
        String primeroA=A.substring(0,1);
        String sinAsin=A.substring(1,A.length()-1);
        String ultimoA= A.substring(A.length()-1);
        String AsinUltimo=A.substring(0,A.length()-2);
        String Qsin=Q.substring(0,Q.length()-1);
        String QsinUltimo=Q.substring(0,Q.length()-2); 
        String tabla = String.format(DESPLAZAMIENTO,
       tipoOP, algo,primeroA, sinAsin, ultimoA, Qsin, ultimoQ, Q1, primeroA,primeroA,AsinUltimo, ultimoA,QsinUltimo, ultimoQ);
        explicacion =  (tabla);
    }
    private void setTextInit() {
        ImplAlgoritmo a = new ImplAlgoritmo();
        int decM = a.binToDec(M), decQ = a.binToDec(Q);
        String binM = a.decABinS(decM),binQ = a.decABinS(decQ);
        String cuerpo = String.format(NIT,tipoOP,decM,decQ,decM,binM,decQ,binQ,binM,M,binQ,Q, A);
        explicacion = (cuerpo);
    }
    //private String INIT;
    private void setTextSumDif() {
        String s = String.format(SUMDIF,tipoOP, Q,Q1, ultimoQ,Q1,tipoOP , A,OP,M,Res);
        explicacion = (s);
    }
    String NIT = 
    "<center><h1> %s</h1></center><br><div style=\"text-align: center;\">"
            + "Dados los números %s y %s, es necesario:<br><br>"+
                "1. Convertirlos a binario complemento a dos<br>"+
                "<br> %s = %s<br> %s = %s<br> <br>"+
                "2. Igualar ambos al mismo número de bits<br>"+
                "<br> %s = %s<br> %s = %s<br> <br>"+
                "3. Inicializar a A en %s (Asignarle tantos ceros como dígitos tenga M)<br>"+
                "<br> 4. Inicializar Q1 en 0</div>";
    String SUMDIF = "<center><h1> %s</h1></center><br>"
            + "<center><p>Ya que el último dígito de <b>Q</b> (%s) y de <b>Q-1</b> (%s) <br>"
            + "es <b>%s</b> y <b>%s</b> respectivamente,<br>"
            + "es necesario <b>%s</b> a <b>A</b> y a <b>M</b></p><br><br></center>"
            + "<center>&nbsp;%s</center>"
            + "<center>%s%s</center>"
            + "<center>_____________</center>"
            + "<center>&nbsp;%s</center>";
    String DESPLAZAMIENTO = "<center><h1> %s  </h1></center><br>"
            + "<p> Los desplazamientos aritméticos permiten la multiplicación y la división por dos, de números enteros con signo, por una potencia de dos. Desplazar n bits hacia la izquierda o a la derecha equivale a multiplicar o dividir por 2n.\n" +
"Se trata de una operación bit a bit en la que el bit más significativo (el de más a la izquierda) se duplica y luego se desplaza los demás bits, cada bit en el operando es simplemente movido una posición a la derecha. El último bit es descartado </p> %s"+
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
            "</tr></tbody> </table>";
    String algo = "<table style=\"width: 100%;\" border=\"1\">";
    String tipoOP,OP,Q, Q1,A,M,ultimoQ,Res;
}
