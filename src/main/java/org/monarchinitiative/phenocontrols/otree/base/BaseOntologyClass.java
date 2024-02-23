package org.monarchinitiative.phenocontrols.otree.base;

import org.monarchinitiative.phenol.ontology.data.TermId;

public abstract class BaseOntologyClass {

  private final TermId termId;
  private final String label;

  protected BaseOntologyClass(TermId termId, String label) {
    this.termId = termId;
    this.label = label;
  }

  public TermId getTermId() {
    return termId;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public String toString() {
    return "BaseOntologyClass{" +
      "id=" + termId.getValue() +
      ", label=" + label +
      '}';
  }
}
