package HYanwork.TheFirst.Animals;

abstract class Animal {
    private int years;
    private String gender;
    private String kind;
    abstract void speak();
    abstract void eat();

    public int getYears() {
        return years;
    }

    public String getGender() {
        return gender;
    }

    public String getKind() {
        return kind;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setYears(int years) {
        this.years = years;
    }
}
