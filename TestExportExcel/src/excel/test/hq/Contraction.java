package excel.test.hq;

public class Contraction {
	public static String tempString = "acdesfghsadajfkqsfab������12121212";

	/**
	  * �����ֽ���Ŀռ�ȥ����ѹ��
	  * @param args
	  */
	public static void main(String[] args) {
	  System.out.println("ѹ��ǰ�ַ������ݣ�"+tempString);
	  System.out.println("ѹ��ǰ�ַ�����С:"+tempString.length());
	  
	  String resultString = compactString(tempString);
	  System.out.println("ѹ�����ַ������ݣ�"+resultString);
	  System.out.println("ѹ�����ַ�����С��"+resultString.length());
	  
	  String convertString = decompressionString(resultString);
	  System.out.println("��ѹ���ַ������ݣ�"+convertString);
	  System.out.println("��ѹ���ַ�����С��"+convertString.length());
	}

	/**
	  * ͨ���ӿ�compactString()��ѹ����ʽ���н�ѹ
	  * @param tempString
	  * @return
	  */
	public static String decompressionString(String tempString){
	  char[] tempBytes = tempString.toCharArray();
	  StringBuffer sb = new StringBuffer();
	  for (int i = 0; i < tempBytes.length; i++) {
	   char c = tempBytes[i];
	   char firstCharacter = (char) (c >>> 8);
	   char secondCharacter = (char) ((byte)c);
	   sb.append(firstCharacter);
	   if(secondCharacter != 0)
	    sb.append(secondCharacter);
	  }
	  return sb.toString();
	}


	/**
	  * ����Ҫ����ѹ�����ַ�������ѹ��������һ����Խ�С���ַ���
	  * @param tempString
	  * @return
	  */
	public static String compactString(String tempString) {
	  StringBuffer sb = new StringBuffer();
	  byte[] tempBytes = tempString.getBytes();
	  for (int i = 0; i < tempBytes.length; i+=2) {
	   char firstCharacter = (char)tempBytes[i];
	   char secondCharacter = 0;
	   if(i+1<tempBytes.length)
	    secondCharacter = (char)tempBytes[i+1];
	   firstCharacter <<= 8;
	   sb.append((char)(firstCharacter+secondCharacter));
	  }
	  return sb.toString();
	}
}
