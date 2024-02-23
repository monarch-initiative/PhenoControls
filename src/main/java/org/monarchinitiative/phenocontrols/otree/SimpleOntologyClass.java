package org.monarchinitiative.phenocontrols.otree;

import org.monarchinitiative.phenocontrols.otree.base.BaseOntologyClass;
import org.monarchinitiative.phenol.ontology.data.TermId;

public class SimpleOntologyClass extends BaseOntologyClass {

  public SimpleOntologyClass(TermId termId, String label) {
    super(termId, label);
  }

  @Override
  public String toString() {
    return "SimpleOntologyClass{" +
      "id=" + getTermId().getValue() +
      ", label=" + getLabel() +
      '}';
  }
}
