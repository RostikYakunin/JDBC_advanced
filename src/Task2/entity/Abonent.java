package Task2.entity;

public class Abonent {
    private int id;
    private String lastName;
    private int telephone;

    public Abonent(int id, String lastName, int telephone) {
        this.id = id;
        this.lastName = lastName;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "\nAbonent{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", telephone=" + telephone +
                '}' ;
    }
}
