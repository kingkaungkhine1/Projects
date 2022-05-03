package dvd.model;

public class DVD {
    String titleString = "";
    String releaseDate = "";
    String MPAA = "";
    String dirName = "";
    String stuName = "";
    String userNote = "";

    public DVD(String title, String reDate, String rating, String dir, String stu, String note)
    {
        this.titleString = title;
        this.releaseDate = reDate;
        this.MPAA = rating;
        this.dirName = dir;
        this.stuName = stu;
        this.userNote = note;
    }

    public String getTitleString() {
        return this.titleString;
    }

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMPAA() {
        return this.MPAA;
    }

    public void setMPAA(String MPAA) {
        this.MPAA = MPAA;
    }

    public String getDirName() {
        return this.dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getUserNote() {
        return this.userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

}
