package org.monarchinitiative.phenocontrols.otree;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import org.monarchinitiative.phenocontrols.otree.base.BaseOntologyClassTreeView;
import org.monarchinitiative.phenocontrols.otree.simple.OntologyClassTreeCell;
import org.monarchinitiative.phenocontrols.otree.simple.OntologyClassTreeItem;
import org.monarchinitiative.phenol.ontology.data.MinimalOntology;
import org.monarchinitiative.phenol.ontology.data.Term;

import java.util.Optional;

public class SimpleOntologyClassTreeView extends BaseOntologyClassTreeView<SimpleOntologyClass> {

  @Override
  protected Callback<TreeView<SimpleOntologyClass>, TreeCell<SimpleOntologyClass>> prepareCellFactory() {
    return tw -> new OntologyClassTreeCell();
  }

  @Override
  protected void bind(MinimalOntology ontology) {
    if (ontology != null) {

      // bind
      Optional<Term> root = ontology.termForTermId(ontology.getRootTermId());
      if (root.isPresent()) {
        Term term = root.get();
        SimpleOntologyClass pf = new SimpleOntologyClass(term.id(), term.getName());
        setRoot(new OntologyClassTreeItem(ontology, pf));
      } else
        setRoot(null);
    }
  }

}
