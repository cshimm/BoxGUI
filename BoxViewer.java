import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author cameron shimmin
 * The BoxViewer draws a UI with 3 TextFields: Length, Width, Height
 * and 3 buttons to Display the box details, Calculate Volume and
 * Calculate Surface Area.
 */
public class BoxViewer extends JFrame {
    static final int STARTING_X_POS = 15;
    static final int STARTING_Y_POS = 15;

    /**
     * Constructs a BoxViewer window with the specified title.
     *
     * @param title The title of the BoxViewer window.
     * This constructor sets the window title, size, default close operation, and layout.
     * The size is initialized to 250 pixels in width and 275 pixels in height.
     * The default close operation is set to exit the application when the window is closed.
     * The layout manager is set to null, allowing custom positioning of components.
     */
    public BoxViewer(String title) {
        setTitle(title);
        setSize(250, 275);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }

    /**
     * Draws a basic JButton with a label and default size
     *
     * @param label The Button's label
     * @return A JButton with a label and default size
     */
    static JButton DrawButton(String label) {
        JButton btn = new JButton(label);
        btn.setBounds(0, 0, 75, 30);
        return btn;
    }

    /**
     * Draws an empty container panel with a vertical BoxLayout and default background color
     *
     * @return empty container
     */
    static JPanel DrawVerticalPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    /**
     * A JPanel that displays a JLabel and JTextField as children.
     */
    public static class LabelTextPanel extends JPanel {
        JLabel label;
        public JTextField textField;

        /**
         * Constructs a LabelTextPanel with a specified label.
         *
         * @param label The label to display alongside the text input field.
         * This constructor initializes a LabelTextPanel, which includes a label and a text input field.
         * The label is set to the specified text and displayed in red color.
         * A text input field with a maximum length of 8 characters is created.
         * KeyListener is added to the text input field to restrict input to digits and handle decimal input.
         */
        public LabelTextPanel(String label) {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.label = new JLabel(label);
            this.label.setForeground(Color.RED);
            textField = new JTextField(8);

            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    // We need to ensure the user cannot enter non digit characters
                    if (!Character.isDigit(c) && c != '.') {
                        e.consume();
                    }
                    // This will ensure the user cannot enter more than one decimal character
                    if (c == '.' && textField.getText().contains(".")) {
                        e.consume();
                    }
                }
            });
            add(this.label);
            add(textField);
        }

        /**
         * @return The text value from the JTextField
         */
        public String getTextValue() {
            return textField.getText();
        }
    }

    public static void main(String[] args) {
        // Instantiate Box and BoxViewer objects
        Box box = new Box(0, 0, 0);
        BoxViewer boxViewer = new BoxViewer("BoxViewer");

        /*  Initialize the dimensions panel, which will contain three LabelTextPanels.
            Each LabelTextPanel represents the length, width, and height.

            For each LabelTextPanel, we attach DocumentListeners to the textField component
            to manage insert and remove updates. These listeners update the corresponding
            properties of the box (length, width, height).
         */
        var dimsPanel = DrawVerticalPanel();
        dimsPanel.setBounds(STARTING_X_POS, STARTING_Y_POS, 200, 100);
        var lengthPanel = new LabelTextPanel("Length: ");
        var widthPanel = new LabelTextPanel("Width: ");
        var heightPanel = new LabelTextPanel("Height: ");
        lengthPanel.textField.getDocument().addDocumentListener(new DocumentListener() {
            void setBoxLength() {
                if (!lengthPanel.getTextValue().isEmpty())
                    box.setLength(lengthPanel.getTextValue());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                setBoxLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setBoxLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        widthPanel.textField.getDocument().addDocumentListener(new DocumentListener() {
            void setBoxWidth() {
                if (!widthPanel.getTextValue().isEmpty())
                    box.setWidth(widthPanel.getTextValue());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                setBoxWidth();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setBoxWidth();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        heightPanel.textField.getDocument().addDocumentListener(new DocumentListener() {
            void setBoxHeight() {
                if (!heightPanel.getTextValue().isEmpty())
                    box.setHeight(heightPanel.getTextValue());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                setBoxHeight();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setBoxHeight();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        dimsPanel.add(lengthPanel);
        dimsPanel.add(widthPanel);
        dimsPanel.add(heightPanel);

        /*
            Initialize a container for the buttons.
            Then initialize the 3 buttons: Calculate Volume, Calculate Surface Area and Show Box Details
            and provide them with Action Listeners.
         */
        var buttonsPanel = DrawVerticalPanel();
        buttonsPanel.setBounds(STARTING_X_POS, STARTING_Y_POS + 100, 200, 100);

        var volBtn = DrawButton("Calculate Volume");
        volBtn.addActionListener((actionEvent) ->
                JOptionPane.showMessageDialog(boxViewer, "Volume: " + box.calculateVolume())
        );

        var surfaceAreaBtn = DrawButton("Calculate Surface Area");
        surfaceAreaBtn.addActionListener((actionEvent) ->
                JOptionPane.showMessageDialog(boxViewer, "Surface Area: " + box.calculateSurfaceArea())
        );

        var detailsBtn = DrawButton("Show Box Details");
        detailsBtn.setBounds(STARTING_X_POS, STARTING_Y_POS + 200, 200, 30);
        detailsBtn.addActionListener(actionEvent -> {
            String displayString = "Length: " + box.getLength() + "\nWidth: " +
                    box.getWidth() + "\nHeight: " + box.getHeight();
            JOptionPane.showMessageDialog(boxViewer, displayString);
        });

        buttonsPanel.add(volBtn);
        buttonsPanel.add(surfaceAreaBtn);
        buttonsPanel.add(detailsBtn);

        // Finally, add the dimensions panel and buttons panel to the BoxViewer and set to visible
        boxViewer.add(dimsPanel);
        boxViewer.add(buttonsPanel);

        boxViewer.setVisible(true);
    }
}