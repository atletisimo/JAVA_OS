/*
Секвенца од броеви се смета за цик цак секвенца ако броевите во секвенцата се наизменично позитивни и негативни т.е. за секој пар од последователни броеви важи 
дека едниот е позитивен, а другиот е негативен.

На пример -1 2 -9 8 -4 е цик цак секвенца, но -1 9 7 -3 8 -3 не е, затоа што 9 и 7 се соседни броеви, но и двата се позитивни. 
Цик цак секвенцата може да почне или со позитивен или со негативен број. Секвенца од само еден ненулти број се смета како цик цак секвенца.
За дадена низа од броеви да се напише алгоритам кој ќе ја врати должината на најдолгата подниза која претставува цик цак секвенца.


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {
    
    static int najdiNajdolgaCikCak(int a[]) {
		boolean flag=false, first = false,poz=false;
		int max=0,br=0;
		
		for(int i=0;i<a.length;i++)
		{
			flag=true;
			if(first==false&&a[i]>0)
			{
				poz=true;
				br=1;
				first=true;
			}
			else if(first==false&&a[i]<0)
			{
				poz=false;
				br=1;
				first=true;
			}
			else
			{
				if(a[i]>0 && poz==false)
				{
					br++;
					poz=true;
				}
				else if(a[i]<0 && poz==true)
				{
					br++;
					poz=false;
				}
				else if(a[i]==0)
				{
					
						if(br>max)
                        {
                        max=br;
                        }
							
						
					first=false;
					br=0;
					
				}
				else
				{
					if(br>max)
					{
						max=br;
					}
					
					br=1;
					
					if(a[i]>0) poz=true;
					else poz=false;
				}
			}
            if(br>max) max=br;
		}
		
		return max;
	
	}
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());
        
        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);
        
        br.close();
       	
    }
    
}
