package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.ModalController;
import model.Modal;

public class TestModal {

  @Test
  public void cadastrarModal() {
    int id = 0;
    try {
      id =
          ModalController.cadastrarModal(new String[] {}, "test", "test", "test", "111", "test",
              "2000", "0", "0", "2016-01-11 00:00:00");
    } catch (Exception e) {
      fail();
    } finally {
      if (id > 0)
        ModalController.deletarModal(id);
    }
  }

  @Test
  public void listarModais() {
    int id =
        ModalController.cadastrarModal(new String[] {}, "test", "test", "test", "111", "test",
            "2000", "0", "0", "2016-01-11 00:00:00");
    ArrayList<Modal> l =
        ModalController.listarModais("test", "test", "test", "111", "test", "2000");
    ModalController.deletarModal(id);
    assertNotNull(l);
    assertEquals(l.size(), 1);
  }

  @Test
  public void alterarModal() {
    int id = 0;
    try {
      id =
          ModalController.cadastrarModal(new String[] {}, "test", "test", "test", "111", "test",
              "2000", "0", "0", "2016-01-11 00:00:00");
      ModalController.alterarModal(id, new String[] {}, "asdf", "asdf", "asdf", "222", "asdf",
          "2002", "1", "1", "2016-01-12 01:01:01");
    } catch (Exception e) {
      fail();
    } finally {
      if (id > 0)
        ModalController.deletarModal(id);
    }
  }

  @Test
  public void deletarModal() {
    try {
      int id =
          ModalController.cadastrarModal(new String[] {}, "test", "test", "test", "111", "test",
              "2000", "0", "0", "2016-01-11 00:00:00");
      ModalController.deletarModal(id);
    } catch (Exception e) {
      fail();
    }
  }

}
