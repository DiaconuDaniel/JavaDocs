package exercise3;

/**
 * Created by Daniel.Diaconu on 17/07/07.
 */
public class AnotherStudent extends Student {

    public AnotherStudent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int hashCode(){
        int result = this.getFirstName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        AnotherStudent stud = (AnotherStudent) obj;
        if(this.getFirstName().equals(stud.getFirstName()) == false){
            return false;
        }

        return true;
    }


}
