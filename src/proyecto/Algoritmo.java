/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author emmanuel
 */
public class Algoritmo {
    private double almacenar[]=new double[4];
    private Double dist;
    private Double result;
    private Double resultado;
    private Double resultado2;
    private int pos=0;
    private double mayor=0.0;
     
     
     
    public double[] puntos(double min,double max){
        double celda=(max-min)/3;
        almacenar[0]=min;
        almacenar[1]=min+celda;                         // Este método ayuda a determinar los puntos candidatos (intersecciones) a centroide. 
        almacenar[2]=almacenar[1]+celda;
        almacenar[3]=max;
        return almacenar;
       }
  
    public double Distancia(double x1, double x2, double y1, double y2){
    dist=Math.hypot((x2-x1), (y2-y1));                                      //Con este método se determinan las distancias entre cada intersección y cada dato que se ingresó.
        return dist;
    }  
   
    public double fmont(double sigma, double dist){
        result= Math.exp(-((Math.pow(dist,2))/(2*(Math.pow(sigma,2)))));        //Este es el método con el cual se calcula la función montaña.
        return result;
    }
   
     public double fdesc(double beta, double dist){
        resultado= Math.exp(-((Math.pow(dist,2))/(2*(Math.pow(beta,2)))));      //Este es el método con el cual se calcula una de las partes de la función de deconstrucción.
        return resultado;
       } 
    
     public double fdesc2(double mc1, double mI, double fdesc){                    //Con este método se lleva acabo el cálculo total de la función de deconstrucción
             resultado2=mI-(mc1*fdesc); 
         return resultado2;
     }  
      
    public int centroid(double []vec){                                             //Este método nos sirvió para determinar cual de los puntos candidatos o intersecciones del enrrejado era el mas optimo
            mayor=vec[0];
            for(int i=0;i<vec.length;i++){
                if(vec[i]>mayor){
                    mayor=vec[i];
                    pos=i;
                }
            }
            return pos;
        }
}
