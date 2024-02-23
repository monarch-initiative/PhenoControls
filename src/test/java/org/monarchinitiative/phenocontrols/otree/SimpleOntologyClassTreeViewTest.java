package org.monarchinitiative.phenocontrols.otree;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.monarchinitiative.phenocontrols.BaseControllerTest;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeUnit;

@Disabled("To be run manually when needed.")
@ExtendWith(ApplicationExtension.class)
public class SimpleOntologyClassTreeViewTest extends BaseControllerTest {

  private SimpleOntologyClassTreeView treeView;

  @Start
  public void start(Stage stage) throws Exception {
    treeView = new SimpleOntologyClassTreeView();

    Scene scene = new Scene(treeView, 800, 600);
    stage.setScene(scene);
    stage.setTitle("Onto tree view test");
    stage.initStyle(StageStyle.DECORATED);
    stage.show();
  }

  @Test
  public void testOntoTreeView(FxRobot robot) {
    robot.sleep(3, TimeUnit.SECONDS);

    Platform.runLater(() -> treeView.setOntology(HPO));

    robot.sleep(20, TimeUnit.SECONDS);
  }
}
