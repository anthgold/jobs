public class Jobs {
  private String mWorkPlace;
  private String mTitle;
  private String mDuties;
  private String mTimeSpent;

  public Jobs(String workPlace, String title, String duties, String timeSpent) {
    mWorkPlace = workPlace;
    mTitle = title;
    mDuties = duties;
    mTimeSpent  = timeSpent;
  }

  public String getWorkPlace() {
    return mWorkPlace;
  }
  public String getTitle() {
    return mTitle;
  }
  public String getDuties() {
    return mDuties;
  }
  public String getTime() {
    return mTimeSpent;
  }

}
