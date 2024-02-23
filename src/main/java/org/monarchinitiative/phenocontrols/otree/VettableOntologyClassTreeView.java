package org.monarchinitiative.phenocontrols.otree;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.MapProperty;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import org.monarchinitiative.phenocontrols.otree.base.BaseOntologyClassTreeView;
import org.monarchinitiative.phenocontrols.otree.vettable.OntologyClassTreeCell;
import org.monarchinitiative.phenocontrols.otree.vettable.OntologyClassTreeItem;
import org.monarchinitiative.phenocontrols.otree.vettable.OntologyClassTreeItemManager;
import org.monarchinitiative.phenol.ontology.data.MinimalOntology;
import org.monarchinitiative.phenol.ontology.data.Term;
import org.monarchinitiative.phenol.ontology.data.TermId;

import java.util.Optional;


public class VettableOntologyClassTreeView extends BaseOntologyClassTreeView<VettableOntologyClass> implements Observable {

  private final OntologyClassTreeItemManager phenotypeFeatureTreeItemManager = new OntologyClassTreeItemManager();

  public VettableOntologyClassTreeView() {
//    noinspection ConstantConditions
    getStylesheets().add(OntologyClassTreeItem.class.getResource("onto-tree-view.css").toExternalForm());
  }

  @Override
  protected Callback<TreeView<VettableOntologyClass>, TreeCell<VettableOntologyClass>> prepareCellFactory() {
    return tw -> new OntologyClassTreeCell();
  }

  @Override
  protected void bind(MinimalOntology ontology) {
    if (ontology != null) {
      // bind
      Optional<Term> root = ontology.termForTermId(ontology.getRootTermId());
      if (root.isPresent()) {
        setRoot(new OntologyClassTreeItem(phenotypeFeatureTreeItemManager.getOntologyClassForTerm(root.get()), ontology, phenotypeFeatureTreeItemManager));
      } else
        setRoot(null);
    }
  }

  @Override
  public void addListener(InvalidationListener listener) {
    phenotypeFeatureTreeItemManager.addListener(listener);
  }

  @Override
  public void removeListener(InvalidationListener listener) {
    phenotypeFeatureTreeItemManager.removeListener(listener);
  }

  public MapProperty<TermId, VettableOntologyClass> ontoItemsProperty() {
    return phenotypeFeatureTreeItemManager.ontologyClassesProperty();
  }

}
