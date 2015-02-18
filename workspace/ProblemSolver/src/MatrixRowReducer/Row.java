package MatrixRowReducer;
public class Row{
	int col;
	Fraction [] fractions;
	// Constructs a row given an array of fractions
	public Row (Fraction [] list){
		//System.out.println("New row of created using a list of fractions");
fractions=list;
col=list.length;
	}
	//System.out.println("New row of created using a list of integers");
	public Row (int [] list){
	fractions=new Fraction [list.length];
for (int i=0; i<list.length; i++)
{
	fractions[i]=new Fraction (list[i]);
	
		}
col=list.length;
	}	
	// Constructs a row given the length of the row.
	//Each fraction will default to 0/1
	public Row (int length)
	{
		//System.out.println("New row of zeros created using the int: "+length);
		col=length;
		fractions=new Fraction [col];
	}
	// Creates a row with only one entry, the 0/1 fraction.
	public Row ()
	{
		//System.out.println("New row of zeros created using nothing");
		col=1;
		fractions=new Fraction [1];
		fractions[0]=new Fraction();
	}
	// Creates a row of length columns that is full zeros except
	//for the chosen location which has the given value.
	//This is useful for creation of scaler multiples of the identity matrix.
	public Row (int location, Fraction value, int length)
	{
		//System.out.println("New row of zeros and 1 value created using the location int: "+location+"the fraction:"+value.getNumerator()+"/"+value.getDenominator()+"and the length int"+length);
		col=length;
		fractions=new Fraction [length];
		for (int i=0;i<length;i++)
		{
		fractions[i]=new Fraction();
		}
		fractions[location]=value;
	}
	//Adds another row to the row in question.
	public Row subtractRows(Row subtractor)
	{
		Row c=new Row(subtractor.col);
		if (subtractor.col==this.col)
		{
	for (int i=0;i<col;i++)
	{
		//System.out.println("index"+i);
		//System.out.println("subtracting"+a.fractions[i].getNumerator()+"/"+a.fractions[i].getDenominator()+" from "+ this.fractions[i].getNumerator()+"/"+this.fractions[i].getDenominator());
				
		Fraction temp=subtractor.fractions[i].negativeFraction();
		c.fractions[i]=Fraction.addFraction(this.fractions[i],temp);
	}
		}
		return c;
	}
	public Row addRows(Row a)
	{
		Row c=new Row(a.col);
		if (a.col==this.col)
		{
	for (int i=0;i<col;i++)
	{
		c.fractions[i]=Fraction.addFraction(this.fractions[i],  a.fractions[i]);
	}
		}
		return c;
	}
	//Multiplies the row in question by a constant.
	public Row multiplyRows(Fraction a)
	{
    Row c=new Row(this.col);
	for (int i=0;i<this.col; i++)
	{
	c.fractions[i]=Fraction.multiplyFraction(this.fractions[i], a);
	}
	return c;
	}
// This methods returns true if the entire row is made up of zeros, false otherwise
	public boolean allZero()
	{
		for (int i=0;i<col; i++)
		{
		if (fractions[i].getNumerator()!=0)	
		{
			return false;
		}
		}
		return true;
	}
	/* This next method is critical. First it finds the first non zero location in the row
	 * It stores that location as i then returns it.
	 */
	public int leadLocation()
	{
		boolean nonZero=false;
		int i=0;
		while (nonZero)
		{ nonZero=(fractions[i].getNumerator()==0);
		if (!nonZero)
		{
			i++;
	   /* The highest index value is at column number-1. 
	    *If i equals col (column number), none of the index values had none zero.
	  *value hence a row of all zeros.
	  */
			if (i>=col)
	    {
	    	System.out.println("Row of zeros");
	    	 /*This stops the program from testing impossible index values.
	    	  *  All zero rows will have an imaginary leading 1 at col 
	    	  *  (which is not an actual index location) for calculation purposes.
	    	  *  Specifically when sorting rows this imaginary index value will makes sure
	    	  *  all zero rows are brought to the bottom.
	    	  */
	    return i;
	   
	    }
		}		
	}
		return i;
	}
/*
	 * This gets the location of the leading variable from the previous method.
	 * Then it multiplies the entire row by the reciprocal of the fraction at i. 
	 * This changes the row to have a leading 1.
	 * IT also returns i, returning the location of the leading 1.
	 */
	public int reducedRow()
	{
	int i=leadLocation();	
		Row temp=this.multiplyRows(this.fractions[i].reciprocal());
		this.fractions=temp.fractions;
	return i;
}
	
	public void printRow()
	{
  for (int i=0; i<col;i++)
  {
	fractions[i].printFraction(); 
  }
		
		
	}
	public String toString()
	{
		StringBuilder writeMatrix= new StringBuilder();
		for (int i=0; i<col;i++)
		{
			writeMatrix.append(fractions[i].toString());

			writeMatrix.append(" ");
        }
	return writeMatrix.toString();
	}
}