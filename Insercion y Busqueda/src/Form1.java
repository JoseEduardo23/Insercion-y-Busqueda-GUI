import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Form1 {
    public JPanel MainPanel;
    public JTextField CNomb;
    public JTextField CApel;
    public JTextField CCI;
    public JTextField CNB1;
    public JTextField CNB2;
    public JButton B1;
    public JLabel Estudiante;
    public JLabel Cedula;
    public JTextField GNEST;
    public JTextField GCIEST;
    public JTextField GNB1EST;
    public JTextField GNB2EST;
    public JTextField NEST;
    public JTextField CEST;
    public JLabel GN;
    public JLabel GC;
    public JLabel GN1;
    public JLabel GN2;
    private JButton B2;

    public Form1() {
        B1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/estudiantes";
                String user = "root";
                String password = "j.eduardo23";

                String nombre = CNomb.getText() + " " + CApel.getText();
                String cedula = CCI.getText();
                Double B1 = Double.parseDouble(CNB1.getText());
                Double B2 = Double.parseDouble(CNB2.getText());

                String sql = "INSERT INTO estudiantes (Cedula_EST, Nombre_EST, B1, B2) VALUES (?, ?, ?, ?)";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    try (Connection connection = DriverManager.getConnection(url, user, password)) {
                        PreparedStatement cadenaPreparada = connection.prepareStatement(sql);

                        cadenaPreparada.setString(1, cedula);
                        cadenaPreparada.setString(2, nombre);
                        cadenaPreparada.setDouble(3, B1);
                        cadenaPreparada.setDouble(4, B2);

                        int filasInsertadas = cadenaPreparada.executeUpdate();
                        if (filasInsertadas > 0) {
                            System.out.println("Se ha insertado correctamente el estudiante.");
                            JOptionPane.showMessageDialog(null, "DATOS INGRESADOS", null, JOptionPane.WARNING_MESSAGE);
                        } else {
                            System.out.println("No se ha podido insertar el estudiante.");
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        B2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/estudiantes";
                String user = "root";
                String password = "j.eduardo23";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conectado a la base de datos");

                    String query = "SELECT * FROM estudiantes WHERE Cedula_EST = ? OR Nombre_EST = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, CEST.getText());
                    statement.setString(2, NEST.getText());
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        String nombre = resultSet.getString("Nombre_EST");
                        String cedula = resultSet.getString("Cedula_EST");
                        double b1 = resultSet.getDouble("B1");
                        double b2 = resultSet.getDouble("B2");

                        GNEST.setText(nombre);
                        GCIEST.setText(cedula);
                        GNB1EST.setText(String.valueOf(b1));
                        GNB2EST.setText(String.valueOf(b2));
                    } else {
                        JOptionPane.showMessageDialog(null, "ESTUDIANTE NO ENCONTRADO", null, JOptionPane.WARNING_MESSAGE);
                        GCIEST.setText("");
                        GNB1EST.setText("");
                        GNB2EST.setText("");
                    }
                } catch (SQLException e1) {
                    System.out.println(e1);
                    GNEST.setText("Error en la conexión a la base de datos");
                    GCIEST.setText("");
                    GNB1EST.setText("");
                    GNB2EST.setText("");
                }
            }
        });
    }
}