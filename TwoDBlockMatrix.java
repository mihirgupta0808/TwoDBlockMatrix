
import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;






public class TwoDBlockMatrix{
	static float[][] matrix ; 
	private float[][] pm ;
	// reomove print statements
	// made function for round , allowed ? 
    
    
    // change return type of BuildTwoDBlockMatrix to TwoDBlockMatrix in submission

    
    //this way the variable inp will be referenced to in, it won't create a duplicate.

//Use inp = in.clone() for duplicating data.
	
	public float[][] getpm() {
		return pm ; 	
	}

    public static TwoDBlockMatrix buildTwoDBlockMatrix(InputStream in ) throws IOException{
        // now calculating dimnesion of matrix 
    	int content;
		String content_str = "";
		while ((content = in.read()) != -1) {
			content_str = content_str + (char)content;
		}
        int r = 0 ; 
        int c = 0 ;
        int rt = 0 ; 
        int ct = 0 ;  
        int f = 1 ; // if f is 1 code checking for block location will work
         // if f is 2  code checking for rows and cols of matrix ending with ; will work 
         // if f is 3 code checking for # will work 
        //i2 = in.clone() ; 
        
        
        // change next line in submission code
        Scanner s1 = new Scanner(content_str) ;
        
        
        while( s1.hasNextLine()){
            switch(f){
                case 1 :
                	String line = s1.nextLine() ; 
                	String[] line1 = line.split(" ");
                    int a = Integer.parseInt(line1[0]);
                    int b =Integer.parseInt(line1[1]);
                    rt = a - 1 ; 
                    ct = b - 1 ;
                    f = 2 ; 
                    break ; 
                case 2 :
                    int l = 0 ;
                    int w = 0 ;
                    while(s1.hasNextLine()){
                    	String line2 = s1.nextLine() ;
                    	if ( line2.charAt(0) == '#') {
                    		// now flag goes to 1 
                    		f = 1 ;
                            rt += l ; 
                            ct += w ; 
                            if ( rt > r ) r = rt ; 
                            if ( ct > c) c = ct ;
                            break ; 
                    	}
                    	else {
                    		// f remains 2  i.e we have rows and columns and ;
                    		l++ ;
                    		int n = line2.length();
                            line2=line2.substring(0,n-1);
                            //System.out.println(line2);
                            int wc = 0;                                                       
                            Scanner ls = new Scanner(line2) ;
                            while( ls.hasNextFloat() ){
                            	float temp = ls.nextFloat() ; 
                                wc++ ; 
                            }
                            if ( wc > w ) w =  wc ;  
                            
                    	}
                    }
                    break ;  
            }
        }
        //now size of matrix will be r c
        // creating array of array of size r c and then filling it .\
        //  !!!!!!!!!!!!  rounding float/float to 2 decimal places is missing !!!!!!!!!!!!
        matrix = new float[r][c] ; 
        int ctf = 0 ;
        int rtf = 0 ; 
        f = 1 ; 
        Scanner s2 = new Scanner(content_str) ;
        while( s2.hasNextLine()){
            switch(f){
                case 1 :
                	String line = s2.nextLine() ; 
                	String[] line1 = line.split(" ");
                    int a = Integer.parseInt(line1[0]);
                    int b =Integer.parseInt(line1[1]); 
                    rt = a - 1 ; 
                    ct = b - 1 ;
                    rtf = a-1 ;
                    ctf = b - 1 ; 
                    
                    f = 2 ; 
                    break ; 
                case 2 :
                    while(s2.hasNextLine()){
                    	String line2 = s2.nextLine() ;
                    	if ( line2.charAt(0) == '#') {
                    		// now flag goes to 1 
                    		f = 1 ;
                           // rt += l ; 
                           // ct += w ; 
                           // if ( rt > r ) r = rt ; 
                           // if ( ct > c) c = ct ;
                            break ; 
                    	}
                    	else {
                    		// f remains 2  i.e we have rows and columns and ;
                    		//l++ ;
                    		rt++ ;
                    		int n = line2.length();
                            line2=line2.substring(0,n-1);
                            //System.out.println(line2);
                            //int wc = 0;                                                       
                            Scanner ls = new Scanner(line2) ;
                            ct = ctf; 
                            while( ls.hasNextFloat() ){
                            	ct ++ ; 
                            	float temp = ls.nextFloat() ; 
                            	
                                matrix[rt-1][ct-1] = temp ; 
                               // System.out.println(matrix[rt-1][ct-1]);
                                
                            }
                            //if ( wc > w ) w =  wc ;  
                            
                    	}
                    }
                    break ;   
            }
        }
        
        TwoDBlockMatrix b = new TwoDBlockMatrix(matrix.clone());
        return b ;
    }
    public TwoDBlockMatrix( float[][] array){
    	matrix = array.clone() ;
    	pm = array.clone() ;
    }	
     public float[][] transposefake(float[][] arr) {
    	int n = arr.length;
    	float[][] tp = new float[n][n] ; 
    	
    	for ( int i = 0 ; i < n ; i++) {
    		for ( int j = 0 ; j < n ; j++) {
    			tp[j][i] = arr[i][j] ;  
    		}
    	}
    	
    	return tp ; 
    	
    }
     public TwoDBlockMatrix transpose() {
    	int r = matrix.length;
    	int c = matrix[0].length;
    	float[][] tp = new float[c][r] ; 
    	
    	for ( int i = 0 ; i < r ; i++) {
    		for ( int j = 0 ; j < c ; j++) {
    			tp[j][i] = matrix[i][j] ;  
    		}
    	}
    	TwoDBlockMatrix mat = new TwoDBlockMatrix(tp);
    	return mat ; 
    	
    }
    public TwoDBlockMatrix multiply(TwoDBlockMatrix other) throws IncompatibleDimensionException {
    	float[][] m1 = pm ;
    	int r1 = pm.length;
    	if ( r1 == 0 ) {
    		throw new IncompatibleDimensionException("Matrix multilication not possible");
    		
    	}
    	int c1 = pm[0].length;
    	if ( c1 == 0 ) {
    		throw new IncompatibleDimensionException("Matrix multilication not possible");
    	}
    	float[][] m2 = other.getpm() ; 
    	int r2 = m2.length ;
    	if ( r2 == 0 ) {
    		throw new IncompatibleDimensionException("Matrix multilication not possible");
    	}
    	int c2 = m2[0].length ;
    	if ( c2 == 0 ) {
    		throw new IncompatibleDimensionException("Matrix multilication not possible");
    	}
        
		
		
    	if ( c1 != r2) {
			
    		throw new IncompatibleDimensionException("Matrix multilication not possible");
    	}
    	else {
    		float[][] m = new float[r1][c2];
    		for (int i = 0 ; i < r1 ; i++) {
    			for ( int j = 0 ; j < c2 ; j++) {
    				float sum = 0 ;
    				for ( int k = 0 ; k < c1 ; k++) {
    					sum += m1[i][k]*m2[k][j] ; 
    				}
    				m[i][j] = sum ;
    			}
    		}
    		TwoDBlockMatrix matmul = new TwoDBlockMatrix(m);
    		return matmul ;
    	}   	
    }
    
