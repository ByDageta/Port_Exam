package PaqI3;

public class Main {
    public static void main(String[] args) {
        PortGUI gui = new PortGUI(); //GUI

        for (int i = 0; i < gui.NHUBS; i++) { //Initialize hubs
            gui.hub[i] = new Hub();
        }
    }
}