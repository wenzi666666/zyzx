package net.tfedu.zhl.cloud.resource.util;

import net.tfedu.zhl.fileservice.Base64;

public class MD5BASE64Test {
	
	public static void main(String[] args) throws Exception {
		
		
		String name = "566h55CG5ZGY";
		
		name = new String(Base64.decode(name));
		
		System.out.println("-name-"+name);
		
		
		
	}

}
