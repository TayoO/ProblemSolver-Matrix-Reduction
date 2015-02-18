package MatrixRowReducer;

public class RowReducerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] tester = {-1, 10, -5};
		Row testRow=new Row (tester);
		testRow.printRow();
		int [] test2 ={1,0,0};
		Row testSubtract= new Row (test2);
		testSubtract.printRow();
		Row result=testRow.subtractRows(testSubtract);
		result.printRow();
		/*
int [][] nums= {{2,2},{2,2}};
		Matrix subtractor= new Matrix(nums);
int [][] nums3= {{3,3},{3,3}};
Matrix original= new Matrix(nums3);
Fraction test=Fraction.addFraction(original.rows[0].fractions[0], subtractor.rows[0].fractions[0].negativeFraction());
System.out.println("Test fraction");
test.printFraction();
original.rows[0].subtractRows(subtractor.rows[0]);
original.rows[0].printRow();
original.subtractMatrix(subtractor);
original.printMatrix();
*/
int [] eigenTest={1,2,3,4,5,6,7,8};
Fraction [] input=new Fraction [8];
int [] [] values = new int [][]{
		{-1,10,-5},
		{0,4,0},
		{2,-4,6}};
Matrix testCase= new Matrix (values);
boolean [] eigen=new boolean [8];
for (int i=0; i<8;i++)
{
	//System.out.println("input index "+i);
	input[i]=new Fraction (eigenTest[i]);
	//System.out.println("eigen index "+i);
	eigen[i]=Matrix.eigenValue(testCase, input[i]);
	System.out.println(eigen[i]);
}
	}

}
