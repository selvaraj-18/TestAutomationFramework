package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	//private static final int MAX_NUM_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_NUM_OF_ATTEMPTS"));
	
	private static final int MAX_NUM_OF_ATTEMPTS = JSONUtility.readJson(Env.DEV).getMAX_NUM_OF_ATTEMPTS();

	private static int CURRENT_ATTEMPTS = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (CURRENT_ATTEMPTS <= MAX_NUM_OF_ATTEMPTS) {
			CURRENT_ATTEMPTS++;
			return true;

		}

		return false;
	}

}
