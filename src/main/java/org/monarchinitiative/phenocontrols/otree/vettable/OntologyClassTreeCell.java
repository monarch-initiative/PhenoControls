package org.monarchinitiative.phenocontrols.otree.vettable;

import javafx.scene.control.TreeCell;
import org.monarchinitiative.phenocontrols.otree.VettableOntologyClass;

public class OntologyClassTreeCell extends TreeCell<VettableOntologyClass> {

  private final SelectionStatusButton selectionButton = new SelectionStatusButton();

  private VettableOntologyClass previous;

  @Override
  protected void updateItem(VettableOntologyClass item, boolean empty) {
    super.updateItem(item, empty);

    if (empty || item == null) {
      setGraphic(null);
      setText(null);
      if (previous != null)
        previous.selectionStatusProperty().unbind();
      previous = null;
    } else {
      setGraphic(selectionButton);
      setText(item.getLabel());

      selectionButton.setVettingStatus(item.getSelectionStatus());
      item.selectionStatusProperty().bind(selectionButton.vettingStatusBinding());
      previous = item;
    }
  }

}

