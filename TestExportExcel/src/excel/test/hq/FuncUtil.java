package excel.test.hq;

public class FuncUtil {
	/**
	 * ��MAC��ַת��Ϊ������<br>
	 *
	 */
	public static long convertMacToLong(String mac, String separator) {
		String[] parts = mac.split(separator);
		if (parts.length != 6) {
			return 0L;
		}

		long macInLong = 0L;
		for (String part : parts) {
			try {
				int m = Integer.parseInt(part, 16);
				if ((m < 0) || (m > 255)) {
					return 0L;
				}
				macInLong = macInLong << 8 | m;
			} catch (NumberFormatException nfe) {
				return 0L;
			}
		}

		return macInLong;
	}

	/**
	 * ������ת��Ϊ16��λ������
	 * 
	 * @param macPart
	 * @return
	 */
	private static String long2Hex4Mac(long macPart, boolean upcase) {
		if (macPart < 0L) {
			throw new IllegalArgumentException("Wrong macPart: " + macPart);
		}
		String macHex = upcase ? Long.toHexString(macPart).toUpperCase() : Long.toHexString(macPart).toLowerCase();
		if (macHex.length() == 1) {
			return "0" + macHex;
		} else {
			return macHex;
		}
	}

	/**
	 * ������ת��ΪMAC��ַ<br>
	 * ע��MAC��ַ��ʽΪ���ŷָ���ĸ��д�ĸ�ʽ<br>
	 *
	 */
	public static String convertLongToMac(long longMac, String separator, boolean upcase) {
		if (longMac < 0L) {
			throw new IllegalArgumentException("Wrong mac in long: " + longMac);
		}
		return long2Hex4Mac(longMac >> 40 & 0xFF, upcase) + separator + long2Hex4Mac(longMac >> 32 & 0xFF, upcase)
				+ separator + long2Hex4Mac(longMac >> 24 & 0xFF, upcase) + separator
				+ long2Hex4Mac(longMac >> 16 & 0xFF, upcase) + separator + long2Hex4Mac(longMac >> 8 & 0xFF, upcase)
				+ separator + long2Hex4Mac(longMac & 0xFF, upcase);
	}
	
	/**
	 * ��ip��ַת��Ϊ������
	 *
	 */
	public static long convertHostIpToLong(String hostIp) {
		if (null == hostIp) {
			throw new NullPointerException("Host IP can not be null");
		}

		String[] parts = hostIp.split("\\x2e");
		if (parts.length != 4) {
			return 0L;
		}

		long ip = 0L;
		for (String part : parts) {
			try {
				int p = Integer.parseInt(part);
				if ((p < 0) || (p > 255)) {
					return 0L;
				}
				ip = ip << 8 | p;
			} catch (NumberFormatException nfe) {
				return 0L;
			}
		}

		return ip;
	}
}
