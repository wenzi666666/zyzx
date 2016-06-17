package net.tfedu.zhl.cloud.resource.util;

import java.io.IOException;
import java.net.URLDecoder;

import org.springframework.util.SystemPropertyUtils;

import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.xxtea;

public class MD5BASE64Test {
	
	public static void main(String[] args) throws Exception {
		String  s= "vGbFi51DopXPM5iIFmyHXCNhEtsu9F4Uq6WnA2naPyshHbtZtlI%2FqjsvV7hT2JAM9jsm6pX6ZTBxIJqC9tARhcIaALXpb3buVKy4oFPmjHuuDfW2J609HhUhe21JfPDb";
		
		s = URLDecoder.decode(s, "utf-8");
		
		
		byte[] temp =  Base64.decode(s);
		
		temp =   xxtea.decrypt(temp, "9k8i78jug6hd93kjf84h".getBytes());
		
		System.out.println(new String(temp ));
	}

}