    public float[][] minormatrix( int i , int j , float[][] arr ) {
    	int n = arr.length ; 
    	
    	// gives minor matrix of element at index i,j in a square matrix of size n
    	float[][] m = new float[n-1][n-1] ; 
    	int p = 0 ; int q = 0 ; 
    	for ( int x = 0 ; x < n-1 ; x ++ ) {
    		if ( p == i ) {
    			p++ ;
    		}
    		for ( int y = 0 ; y < n-1 ; y ++) {
    			if ( q == j ) {
    				q++ ; 
    			}
    			m[x][y] = arr[p][q] ; 
    			if ( y == n-2 ) {
    				q = 0 ;
    			}
    			else{
    				q++;
    			}
    		}
    		p++;
    	}
    	return m ;
    	
    }
    public float determinant(float[][] arr) {
    	float det = 0  ;
    	int l = arr.length ;
    	if (l == 1) return arr[0][0] ; 
    	for ( int x = 0 ; x < l ; x ++) {
    		float[][] sub = minormatrix(0,x,arr);
    		int i = 0 ;
    		int cf ;
    		if ( x % 2 == 0 ) cf = 1 ;
    		else {
    			cf = -1 ;
    		}
    		det += cf*arr[0][x]*determinant(sub) ; 
    		
    	}
    	return det ; 
    }
    public float[][] adjoint(float[][] arr){
    	int n = arr.length ;
    	float[][] adj = new float[n][n];
    	for ( int i = 0 ; i < n ; i++) {
    		for ( int j = 0 ; j < n ; j++) {
    			int cf ;
    			if ( (i + j) % 2 == 0 ) cf = 1 ;
    			else cf = -1 ;
    			adj[i][j] = cf*determinant(minormatrix(i,j,arr)) ; 
    		}
    	}
    	adj = transposefake(adj) ;
    	return adj ; 
    	
    	
    }
    
    
    public TwoDBlockMatrix inverse() throws InverseDoesNotExistException{
    	if ( pm.length == 0 ) {
    		throw new InverseDoesNotExistException("inverse does not exist");
    		
    	}
    	if ( (pm.length != pm[0].length) || determinant(pm) == 0 ) {
    		throw new InverseDoesNotExistException("inverse does not exist");
		}
		
    	
		float detpm = determinant(pm);
		if ( detpm == 0 ) throw new InverseDoesNotExistException("inverse does not exist");
		else{
    	    int n = pm.length;
    		float[][] inverse = adjoint(pm).clone();
    		for ( int i = 0 ; i < n ; i++) {
    			for ( int j = 0 ; j < n ; j++) {
    				inverse[i][j] = inverse[i][j] / detpm ;
    			}
			}
			TwoDBlockMatrix invblock = new TwoDBlockMatrix(inverse) ;	
    	    return invblock ;
		}
		
    	
		
	
		

		
		
    }
    public TwoDBlockMatrix getSubBlock(int row_start , int col_start , int row_end , int col_end) throws SubBlockNotFoundException{
        row_end -- ;
    	col_end -- ;
    	int r = pm.length ;
    	if ( r == 0 ) {
    		throw new SubBlockNotFoundException("subblock does not exist");
    		
    	}
    	int c = pm[0].length;
    	if ( c == 0 ) {
    		throw new SubBlockNotFoundException("subblock does not exist");
    		
    	}
    	if ( row_start > row_end || col_start > col_end || row_end > r || col_end >c ) {
    		throw new SubBlockNotFoundException("subblock does not exist");
    	}
    	int l = (row_end - row_start) +1 ;
    	int b = (col_end - col_start) +1 ;
    	int p = row_start -1 ;
    	int q = col_start - 1 ;
    	float[][] subarr = new float[l][b];
    	for ( int i = 0 ; i < l ; i++){
    		q = col_start - 1 ;
    		
    		for( int j = 0 ; j < b ; j++) {
    			subarr[i][j] = pm[p][q];
    			q++;
    			
    			
    		}
    		
    		p++;
    		
    	}
    	TwoDBlockMatrix subblock = new TwoDBlockMatrix(subarr);
    	return subblock ;
    	
    	
    	
    	
    }
    @Override
    public String toString() {
    	String answer = "";
    	float[][] matstr = pm.clone() ; 
    	// r , c dimensions of matirx 
    	// rs , cs starting index of a bolck 
    	// re , ce , end index of block 
    	int r = pm.length;
    	int c = pm[0].length ;
    	int rs = 0  ;
    	int cs = 0;
    	int ce =0 ; 
    	int re = 0; 
    	Boolean block = false ;
    	for ( int  i = 0 ; i < r ; i ++ ) {
    		for ( int j = 0 ; j < c ; j++) {
    			if ( matstr[i][j] == 0 ) {
    				// keep looking
    			}
    			else {
    				rs = i ;
    				cs = j ; 
    				int x = rs ;
    				Boolean colend = false;
    				for ( int y = cs ; y < c ; y ++) {
						if ( matstr[x][y] == 0 ) {
							y--;
							ce = y;
							colend = true ;
							break ;
						}
						
					}
    				if ( !colend) {
    					ce = c - 1;
    				}
    				if ( rs == r-1 ) {
    					re = r-1 ; 
    				}
    				else {
    					for ( x = rs+1 ; x < r ; x++ ) {
    					    for ( int y = cs ; y <= ce ; y++) {
    					       if ( matstr[x][y] == 0) {
    					    	   if ( y <= ce ) {
    					    		   re = x - 1;
    							       block = true ;
    							       break ; 
    						       }
    					    	   else {
    					    		   // y == ce so keep going to ext row
    					    	   }
    					       }
    				        }
    				       if ( block ) break ; 
    				       else {
    					        // do nothing 
    				       }
    			        }
        				if ( !block ) {
        					re = r - 1 ;
        					block = true ; 
        				} 
        				block = false ; 
    				}
    				
    				
    			    
    				
    				DecimalFormat format2dec = new DecimalFormat("0.00");
    				//System.out.println(format2dec.format(your_float));

    				
    				answer += String.valueOf(rs+1) ;
    				answer += " ";
    				answer += String.valueOf(cs + 1);
    				answer += "\n";
    				for ( int p  = rs ; p <= re ; p++) {
    					for (  int q = cs ; q <= ce ; q++) {
    						if ( q != ce) {
    							answer += format2dec.format(matstr[p][q]);
    							answer += " ";
    						}
    						else {
    							answer += format2dec.format(matstr[p][q]) ;
    							answer += ";\n";
    							
    						}
    						matstr[p][q] = 0 ;
    						
    						
    					}	
    				}
    				answer += "#\n" ;
    			}	
    		}
    	}
    	return answer ;		
    						
    				
    }
	
	
	
        
        public static void main(String[] Args) throws FileNotFoundException,IOException,IncompatibleDimensionException,InverseDoesNotExistException,SubBlockNotFoundException{
        	/*
        	File file = new File("C:\\Users\\Tushar Gupta\\Desktop\\COL106\\test1.txt") ; 
            InputStream in  = new FileInputStream(file);
			TwoDBlockMatrix mat = TwoDBlockMatrix.buildTwoDBlockMatrix(in);
			float[][] arr = mat.getpm();
			System.out.println("testing bulid");

			for (int i = 0; i < arr.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < arr[i].length; j++) {
                   System.out.print(arr[i][j] + " ");
                }
                System.out.print("\n");
           }
            System.out.print("\n end ");
            
            
            System.out.println("transpose check ");
            TwoDBlockMatrix mattp = mat.transpose();
			float[][] m1 = mattp.getpm() ; 
			for (int i = 0; i < m1.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < m1[i].length; j++) {
                   System.out.print(m1[i][j] + " ");
                }
                System.out.print("\n");
           }
            System.out.print("\n end ");
          
            float[][] multarrother = new float[3][4];
            for ( int i = 0 ; i < 3 ; i++) {
            	for ( int j = 0 ; j < 4 ; j ++) {
            		multarrother[i][j] = 1 ; 
            	}
            	
            }
            
            
            
           
            TwoDBlockMatrix multmatother = new TwoDBlockMatrix(multarrother);
            float[][] arr1 = new float[4][3] ;
            for ( int i = 0 ; i < 4 ; i++) {
            	for ( int j = 0 ; j < 3 ; j ++) {
            		arr1[i][j] = i+j ; 
            	}
            }
            TwoDBlockMatrix mat1 = new TwoDBlockMatrix(arr1);
            TwoDBlockMatrix multiplied = mat1.multiply(multmatother);
            float[][] m = multiplied.getpm() ; 
            for (int i = 0; i < arr1.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < arr1[i].length; j++) {
                   System.out.print(arr1[i][j] + " ");
                }
                System.out.print("\n");
           }
            System.out.print("\n end ");
            
            
            
            
            
            for (int i = 0; i < multarrother.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < multarrother[i].length; j++) {
                   System.out.print(multarrother[i][j] + " ");
                }
                System.out.print("\n");
           }
            System.out.print("\n end ");
            
            
            
            
            
            
            
            
            
            for (int i = 0; i < m.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < m[i].length; j++) {
                   System.out.print(m[i][j] + " ");
                }   
                System.out.print("\n");
           }
			System.out.print("\n end ") ;
			
			float[][] arr2 = new float[3][3] ;
			for ( int i = 0 ; i < 3 ; i++) {
            	for ( int j = 0 ; j < 3 ; j ++) {
            		arr2[i][j] = i+j ; 
            	}
			}
			arr2[0][0] = 5 ;
			TwoDBlockMatrix inversecheckmat = new TwoDBlockMatrix(arr2).inverse() ; 
			float[][] minv = inversecheckmat.getpm() ; 
			System.out.println("inverse check");
			

			for (int i = 0; i < minv.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < minv[i].length; j++) {
                   System.out.print(minv[i][j] + " ");
                }   
                System.out.print("\n");
            }
			System.out.print("\n end ") ;

            
            
            
            
            
            
         
            //TwoDBlockMatrix mincheck = new TwoDBlockMatrix(arr1);
           // float[][] m = mincheck.minormatrix(1, 1, arr1);
           // TwoDBlockMatrix adjcheck = new TwoDBlockMatrix(arr1);
            //float[][] m = adjcheck.adjoint(arr1);
            
            TwoDBlockMatrix subblockcheck = new TwoDBlockMatrix(arr2);
            //float[][] m = adjcheck.adjoint(arr1);
			float[][] sb = subblockcheck.getSubBlock(2,2,4,4).getpm() ; 
			System.out.println("subblock check");
			for (int i = 0; i < sb.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < sb[i].length; j++) {
                   System.out.print(sb[i][j] + " ");
                }   
                System.out.print("\n");
            }
			System.out.print("\n end ") ;

			
            String l = subblockcheck.toString();
            
            //float[][] m = mat.getpm();
            
            
            
            System.out.print(l);
            //float d = adjcheck.determinant(arr1);
			//System.out.println(d);
			*/

        /*
			System.out.println("new cases") ; 
			File File1 = new File("C:\\Users\\Tushar Gupta\\Desktop\\COL106\\file.txt") ; 
			InputStream in1  = new FileInputStream(File1);
			
			File File2 = new File("C:\\Users\\Tushar Gupta\\Desktop\\COL106\\other.txt") ; 
            InputStream in2 = new FileInputStream(File2);

			TwoDBlockMatrix first = TwoDBlockMatrix.buildTwoDBlockMatrix(in1);
			float[][] firstarr = first.getpm();
			





			for (int i = 0; i < firstarr.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < firstarr[i].length; j++) {
                   System.out.print(firstarr[i][j] + " ");
                }   
                System.out.print("\n");
            }
			System.out.print("\n end ") ;









			TwoDBlockMatrix second = TwoDBlockMatrix.buildTwoDBlockMatrix(in2);
			float[][] secondarr = second.getpm();
			for (int i = 0; i < secondarr.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < secondarr[i].length; j++) {
                   System.out.print(secondarr[i][j] + " ");
                }   
                System.out.print("\n");
            }
			System.out.print("\n end ") ;






			TwoDBlockMatrix third =  first.multiply(second);
			float[][] thirdarr = third.getpm() ;
			for (int i = 0; i < thirdarr.length; i++) {
                // Loop through all elements of current row 
                for (int j = 0; j < thirdarr[i].length; j++) {
                   System.out.print(thirdarr[i][j] + " ");
                }   
                System.out.print("\n");
            }
			System.out.print("\n end ") ;



            
            
        	/**float a = -0.075 ;
        	float b = -0.085;
        	float c = -0.086 ;
        	float d = -0.076;
        	
        	float e = -0.0861;
        	float f = -0.0761;
        	float g = -0.0751;
        	float h = -0.0851;
        	float i = -10;
        	float j = -1 ; 
        	float k = -0.1 ;
        	System.out.println(TwoDBlockMatrix.rounder(a));
        	System.out.println(TwoDBlockMatrix.rounder(b));
        	System.out.println(TwoDBlockMatrix.rounder(c));
        	System.out.println(TwoDBlockMatrix.rounder(d));
        	System.out.println(TwoDBlockMatrix.rounder(e));
        	System.out.println(TwoDBlockMatrix.rounder(f));
        	System.out.println(TwoDBlockMatrix.rounder(g));
            System.out.println(TwoDBlockMatrix.rounder(h));
            System.out.println(TwoDBlockMatrix.rounder(i));
            System.out.println(TwoDBlockMatrix.rounder(j));
            System.out.println(TwoDBlockMatrix.rounder(k));
            **/
		}

		
		
        
         
         
         
         
          

          

}


class IncompatibleDimensionException extends Exception
{
	public IncompatibleDimensionException(String s)
	{
	// Call constructor of parent Exception
		super(s);
		}
}
class InverseDoesNotExistException extends Exception
{
	public InverseDoesNotExistException(String s)
	{
	// Call constructor of parent Exception
		super(s);
		}
}
class SubBlockNotFoundException extends Exception
{
	public SubBlockNotFoundException(String s)
	{
	// Call constructor of parent Exception
		super(s);
		}
	

}










