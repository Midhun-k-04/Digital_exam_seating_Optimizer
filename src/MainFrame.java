import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainFrame extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color NAVY = new Color(24, 48, 80);
    private final Color BLUE = new Color(38, 83, 135);
    private final Color LIGHT_BLUE = new Color(232, 240, 249);
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(40, 45, 50);
    private final Color GREEN = new Color(50, 140, 90);
    private final Color RED = new Color(190, 70, 70);
    private final Color BORDER = new Color(220, 225, 230);

    // =========================================================
    // MAIN COMPONENTS
    // =========================================================

    private JPanel contentPanel;

    private JLabel studentCountLabel;
    private JLabel hallCountLabel;
    private JLabel seatCountLabel;
    private JLabel assignedCountLabel;

    private JTextField registerField;
    private JTextField nameField;
    private JTextField courseField;
    private JTextField searchField;

    private JTextArea messageArea;

    private JPanel seatingPanel;

    private JTable studentTable;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MainFrame() {

        setTitle("Digital Exam Seating Optimizer");
        setSize(1250, 760);
        setMinimumSize(new Dimension(1100, 650));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createInterface();

        loadDashboardData();
    }

    // =========================================================
    // MAIN INTERFACE
    // =========================================================

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        // Header
        mainPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        // Sidebar
        mainPanel.add(
                createSidebar(),
                BorderLayout.WEST
        );

        // Content
        contentPanel =
                new JPanel(new BorderLayout());

        contentPanel.setBackground(BACKGROUND);

        showDashboard();

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(new BorderLayout());

        header.setPreferredSize(
                new Dimension(0, 78)
        );

        header.setBackground(NAVY);

        header.setBorder(
                new EmptyBorder(12, 25, 12, 25)
        );

        JLabel college =
                new JLabel("KJIT");

        college.setForeground(WHITE);

        college.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        JLabel title =
                new JLabel(
                        "DIGITAL EXAM SEATING OPTIMIZER"
                );

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        JPanel left =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        left.setOpaque(false);

        left.add(college);

        left.add(
                Box.createHorizontalStrut(25)
        );

        left.add(title);

        JLabel status =
                new JLabel(
                        "● System Ready"
                );

        status.setForeground(
                new Color(150, 220, 170)
        );

        status.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        header.add(
                left,
                BorderLayout.WEST
        );

        header.add(
                status,
                BorderLayout.EAST
        );

        return header;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(220, 0)
        );

        sidebar.setBackground(
                new Color(30, 55, 90)
        );

        sidebar.setBorder(
                new EmptyBorder(
                        25, 15, 20, 15
                )
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel menu =
                new JLabel("MAIN MENU");

        menu.setForeground(
                new Color(180, 195, 215)
        );

        menu.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        menu.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(menu);

        sidebar.add(
                Box.createVerticalStrut(18)
        );

        sidebar.add(
                createMenuButton(
                        "Dashboard",
                        e -> showDashboard()
                )
        );

        sidebar.add(
                createMenuButton(
                        "Student Management",
                        e -> showStudentManagement()
                )
        );

        sidebar.add(
                createMenuButton(
                        "Seating Plan",
                        e -> showSeatingPlan()
                )
        );

        sidebar.add(
                createMenuButton(
                        "Search Student",
                        e -> showSearchStudent()
                )
        );

        sidebar.add(
                createMenuButton(
                        "Reports",
                        e -> showReports()
                )
        );

        sidebar.add(
                Box.createVerticalGlue()
        );

        sidebar.add(
                createMenuButton(
                        "About Project",
                        e -> showAbout()
                )
        );

        return sidebar;
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createMenuButton(
            String text,
            java.awt.event.ActionListener action) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        190,
                        45
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setForeground(WHITE);

        button.setBackground(
                new Color(30, 55, 90)
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setBorder(
                new EmptyBorder(
                        10, 15, 10, 10
                )
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addActionListener(action);

        return button;
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void showDashboard() {

        contentPanel.removeAll();

        JPanel page =
                new JPanel(new BorderLayout(15, 15));

        page.setBackground(BACKGROUND);

        page.setBorder(
                new EmptyBorder(
                        25, 25, 25, 25
                )
        );

        // Heading
        JPanel heading =
                createPageHeading(
                        "Examination Dashboard",
                        "Manage students and generate optimized seating"
                );

        page.add(
                heading,
                BorderLayout.NORTH
        );

        // Center
        JPanel center =
                new JPanel();

        center.setBackground(BACKGROUND);

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        // Statistics
        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        cards.setBackground(BACKGROUND);

        cards.add(
                createStatCard(
                        "TOTAL STUDENTS",
                        studentCountLabel =
                                new JLabel("0")
                )
        );

        cards.add(
                createStatCard(
                        "EXAM HALLS",
                        hallCountLabel =
                                new JLabel("2")
                )
        );

        cards.add(
                createStatCard(
                        "TOTAL SEATS",
                        seatCountLabel =
                                new JLabel("10")
                )
        );

        cards.add(
                createStatCard(
                        "ASSIGNED SEATS",
                        assignedCountLabel =
                                new JLabel("0")
                )
        );

        center.add(cards);

        center.add(
                Box.createVerticalStrut(20)
        );

        // Quick actions
        JPanel quickPanel =
                createWhitePanel();

        quickPanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        15,
                        15
                )
        );

        JLabel quick =
                new JLabel("QUICK ACTIONS");

        quick.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        quickPanel.add(quick);

        JButton add =
                createBlueButton(
                        "Add Student"
                );

        add.addActionListener(
                e -> showStudentManagement()
        );

        JButton seating =
                createBlueButton(
                        "Generate Seating"
                );

        seating.addActionListener(
                e -> generateSeating()
        );

        JButton search =
                createBlueButton(
                        "Search Student"
                );

        search.addActionListener(
                e -> showSearchStudent()
        );

        quickPanel.add(add);
        quickPanel.add(seating);
        quickPanel.add(search);

        center.add(quickPanel);

        center.add(
                Box.createVerticalStrut(20)
        );

        // Recent allocations
        JPanel recent =
                createWhitePanel();

        recent.setLayout(
                new BorderLayout()
        );

        JLabel recentTitle =
                new JLabel(
                        "RECENT SEATING ALLOCATIONS"
                );

        recentTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        recentTitle.setBorder(
                new EmptyBorder(
                        12, 15, 12, 15
                )
        );

        recent.add(
                recentTitle,
                BorderLayout.NORTH
        );

        recent.add(
                createStudentTable(),
                BorderLayout.CENTER
        );

        center.add(recent);

        page.add(
                center,
                BorderLayout.CENTER
        );

        contentPanel.add(page);
        loadDashboardData();
        refreshPage();
    }

    // =========================================================
    // STUDENT MANAGEMENT
    // =========================================================

    private void showStudentManagement() {

        contentPanel.removeAll();

        JPanel page =
                new JPanel(new BorderLayout(15, 15));

        page.setBackground(BACKGROUND);

        page.setBorder(
                new EmptyBorder(
                        25, 25, 25, 25
                )
        );

        page.add(
                createPageHeading(
                        "Student Management",
                        "Add student information to the examination database"
                ),
                BorderLayout.NORTH
        );

        JPanel form =
                createWhitePanel();

        form.setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(12, 15, 12, 15);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel title =
                new JLabel(
                        "Student Registration"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        form.add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        form.add(
                new JLabel("Register Number"),
                gbc
        );

        registerField =
                createTextField();

        gbc.gridx = 1;

        form.add(
                registerField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 2;

        form.add(
                new JLabel("Student Name"),
                gbc
        );

        nameField =
                createTextField();

        gbc.gridx = 1;

        form.add(
                nameField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 3;

        form.add(
                new JLabel("Course"),
                gbc
        );

        courseField =
                createTextField();

        gbc.gridx = 1;

        form.add(
                courseField,
                gbc
        );

        JButton save =
                createBlueButton(
                        "SAVE STUDENT"
                );

        gbc.gridx = 1;
        gbc.gridy = 4;

        form.add(save, gbc);

        save.addActionListener(
                e -> addStudent()
        );

        page.add(
                form,
                BorderLayout.NORTH
        );

        page.add(
                createStudentTable(),
                BorderLayout.CENTER
        );

        contentPanel.add(page);

        refreshPage();
    }

    // =========================================================
    // ADD STUDENT TO MYSQL
    // =========================================================

    private void addStudent() {

        String register =
                registerField.getText().trim();

        String name =
                nameField.getText().trim();

        String course =
                courseField.getText().trim();

        if (register.isEmpty() ||
                name.isEmpty() ||
                course.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter all student details.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String query =
                "INSERT INTO students " +
                "(register_no, name, course) " +
                "VALUES (?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setString(1, register);
            statement.setString(2, name);
            statement.setString(3, course);

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully."
            );

            registerField.setText("");
            nameField.setText("");
            courseField.setText("");

            loadDashboardData();

            showStudentManagement();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add student.\n" +
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // SEARCH STUDENT
    // =========================================================

    private void showSearchStudent() {

        contentPanel.removeAll();

        JPanel page =
                new JPanel(new BorderLayout(15, 15));

        page.setBackground(BACKGROUND);

        page.setBorder(
                new EmptyBorder(
                        25, 25, 25, 25
                )
        );

        page.add(
                createPageHeading(
                        "Search Student",
                        "Linear Search is used to find student seating details"
                ),
                BorderLayout.NORTH
        );

        JPanel top =
                createWhitePanel();

        top.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        15,
                        18
                )
        );

        top.add(
                new JLabel("Register Number:")
        );

        searchField =
                createTextField();

        searchField.setPreferredSize(
                new Dimension(
                        220,
                        35
                )
        );

        top.add(searchField);

        JButton search =
                createBlueButton(
                        "SEARCH"
                );

        top.add(search);

        page.add(
                top,
                BorderLayout.NORTH
        );

        messageArea =
                new JTextArea();

        messageArea.setEditable(false);

        messageArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        15
                )
        );

        messageArea.setBorder(
                new EmptyBorder(
                        20, 20, 20, 20
                )
        );

        page.add(
                new JScrollPane(messageArea),
                BorderLayout.CENTER
        );

        search.addActionListener(
                e -> searchStudent()
        );

        contentPanel.add(page);

        refreshPage();
    }

    // =========================================================
    // LINEAR SEARCH
    // =========================================================

    private void searchStudent() {

        String register =
                searchField.getText().trim();

        if (register.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a register number."
            );

            return;
        }

        StudentDatabase database =
                new StudentDatabase();

        StudentLinkedList list =
                database.getStudents();

        Student student =
                list.searchStudent(register);

        if (student != null) {

            messageArea.setText(
                    "STUDENT FOUND\n\n" +
                    "Register Number : " +
                    student.registerNo +
                    "\nName            : " +
                    student.name +
                    "\nCourse          : " +
                    student.course
            );

            loadSeatingFromDatabase(
                    student
            );

        } else {

            messageArea.setText(
                    "Student not found."
            );
        }
    }

    // =========================================================
    // SEATING PLAN
    // =========================================================

    private void showSeatingPlan() {

        contentPanel.removeAll();

        JPanel page =
                new JPanel(new BorderLayout(15, 15));

        page.setBackground(BACKGROUND);

        page.setBorder(
                new EmptyBorder(
                        25, 25, 25, 25
                )
        );

        page.add(
                createPageHeading(
                        "Examination Seating Plan",
                        "View the seats assigned by the Greedy allocation algorithm"
                ),
                BorderLayout.NORTH
        );

        seatingPanel =
                new JPanel();

        seatingPanel.setBackground(
                BACKGROUND
        );

        seatingPanel.setLayout(
                new BoxLayout(
                        seatingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JScrollPane scroll =
                new JScrollPane(
                        seatingPanel
                );

        scroll.setBorder(null);

        page.add(
                scroll,
                BorderLayout.CENTER
        );

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottom.setBackground(BACKGROUND);

        JButton generate =
                createBlueButton(
                        "GENERATE SEATING"
                );

        generate.addActionListener(
                e -> generateSeating()
        );

        bottom.add(generate);

        page.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(page);

        displaySeatingFromDatabase();

        refreshPage();
    }

    // =========================================================
    // GENERATE SEATING
    // =========================================================

    private void generateSeating() {

        StudentDatabase database =
                new StudentDatabase();

        StudentLinkedList studentList =
                database.getStudents();

        Seat[] seats = {

                new Seat("Hall A", "A1"),
                new Seat("Hall A", "A2"),
                new Seat("Hall A", "A3"),
                new Seat("Hall A", "A4"),
                new Seat("Hall A", "A5"),

                new Seat("Hall B", "B1"),
                new Seat("Hall B", "B2"),
                new Seat("Hall B", "B3"),
                new Seat("Hall B", "B4"),
                new Seat("Hall B", "B5")
        };

        GreedyAllocator allocator =
                new GreedyAllocator();

        allocator.allocateSeats(
                studentList,
                seats
        );

        SeatingDatabase seatingDatabase =
                new SeatingDatabase();

        Student current =
                studentList.head;

        while (current != null) {

            seatingDatabase.updateSeating(
                    current.registerNo,
                    current.hall,
                    current.seat
            );

            current =
                    current.next;
        }

        JOptionPane.showMessageDialog(
                this,
                "Seating allocation completed successfully.",
                "Allocation Complete",
                JOptionPane.INFORMATION_MESSAGE
        );

        loadDashboardData();

        if (seatingPanel != null) {
            displaySeatingFromDatabase();
        }
    }

    // =========================================================
    // DISPLAY SEATING
    // =========================================================

    private void displaySeatingFromDatabase() {

        if (seatingPanel == null) {
            return;
        }

        seatingPanel.removeAll();

        addHallPanel(
                "Hall A",
                "A"
        );

        seatingPanel.add(
                Box.createVerticalStrut(20)
        );

        addHallPanel(
                "Hall B",
                "B"
        );

        seatingPanel.revalidate();
        seatingPanel.repaint();
    }

    // =========================================================
    // HALL PANEL
    // =========================================================

    private void addHallPanel(
            String hallName,
            String prefix) {

        JPanel hallPanel =
                createWhitePanel();

        hallPanel.setLayout(
                new BorderLayout()
        );

        JLabel title =
                new JLabel(
                        hallName
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        title.setBorder(
                new EmptyBorder(
                        12, 15, 12, 15
                )
        );

        hallPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel seats =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                12,
                                12
                        )
                );

        seats.setBackground(WHITE);

        seats.setBorder(
                new EmptyBorder(
                        15, 15, 15, 15
                )
        );

        for (int i = 1; i <= 5; i++) {

            String seatNumber =
                    prefix + i;

            String student =
                    getStudentForSeat(
                            hallName,
                            seatNumber
                    );

            seats.add(
                    createSeatCard(
                            seatNumber,
                            student
                    )
            );
        }

        hallPanel.add(
                seats,
                BorderLayout.CENTER
        );

        seatingPanel.add(hallPanel);
    }

    // =========================================================
    // SEAT CARD
    // =========================================================

    private JPanel createSeatCard(
            String seat,
            String student) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        boolean occupied =
                student != null;

        card.setBackground(
                occupied
                        ? LIGHT_BLUE
                        : new Color(242, 244, 246)
        );

        card.setBorder(
                BorderFactory.createLineBorder(
                        occupied
                                ? BLUE
                                : new Color(
                                        205,
                                        210,
                                        215
                                )
                )
        );

        JLabel seatLabel =
                new JLabel(
                        seat,
                        SwingConstants.CENTER
                );

        seatLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        seatLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel studentLabel =
                new JLabel(
                        occupied
                                ? student
                                : "AVAILABLE",
                        SwingConstants.CENTER
                );

        studentLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        studentLabel.setForeground(
                occupied
                        ? BLUE
                        : new Color(
                                110,
                                115,
                                120
                        )
        );

        studentLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(
                Box.createVerticalGlue()
        );

        card.add(seatLabel);

        card.add(
                Box.createVerticalStrut(8)
        );

        card.add(studentLabel);

        card.add(
                Box.createVerticalGlue()
        );

        card.setPreferredSize(
                new Dimension(
                        150,
                        90
                )
        );

        return card;
    }

    // =========================================================
    // GET STUDENT FOR SEAT
    // =========================================================

    private String getStudentForSeat(
            String hall,
            String seat) {

        String query =
                "SELECT name FROM students " +
                "WHERE hall = ? AND seat = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setString(1, hall);
            statement.setString(2, seat);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                return result.getString("name");
            }

        } catch (Exception e) {

            return null;
        }

        return null;
    }

    // =========================================================
    // LOAD SEATING FOR SEARCH
    // =========================================================

    private void loadSeatingFromDatabase(
            Student student) {

        String query =
                "SELECT hall, seat FROM students " +
                "WHERE register_no = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setString(
                    1,
                    student.registerNo
            );

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                messageArea.append(
                        "\nHall            : " +
                        result.getString("hall") +
                        "\nSeat            : " +
                        result.getString("seat")
                );
            }

        } catch (Exception e) {

            messageArea.append(
                    "\nUnable to load seating."
            );
        }
    }

    // =========================================================
    // REPORTS
    // ========================================================= 
