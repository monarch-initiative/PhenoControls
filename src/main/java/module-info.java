/**
 * PhenoControls provides JavaFX UI elements for applications that use Human Phenotype Ontology.
 */
module org.monarchinitiative.phenocontrols {
  requires org.monarchinitiative.phenol.core;

  requires javafx.base;
  requires javafx.controls;
  requires org.controlsfx.controls;

  requires org.slf4j;

  exports org.monarchinitiative.phenocontrols.otree;
}
