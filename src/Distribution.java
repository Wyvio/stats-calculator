//import java.util.Scanner;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Distribution {
	// Constants
	public static final NormalDistribution NULL_DISTRIBUTION = new NormalDistribution();

	// Set-up variables
	double sampleMean;
	double sampleStdev;
	int sampleSize;
	double significanceLevel;

	// State the hypotheses
	double nullHypothesis;
	int altHypothesis; // negative means testing <, 0 =, positive >

	// Used for testing
	private double z;
	private double pvalue;
	
	// Final conclusion
	String conclusion;

	public Distribution() {
		// Null constructor
	}

	// Solve and find conclusion
	public void solve() {
		try {
			// Calculate observed value of test statistic; z
			z = calculateTestStatistic();

			// Calculate p-value
			pvalue = calculatePValue();

			// Final conclusion
			processConclusion();
		} catch (Exception e) {
			System.err.println("cannot solve; did not getData");
		}
	}

	// Retrieves necessary data using passed in Scanner
	//	public void getData(Scanner console) {
	//		// Take in values
	//		System.out.print("What is the sample's mean? ");
	//		sampleMean = console.nextDouble();
	//		System.out.print("What is the sample's standard deviation? ");
	//		sampleStdev = console.nextDouble();
	//		System.out.print("What is the sample's size? ");
	//		sampleSize = console.nextInt();
	//		System.out.print("What is the test's significance level? ");
	//		significanceLevel = console.nextDouble();
	//
	//		System.out.print("What is the null hypothesis (population mean = ?) ? ");
	//		nullHypothesis = console.nextDouble();
	//		System.out.print("What is the alternative hypothesis (negative means testing <, positive >) ? ");
	//		altHypothesis = console.nextInt();
	//
	//	}

	// Calculates test statistic z
	private double calculateTestStatistic() throws ArithmeticException {
		// Calculate observed value of test statistic; z
		double z = (sampleMean - nullHypothesis) / (sampleStdev / Math.sqrt(sampleSize));

		// Check if the value exists
		if (String.valueOf(z).equals("NaN")) {
			throw new ArithmeticException();
		}
		return z;
	}

	// Calculates p-value
	private double calculatePValue() {
		if (altHypothesis < 0) {
			pvalue = NULL_DISTRIBUTION.cumulativeProbability(z);
		} else if (altHypothesis > 0) {
			pvalue = 1 - NULL_DISTRIBUTION.cumulativeProbability(z);
		}
		return pvalue;
	}

	// Saves conclusion in field
	public void processConclusion() {
		conclusion = "Final conclusion: ";
		if (pvalue <= significanceLevel) {
			conclusion += "Reject the null hypothesis";
		} else {
			conclusion += "Accept the null hypothesis";
		}
	}

	public String getConclusion() {
		return conclusion;
	}

	public double getSampleMean() {
		return sampleMean;
	}

	public void setSampleMean(double sampleMean) {
		this.sampleMean = sampleMean;
	}

	public double getSampleStdev() {
		return sampleStdev;
	}

	public void setSampleStdev(double sampleStdev) {
		this.sampleStdev = sampleStdev;
	}

	public int getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}

	public double getSignificanceLevel() {
		return significanceLevel;
	}

	public void setSignificanceLevel(double significanceLevel) {
		this.significanceLevel = significanceLevel;
	}

	public double getNullHypothesis() {
		return nullHypothesis;
	}

	public void setNullHypothesis(double nullHypothesis) {
		this.nullHypothesis = nullHypothesis;
	}

	public int getAltHypothesis() {
		return altHypothesis;
	}

	public void setAltHypothesis(int altHypothesis) {
		this.altHypothesis = altHypothesis;
	}

	public double getZ() {
		return z;
	}

	public double getPvalue() {
		return pvalue;
	}

	// Prints out String of all its data
	public String toString() {
		/* @formatter:off */
		String symbol = altHypothesis < 0 
				? "<" 
				: ">";
		return "Distribution ~ N(" + sampleMean + ", " + sampleStdev + "^2)"
				+ "\nSample size n = " + sampleSize
				+ "\nSignificance level a = " + significanceLevel
				+ "\nNull hypothesis H0: mean = " + nullHypothesis
				+ "\nAlternate hypothesis H1: mean " + symbol + " " + nullHypothesis;
		/* @formatter:on */
	}
}