private void showReports() {

    contentPanel.removeAll();

    JPanel page =
            new JPanel(new BorderLayout(15, 20));

    page.setBackground(BACKGROUND);

    page.setBorder(
            new EmptyBorder(25, 25, 25, 25)
    );

    // Page heading
    page.add(
            createPageHeading(
                    "Examination Reports",
                    "View, export and print the student seating arrangement"
            ),
            BorderLayout.NORTH
    );

    // Main report panel
    JPanel reportPanel = createWhitePanel();

    reportPanel.setLayout(new BorderLayout(10, 10));

    // Report title
    JLabel reportTitle =
            new JLabel("STUDENT SEATING ALLOCATION REPORT");

    reportTitle.setFont(
            new Font("Arial", Font.BOLD, 17)
    );

    reportTitle.setForeground(BLUE);

    reportTitle.setBorder(
            new EmptyBorder(10, 10, 10, 10)
    );

    reportPanel.add(
            reportTitle,
            BorderLayout.NORTH
    );

    // Student table from MySQL
    JScrollPane tableScroll = createStudentTable();

    reportPanel.add(
            tableScroll,
            BorderLayout.CENTER
    );

    page.add(
            reportPanel,
            BorderLayout.CENTER
    );

    // Report action buttons
    JPanel buttons =
            new JPanel(
                    new FlowLayout(FlowLayout.RIGHT, 10, 5)
            );

    buttons.setBackground(BACKGROUND);

    JButton refreshButton =
            createBlueButton("REFRESH REPORT");

    JButton exportButton =
            createBlueButton("EXPORT CSV");

    JButton printButton =
            createBlueButton("PRINT REPORT");

    refreshButton.addActionListener(
            e -> showReports()
    );

    exportButton.addActionListener(
            e -> exportReportToCSV()
    );

    printButton.addActionListener(e -> {

        if (studentTable == null) {
            return;
        }

        try {
            boolean complete =
                    studentTable.print();

            if (complete) {
                JOptionPane.showMessageDialog(
                        this,
                        "Report printed successfully."
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to print report:\n" + ex.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    });

    buttons.add(refreshButton);
    buttons.add(exportButton);
    buttons.add(printButton);

    page.add(
            buttons,
            BorderLayout.SOUTH
    );

    contentPanel.add(page);

    refreshPage();
}


private void exportReportToCSV() {

    if (studentTable == null) {
        JOptionPane.showMessageDialog(
                this,
                "No report table is available."
        );
        return;
    }

    JFileChooser chooser = new JFileChooser();

    chooser.setDialogTitle("Save Seating Report");

    chooser.setSelectedFile(
            new java.io.File("Exam_Seating_Report.csv")
    );

    int choice = chooser.showSaveDialog(this);

    if (choice != JFileChooser.APPROVE_OPTION) {
        return;
    }

    java.io.File file = chooser.getSelectedFile();

    try (java.io.FileWriter writer =
                 new java.io.FileWriter(file)) {

        javax.swing.table.TableModel model =
                studentTable.getModel();

        // Write column headings
        for (int col = 0; col < model.getColumnCount(); col++) {

            writer.write(
                    "\"" +
                    model.getColumnName(col).replace("\"", "\"\"") +
                    "\""
            );

            if (col < model.getColumnCount() - 1) {
                writer.write(",");
            }
        }

        writer.write(System.lineSeparator());

        // Write student records
        for (int row = 0; row < model.getRowCount(); row++) {

            for (int col = 0; col < model.getColumnCount(); col++) {

                Object value = model.getValueAt(row, col);

                String text =
                        value == null ? "" : value.toString();

                writer.write(
                        "\"" + text.replace("\"", "\"\"") + "\""
                );

                if (col < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }

            writer.write(System.lineSeparator());
        }

        JOptionPane.showMessageDialog(
                this,
                "Report exported successfully:\n" +
                file.getAbsolutePath(),
                "Export Complete",
                JOptionPane.INFORMATION_MESSAGE
        );

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to export report:\n" + ex.getMessage(),
                "Export Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

    // =========================================================
    // ABOUT
    // =========================================================

    private void showAbout() {

    contentPanel.removeAll();

    JPanel page =
            new JPanel(new BorderLayout(20, 20));

    page.setBackground(BACKGROUND);

    page.setBorder(
            new EmptyBorder(
                    25, 30, 25, 30
            )
    );

    // =====================================================
    // TITLE
    // =====================================================

    JPanel heading =
            createPageHeading(
                    "About Project",
                    "Digital Exam Seating Optimizer"
            );

    page.add(
            heading,
            BorderLayout.NORTH
    );


    // =====================================================
    // INFORMATION AREA
    // =====================================================

    JPanel information =
            createWhitePanel();

    information.setLayout(
            new BoxLayout(
                    information,
                    BoxLayout.Y_AXIS
            )
    );

    information.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            BORDER
                    ),
                    new EmptyBorder(
                            25, 30, 25, 30
                    )
            )
    );


    // Project introduction

    JLabel introTitle =
            new JLabel(
                    "Project Overview"
            );

    introTitle.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    19
            )
    );

    introTitle.setForeground(BLUE);

    information.add(introTitle);

    information.add(
            Box.createVerticalStrut(10)
    );


    JTextArea introduction =
            createAboutText(
                    "The Digital Exam Seating Optimizer is a "
                    + "Java-based application developed to "
                    + "simplify and organize examination seating. "
                    + "The system assigns students to available "
                    + "seats in different examination halls while "
                    + "following the defined seating rules."
            );

    information.add(introduction);

    information.add(
            Box.createVerticalStrut(20)
    );


    // =====================================================
    // DATA STRUCTURE
    // =====================================================

    JLabel dataTitle =
            new JLabel(
                    "Data Structure Used"
            );

    dataTitle.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    18
            )
    );

    dataTitle.setForeground(BLUE);

    information.add(dataTitle);

    information.add(
            Box.createVerticalStrut(8)
    );


    JTextArea dataText =
            createAboutText(
                    "Linked List: Student records are stored as "
                    + "nodes in a linked list. Each node contains "
                    + "student details and seating information. "
                    + "The linked list allows student records to "
                    + "be added, searched and managed."
            );

    information.add(dataText);

    information.add(
            Box.createVerticalStrut(20)
    );


    // =====================================================
    // GREEDY TECHNIQUE
    // =====================================================

    JLabel greedyTitle =
            new JLabel(
                    "Algorithmic Technique"
            );

    greedyTitle.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    18
            )
    );

    greedyTitle.setForeground(BLUE);

    information.add(greedyTitle);

    information.add(
            Box.createVerticalStrut(8)
    );


    JTextArea greedyText =
            createAboutText(
                    "Greedy Technique: The system assigns a "
                    + "suitable available seat to each student "
                    + "one by one. It selects the best available "
                    + "seat at that moment while checking the "
                    + "defined seating condition."
            );

    information.add(greedyText);

    information.add(
            Box.createVerticalStrut(20)
    );


    // =====================================================
    // SEARCHING
    // =====================================================

    JLabel searchTitle =
            new JLabel(
                    "Searching Technique"
            );

    searchTitle.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    18
            )
    );

    searchTitle.setForeground(BLUE);

    information.add(searchTitle);

    information.add(
            Box.createVerticalStrut(8)
    );


    JTextArea searchText =
            createAboutText(
                    "Linear Search: The system checks student "
                    + "records one by one to find a particular "
                    + "student using the register number."
            );

    information.add(searchText);

    information.add(
            Box.createVerticalStrut(20)
    );


    // =====================================================
    // SORTING
    // =====================================================

    JLabel sortTitle =
            new JLabel(
                    "Sorting Technique"
            );

    sortTitle.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    18
            )
    );

    sortTitle.setForeground(BLUE);

    information.add(sortTitle);

    information.add(
            Box.createVerticalStrut(8)
    );


    JTextArea sortText =
            createAboutText(
                    "Bubble Sort: Student records are compared "
                    + "with adjacent records and swapped when "
                    + "they are in the wrong order. This is used "
                    + "to organize students according to their "
                    + "register numbers."
            );

    information.add(sortText);

    information.add(
            Box.createVerticalStrut(20)
    );


    // =====================================================
    // TECHNOLOGY
    // =====================================================

    JLabel technologyTitle =
            new JLabel(
                    "Technology Used"
            );

    technologyTitle.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    18
            )
    );

    technologyTitle.setForeground(BLUE);

    information.add(technologyTitle);

    information.add(
            Box.createVerticalStrut(8)
    );


    JTextArea technologyText =
            createAboutText(
                    "Java is used for the application logic and "
                    + "data structure implementation. Java Swing "
                    + "is used to create the graphical user "
                    + "interface. MySQL is used to store student "
                    + "and seating information, and JDBC is used "
                    + "to connect the Java application with the "
                    + "database."
            );

    information.add(technologyText);

    information.add(
            Box.createVerticalStrut(20)
    );


    // =====================================================
    // PROJECT DETAILS
    // =====================================================

    JLabel detailsTitle =
            new JLabel(
                    "Project Details"
            );

    detailsTitle.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    18
            )
    );

    detailsTitle.setForeground(BLUE);

    information.add(detailsTitle);

    information.add(
            Box.createVerticalStrut(8)
    );


    JTextArea detailsText =
            createAboutText(
                    "Student: MIDHUN K\n"
                    + "Register Number: 26MCAB59\n"
                    + "Class: I MCA B\n"
                    + "Course: Data Structures and "
                    + "Algorithmic Problem Solving"
            );

    information.add(detailsText);


    // =====================================================
    // SCROLL
    // =====================================================

    JScrollPane scrollPane =
            new JScrollPane(
                    information
            );

    scrollPane.setBorder(null);

    scrollPane.getVerticalScrollBar()
            .setUnitIncrement(16);

    page.add(
            scrollPane,
            BorderLayout.CENTER
    );


    contentPanel.add(page);

    refreshPage();
    }

    // Helper method used by the About Project page
    private JTextArea createAboutText(String text) {

        JTextArea area = new JTextArea(text);

        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(Color.WHITE);
        area.setForeground(Color.BLACK);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setBorder(null);
        area.setAlignmentX(Component.LEFT_ALIGNMENT);

        return area;
    }

    // =========================================================
    // STUDENT TABLE
    // =========================================================

   private JScrollPane createStudentTable() {

    String[] columns = {
            "Register No",
            "Name",
            "Course",
            "Hall",
            "Seat"
    };

    DefaultTableModel model =
            new DefaultTableModel(columns, 0);

    // Create a Linked List to store student records
    StudentLinkedList studentList =
            new StudentLinkedList();

    String query =
            "SELECT register_no, name, course, hall, seat " +
            "FROM students";

    try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet result =
                    statement.executeQuery()
    ) {

        // Step 1: Load students from MySQL into Linked List
        while (result.next()) {

            Student student = new Student(
                    result.getString("register_no"),
                    result.getString("name"),
                    result.getString("course")
            );

            student.hall = result.getString("hall");
            student.seat = result.getString("seat");

            studentList.addStudent(student);
        }

        // Step 2: Sort using your Bubble Sort algorithm
        studentList.bubbleSort();

        // Step 3: Add sorted records to the report table
        Student current = studentList.head;

        while (current != null) {

            model.addRow(new Object[] {
                    current.registerNo,
                    current.name,
                    current.course,
                    current.hall,
                    current.seat
            });

            current = current.next;
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load student report:\n" +
                        e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    studentTable = new JTable(model);

    studentTable.setRowHeight(30);

    studentTable.setFont(
            new Font("Arial", Font.PLAIN, 13)
    );

    studentTable.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13)
    );

    studentTable.setGridColor(BORDER);

    studentTable.setSelectionBackground(LIGHT_BLUE);
    studentTable.setSelectionForeground(Color.BLACK);

    return new JScrollPane(studentTable);

    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            JLabel value) {

        JPanel card =
                createWhitePanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        titleLabel.setForeground(
                new Color(
                        100,
                        105,
                        110
                )
        );

        value.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        value.setForeground(BLUE);

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(value);

        return card;
    }

    // =========================================================
    // PAGE HEADING
    // =========================================================

    private JPanel createPageHeading(
            String title,
            String subtitle) {

        JPanel panel =
                new JPanel();

        panel.setBackground(BACKGROUND);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        titleLabel.setForeground(TEXT);

        JLabel subtitleLabel =
                new JLabel(subtitle);

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        subtitleLabel.setForeground(
                new Color(
                        100,
                        105,
                        110
                )
        );

        panel.add(titleLabel);

        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(subtitleLabel);

        return panel;
    }

    // =========================================================
    // WHITE PANEL
    // =========================================================

    private JPanel createWhitePanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                10, 10, 10, 10
                        )
                )
        );

        return panel;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setPreferredSize(
                new Dimension(
                        300,
                        35
                )
        );

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        return field;
    }

    // =========================================================
    // BLUE BUTTON
    // =========================================================

   private JButton createBlueButton(String text) {

    JButton button = new JButton(text);

    button.setBackground(BLUE);
    button.setForeground(Color.WHITE);

    button.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    12
            )
    );

    button.setFocusPainted(false);
    button.setOpaque(true);
    button.setBorderPainted(false);

    button.setBorder(
            new EmptyBorder(
                    11, 20, 11, 20
            )
    );

    return button;
}
    

    // =========================================================
    // LOAD DASHBOARD COUNTS
    // =========================================================

    private void loadDashboardData() {

        String query =
                "SELECT COUNT(*) FROM students";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next() &&
                    studentCountLabel != null) {

                studentCountLabel.setText(
                        String.valueOf(
                                result.getInt(1)
                        )
                );
            }

        } catch (Exception e) {

            if (studentCountLabel != null) {
                studentCountLabel.setText("0");
            }
        }

        String assignedQuery =
                "SELECT COUNT(*) FROM students " +
                "WHERE hall IS NOT NULL " +
                "AND hall <> '' " +
                "AND seat IS NOT NULL " +
                "AND seat <> ''";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                assignedQuery
                        );

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next() &&
                    assignedCountLabel != null) {

                assignedCountLabel.setText(
                        String.valueOf(
                                result.getInt(1)
                        )
                );
            }

        } catch (Exception e) {

            if (assignedCountLabel != null) {
                assignedCountLabel.setText("0");
            }
        }
    }

    // =========================================================
    // REFRESH
    // =========================================================

    private void refreshPage() {

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    try {

                        UIManager.setLookAndFeel(
                                UIManager
                                        .getSystemLookAndFeelClassName()
                        );

                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    new MainFrame()
                            .setVisible(true);
                }
        );
    }
}