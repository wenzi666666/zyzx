package net.tfedu.zhl.helper.encryption;

import java.io.IOException;

import net.tfedu.zhl.fileservice.Base64;

/**
 * e备课的参数加密，
 * 
 * 第一次在下面的接口中使用：
 * /v1.0/resViewPage 
 * @author wangwr
 *
 */
public class EPrepareParamEncrypt {

	
	public static String encode(String str)
    {
        String htext = "";

        for (int i = 0; i < str.length(); i++)
        {
            htext = htext + (char)(str.charAt(i) + 10 - 1 * 2);
        }
        return Base64.encode(htext.getBytes());
    }

    public static String decode(String str) throws IOException
    {
    	str =  new String(Base64.decode(str));
    	
        String dtext ="";

        for (int i = 0; i < str.length(); i++)
        {
            dtext = dtext + (char)(str.charAt(i) - 10 + 1 * 2);
        }
        return dtext;
    }
    
    
    public static void main(String[] args) throws IOException {
		String s = "csls10";
		String se = encode(s);
		System.out.println(se); 
		System.out.println(decode(se));
	}
}
