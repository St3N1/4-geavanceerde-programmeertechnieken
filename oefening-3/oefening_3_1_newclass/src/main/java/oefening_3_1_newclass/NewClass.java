package oefening_3_1_newclass;

public class NewClass extends OldClass {
    @Override
    public void print() {
        System.out.println("I am the dynamically loaded new class.");
    }
}
