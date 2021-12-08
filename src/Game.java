import controller.DungeonControllerImpl;
import model.dungeon.DungeonModel;
import model.dungeon.Model;
import view.DungeonForm;
import view.DungeonView;
import view.SpecsForm;

public class Game {

  public static void main(String[] args) {
    // Assemble all the pieces of the MVC
    DungeonForm v = new SpecsForm("MVC Game");
    DungeonControllerImpl c = new DungeonControllerImpl(v);
//    c.initController();
  }
}
