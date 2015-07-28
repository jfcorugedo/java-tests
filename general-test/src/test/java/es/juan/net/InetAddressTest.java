package es.juan.net;

import java.net.InetAddress;

import org.junit.Test;

public class InetAddressTest {

	@Test
	public void test() throws Exception{
		InetAddress address = InetAddress.getByAddress(new byte[]{127,0,0,1});
		System.out.println(address.getHostName());
		System.out.println(address.getAddress());
		System.out.println(address.isLoopbackAddress());
	}
}
