package ita.Q2_P2_CES29;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

public class VulnerableClassTest {

	@Test
	public void InvalidFilenameTest() {
		VulnerableClass v = new VulnerableClass();
		String filename = "{  nocivo;  }";
		
		try {
			v.vulnerableMethod(filename);
			fail();
		}
		catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void InvalidScannerOpr() {
		systemInMock.provideLines("OPERAÇÃO INVÁLIDA");
		VulnerableClass v = new VulnerableClass();
		String filename = "permitido";
		
		try {
			try {
				v.vulnerableMethod(filename);
				fail();
			}
			catch(IllegalArgumentException e) {
				//e.printStackTrace();
			}
		}
		catch(IOException f) {
			fail();
		}
	}
	
	@Test
	public void OprReadUnexistentFile() {
		systemInMock.provideLines("R");
		VulnerableClass v = new VulnerableClass();
		String filename = "read";
		
		try {
			v.vulnerableMethod(filename);
			fail();
		}
		catch(IOException f) {
			//f.printStackTrace();
		}
	}

	@Test
	public void OprWriteFile() {
		systemInMock.provideLines("W", "escrevi algo");
		VulnerableClass v = new VulnerableClass();
		String filename = "write";
		
		try {
			v.vulnerableMethod(filename);
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}
	
}
