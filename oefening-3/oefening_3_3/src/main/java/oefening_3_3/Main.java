package oefening_3_3;

public class Main {
    public static void main(String[] args) {
        MovementComponent[] components = new MovementComponent[3];
        components[0] = new MovementComponent();
        components[1] = new MovementComponent();
        components[2] = new MovementComponent();

        components[0].ax = 0.2f;
        components[0].ay = 0.1f;

        components[1].ax = 0.3f;
        components[1].ay = 0.2f;

        components[2].ax = 0.2f;
        components[2].ay = 0.1f;

        components = new MovementUpdater().update(components);
        components = new MovementUpdater().update(components);
        components = new MovementUpdater().update(components);
        components = new MovementUpdater().update(components);
        components = new MovementUpdater().update(components);
        components = new MovementUpdater().update(components);

        // for (MovementComponent updatedComponent : updatedComponents) {
        // System.out.println("===========================");
        // System.out.println("ax - " + updatedComponent.ax);
        // System.out.println("ay - " + updatedComponent.ay);
        // System.out.println("vx - " + updatedComponent.vx);
        // System.out.println("vy - " + updatedComponent.vy);
        // }
        // System.out.println("===========================");

        // Load the file and run it, this will define our function

        // Get the function and prepare the argument

        // Call the function and get the fibonacci number back
        // Convert the Lua fibonacci number to a normal Java number
        // System.out.println("The " + N + "-th fibonacci number is " +
        // lua_fib.checkint());
    }
}
