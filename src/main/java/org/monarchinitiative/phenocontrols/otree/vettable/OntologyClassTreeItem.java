package org.monarchinitiative.phenocontrols.otree.vettable;


import javafx.scene.control.TreeItem;
import org.monarchinitiative.phenocontrols.otree.VettableOntologyClass;
import org.monarchinitiative.phenocontrols.otree.base.BaseOntologyClassTreeItem;
import org.monarchinitiative.phenol.ontology.data.MinimalOntology;
import org.monarchinitiative.phenol.ontology.data.Term;

import java.util.Objects;

public class OntologyClassTreeItem extends BaseOntologyClassTreeItem<VettableOntologyClass> {
  private final OntologyClassTreeItemManager treeItemManager;

  public OntologyClassTreeItem(VettableOntologyClass item, MinimalOntology ontology, OntologyClassTreeItemManager treeItemManager) {
    super(ontology, item);
    this.treeItemManager = Objects.requireNonNull(treeItemManager);
  }

  @Override
  protected TreeItem<VettableOntologyClass> treeItemForTerm(MinimalOntology ontology, Term term) {
    return new OntologyClassTreeItem(treeItemManager.getOntologyClassForTerm(term), ontology, treeItemManager);
  }

}
