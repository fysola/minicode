/**
 * @author: create by Young
 * @version: v1.0
 * @description: PACKAGE_NAME
 * @date:2019/4/09 08:52
 */

//mock properties
enum PrinterType { STAGE1, STAGE2 }

interface Printer {

	void print(int num);

	//only compile for jdk8+
	default boolean divisableByP(int n,int p) {
		return n % p == 0;
	}

	default boolean containsQ(int n,int q) {
		int bit;
		do {
			if( (bit = n % 10) == q ) return true;
			n /= 10;
		} while(n != 0);
		return false;
	}
}

class Stage1Printer implements Printer {
	/**
	 * print 'Fizz' instead of the number multiples of three
	 * print 'Buzz' instead of the number multiples of five
	 * print 'FizzBuzz' instead of the number multiples of both three and five
	 * @param num
	 */
	public void print(int num) {
		for (int i = 1; i <= num; i++) {
			if(divisableByP(i,3) && !divisableByP(i,5)) System.out.println("Fizz");
			else if(divisableByP(i,5) && !divisableByP(i,3)) System.out.println("Buzz");
			else if(divisableByP(i,3) && divisableByP(i,5)) System.out.println("FizzBuzz");
			else System.out.println(i);
		}
	}
}

class Stage2Printer implements Printer {
	/**
	 * print 'Fizz' if the number is divisible by 3 or if it contains a 3
	 * print 'Buzz' if the number is divisible by 5 or if it contains a 5
	 * print 'FizzBuzz' if the number is divisible by 3 or 3 or if it contains a 3 or 5
	 * @param num
	 */
	public void print(int num) {
		for (int i = 1; i <= num; i++) {
			if( (divisableByP(i,3)|| containsQ(i,3) )
					&& (divisableByP(i,5)|| containsQ(i,5) )) System.out.println("FizzBuzz");
			else if(divisableByP(i,3) || containsQ(i,3)) System.out.println("Fizz");
			else if(divisableByP(i,5) || divisableByP(i,5)) System.out.println("Buzz");
			else System.out.println(i);
		}
	}
}

class PrinterFactory {
	public static Printer getInstance(PrinterType type) {
		return type == PrinterType.STAGE1 ? new Stage1Printer() : new Stage2Printer();
	}
}

public class App {
	public static void main(String args[]) {
		//mock to get beans from container which should be configured
		Printer printer = PrinterFactory.getInstance(PrinterType.STAGE1);
		printer.print(100);
	}
}
