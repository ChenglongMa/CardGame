package view.dialogs;

import model.SimplePlayer;
import model.interfaces.Player;
import view.panels.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerDialog extends JDialog implements ActionListener {
    private static final String OK_COMMAND = "ok";
    private static final String CANCEL_COMMAND = "cancel";
    private final JButton btnOk;
    private final JButton btnCancel;
    private final JLabel lblId;
    private final JLabel lblName;
    private final JLabel lblPoints;
    private final JTextField txtId;
    private final JTextField txtName;
    private final JTextField txtPoints;
    private Player player = null;


    public AddPlayerDialog(PlayerPanel owner) {
        setTitle("Add New Player");
        setModal(true);
//        super(owner, "Add New Player", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(owner.getParent());
        btnOk = new JButton("  OK  ");
        btnCancel = new JButton("Cancel");

        lblId = new JLabel("ID:");
        lblName = new JLabel("Name:");
        lblPoints = new JLabel("Points:");

        txtId = new JTextField();
        txtName = new JTextField();
        txtPoints = new JTextField();


        setLayout();
        addIdCpnts();
        addNameCpnts();
        addButtons();
        addPointCpnts();
        setResizable(false);

        btnOk.setActionCommand(OK_COMMAND);
        btnCancel.setActionCommand(CANCEL_COMMAND);
        btnOk.addActionListener(this);
        btnCancel.addActionListener(this);
    }

    public Player showDialog() {
        setVisible(true);
        return player;
    }

    private void addIdCpnts() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblId, gbc);


        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;
        g.insets = new Insets(5, 5, 5, 5);
        g.gridwidth = 2;
        g.gridx = 1;
        g.gridy = 0;
        add(txtId, g);
    }

    private void addNameCpnts() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblName, gbc);


        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;
        g.insets = new Insets(5, 5, 5, 5);
        g.gridwidth = 2;
        g.gridx = 1;
        g.gridy = 1;
        add(txtName, g);
    }

    private void addPointCpnts() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblPoints, gbc);


        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;
        g.insets = new Insets(5, 5, 5, 5);
        g.gridwidth = 3;
        g.gridx = 1;
        g.gridy = 2;
        add(txtPoints, g);
    }

    private void addButtons() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnOk, gbc);


        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.SOUTHEAST;
        gbc2.insets = new Insets(10, 10, 10, 10);
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.gridx = 2;
        gbc2.gridy = 3;
        add(btnCancel, gbc2);
    }

    private void setLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0E-4};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};
        setLayout(gridBagLayout);
    }

    private void setPlayer() {
        String id = txtId.getText();
        String name = txtName.getText();
        int points = Integer.parseInt(txtPoints.getText());
        player = new SimplePlayer(id, name, points);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case OK_COMMAND:
                    setPlayer();
                    break;
                case CANCEL_COMMAND:
                    break;
            }
            setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
