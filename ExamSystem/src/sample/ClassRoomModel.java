package sample;

public class ClassRoomModel {
    private int uniqueID;
    private String name;
    private int level;   //Year: 7, 8 , 9
    private String division;    //A, B, C

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "ClassRoomModel{" +
                "uniqueID=" + uniqueID +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", division='" + division + '\'' +
                '}';
    }

}
