package com.example.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView pantalla;
    Button uno,dos,tres,cuatro,cinco,seis,siete,ocho,nueve,cero,suma,resta,mult,div,elev,ap,close,res,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantalla=findViewById(R.id.pantalla);

        uno=findViewById(R.id.uno);
        dos=findViewById(R.id.dos);
        tres=findViewById(R.id.tres);
        cuatro=findViewById(R.id.cuatro);
        cinco=findViewById(R.id.cinco);
        seis=findViewById(R.id.seis);
        siete=findViewById(R.id.siete);
        ocho=findViewById(R.id.ocho);
        nueve=findViewById(R.id.nueve);
        cero=findViewById(R.id.cero);
        suma=findViewById(R.id.suma);
        resta=findViewById(R.id.resta);
        mult=findViewById(R.id.multiplicacion);
        div=findViewById(R.id.division);
        elev=findViewById(R.id.elevado);
        ap=findViewById(R.id.apertura);
        close=findViewById(R.id.cierre);
        reset=findViewById(R.id.reset);
        res=findViewById(R.id.igual);

        uno.setOnClickListener(this);
        dos.setOnClickListener(this);
        tres.setOnClickListener(this);
        cuatro.setOnClickListener(this);
        cinco.setOnClickListener(this);
        seis.setOnClickListener(this);
        siete.setOnClickListener(this);
        ocho.setOnClickListener(this);
        nueve.setOnClickListener(this);
        cero.setOnClickListener(this);
        suma.setOnClickListener(this);
        resta.setOnClickListener(this);
        mult.setOnClickListener(this);
        div.setOnClickListener(this);
        elev.setOnClickListener(this);
        ap.setOnClickListener(this);
        close.setOnClickListener(this);
        reset.setOnClickListener(this);
        res.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uno:pantalla.setText(pantalla.getText().toString()+"1");             break;
            case R.id.dos:pantalla.setText(pantalla.getText().toString()+"2");             break;
            case R.id.tres:pantalla.setText(pantalla.getText().toString()+"3");            break;
            case R.id.cuatro:pantalla.setText(pantalla.getText().toString()+"4");          break;
            case R.id.cinco:pantalla.setText(pantalla.getText().toString()+"5");           break;
            case R.id.seis:pantalla.setText(pantalla.getText().toString()+"6");            break;
            case R.id.siete:pantalla.setText(pantalla.getText().toString()+"7");           break;
            case R.id.ocho:pantalla.setText(pantalla.getText().toString()+"8");            break;
            case R.id.nueve:pantalla.setText(pantalla.getText().toString()+"9");           break;
            case R.id.cero:pantalla.setText(pantalla.getText().toString()+"0");            break;
            case R.id.suma:pantalla.setText(pantalla.getText().toString()+"+");            break;
            case R.id.resta:pantalla.setText(pantalla.getText().toString()+"-");           break;
            case R.id.multiplicacion:pantalla.setText(pantalla.getText().toString()+"*");  break;
            case R.id.division:pantalla.setText(pantalla.getText().toString()+"/");        break;
            case R.id.elevado:pantalla.setText(pantalla.getText().toString()+"^");         break;
            case R.id.apertura:pantalla.setText(pantalla.getText().toString()+"(");        break;
            case R.id.cierre:pantalla.setText(pantalla.getText().toString()+")");          break;
            case R.id.reset:pantalla.setText("");                                          break;
            case R.id.igual:pantalla.setText(calcular(pantalla.getText().toString()));con=0;     break;
            default:                                                                             break;
        }
    }
    int con=0;
    private String calcular(String cad) {
        Stack<String> ent=new Stack<>();
        Stack<String> signos=new Stack<>();
        String aux="";
        try {
            while(con<cad.length()){
                String x=cad.charAt(con)+"";
                if(isNumber(x)){
                    aux+=x;
                }else{
                    if(!aux.equals("")){
                        ent.push(aux);
                        aux="";
                    }
                    if(x.equals("(")){
                        con++;
                        ent.push(calcular(cad));
                    }else if(x.equals(")")){
                        while(!signos.isEmpty()){
                            ent.push(operar(signos.pop(),ent.pop(),ent.pop()));
                        }
                        return ent.pop();
                    }else if(!signos.isEmpty()){
                        if(high(x)>high(ent.peek())){
                            while(!signos.isEmpty()){
                                ent.push(operar(signos.pop(),ent.pop(),ent.pop()));
                            }
                            signos.push(x);
                        }else if(high(x)==high(ent.peek())){
                            ent.push(operar(signos.pop(),ent.pop(),ent.pop()));
                            signos.push(x);
                        }else{
                            signos.push(x);
                        }
                    }else{
                        signos.push(x);
                    }
                }
                con++;
            }
            while(!signos.isEmpty()){
                if(!aux.equals(""))
                    ent.push(aux);
                ent.push(operar(signos.pop(),ent.pop(),ent.pop()));
            }
            return ent.pop();
        }catch (Exception e){
            return ""+e;
        }
    }

    private boolean isNumber(String x) {
        try {
            Integer.parseInt(x);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private String operar(String s,String bb,String aa){
        int a=Integer.parseInt(aa);
        int b=Integer.parseInt(bb);
        switch (s){
            case "+":return (a+b)+"";
            case "-":return (a-b)+"";
            case "*":return (a*b)+"";
            case "/":return (a/b)+"";
            case "^":return ((int)Math.pow(a,b))+"";
            default:break;
        }
        return "";
    }
    private int high(String x){
        switch (x){
            case "+":return 2;
            case "-":return 2;
            case "*":return 1;
            case "/":return 1;
            case "^":return 0;
            default:break;
        }
        return -1;
    }
}
