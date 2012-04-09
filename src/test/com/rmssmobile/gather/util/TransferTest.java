package com.rmssmobile.gather.util;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

public class TransferTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetChars() throws UnsupportedEncodingException {
		String[] test = {"12345678", "zcvcvbbnhjtyue"};		
		String[] result = new String[2];
		for(int i = 0; i < test.length; i++) {
			result[i] = Transfer.getMD5(test[i].getBytes("gbk"));
		}
		String[] md5 = {"25d55ad283aa400af464c76d713c07ad", "af1a9a1c47c195dd6b714bc3a7b2d704"};
		assertArrayEquals("MD5 wrong", md5, result);
	}
}
