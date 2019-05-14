import java.util.*;
public class Solution {
    public int cnttrue(String A) {
        //calculate number of ways for expression to be true
        ArrayList<Character>exp=new ArrayList<Character>();
        ArrayList<Character>oper=new ArrayList<Character>();
        for(int i=0;i<A.length();i++)
        {
            if(A.charAt(i)=='T'||A.charAt(i)=='F')
            exp.add(A.charAt(i));
            else
            oper.add(A.charAt(i));
        }
        int n=exp.size();
        long t[][]=new long[n][n];
        long f[][]=new long[n][n];
        for(int i=0;i<n;i++)
        {
            if(exp.get(i)=='T')
            {
            t[i][i]=1;
            f[i][i]=0;
            }
            else
            {
                f[i][i]=1;
                t[i][i]=0;
            }
        }
        for(int gap=1;gap<n;gap++)
        {
            int i=0;
            for(int j=gap;j<n;j++)
            {
                t[i][j]=f[i][j]=0;
              for(int g=0;g<gap;g++)
              {
                  int k=i+g;
                  long tik=t[i][k]+f[i][k];
                  long tkj=t[k+1][j]+f[k+1][j];
                  if(oper.get(k)=='&')
                  {
                      t[i][j]+=t[i][k]*t[k+1][j];
                      f[i][j]+=tik*tkj-(t[i][k]*t[k+1][j]);
                  }
                  else if(oper.get(k)=='|')
                  {
                       f[i][j]+=f[i][k]*f[k+1][j];
                      t[i][j]+=tik*tkj-(f[i][k]*f[k+1][j]);
                      
                  }
                  else if(oper.get(k)=='^')
                  {
                      t[i][j]+=t[i][k]*f[k+1][j]+(f[i][k]*t[k+1][j]);
                      f[i][j]+=t[i][k]*t[k+1][j]+f[i][k]*f[k+1][j];
                  }
              }
              i++;
            }
        }
        return (int)((t[0][n-1])%1003);
    }
}

