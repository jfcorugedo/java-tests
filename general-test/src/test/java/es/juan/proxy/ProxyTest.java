package es.juan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ProxyTest {

	@Test
	public void proxy() throws Throwable{
		
		InvocationHandler handler = new MyInvocationHandler(new MyClass());
		MyInterface f = (MyInterface) Proxy.newProxyInstance(MyClass.class.getClassLoader(),
                new Class[] { MyInterface.class },
                handler);
		
		int result = f.test();
		
		assertThat(result).isEqualTo(20);
	}
}

class MyInvocationHandler implements InvocationHandler {

	private MyInterface wrappedInstance;
	public MyInvocationHandler(MyInterface object) {
		this.wrappedInstance = object;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(method.getName().equals("test")){
			return 20;
		} else {
			return method.invoke(this.wrappedInstance, args);
		}
	}
}
