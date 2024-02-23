package org.monarchinitiative.phenocontrols.otree;

/**
 * An enum representing vetting status assigned by a curator.
 */
public enum VettingStatus {

  /**
   * The item is neither {@link #APPROVED} nor {@link #REJECTED}.
   */
  INDETERMINATE,

  /**
   * The curator approved the item.
   */
  APPROVED,

  /**
   * The curator rejected the entity.
   */
  REJECTED

}
