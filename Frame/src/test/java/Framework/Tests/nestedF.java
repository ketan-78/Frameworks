package Framework.Tests;

import org.testng.annotations.Test;

import Framework.TestComponents.BaseTest;

public class nestedF extends BaseTest{
	@Test
	public void MethodSecond() 
	{
		nf.nestedFrame();
	}
}
