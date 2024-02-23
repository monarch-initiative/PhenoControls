package org.monarchinitiative.phenocontrols.otree.base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.monarchinitiative.phenol.ontology.data.MinimalOntology;
import org.monarchinitiative.phenol.ontology.data.Term;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseOntologyClassTreeItem<T extends BaseOntologyClass> extends TreeItem<T> {

  private final MinimalOntology ontology;
  private boolean childrenWereAdded;

  protected BaseOntologyClassTreeItem(MinimalOntology ontology, T value) {
    super(value);
    this.ontology = ontology;
  }

  @Override
  public boolean isLeaf() {
    return ontology.graph().isLeaf(getValue().getTermId());
  }

  @Override
  public ObservableList<TreeItem<T>> getChildren() {
    if (!childrenWereAdded) {
      ObservableList<TreeItem<T>> children = ontology.graph().getChildrenStream(getValue().getTermId())
        .map(ontology::termForTermId)
        .flatMap(Optional::stream)
        .filter(Objects::nonNull)
        .sorted(Comparator.comparing(Term::getName))
        .map(term -> treeItemForTerm(ontology, term))
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
      super.getChildren().setAll(children);
      childrenWereAdded = true;
    }

    return super.getChildren();
  }

  protected abstract TreeItem<T> treeItemForTerm(MinimalOntology ontology, Term term);
}
