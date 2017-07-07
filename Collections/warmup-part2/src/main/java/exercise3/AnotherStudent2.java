package exercise3;

/**
 * Created by Daniel.Diaconu on 17/07/07.
 */
public class AnotherStudent2 extends Student{
    public AnotherStudent2(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int hashCode(){
        int result = this.getFirstName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        AnotherStudent2 a1 = (AnotherStudent2) obj;
        if(this.getFirstName().equals(a1.getFirstName()) == false){
            return false;
        }
        if(this.getFirstName().equals(a1.getLastName()) == false){
            return false;
        }


        return true;
    }
}
