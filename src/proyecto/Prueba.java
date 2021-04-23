/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author emmanuel
 */
public class Prueba {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Algoritmo objAlgoritmo = new Algoritmo();
        double []datosx={8,2,6};
        double []datosy={0,9,6};
        double sigma=1.0;
        double beta=1.0;
        
        System.out.println("Las posiciones de los datos són: \n");
        for(int i=0; i<3;i++){
            System.out.println(datosx[i]+"\t"+datosy[i]+"\n");
        }
        
        System.out.println("El valor de σ es: "+sigma);
        System.out.println("El valor de β es: "+beta);

        
        ArrayList<Double> obj1;
        obj1 = new ArrayList<>();
           for(int i=0;i<3;i++){
               obj1.add(datosx[i]);
         }
        ArrayList<Double> obj2;
        obj2 = new ArrayList<>();
           for(int i=0;i<3;i++){
               obj2.add(datosy[i]);
         }   
        Collections.sort(obj1);
        Collections.sort(obj2);
        System.out.println("\nEl paramatro de muestra en x es: "+obj1.get(0)+" - "+obj1.get(2));
        System.out.println("El paramatro de muestra en y es: "+obj2.get(0)+" - "+obj2.get(2));
        double amin=obj1.get(0);
        double amax=obj1.get(2);
        double bmin=obj2.get(0);
        double bmax=obj2.get(2);
        System.out.println("\n");
        
        System.out.println("A continuación se muestran las intersecciones del enrrejado...");
        System.out.println("(Cada intersección es un punto candidato a centroide)\n");
        double []interx= new double[16];
        double []intery= new double[16];
          Algoritmo objAlgoritmo2 = new Algoritmo();
        double [] vectA=objAlgoritmo.puntos(amin,amax);
        double [] vectB=objAlgoritmo2.puntos(bmin,bmax);
        for(int i=0;i<4;i++){
        for(int j=0;j<4;j++){
           System.out.println(vectA[i]+"\t"+vectB[j]+"\n");
           interx[i]=vectA[0];
           interx[4+i]=vectA[1];
           interx[8+i]=vectA[2];
           interx[12+i]=vectA[3];
           intery[i]=vectB[i];
           intery[4+i]=vectB[i];
           intery[8+i]=vectB[i];
           intery[12+i]=vectB[i];
        }
        }
    
        System.out.println("\n**** Función Montaña ****\n\n");
        System.out.println("A continuación se muestran las distancias entre cada intersección y dato...\n");
        
        Algoritmo objAlgoritmo3 = new Algoritmo();
        ArrayList<Double>obj3;
        obj3 = new ArrayList<>();
        for(int i=0;i<16;i++){
            for(int j=0;j<3;j++){
             obj3.add(objAlgoritmo3.Distancia(interx[i],datosx[j],intery[i],datosy[j]));  
            }   
        }
        for(int i=0;i<48 ;i++){
            System.out.println(obj3.get(i));
        }
        System.out.println("\nA continuación se muestra el valor de la función montaña para cada intersección...\n");

        Algoritmo objAlgoritmo4 = new Algoritmo();
         Algoritmo objAlgoritmo5 = new Algoritmo();
          double []vecfun = new double[16];
          for(int i =0;i<16;i++){
              vecfun[i]=0.0;
          }
          for(int i=0;i<16;i++){
              for(int j=0;j<3;j++){
                  vecfun[i]=vecfun[i]+objAlgoritmo4.fmont(sigma,obj3.get((i*3)+j));
              }
          }
        for(int i=0;i<16;i++){
              System.out.println(vecfun[i]);
          }
        int position = 0;
        position=objAlgoritmo5.centroid(vecfun);
        System.out.println("\n El centroide candidato es: "+ interx[position]+","+ intery[position]);

        System.out.println("\n\n **** Función de deconstrucción ****\n\n");
        Algoritmo objAlgoritmo6 = new Algoritmo();
        Algoritmo objAlgoritmo7 = new Algoritmo();
        double []vec2 = new double[16];
        double []vec3 = new double[16];
        double []vec4 = new double[16];
         for(int i=0;i<16;i++){
            
            vec2[i]=objAlgoritmo.Distancia(interx[i], interx[position], intery[i] , intery[position]);
        }
         int pos = 0;
        double mayor;
        mayor=vecfun[0];
        for(int i=0;i<16;i++){
            if(vecfun[i]>mayor){
                mayor=vecfun[i];
                pos=i;
            }
        }
        for(int i=0;i<16;i++){
            vec3[i]=objAlgoritmo6.fdesc(beta,vec2[i]);
            vec4[i]=objAlgoritmo7.fdesc2(mayor, vecfun[i], vec3[i]);
        }
        System.out.println("\nA continuación se muestra el valor de la nueva función montaña después de la función de deconstrucción para cada intersección...\n");
        
        for(int i=0;i<16;i++){
              System.out.println(vec4[i]);
          }
        int position1 = 0;
        position1=objAlgoritmo.centroid(vec4);
        System.out.println("\n El siguiente centroide después de la función de deconstrucción es: "+ interx[position1]+","+ intery[position1]);
    }
}
