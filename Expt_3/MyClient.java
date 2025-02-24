import java.util.*;
import java.rmi.*;

public class MyClient
{
public static void main(String args[])
{
try
{
Scanner sc=new Scanner(System.in);
Adder stub=(Adder)Naming.lookup("rmi://localhost:5000/sonoo");
System.out.println("Performed by Didar\n");
System.out.println("Enter First Number:\n");
int a=sc.nextInt();
System.out.println("Enter Second Number:\n");
int b=sc.nextInt();
System.out.println(stub.add(a,b));
}
catch(Exception e)
{ System.out.println(e); }
}
}