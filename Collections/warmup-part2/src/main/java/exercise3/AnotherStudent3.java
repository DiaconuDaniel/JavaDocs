package exercise3;

/**
 * Created by Daniel.Diaconu on 17/07/07.
 */
public class AnotherStudent3 extends Student{

    public AnotherStudent3(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int hashCode(){
        int result = this.getFirstName().hashCode() + this.getLastName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        AnotherStudent3 a1 = (AnotherStudent3) obj;
        if(this.getFirstName().equals(a1.getFirstName()) == false){
            return false;
        }
        return true;
    }
}
