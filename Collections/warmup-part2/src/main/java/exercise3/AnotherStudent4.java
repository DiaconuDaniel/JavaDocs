package exercise3;

/**
 * Created by Daniel.Diaconu on 17/07/07.
 */
public class AnotherStudent4 extends Student{
    public AnotherStudent4(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int hashCode(){
        int result = this.getFirstName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        AnotherStudent4 a1 = (AnotherStudent4) obj;
        if(this.getFirstName().equals(a1.getFirstName()) == false){
            return false;
        }
        if(this.getFirstName().equals(a1.getLastName()) == false){
            return false;
        }


        return true;
    }
}
