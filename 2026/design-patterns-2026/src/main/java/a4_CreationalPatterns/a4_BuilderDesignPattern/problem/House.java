package a4_CreationalPatterns.a4_BuilderDesignPattern.problem;

public class House {
  private String foundation;
  private String structure;
  private String roof;
  private boolean hasGarage;
  private boolean hasSwimmingPool;
  private boolean hasGarden;

  // constructor with all parameters
  public House(
      String foundation,
      String structure,
      String roof,
      boolean hasGarage,
      boolean hasSwimmingPool,
      boolean hasGarden) {
    this.foundation = foundation;
    this.structure = structure;
    this.roof = roof;
    this.hasGarage = hasGarage;
    this.hasSwimmingPool = false;
    this.hasGarden = hasGarden;
  }

  @Override
  public String toString() {
    return "House{"
        + "foundation='"
        + foundation
        + '\''
        + ", structure='"
        + structure
        + '\''
        + ", roof='"
        + roof
        + '\''
        + ", hasGarage="
        + hasGarage
        + ", hasSwimmingPool="
        + hasSwimmingPool
        + ", hasGarden="
        + hasGarden
        + '}';
  }
}
