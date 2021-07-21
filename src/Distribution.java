import java.util.Scanner;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Distribution {

	// Set-up variables
	double sampleMean;
	double sampleStdev;
	int sampleSize;
	double significanceLevel;

	// State the hypotheses
	double nullHypothesis;
	int altHypothesis; // negative means testing <, 0 =, positive >

	// Used for testing
	double z;
	NormalDistribution nullDist;
	double pvalue;

	public Distribution() {
		// Initialize some variables
		nullDist = new NormalDistribution();
	}

	// Solve and find conclusion
	public void solve() {

		try {
			// Calculate observed value of test statistic; z
			z = calculateTestStatistic();
			System.out.println("The observed value of the test statistic is " + z);

			// Calculate p-value
			pvalue = calculatePValue(nullDist);
			System.out.println("The p-value is " + pvalue);

			// Final conclusion
			processConclusion();
		} catch (Exception e) {
			System.err.println("cannot solve; did not getData");
		}
	}

	// Retrieves necessary data using passed in Scanner
	public void getData(Scanner console) {
		// Take in values
		System.out.print("What is the sample's mean? ");
		sampleMean = console.nextDouble();
		System.out.print("What is the sample's standard deviation? ");
		sampleStdev = console.nextDouble();
		System.out.print("What is the sample's size? ");
		sampleSize = console.nextInt();
		System.out.print("What is the test's significance level? ");
		significanceLevel = console.nextDouble();

		System.out.print("What is the null hypothesis (population mean = ?) ? ");
		nullHypothesis = console.nextDouble();
		System.out.print("What is the alternative hypothesis (negative means testing <, positive >) ? ");
		altHypothesis = console.nextInt();

	}

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
	private double calculatePValue(NormalDistribution nullDist) {
		if (altHypothesis < 0) {
			pvalue = nullDist.cumulativeProbability(z);
		} else if (altHypothesis > 0) {
			pvalue = 1 - nullDist.cumulativeProbability(z);
		}
		return pvalue;
	}

	// Prints out conclusion in console
	public void processConclusion() {
		System.out.print("Final conclusion: ");
		if (pvalue <= significanceLevel) {
			System.out.println("Reject the null hypothesis");
		} else {
			System.out.println("Accept the null hypothesis");
		}
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

	public void setZ(double z) {
		this.z = z;
	}

	public NormalDistribution getNullDist() {
		return nullDist;
	}

	public void setNullDist(NormalDistribution nullDist) {
		this.nullDist = nullDist;
	}

	public double getPvalue() {
		return pvalue;
	}

	public void setPvalue(double pvalue) {
		this.pvalue = pvalue;
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
				+ "\nAlternate hypothesis H1: mean " + symbol + " " + altHypothesis;
		/* @formatter:on */
	}
}
