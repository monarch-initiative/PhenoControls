package org.monarchinitiative.phenocontrols.otree.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import org.monarchinitiative.phenol.ontology.data.Identified;
import org.monarchinitiative.phenol.ontology.data.MinimalOntology;
import org.monarchinitiative.phenol.ontology.data.TermId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.Stack;

public abstract class BaseOntologyClassTreeView<T extends BaseOntologyClass> extends TreeView<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(BaseOntologyClassTreeView.class);

  protected final ObjectProperty<MinimalOntology> ontology = new SimpleObjectProperty<>();

  protected BaseOntologyClassTreeView() {
    setShowRoot(false);
    getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    disableProperty().bind(ontology.isNull());
    ontology.addListener((obs, old, novel) -> {
      setRoot(null);
      bind(novel);
    });
    setCellFactory(prepareCellFactory());
  }

  protected abstract Callback<TreeView<T>, TreeCell<T>> prepareCellFactory();

  protected abstract void bind(MinimalOntology ontology);

  public MinimalOntology getOntology() {
    return ontology.get();
  }

  public void setOntology(MinimalOntology ontology) {
    this.ontology.set(ontology);
  }

  public ObjectProperty<MinimalOntology> ontologyProperty() {
    return ontology;
  }

  public void scrollTo(Identified identified) {
    MinimalOntology ontology = this.ontology.get();
    if (ontology == null)
      // shouldn't happen but let's be 100% sure
      return;
    if (ontology.graph().existsPath(identified.id(), ontology.getRootTermId())) {
      // find root -> term path through the tree
      Stack<TermId> stack = constructPath(ontology, identified.id());

      // expand tree nodes in top -> down direction
      List<TreeItem<T>> children = getRoot().getChildren();
      stack.pop(); // get rid of 'All' node which is hidden
      TreeItem<T> target = getRoot();
      while (!stack.empty()) {
        TermId current = stack.pop();
        for (TreeItem<T> child : children) {
          if (child.getValue().getTermId().equals(current)) {
            child.setExpanded(true);
            target = child;
            children = child.getChildren();
            break;
          }
        }
      }
      requestFocus();
      getSelectionModel().select(target);
      scrollTo(getSelectionModel().getSelectedIndex() - 5);
    } else {
      LOGGER.warn("Unable to find the path from {} to {}", ontology.getRootTermId(), identified.id());
    }
  }

  private static Stack<TermId> constructPath(MinimalOntology ontology, TermId termId) {
    Stack<TermId> path = new Stack<>();
    path.add(termId);

    Set<TermId> parents = ontology.graph().getParents(termId);
    while (!parents.isEmpty()) {
      TermId parent = parents.iterator().next();
      ontology.termForTermId(parent)
        .map(Identified::id)
        .ifPresent(path::add);
      parents = ontology.graph().getParents(parent);
    }
    return path;
  }
}
