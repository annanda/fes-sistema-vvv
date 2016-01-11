package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestPessoa.class,
	TestUsuario.class,
	TestCidade.class
})
public class AllTests {

}
