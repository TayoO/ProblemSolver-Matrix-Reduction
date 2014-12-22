ProblemSolver-Matrix-Reduction
==============================

This is a bunch of classes and methods useful for Matrix reduction (still in progress)

// Number of rows and array of the rows respectively	
int rowNum;
Row [] rows;
// Creates a matrix given an array of rows (creating a rowNum by rows[0].length matrix of various fractions)
public Matrix (Row [] input)
{
	rowNum=input.length;
	rows=input;
}
/* This creates the identity matrix multiplies by the value of fractions.
 * The identity matrix is a matrix with where every location the row number= the column number
 * row value is 1. This is the same as a diagonal line from the top left to the bottom right.
 * This is only possible in square matrices, hence the dimension value is used for
 * both the number of rows and the number of columns.
 *  In this case, the value is whatever the value variable is equal to.
 */
public Matrix (Fraction value, int dimension)
{
	rowNum=dimension;
	rows=new Row [dimension];
	for ( int i=0;i<dimension;i++)
		rows[i]=new Row (i, value, dimension);
}
// Creates a new matrix with the given rows and columns (rowNum by columns matrix of 0/1 fractions)
public Matrix (int rowNumber, int columns)
{
	rowNum=rowNumber;
	for (int i=0;i<rowNumber;i++)
		rows [i]=new Row (columns);
}
// Creates a matrix given a 2d array of fractions (creating a input.length by input[0].length of various fractions)
public Matrix (Fraction [][] input)
{
	for (int x=0; x<input.length; x++);
	rowNum=input.length;
	rows = new Row [rowNum];
	for (int i=0;i<input.length;i++)
		{
		
		rows [i]=new Row (input[i]);
		}
}
public Matrix (int [][] input)
{
	Fraction [][] fractions=new Fraction [input.length][input[0].length];
	for (int i=0; i<input.length; i++)
	{
		for (int j=0;j<input[0].length; j++)
		{
			fractions[i][j]=new Fraction (input[i][j]);
		}
 
	}
	 Matrix output=new Matrix(fractions);
	 rowNum=output.rowNum;
	 rows=output.rows;
	return;
}
// Switches the order of 2 rows at the given index values. For example if input is 1 and 3, rows[1] is switched with rows[3]
public void switchRows(int swap1, int swap2)
{
	Row temp=this.rows[swap1];
	this.rows[swap1]=this.rows[swap2];
	this.rows[swap2]=temp;
}
/*
 * This reduces each 1 to leading 1 form.
 * LeadingOne form is every row dived by a scaler so that
 * its first number from the left (lowest index) column is 1.
 */
public int [] getLeadOnes()
{
	int [] leadOne= new int [rowNum];
	for (int i=0; i<rowNum; i++)
	{
		leadOne[i]=rows[i].reducedRow();
	}
	return leadOne;
}
/* The following 4 methods with the same name each put the matrix in row echelon form.
 * Row echelon form is row has the first non zero column from the left as zero
 * which is called the leading 1.
 * Each column only has 1 leading one
 * Also the leading 1 farthest to the left is at the top, going down in order
 * with the leading 1 farthest to the right at the bottom.
 * The difference between this methods is simply to handle different kinds of input.
 */
public int rowEchelonForm()
{
	int colReduced=0;
	int rank=rowNum;
	return rowEchelonForm(colReduced, rank);
}
public int rowEchelonForm(int [] leadOne)
{
	int colReduced=0;
	int rank=rowNum;
	return rowEchelonForm(colReduced, rank, leadOne);
}
public int rowEchelonForm(int colReduced, int rank)
{   //int rank=rowNum;
	int [] leadOne= getLeadOnes();
	sortMatrix(leadOne);
	int target=leadOne[colReduced];
	for (int i=colReduced+1;i+1<rowNum;i++)
	{
		if (target==leadOne[i])
		{
			rows[i]=rows[i].subtractRows(rows[target]);
			leadOne[i]=rows[i].leadLocation();
			if (leadOne[i]==rows[0].col)
			{
				rank=rank-1;
			}
			
		}
		sortMatrix(leadOne);
	}
	colReduced++;
	if (colReduced!=rows[0].col)
	return rowEchelonForm(colReduced, rank);
	else
	return rank;
	}
public int rowEchelonForm(int colReduced, int rank, int [] leadOne)
{   //int rank=rowNum;
	sortMatrix(leadOne);
	int target=leadOne[colReduced];
	for (int i=colReduced+1;i+1<rowNum;i++)
	{
		if (target==leadOne[i])
		{
			rows[i]=rows[i].subtractRows(rows[target]);
			leadOne[i]=rows[i].leadLocation();
			if (leadOne[i]==rows[0].col)
			{
				rank=rank-1;
			}
			
		}
		sortMatrix(leadOne);
	}
	colReduced++;
	if (colReduced!=rows[0].col)
	return rowEchelonForm(colReduced, rank);
	else
	return rank;
	}
