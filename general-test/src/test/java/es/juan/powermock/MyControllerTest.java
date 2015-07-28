package es.juan.powermock;

import java.security.InvalidParameterException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MyController.class)
public class MyControllerTest {

	@Test(expected=InvalidParameterException.class)
    public void test() throws Exception {
            whenNew(CoolValidator.class).withAnyArguments().thenThrow(new InvalidParameterException("error message"));

            MyController controller = new MyController();
            controller.doSomeStuff("test"); // y is the method doing "new MyClass()"
           
	}
}
