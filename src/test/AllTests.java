package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPessoa.class, TestUsuario.class, TestPassageiro.class, TestCidade.class,
        TestModal.class, TestPontoDeVenda.class, TestPercurso.class, TestViagem.class,
        TestReserva.class, TestTicket.class })
public class AllTests {

}
