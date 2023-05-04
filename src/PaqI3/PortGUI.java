//DAVID GARCÍA GUIRADO - GROUP I

package PaqI3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class PortGUI extends JFrame {
    public final int NHUBS = 3; //NUMBER OF HUBS
    public Hub[] hub = new Hub[NHUBS]; //HUB (THERE ARE 3)

    //GUI
    private JPanel mainPanel;
    private JTextField textID;
    private JTextField textWeight;
    private JTextArea textDescription;
    private JTextField textCompanySends;
    private JTextField textCompanyReceives;
    private JButton buttonPile;
    private JButton buttonUnpile;
    private JButton buttonShowDescription;
    private JTextArea textDescription2;
    private JButton buttonNumberContainers;
    private JComboBox comboBoxCountry1;
    private JComboBox comboBoxCountry2;
    private JRadioButton RadioButton1;
    private JRadioButton RadioButton2;
    private JRadioButton RadioButton3;
    private JTextArea textState1;
    private JCheckBox checkBoxInspection;
    private JTextArea textState3;
    private JTextArea textState2;
    private JTextField textNumberContainers;
    public JLabel imageCompany;

    public PortGUI() {
        setContentPane(mainPanel);
        setTitle("Manage hub");
        setSize(910, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < NHUBS; i++) { //Initialize hubs
            hub[i] = new Hub();
        }
        textState1.setText(hub[NHUBS - 3].toString()); //Initialize hub plan (state)
        textState2.setText(hub[NHUBS - 2].toString());
        textState3.setText(hub[NHUBS - 1].toString());

        //DISPLAY DATA FROM A CONTAINER ("Show container description" BUTTON)
        buttonShowDescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDescription2.setForeground(new Color(0, 0, 0));

                try {
                    int identifier = Integer.parseInt(JOptionPane.showInputDialog("Enter ID of the container you want to get data from\n")); //Show message asking for ID and assign it
                    String message = "Could not find any container with that ID."; //Message to be displayed (defaults to not have found any container)
                    for (int i = 0; i < NHUBS; i++) {
                        message = hub[i].displayContainerData(identifier);
                        if (!message.equals("Could not find any container with that ID.")) break; //Exit for loop after finding a container with that ID
                    }
                    textDescription2.setText(message); //Display data
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(mainPanel, "ERROR: Enter a valid value!");
                }
            }
        });
        //STACK CONTAINER ("Pile" BUTTON)
        buttonPile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container container = new Container(); //Create new container object
                try {
                    container.setIdentifier(Integer.parseInt(textID.getText())); //Set identifier
                    try {
                        container.setWeight(Integer.parseInt(textWeight.getText())); //Set weight (Multiply by 1000 to go from tons to kg)
                        container.setCountry((String) comboBoxCountry2.getSelectedItem()); //Set country
                        container.setInspected(checkBoxInspection.isSelected()); //Set inspected
                        if (RadioButton1.isSelected()) container.setPriorityLevel(1); //Set priority (1 if 1 is checked)
                        else if (RadioButton2.isSelected()) container.setPriorityLevel(2); //2 if 2 is checked
                        else if (RadioButton3.isSelected()) container.setPriorityLevel(3); //3 if 3 is checked or no one is checked
                        else JOptionPane.showMessageDialog(mainPanel, "ERROR: Select a priority for the container!");

                        if (RadioButton1.isSelected() || RadioButton2.isSelected() || RadioButton3.isSelected()) {
                            container.setDescription(textDescription.getText()); //Set description
                            container.setCompanySends(textCompanySends.getText()); //Set company that sends it
                            container.setCompanyReceives(textCompanyReceives.getText()); //Set company that receives it

                            String confirmationMessage; //Stack container in the corresponding hub checking if they are full or not
                            if (container.priorityLevel == 1) {
                                if (!hub[NHUBS - 3].isPriority1full()) confirmationMessage = hub[NHUBS - 3].stackContainer(container);
                                else if (!hub[NHUBS - 2].isPriority1full()) confirmationMessage = hub[NHUBS - 2].stackContainer(container);
                                else confirmationMessage = hub[NHUBS - 1].stackContainer(container);
                            } else if (container.priorityLevel == 2) {
                                if (!hub[NHUBS - 3].isPriority2full()) confirmationMessage = hub[NHUBS - 3].stackContainer(container);
                                else if (!hub[NHUBS - 2].isPriority2full()) confirmationMessage = hub[NHUBS - 2].stackContainer(container);
                                else confirmationMessage = hub[NHUBS - 1].stackContainer(container);
                            } else {
                                if (!hub[NHUBS - 3].isPriority3full()) confirmationMessage = hub[NHUBS - 3].stackContainer(container);
                                else if (!hub[NHUBS - 2].isPriority3full()) confirmationMessage = hub[NHUBS - 2].stackContainer(container);
                                else confirmationMessage = hub[NHUBS - 1].stackContainer(container);
                            }
                            JOptionPane.showMessageDialog(mainPanel, confirmationMessage); //Show confirmation message + stack container using hub method
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(mainPanel, "ERROR: Enter a valid value for the weight!");
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(mainPanel, "ERROR: Enter a valid value for the ID!");
                }
                textState1.setText(hub[NHUBS - 3].toString()); //Update hub plan (state)
                textState2.setText(hub[NHUBS - 2].toString());
                textState3.setText(hub[NHUBS - 1].toString());
            }
        });
        //REMOVE CONTAINER ("Unpile" BUTTON)
        buttonUnpile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0, hubNumber = 0;
                try {
                    column = Integer.parseInt(JOptionPane.showInputDialog("From which COLUMN you want to remove a container?\n\t(First column to the left is 1)\n")); //Show message asking for column and assign it
                    try {
                        hubNumber = Integer.parseInt(JOptionPane.showInputDialog("From which HUB you want to remove a container (in column " + column + ")?\n"));
                        JOptionPane.showMessageDialog(mainPanel, hub[hubNumber - 1].removeContainer(column)); //Remove container using hub method

                        if (column == 1) hub[hubNumber - 1].setPriority1full(false); //If that column was full, set it to not full
                        else if (column == 2) hub[hubNumber - 1].setPriority2full(false);
                        else hub[hubNumber - 1].setPriority3full(false);

                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(mainPanel, "ERROR: Enter a valid value!");
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(mainPanel, "ERROR: Enter a valid value!");
                }

                textState1.setText(hub[NHUBS - 3].toString()); //Update hub plan (state)
                textState2.setText(hub[NHUBS - 2].toString());
                textState3.setText(hub[NHUBS - 1].toString());
            }
        });
        //COUNT CONTAINERS FROM A SELECTED COUNTRY ("Number of containers form:" BUTTON)
        buttonNumberContainers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0; //Variable to keep track of the count
                for (int i = 0; i < NHUBS; i++) { //Search on all 3 hubs
                    count += hub[i].countContainersFromCountry((String) comboBoxCountry1.getSelectedItem()); //Sum to the total count
                }
                JOptionPane.showMessageDialog(mainPanel, "There are " + count + " containers from " + comboBoxCountry1.getSelectedItem() + "."); //Show message with total count
                textNumberContainers.setText("N: " + count + " containers from " + comboBoxCountry1.getSelectedItem() + "."); //Show the same message in a text field
            }
        });
        //PRIORITIES (UNCHECK THE OTHER ONES)
        RadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RadioButton2.setSelected(false);
                RadioButton3.setSelected(false);
            }
        });
        RadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RadioButton1.setSelected(false);
                RadioButton3.setSelected(false);
            }
        });
        RadioButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RadioButton1.setSelected(false);
                RadioButton2.setSelected(false);
            }
        });
        //CLEAR DESCRIPTION TEXT AREA WHEN CLICKING
        textDescription.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (textDescription.getText().equals("Give a description of the container")) {
                    textDescription.setText("");
                    textDescription.setForeground(new Color(0, 0, 0));
                }
            }
        });
    }
}
