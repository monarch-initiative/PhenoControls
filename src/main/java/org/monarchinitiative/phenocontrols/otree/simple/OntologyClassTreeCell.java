package org.monarchinitiative.phenocontrols.otree.simple;

import javafx.scene.control.TreeCell;
import org.monarchinitiative.phenocontrols.otree.SimpleOntologyClass;

public class OntologyClassTreeCell extends TreeCell<SimpleOntologyClass> {

  @Override
  protected void updateItem(SimpleOntologyClass item, boolean empty) {
    super.updateItem(item, empty);

    if (empty || item == null) {
      setText(null);
    } else {
      setText(getItem().getLabel());
    }
  }
}
