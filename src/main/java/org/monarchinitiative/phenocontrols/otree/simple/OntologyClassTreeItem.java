package org.monarchinitiative.phenocontrols.otree.simple;

import javafx.scene.control.TreeItem;
import org.monarchinitiative.phenocontrols.otree.SimpleOntologyClass;
import org.monarchinitiative.phenocontrols.otree.base.BaseOntologyClassTreeItem;
import org.monarchinitiative.phenol.ontology.data.MinimalOntology;
import org.monarchinitiative.phenol.ontology.data.Term;

public class OntologyClassTreeItem extends BaseOntologyClassTreeItem<SimpleOntologyClass> {

  public OntologyClassTreeItem(MinimalOntology ontology, SimpleOntologyClass feature) {
    super(ontology, feature);
  }

  @Override
  protected TreeItem<SimpleOntologyClass> treeItemForTerm(MinimalOntology ontology, Term term) {
    return new OntologyClassTreeItem(ontology, new SimpleOntologyClass(term.id(), term.getName()));
  }

}
