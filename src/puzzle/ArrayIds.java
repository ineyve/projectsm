/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

/**
 *
 * @author ineyv
 */
public class ArrayIds {
   private int[][] matrixIds;
   private static ArrayIds instance = null;
   protected ArrayIds() {
      // Exists only to defeat instantiation.
   }
   public static ArrayIds getInstance() {
      if(instance == null) {
         instance = new ArrayIds();
      }
      return instance;
   }
   
   public static int[][][] toMatrixWithIds(int [][] matrix)
   {
       /*
       
       Encontra as peças e atribui um id a cada
       
       */
        int[][][] toMatrix = new int[matrix.length][matrix.length][3];
        int id=1;   
        int largePiece=0;
        for(int l=0; l < matrix.length; l++)
        {
            largePiece=0; 
            for(int c=0; c < matrix.length; c++)
            {
                int val = matrix[l][c];
                if(largePiece<=0)
                {
                    switch(val){
                        //horizontal
                        case 4:
                            largePiece=1;
                        break;
                        case 6: 
                            largePiece=2;
                        break;
                        case 8:
                            largePiece=3;
                        break;
                    }
                    
                    switch(val){
                        case 1: case 2: case 4: case 6: case 8:
                            toMatrix[l][c][0]=id;
                            toMatrix[l][c][1]=val;
                            //if(val>=4)
                                //toMatrix[l][c][2]+=10;//+10; change the image of the first element!!
                            id++;
                        break;
                    }
                }else{
                    toMatrix[l][c][0]=id-1;
                    toMatrix[l][c][1]=val;
                    //toMatrix[l][c][2]=val;
                    largePiece--;
                }
            }
        }
        
        for(int c=0; c < matrix.length; c++)
        {
            largePiece=0;
            for(int l=0; l < matrix.length; l++)
            {
                int val = matrix[l][c];
                if(largePiece<=0)
                {
                    switch(val){
                        //vertical
                        case 5: 
                            largePiece=1;
                        break;
                        case 7: 
                            largePiece=2;
                        break;
                        case 9:
                            largePiece=3;
                        break;
                    }
                    switch(val){
                        case 3: case 5: case 7: case 9:
                            toMatrix[l][c][0]=id;
                            toMatrix[l][c][1]=val;
                            //if(val>=5)
                            //    toMatrix[l][c][2]+=10;//+10; //change the image of the first element!!
                            id++;
                        break;
                    }
                    
                }else{
                    toMatrix[l][c][0]=id-1;
                    toMatrix[l][c][1]=val;
                    //toMatrix[l][c][2]=val;
                    largePiece--;
                }
                
            }
        }
        return toMatrix;
   }
   
   public static String matrixToString(int [][][] Matrix)
   {
       String output="";
       for(int l=0; l<Matrix.length; l++)
       {
           for(int c=0; c<Matrix.length; c++)
           {
               output+=Matrix[l][c][0]+",";
           }
           output+="\n";
       }
       output+="||\n";
       for(int l=0; l<Matrix.length; l++)
       {
           for(int c=0; c<Matrix.length; c++)
           {
               output+=Matrix[l][c][1]+",";
           }
           output+="\n";
       }
       return output;
   }
}
