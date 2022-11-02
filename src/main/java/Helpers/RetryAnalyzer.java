package Helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    Helpers helpers = new Helpers();
    //Counter to keep track of retry attempts
    int retryAttemptsCounter = 1;

    //The max limit to retry running of failed test cases
    //Set the value to the number of times we want to retry
    int maxRetryLimit = Integer.parseInt(helpers.readConfigBaseParameters("maxRetryLimit"));

    //Method to attempt retries for failure tests
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if(retryAttemptsCounter < maxRetryLimit){
                retryAttemptsCounter++;
                return true;
            }
        }
        return false;
    }

}
