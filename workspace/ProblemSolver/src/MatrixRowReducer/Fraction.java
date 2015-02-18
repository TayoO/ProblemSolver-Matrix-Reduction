package MatrixRowReducer;
// Fraction class. Every matrix value is expressed as a fraction 
public class Fraction {
	
private int numerator;
private int denominator;
//Refracted get set methods.
int getNumerator() {
	return numerator;
}
void setNumerator(int numerator) {
	this.numerator = numerator;
}
int getDenominator() {
	return denominator;
}
void setDenominator(int denominator) {
	if (denominator!=0)
	this.denominator = denominator;
	else
	System.out.print("Zero denominator error");
}
// Creates a fraction given a numerator and denominator respectively
public Fraction (int a,int b)
{
	//System.out.println("Case a/b:"+a+"/"+b);
	// This automatically reduces the fraction.
	int gcd=gCD(a,b);
	if (gcd==0)
	{
	System.out.println("the pair ("+a+","+b+") produced a gcd of zero, using 1 instead");
	gcd=1;
	}
	setNumerator(a/gcd);
	setDenominator(b/gcd);
	
}
// If you enter only 1 number, it assumes the number is the numerator and the denominator is 1.
// Since the denominator is 1 now reduction is needed
public Fraction (int a)
{
	//System.out.println("Case a/1:"+a+"/1");
	setNumerator(a);
	setDenominator(1);
	
}
//Defaults fractions to 0/1 if no values are inputed
public Fraction()
{
	//System.out.println("Case 0/1");
	setNumerator(0);
	setDenominator(1);
}
// Adds the values of two fractions together. Note this uses the gCD method and creates a new fraction
public static Fraction addFraction(Fraction a, Fraction b)
{int denA=a.getDenominator();
int denB=b.getDenominator();
int numA=a.getNumerator();
int numB=b.getNumerator();
	int newDen=gCD(denA, denB);
	int newNum=(numA*newDen/denA) +(numB*newDen/denB);
	Fraction c=new Fraction(newNum,newDen);
	return c;
}
// makes a new fraction negative the original.
public Fraction negativeFraction()
{
	return new Fraction((0-this.getNumerator()), this.getDenominator());
}
//This multiplies two fractions together by multiplying there denominators and numerators. 
//Note the fraction is automatically reduced upon creation.
public static Fraction multiplyFraction(Fraction a, Fraction b)
{
	int denA=a.getDenominator();
	int denB=b.getDenominator();
	int numA=a.getNumerator();
	int numB=b.getNumerator();
	int newDen=(denA*denB);
	int newNum=(numA*numB);
	Fraction c=new Fraction(newNum,newDen);
	return c;
}

// Flips the numerator and denominator to get the reciprocal.
public  Fraction reciprocal()
{
	Fraction c;
	if (this.numerator!= 0)
	{
	  c= new Fraction(this.denominator,this.numerator);
	 
	}
	else
		{
		//System.out.print("0 has no reciprocal");
		c =new Fraction(0,1);
		}
	return c;
}
//Divides fractions by multiplying by the reciprocal.
public static Fraction divideFraction(Fraction a,Fraction b)
{
	return multiplyFraction(a, b.reciprocal());
}
//Uses the Euclidean Algorithm to find the greatest common denominator of two numbers
public static int gCD(int y, int x)
{
	int max;
	int min;
	if (Math.abs(x)<Math.abs(y))
	{
	max=Math.abs(y);
	min=Math.abs(x);
	}
	else if (y<x)
	{
	max=Math.abs(x);
	min=Math.abs(y);
	}
	else
	return x;
	int temp; 
	if(
			min>0)
	{
		temp=max%min;
     return gCD(min, temp);
	}
	return max;
}
// Prints out the fraction
public void printFraction()
{
	System.out.print(getNumerator()+"/"+getDenominator()+" ");
}
public String toString()
{
	return(getNumerator()+"/"+getDenominator()+" ");
}
}
