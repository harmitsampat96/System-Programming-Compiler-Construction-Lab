import java.util.*;
import java.io.*;
class threeaddcode{
	public static void main(String args[])	{
		Scanner sc = new Scanner(System.in);
		String str;
		int i,j,top,index=0,max=48,n,top2=0;
		System.out.println("Enter the code line:");
		str = sc.nextLine();
		top = str.length();
		char ip[] = new char[top];
		for( i=0; i<top; i++ )
			ip[i] = str.charAt(i);
		top--;
		char addr3[][] = new char[15][5];
		for( i=0; i<15; i++ )
			addr3[i][1] = '=';
		for( i=0; i<=top; i++ )
			if( ip[i]>='0' && ip[i]<='9' )	{
				addr3[top2][2] = ip[i]; 
				ip[i] = (char)max++;
				addr3[top2][0] = ip[i];
				top2++;
			}	
		while( top > 3 ){
			for( i=0; i<=top; i++ ) {
				if( ip[i]==')' ) {
					if(ip[i-2]=='(' ) {
						top--;
						for( j=i; j<=top; j++)
							ip[j] = ip[j+1];
						top--;
						for( j=i-2; j<=top; j++ )
							ip[j] = ip[j+1];
						i=i-2; index = -1;	
						break;	
					}
					else {
						index = i-2;						
						for( j=i-1; j>=0; j-- )
							if( ip[j] == '(' )
								break;
							else if( ip[j] == '*' ) {
								index = j;
								break;
							}
					}
					if(i<=top)	break;					
				}
			}	
			if( i > top ){	
				index = top-1; 
				for( j=top; j>=0; j-- )
					if( ip[j] == '*' ){
						index = j;
						break;
				}
			}	
			if( index >0) {
				addr3[top2][2] = ip[index-1];
				addr3[top2][3] = ip[index];
				addr3[top2][4] = ip[index+1];
				ip[index-1] = (char)max;
				addr3[top2][0] = ip[index-1];
				top2++;
				max++;
				top = top-2;
				for( i=index; i<=top; i++ )
					ip[i] = ip[i+2];
			}	
		}
		for( i=0; i<3; i++ )
			addr3[top2][i] = ip[i];
		top2++;
		System.out.println("\nThree address code is:");
		for( i=0; i<3; i++ )
			for( i=0; i<top2; i++ )	{
				if( ( addr3[i][0]>='0' && addr3[i][0]<='9' ) && ( addr3[i][2]>='0' && addr3[i][2]<='9' ) && (addr3[i][3]=='\0'))
					System.out.print("temp"+addr3[i][0]+" "+addr3[i][1]+" "+addr3[i][2]);
				else
					for( j=0; j<5; j++ ) 	{
						if( addr3[i][j]>='0' && addr3[i][j]<='9' )
							System.out.print("temp");
						System.out.print(addr3[i][j]+" ");
					}
				System.out.println("");
			}
	}
}
/*
Enter the code line:
a=(x+y)*(y+z)+(x+y+z)
Three address code is:
temp0 = x + y
temp1 = y + z
temp2 = y + z
temp3 = x + temp2
temp4 = temp0 * temp1
temp5 = temp4 + temp3
a = temp5
*/