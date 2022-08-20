package com.coronavirus.tracker.models;

public class Location {

  private String state;

  private String country;

  private int latestTotalReport;

  private int diffFromPreviousCases;

  public int getDiffFromPreviousCases() {
    return diffFromPreviousCases;
  }

  public void setDiffFromPreviousCases(int diffFromPreviousCases) {
    this.diffFromPreviousCases = diffFromPreviousCases;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getLatestTotalReport() {
    return latestTotalReport;
  }

  public void setLatestTotalReport(int latestTotalReport) {
    this.latestTotalReport = latestTotalReport;
  }

  @Override
  public String toString() {
    return "Location{" +
        "state='" + state + '\'' +
        ", country='" + country + '\'' +
        ", latestTotalReport=" + latestTotalReport +
        '}';
  }
}
