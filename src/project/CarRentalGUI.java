package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CarRentalGUI extends JFrame implements ActionListener {
    private CarRental carRental;
    private JTextArea outputTextArea;
    private JTextField plateNoTextField, priceTextField, modelTextField, colorTextField, idTextField, nameTextField, phoneTextField, periodTextField;
    private JButton addButton, rentButton, returnButton, deleteButton, displayButton, saveButton, loadButton;
    private JComboBox<String> carTypeComboBox;

    public CarRentalGUI() {
        super("Car Rental System");
        carRental = new CarRental(50); // Create a car rental object
        carRental.addCar(new VIP("243" , 12 , "AUDI" , "GREEN" , new Driver("5" , "Hussam") ));
        carRental.addCar(new VIP("25" , 15 , "MERCEDES" , "BLACK" , new Driver("15" , "MOhammed") ));
        carRental.addCar(new VIP("77" , 15 , "FERRARI" , "WHITE" , new Driver("41" , "Khalid") ));
        carRental.addCar(new Economy("572" , 10 , "TOYOTA" , "GREY" ));
        carRental.addCar(new Economy("542" , 10 , "BMW" , "RED" ));
        carRental.addCar(new Economy("52" , 19 , "PORSCHE" , "RED" ));
        
        // Initialize components
        outputTextArea = new JTextArea(20, 50);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        plateNoTextField = new JTextField(10);
        priceTextField = new JTextField(10);
        modelTextField = new JTextField(10);
        colorTextField = new JTextField(10);
        idTextField = new JTextField(10);
        nameTextField = new JTextField(10);
        phoneTextField = new JTextField(10);
        periodTextField = new JTextField(10);

        addButton = new JButton("Add Car");
        addButton.addActionListener(this);
        rentButton = new JButton("Rent Car");
        rentButton.addActionListener(this);
        returnButton = new JButton("Return Car");
        returnButton.addActionListener(this);
        deleteButton = new JButton("Delete Car");
        deleteButton.addActionListener(this);
        displayButton = new JButton("Display Cars");
        displayButton.addActionListener(this);
        saveButton = new JButton("Save Data");
        saveButton.addActionListener(this);
        loadButton = new JButton("Load Data");
        loadButton.addActionListener(this);

        String[] carTypes = {"Economy", "VIP"};
        carTypeComboBox = new JComboBox<>(carTypes);

        // Layout components
        JPanel inputPanel = new JPanel(new GridLayout(9, 2));
        inputPanel.add(new JLabel("Plate Number:"));
        inputPanel.add(plateNoTextField);
        inputPanel.add(new JLabel("Price per Day:"));
        inputPanel.add(priceTextField);
        inputPanel.add(new JLabel("Model:"));
        inputPanel.add(modelTextField);
        inputPanel.add(new JLabel("Color:"));
        inputPanel.add(colorTextField);
        inputPanel.add(new JLabel("Driver ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Driver Name:"));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Driver Phone:"));
        inputPanel.add(phoneTextField);
        inputPanel.add(new JLabel("Period (days):"));
        inputPanel.add(periodTextField);
        inputPanel.add(new JLabel("Car Type:"));
        inputPanel.add(carTypeComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(rentButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        getContentPane().add(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
        	 // Handle Add Car button click
            String plateNo = plateNoTextField.getText();
            
            if (plateNo.isEmpty()) {
                showErrorDialog("Please enter the plate number of the car.");
                return;
            }
            
            // Check if the car with the same plate number already exists

            if (carRental.getCar(plateNo) != null) {
                showErrorDialog("A car with the same plate number already exists.");
                return;
            }
            double price = Double.parseDouble(priceTextField.getText());
            String model = modelTextField.getText();
            String color = colorTextField.getText();
            int carTypeIndex = carTypeComboBox.getSelectedIndex();

            if (carTypeIndex == 0) {
                // Add Economy car
                carRental.addCar(new Economy(plateNo, price, model, color));
            } else {
                // Add VIP car
                String driverId = idTextField.getText();
                String driverName = nameTextField.getText();
                carRental.addCar(new VIP(plateNo, price, model, color, new Driver(driverId, driverName)));
            }

            outputTextArea.setText("Car added successfully!");
            
        }     else if (e.getSource() == rentButton) {
            // Handle Rent Car button click
            String plateNo = plateNoTextField.getText();
            if (plateNo.isEmpty()) {
                showErrorDialog("Please enter the plate number of the car.");
                return;
            }
            
            Car carToRent = carRental.getCar(plateNo);
            if (carToRent == null) {
                showErrorDialog("No car found with the provided plate number.");
                return;
            }

            int period = 0;
            while (true) {
                try {
                    period = Integer.parseInt(periodTextField.getText());
                    if (period <= 0) {
                        showErrorDialog("Please enter a valid rental period (greater than 0).");
                        return;
                    }
                    break; // Exit the loop if the period is successfully parsed and validated
                } catch (NumberFormatException ex) {
                    showErrorDialog("Please enter a valid integer for the rental period.");
                    return;
                }
            }

            // Create a dialog to get customer information
            JTextField customerIdField = new JTextField(10);
            JTextField customerNameField = new JTextField(20);
            JTextField customerPhoneField = new JTextField(15);

            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("Customer ID:"));
            panel.add(customerIdField);
            panel.add(new JLabel("Customer Name:"));
            panel.add(customerNameField);
            panel.add(new JLabel("Customer Phone:"));
            panel.add(customerPhoneField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Enter Customer Information",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String customerId = customerIdField.getText();
                String customerName = customerNameField.getText();
                String customerPhone = customerPhoneField.getText();

                try {
                    Customer customer = new Customer(customerId, customerName, customerPhone);
                    carRental.rentCar(plateNo, customer, period);
                    outputTextArea.setText("Car rented successfully.");
                } catch (InvalidNameException | InvalidPhoneNumberException ex) {
                    showErrorDialog(ex.getMessage());
                }
            }

        } else if (e.getSource() == returnButton) {
            // Handle Return Car button click
            String plateNo = plateNoTextField.getText();
            
            if (plateNo.isEmpty()) {
                showErrorDialog("Please enter the plate number of the car.");
                return;
            }
            
            if (carRental.returnCar(plateNo)) {
                outputTextArea.setText("Car returned successfully!");
            } else {
                outputTextArea.setText("Failed to return car!");
            }
        } else if (e.getSource() == deleteButton) {
            // Handle Delete Car button click
        	
            String plateNo = plateNoTextField.getText();
            
        	if (plateNo.isEmpty()) {
                showErrorDialog("Please enter the plate number of the car.");
                return;
            }
        	
            if (carRental.deleteCar(carRental.getCar(plateNo))) {
                outputTextArea.setText("Car deleted successfully!");
            } else {
                outputTextArea.setText("Failed to delete car!");
            }
        } else if (e.getSource() == displayButton) {
            // Handle Display Cars button click
            outputTextArea.setText(carRental.toString());
   
        }  else if (e.getSource() == saveButton) {
            // Handle Save Data button click
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                try (FileOutputStream fos = new FileOutputStream(filePath);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(carRental);
                    outputTextArea.setText("Data saved successfully!");
                } catch (IOException ex) {
                    outputTextArea.setText("Failed to save data: " + ex.getMessage());
                }
            }
        } else if (e.getSource() == loadButton) {
            // Handle Load Data button click
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                try (FileInputStream fis = new FileInputStream(filePath);
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    carRental = (CarRental) ois.readObject();
                    outputTextArea.setText("Data loaded successfully!");
                } catch (IOException | ClassNotFoundException ex) {
                    outputTextArea.setText("Failed to load data: " + ex.getMessage());
                }
            }
        }
    }

    
    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                CarRentalGUI gui = new CarRentalGUI();
                gui.setVisible(true);
            });
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