// This method takes a method and sorts it based of the locations of the leading ones.
// The rows with leading ones at a lowest index value for columns(farthest to the left) 
// Are brought to the lowest index values for rows (farthest to the right).
public void sortMatrix(int [] leadOne)
{
	
	int temp;
	Row tempRow;
	for (int j=leadOne.length-1; j>0; j--)
	{
	for ( int i=0; i<j; i++)
	if (leadOne[i]>leadOne[i+1])
		{
		temp=leadOne[i];
		tempRow=rows[i];
		leadOne[i]=leadOne[i+1];
		rows[i]=rows[i+1];
		leadOne[i+1]=temp;
		rows[i+1]=tempRow;
		}
	}
}
/*
 * This method changes a matrix from row echelon form to reduced row echelon form.
 */
public boolean reduceRowEchelonMatrix ()
{
	int [] leadOnes=getLeadOnes();
	int rank=rowEchelonForm(leadOnes);
	//I is the row with the leading 1 being looked at.
	for (int i=rowNum-1; i>=0;i--)
	{		
		if (leadOnes[i]!=rows[0].col&& (i==(rowNum-1) || leadOnes[i]!=leadOnes[i+1]));
		{
			//Since the matrix is already in Row echelon form, any row below row[i] (higher index value than i is already clear
		for (int j=0;j<i; j++)
		{
				/* Constant is equal to the row being subtracted's value 
				 * at the location of the column being cleared.
				 * We are always clearing the column of the leading 1 we are currently looking at.
				 */
				Fraction constant=rows[j].fractions[leadOnes[i]];
				/*
				 * the row[i] has a leading one at leadOnes[i].
				 * The row[j] has a constant value stored as constant at leadOnes[i].
				 * Therefore subtracting row[j]-row[i]*constant will be same 
				 * subtracting a constant from the same constant at leadOnes[i].
				 * This will always equal zero.
				 */
				rows[j]=rows[j].subtractRows(rows[i].multiplyRows(constant));
		}
		}
	}
	return (rank==rowNum);
}
public void subtractMatrix(Matrix a)
{
	if (rowNum==a.rowNum && rows[0].col==a.rows[0].col)
	{
		for (int i=0; i<rowNum;i++)
		{
			rows[i].subtractRows(a.rows[i]);
		}
	}
	else
	{ System.out.println("Rows and columns of first matrix: ("+rowNum+","+rows[0].col+") of second ("+a.rowNum+","+a.rows[0].col+")");
		System.out.println("Can only subtract matrices of the same dimensions.");
	}
}
// This test if a given value is an eigenvalue of the original matrix.
public static boolean eigenValue(Matrix a, Fraction test)
{
a.subtractMatrix(new Matrix(test, a.rowNum));
return !a.reduceRowEchelonMatrix();
}
/*
public Row reducerRow()
{
int rowT=0;
int colT=0;
int notSwapped=rows.length-1;
while (rows[rowT].fractions[colT].getNumerator()==0)
{
	if (notSwapped!=rowT)
	{
this.switchRows(rowT, notSwapped);
notSwapped--;
	}
	else if (colT+1<this.rows[0].fractions.length)
	{
		colT++;
	}

	else
	{
		System.out.println("Can't reduce matrix of all 0's");
		return new Row();
	}
}

	Fraction a=rows[rowT].fractions[colT].reciprocal();
	Row target=new Row (rows.length+2);
	
    Row temp=rows[rowT].multiplyRows(a);
	for (int x=0;x<rows.length; x++)
   target.fractions[x]=temp.fractions[x];	
   target.fractions[rows.length]=new Fraction(rowT);
   target.fractions[rows.length+1]=new Fraction(colT);
	return target;

}
*/
//Originally the matrix was composed of 2d arrays of fractions directly, not arrays of rows
/*
// Every Matrix is defined by a 2d array of fractions. 
	//The length of each dimension stored as rows and columns respectively.
int rows;
int columns;
private Fraction [][] fractions;

Fraction [][] getFractions() {
	return fractions;
}

void setFractions(Fraction [][] fractions) {
	this.fractions = fractions;
}

// Creates a Matrix given an array of fractions
public Matrix (Fraction [][] input)
{
rows=input.length;
columns=input[0].length;
setFractions(input);

}


// Creates a matrix with a rows and b columns with default fractions (0,1)
 Matrix(int a, int b)
 {
	 rows=a;
	 columns=b;
	 fractions=new Fraction[rows][columns];
for (int i=0; i<rows; i++)
{
	for (int j=0; j<columns; j++)
	{
		fractions[i][j]=new Fraction();
	}
}
 }

// Multiplies the targeted row in the matrix by a rational number expressed as a fraction.
public void multiplyRow(int target, Fraction a)
{
	for (int i=0;i<columns; i++)
	{
		fractions[target][i]=Fraction.multiplyFraction(fractions[target][i],a);
	}
}
public void addRows(int target, int adder)
{
}
*/
}
