import org.jdatepicker.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;


public class HW3 extends JFrame {

    private static HW3 frame;
    private JPanel mainPanel;
    private JPanel movieMainPanel;
    private JPanel searchOptionPanel;
    private JPanel genrePanel;
    private JPanel genreLabelPanel;
    private JPanel yearLabelPanel;
    private JPanel yearPanel;
    private JPanel countryLabelPanel;
    private JPanel countryPanel;
    private JPanel castLabelPanel;
    private JPanel castPanel;
    private JPanel directorPanel;
    private JPanel actorPanel;
    private JPanel tagLabelPanel;
    private JPanel tagPanel;
    private JPanel tagValuePanel;
    private JPanel movieLabelPanel;
    private JPanel movieResultPanel;
    private JPanel queryPanel;
    private JPanel comboBoxPanel;
    private JPanel buttonPanel;
    private JPanel userLabelPanel;
    private JPanel userResultPanel;

    private GridBagConstraints gbc;

    private JScrollPane scrollPane;
    private JScrollPane genrePane;
    private JScrollPane countryPane;
    private JScrollPane castPane;
    private JScrollPane tagPane;
    private JScrollPane moviePane;

    private JTextArea queryAreaTextArea;
    private JTextArea tagTextArea;

    private JButton btnMovieQuery;
    private JButton btnUserQuery;
    private JButton btnDate;
    private JButton btnCountry;
    private JButton btnCast;

    private JComboBox andOrComboBox1;
    private JComboBox andOrComboBox2;
    private JComboBox andOrComboBox3;
    private JComboBox andOrComboBox4;
    private JComboBox fromComboBox;
    private JComboBox toComboBox;
    private JComboBox directorComboBox;
    private JComboBox tagComboBox1;


    private JTable table;

    private UtilDateModel model;

    private static final String andOrOperators[] = new String[]{"AND", "OR"};
    private static final String equalOperators[] = new String[]{"=",">","<"};
    private String finalQuery = "";
    private String finalCountryQuery = "";

    private int minimumYear = 0;
//    private String yearRange[] = new String[]{""};

    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521/orcl.21.123.235";
    private String user = "SCOTT";
    private String password = "tiger";
    private Connection connection;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new HW3();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HW3() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1200, 750);
        mainPanel = new JPanel();
        mainPanel.setForeground(Color.WHITE);
        mainPanel.setBackground(Color.decode("#4169e1"));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainPanel);
        mainPanel.setAutoscrolls(true);
        mainPanel.setLayout(null);

        movieMainPanel = new JPanel();
        movieMainPanel.setForeground(Color.decode("#89CFF0"));
        movieMainPanel.setBackground(Color.decode("#89CFF0"));
        movieMainPanel.setBounds(51, 13, 830, 30);
        mainPanel.add(movieMainPanel);
        movieMainPanel.setLayout(null);

        JLabel movieLabel = new JLabel("Movie Properties");
        movieLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        movieLabel.setHorizontalAlignment(SwingConstants.CENTER);
        movieLabel.setForeground(Color.BLACK);
        movieLabel.setBounds(300, 5, 196, 22);
        movieMainPanel.add(movieLabel);

        genreLabelPanel = new JPanel();
        genreLabelPanel.setForeground(Color.gray);
        genreLabelPanel.setBackground(Color.gray);
        genreLabelPanel.setBounds(51, 44, 200, 30);
        mainPanel.add(genreLabelPanel);

        genrePanel = new JPanel();
        genrePanel.setForeground(Color.white);
        genrePanel.setBackground(Color.white);
        genrePanel.setAutoscrolls(true);
        genrePanel.setLayout(new GridLayout(22,1));
        genrePanel.setBounds(51, 74, 200, 220);

        genrePane = new JScrollPane(genrePanel);
        genrePane.setBounds(51, 74, 200, 220);
        genrePane.setPreferredSize(new Dimension(600,600));
        genrePane.setViewportView(genrePanel);
        genrePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(genrePane);
//        mainPanel.add(genrePanel);


        JLabel genreLabel = new JLabel("Genres");
        genreLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        genreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        genreLabel.setForeground(Color.WHITE);
        genreLabel.setBounds(75, 5, 196, 22);
        genreLabelPanel.add(genreLabel);

        yearLabelPanel = new JPanel();
        yearLabelPanel.setForeground(Color.gray);
        yearLabelPanel.setBackground(Color.gray);
        yearLabelPanel.setBounds(51, 296, 200, 30);
        mainPanel.add(yearLabelPanel);

        yearPanel = new JPanel();
        yearPanel.setForeground(Color.white);
        yearPanel.setBackground(Color.white);
        yearPanel.setAutoscrolls(true);
        yearPanel.setBounds(51, 326, 200, 85);
        mainPanel.add(yearPanel);
        yearPanel.setLayout(new GridLayout(0,2));
//
//        JLabel fromLabel = new JLabel("From");
//        fromLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//        fromLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        fromLabel.setForeground(Color.black);
//        fromLabel.setBounds(10, 5, 50, 27);
//        yearPanel.add(fromLabel);
//
//        fromComboBox = new JComboBox(yearRange);
//        fromComboBox.setBounds(10, 5, 50, 27);
//        yearPanel.add(fromComboBox);
//
//        JLabel toLabel = new JLabel("To");
//        toLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//        toLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        toLabel.setForeground(Color.BLACK);
//        toLabel.setBounds(75, 5, 196, 22);
//        yearPanel.add(toLabel);
//
//        toComboBox = new JComboBox(yearRange);
//        toComboBox.setBounds(10, 5, 50, 27);
//        yearPanel.add(toComboBox);


        JLabel yearLabel = new JLabel("Movie Year");
        yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yearLabel.setForeground(Color.WHITE);
        yearLabel.setBounds(75, 301, 196, 22);
        yearLabelPanel.add(yearLabel);

        btnDate = new JButton("Get Country");
        btnDate.setFont(new Font("Tahoma", Font.PLAIN,10 ));
        btnDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (andOrComboBox1.getSelectedIndex() == 0) {
                            getCountries();
                } else {
                            getCountriesForOr();
                }
            }
        });
        btnDate.setBounds(100, 105, 150, 15);
        yearLabelPanel.add(btnDate);

        countryLabelPanel = new JPanel();
        countryLabelPanel.setForeground(Color.gray);
        countryLabelPanel.setBackground(Color.gray);
        countryLabelPanel.setBounds(261, 44, 200, 30);
        mainPanel.add(countryLabelPanel);

        countryPanel = new JPanel();
        countryPanel.setForeground(Color.white);
        countryPanel.setBackground(Color.white);
        countryPanel.setAutoscrolls(true);
        countryPanel.setLayout(new GridLayout(150,1));
        countryPanel.setBounds(261, 74, 200, 337);


        countryPane = new JScrollPane(countryPanel);
        countryPane.setBounds(261, 74, 200, 337);
        countryPane.setPreferredSize(new Dimension(600,600));
        countryPane.setViewportView(countryPanel);
        countryPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(countryPane);
//        mainPanel.add(countryPanel);

        JLabel countryLabel = new JLabel("Countries");
        countryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        countryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        countryLabel.setForeground(Color.WHITE);
        countryLabel.setBounds(285, 5, 196, 22);
        countryLabelPanel.add(countryLabel);

        btnCountry= new JButton("Get Cast");
        btnCountry.setFont(new Font("Tahoma", Font.PLAIN,10 ));
        btnCountry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (andOrComboBox1.getSelectedIndex() == 0) {
                    getCast();
                } else {
                    getCastForOr();
                }
            }
        });
        btnCountry.setBounds(100, 5, 150, 15);
        countryLabelPanel.add(btnCountry);

        castLabelPanel = new JPanel();
        castLabelPanel.setForeground(Color.gray);
        castLabelPanel.setBackground(Color.gray);
        castLabelPanel.setBounds(471, 44, 200, 30);
        mainPanel.add(castLabelPanel);

        castPanel = new JPanel();
        castPanel.setForeground(Color.white);
        castPanel.setBackground(Color.white);
        castPanel.setLayout(new GridLayout(150,1));
        castPanel.setBounds(471, 74, 200, 188);


        castPane = new JScrollPane(castPanel);
        castPane.setBounds(471, 104, 200, 188);
        castPane.setPreferredSize(new Dimension(600,600));
        castPane.setViewportView(castPanel);
        castPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(castPane);

        JLabel castLabel = new JLabel("Cast");
        castLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        castLabel.setHorizontalAlignment(SwingConstants.CENTER);
        castLabel.setForeground(Color.WHITE);
        castLabel.setBounds(495, 5, 196, 22);
        castLabelPanel.add(castLabel);

        btnCast= new JButton("Get Tags");
        btnCast.setFont(new Font("Tahoma", Font.PLAIN,10 ));
        btnCast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (andOrComboBox1.getSelectedIndex() == 0) {
                    getTags();
                } else {
                    getTagsForOr();
                }
            }
        });
        btnCast.setBounds(100, 5, 150, 15);
        castLabelPanel.add(btnCast);

        actorPanel = new JPanel();
        actorPanel.setForeground(Color.white);
        actorPanel.setBackground(Color.white);
        actorPanel.setBounds(471, 74, 200, 30);
        mainPanel.add(actorPanel);

        JLabel actorLabel = new JLabel("Actor/Actress");
        actorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        actorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        actorLabel.setForeground(Color.black);
        actorLabel.setBounds(495, 5, 196, 22);
        actorPanel.add(actorLabel);

        directorPanel = new JPanel();
        directorPanel.setForeground(Color.white);
        directorPanel.setBackground(Color.white);
        directorPanel.setBounds(471, 296, 200, 115);
        directorPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(directorPanel);

        JLabel directorLabel = new JLabel("Director");
        directorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        directorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        directorLabel.setForeground(Color.black);
        directorLabel.setBounds(495, 5, 196, 22);
        directorPanel.add(directorLabel);

        directorComboBox = new JComboBox();
        directorComboBox.setBounds(495, 30, 196, 27);
        directorPanel.add(directorComboBox);

        tagLabelPanel = new JPanel();
        tagLabelPanel.setForeground(Color.gray);
        tagLabelPanel.setBackground(Color.gray);
        tagLabelPanel.setBounds(681, 44, 200, 30);
        mainPanel.add(tagLabelPanel);

        tagPanel = new JPanel();
        tagPanel.setForeground(Color.white);
        tagPanel.setBackground(Color.white);
        tagPanel.setAutoscrolls(true);
        tagPanel.setLayout(new GridLayout(150,1));
        tagPanel.setBounds(681, 74, 200, 257);
//        mainPanel.add(tagPanel);

        tagPane = new JScrollPane(tagPanel);
        tagPane.setBounds(681, 74, 200, 257);
        tagPane.setPreferredSize(new Dimension(600,600));
        tagPane.setViewportView(tagPanel);
        tagPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(tagPane);

        tagValuePanel = new JPanel();
        tagValuePanel.setForeground(Color.white);
        tagValuePanel.setBackground(Color.white);
        tagValuePanel.setAutoscrolls(true);
        tagValuePanel.setLayout(new GridLayout(3,1));
        tagValuePanel.setBounds(681, 333, 200, 78);
        tagValuePanel.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.add(tagValuePanel);

        JLabel tagLabel = new JLabel("Tag Ids and Values");
        tagLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tagLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tagLabel.setForeground(Color.WHITE);
        tagLabel.setBounds(705, 5, 196, 22);
        tagLabelPanel.add(tagLabel);

        JLabel weightLabel = new JLabel("Tag Weight & Value");
        weightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        weightLabel.setAlignmentX(LEFT_ALIGNMENT);
        weightLabel.setForeground(Color.BLACK);
        weightLabel.setBounds(675, 5, 40, 22);
        tagValuePanel.add(weightLabel);

        tagComboBox1 = new JComboBox(equalOperators);
        tagComboBox1.setBounds(340, 5, 180, 27);
        tagValuePanel.add(tagComboBox1);

        tagTextArea = new JTextArea();
        tagTextArea.setBackground(Color.gray);
        tagTextArea.setForeground(Color.gray);
        tagTextArea.setText("");
        tagTextArea.setEditable(true);
        tagTextArea.setBounds(51,5,831,90);
        tagValuePanel.add(tagTextArea);

        movieLabelPanel = new JPanel();
        movieLabelPanel.setForeground(Color.decode("#89CFF0"));
        movieLabelPanel.setBackground(Color.decode("#89CFF0"));
        movieLabelPanel.setBounds(883, 13, 278, 30);
        mainPanel.add(movieLabelPanel);

        JLabel movieResultLabel = new JLabel("Movie Results");
        movieResultLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        movieResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        movieResultLabel.setForeground(Color.BLACK);
        movieResultLabel.setBounds(900, 23, 196, 22);
        movieLabelPanel.add(movieResultLabel);

        movieResultPanel = new JPanel();
        movieResultPanel.setForeground(Color.white);
        movieResultPanel.setBackground(Color.white);
        movieResultPanel.setAutoscrolls(true);
        movieResultPanel.setLayout(new GridLayout(150,1));
        movieResultPanel.setBounds(883, 44, 278, 367);
//        mainPanel.add(movieResultPanel);

        moviePane = new JScrollPane(movieResultPanel);
        moviePane.setBounds(883, 44, 278, 367);
        moviePane.setPreferredSize(new Dimension(600,600));
        moviePane.setViewportView(movieResultPanel);
        moviePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(moviePane);

        searchOptionPanel = new JPanel();
        searchOptionPanel.setForeground(Color.decode("#89CFF0"));
        searchOptionPanel.setBackground(Color.decode("#89CFF0"));
        searchOptionPanel.setBounds(51, 415, 831, 30);
        mainPanel.add(searchOptionPanel);
        searchOptionPanel.setLayout(null);

        JLabel searchForTextField = new JLabel("SEARCH BETWEEN ATTRIBUTES VALUE: ");
        searchForTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
        searchForTextField.setForeground(Color.WHITE);
        searchForTextField.setBounds(300, 5, 300, 16);
        searchOptionPanel.add(searchForTextField);

        comboBoxPanel = new JPanel();
        comboBoxPanel.setForeground(Color.decode("#89CFF0"));
        comboBoxPanel.setBackground(Color.decode("#89CFF0"));
        comboBoxPanel.setBounds(51, 445, 831, 40);
        mainPanel.add(comboBoxPanel);
        comboBoxPanel.setLayout(null);

        andOrComboBox1 = new JComboBox(andOrOperators);
        andOrComboBox1.setBounds(340, 5, 180, 27);
        comboBoxPanel.add(andOrComboBox1);
//
//        andOrComboBox2 = new JComboBox(andOrOperators);
//        andOrComboBox2.setBounds(220, 5, 180, 27);
//        comboBoxPanel.add(andOrComboBox2);
//
//        andOrComboBox3 = new JComboBox(andOrOperators);
//        andOrComboBox3.setBounds(430, 5, 180, 27);
//        comboBoxPanel.add(andOrComboBox3);
//
//        andOrComboBox4 = new JComboBox(andOrOperators);
//        andOrComboBox4.setBounds(640, 5, 180, 27);
//        comboBoxPanel.add(andOrComboBox4);

        queryPanel = new JPanel();
        queryPanel.setBounds(51, 500, 831, 100);
        mainPanel.add(queryPanel);
        queryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        scrollPane = new JScrollPane();
        scrollPane.setBounds(51, 5, 831, 90);
        scrollPane.setPreferredSize(new Dimension(800,70));
        queryPanel.add(scrollPane);
        scrollPane.setLayout(new ScrollPaneLayout());

        queryAreaTextArea = new JTextArea();
        scrollPane.setViewportView(queryAreaTextArea);
        queryAreaTextArea.setText("<SHOW QUERY HERE>");
        queryAreaTextArea.setLineWrap(true);
        queryAreaTextArea.setEditable(false);
        queryAreaTextArea.setBounds(51,5,831,90);


        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#4169e1"));
        buttonPanel.setBounds(51, 600, 831, 50);
        mainPanel.add(buttonPanel);

        btnMovieQuery = new JButton("Execute Movie Query");
        btnMovieQuery.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnMovieQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                generateMovieResults();
            }
        });
        btnMovieQuery.setBounds(100, 105, 187, 34);
        buttonPanel.add(btnMovieQuery);


        btnUserQuery = new JButton("Execute User Query");
        btnUserQuery.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnUserQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                generateUserResults();
            }
        });
        btnUserQuery.setBounds(100, 105, 187, 34);
        buttonPanel.add(btnUserQuery);

        userLabelPanel = new JPanel();
        userLabelPanel.setForeground(Color.decode("#89CFF0"));
        userLabelPanel.setBackground(Color.decode("#89CFF0"));
        userLabelPanel.setBounds(883, 415, 278, 30);
        mainPanel.add(userLabelPanel);

        JLabel userResultLabel = new JLabel("Movie Results");
        userResultLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        userResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userResultLabel.setForeground(Color.BLACK);
        userResultLabel.setBounds(900, 23, 196, 22);
        userLabelPanel.add(userResultLabel);

        userResultPanel = new JPanel();
        userResultPanel.setForeground(Color.white);
        userResultPanel.setBackground(Color.white);
        userResultPanel.setAutoscrolls(true);
        userResultPanel.setBounds(883, 445, 278, 200);
        mainPanel.add(userResultPanel);


        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connection established");

//                gbc = new GridBagConstraints();
//                gbc.gridwidth = GridBagConstraints.REMAINDER;
//                gbc.anchor = GridBagConstraints.NORTHWEST;

                addGenres();
            }else {
                System.out.println("CONNECTION NOT ESTABLISHED!!! ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        Cursor genre =


    }
    private void addGenres() {
        PreparedStatement statement;
        finalQuery = "SELECT DISTINCT M.genre  FROM Movie_Genres M";

        String tagsQuery = "select value from Tags";

        System.out.println(finalQuery);

        String dateQuery = "SELECT MIN(year), MAX(year) FROM movies";

        try {

            boolean emptyResult = true;
            statement = connection.prepareStatement(finalQuery);
            ResultSet resultSet = (statement).executeQuery(finalQuery);

            while (resultSet.next()) {
                JCheckBox cb = new JCheckBox(resultSet.getString(1));

                gbc = new GridBagConstraints();

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.NORTHWEST;
                genrePanel.add(cb,gbc);
                cb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (andOrComboBox1.getSelectedIndex() == 0) {
//                            getCountries();
                        } else {
//                            getCountriesForOr();
                        }
                    }
                });
                genrePanel.revalidate();
                emptyResult = false;
            }

            if (emptyResult) {
                genrePanel.removeAll();
                genrePanel.revalidate();
                genrePanel.repaint();
//                countryPanel.removeAll();
                countryPanel.revalidate();
                countryPanel.repaint();

//                filmingLocationPanel.removeAll();
//                filmingLocationPanel.revalidate();
//                filmingLocationPanel.repaint();
            }
            // To generate Tags Section
            statement = (PreparedStatement) connection.prepareStatement(dateQuery);
            resultSet = ((java.sql.Statement) statement).executeQuery(dateQuery);
            String labels = "";
            int minYear = 0;
            int maxYear = 0;

//            String yearRange[] = new String[10];
            while (resultSet.next()) {
                // System.out.println(resultSet.getString(1));
                if (resultSet.isFirst()){
                    minYear = resultSet.getInt(1);
                    maxYear = resultSet.getInt(2);
                }

            }
            String yearRange[] = new String[maxYear-minYear +2];
            System.out.println(minYear);
            minimumYear = minYear;
            yearRange[0] = "";
            int j =1;
            for (int i=minYear;i<=maxYear;i++){
                yearRange[j] = Integer.toString(i);
                j++;
            }


            JLabel fromLabel = new JLabel("From");
            fromLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
            fromLabel.setHorizontalAlignment(SwingConstants.CENTER);
            fromLabel.setForeground(Color.black);
            fromLabel.setBounds(10, 5, 50, 27);
            yearPanel.add(fromLabel);

            fromComboBox = new JComboBox(yearRange);
            fromComboBox.setBounds(10, 5, 50, 27);
            yearPanel.add(fromComboBox);

            JLabel toLabel = new JLabel("To");
            toLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
            toLabel.setHorizontalAlignment(SwingConstants.CENTER);
            toLabel.setForeground(Color.BLACK);
            toLabel.setBounds(75, 5, 196, 22);
            yearPanel.add(toLabel);

            toComboBox = new JComboBox(yearRange);
            toComboBox.setBounds(10, 5, 50, 27);
            yearPanel.add(toComboBox);


//            textAreaMovieTags.setText(labels);

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    private ArrayList<String> getGenres() {
        ArrayList<String> newString = new ArrayList<String>();
        int i = 0;
        for (Component c : genrePanel.getComponents()) {
            if (c != null && (c instanceof JCheckBox) && ((JCheckBox) c).isSelected()) {
                newString.add(((JCheckBox) c).getText());
                i++;
            }
        }

        return newString;
    }

    private String andGenres() {
        ArrayList<String> genresSelected = (ArrayList<String>) getGenres();
        String query = "";
        for (int i = 0; i < genresSelected.size(); i++) {
            if (i > 0) {
                query += " AND M" + (i - 1) + ".movieID IN ";
            }
            query += "(SELECT M" + i + ".movieID FROM Movie_genres M" + i + " WHERE M" + i + ".Genre = '"
                    + genresSelected.get(i) + "'";
        }
        for (int i = 0; i < genresSelected.size(); i++) {
            query += ")";
        }
        return query;
    }

    private String orGenres() {

        ArrayList<String> genreSelected = (ArrayList<String>) getGenres();
        String queryOr = "";
        for (int i = 0; i < genreSelected.size(); i++) {
            if (i > 0) {
                queryOr += " OR ";

            }
            queryOr += "MG.Genre = '" + genreSelected.get(i) + "'";


        }
        queryOr += ")";

        if (genreSelected.size() == 0) {
            countryPanel.removeAll();
            countryPanel.revalidate();
            countryPanel.repaint();
            return "";
        }
        return queryOr;
    }

    private String andCountries(String query) {

        ArrayList<String> countrySelected = getCountriesList();
        if (!countrySelected.isEmpty()) {
            query += " INTERSECT (";
            for (int i = 0; i < countrySelected.size(); i++) {
                if (i > 0) {
                    query += " AND MC" + (i - 1) + ".movieID IN ";

                }
                query += "(SELECT MC" + i + ".movieID FROM Movie_countries MC" + i + " WHERE MC" + i + ".country = '"
                        + countrySelected.get(i) + "'";

            }
            for (int i = 0; i < countrySelected.size(); i++) {
                query += ")";

            }

            query += ")";
        }
        return query;
    }

    private void getCountries() {

        boolean flag = validateGenre();
        if (flag) {
            countryPanel.removeAll();
            countryPanel.revalidate();
            countryPanel.repaint();

            castPanel.removeAll();
            castPanel.revalidate();
            castPanel.repaint();

            tagPanel.removeAll();
            tagPanel.revalidate();
            tagPanel.repaint();

            directorComboBox.removeAllItems();
            directorComboBox.revalidate();

            PreparedStatement statement;
            ArrayList<String> genresSelected = (ArrayList<String>) getGenres();
            String query = "";

            query = andGenres();

            query += andDate();

//            String dateQuery = "";
//            if (fromComboBox.getSelectedIndex() > 0 && toComboBox.getSelectedIndex() > 0) {
//                int fromOffset = minimumYear + fromComboBox.getSelectedIndex() - 1;
//                int toOffset = minimumYear + toComboBox.getSelectedIndex() - 1;
//                query += " INTERSECT (SELECT id FROM movies WHERE year BETWEEN " + fromOffset + " AND "+ toOffset + ")";
//                dateQuery = "SELECT id FROM movies WHERE year BETWEEN " + fromOffset + " AND "+ toOffset;
//            }

            String finalGenresQuery = query;
            finalQuery = "SELECT DISTINCT M.country  FROM Movie_countries M WHERE M.movieID IN (" + query + ")";
            finalCountryQuery = "SELECT DISTINCT M.movieID  FROM Movie_countries M WHERE M.movieID IN (" + query + ")";
            String finalTagQuery = "SELECT DISTINCT T.value  FROM Tags T WHERE T.id IN " + query;

            System.out.println(finalCountryQuery);

            try {
                if (query.isEmpty()) {
                    countryPanel.removeAll();
                    countryPanel.revalidate();
                    countryPanel.repaint();
                    return;
                }

//                if (!dateQuery.equals("")) {
//                    System.out.println(dateQuery);
//                    PreparedStatement statement2 = connection.prepareStatement(dateQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
//                    ResultSet resultSet1 = ((java.sql.Statement) statement2).executeQuery(dateQuery);
//                    if (resultSet1 != null) {
//                        while (resultSet1.next()){
//                            System.out.println("I am here");
//                            int size = resultSet1.getInt(1);
//                            System.out.println(size);
//                        }
//                    }
//
//                }
                boolean emptyResult = true;
                statement = (PreparedStatement) connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(finalQuery);

                if (resultSet != null) {
                    resultSet.beforeFirst();
                    resultSet.last();
                    int size = resultSet.getRow();
                    System.out.println(size);
                    resultSet.beforeFirst();
                    countryPanel.setLayout(new GridLayout(size, 1));
                    countryPanel.removeAll();
                    countryPanel.revalidate();
                    countryPanel.repaint();
                }

                while (resultSet.next()) {
                    if (null != resultSet && null != resultSet.getString(1) && !resultSet.getString(1).isEmpty()) {
                        JCheckBox cb = new JCheckBox(resultSet.getString(1));
                        countryPanel.add(cb, gbc);
//                    cb.addActionListener(countryActionListener);
                        countryPanel.revalidate();
                        emptyResult = false;
                    }
                }


            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finalQuery = "";
    }

    private void getCountriesForOr() {

        boolean flag = validateGenre();
        if (flag) {


            countryPanel.removeAll();
            countryPanel.revalidate();
            countryPanel.repaint();

            castPanel.removeAll();
            castPanel.revalidate();
            castPanel.repaint();

            tagPanel.removeAll();
            tagPanel.revalidate();
            tagPanel.repaint();

            directorComboBox.removeAllItems();
            directorComboBox.revalidate();

            PreparedStatement statement;
            String queryOr = "";

            queryOr = orGenres();

            queryOr += andDate();


            finalQuery = "(SELECT DISTINCT MC.country FROM Movie_genres MG, Movie_countries MC WHERE MC.movieID = MG.movieID AND (" +queryOr+ ")";
            System.out.println(finalQuery);
            try {
                boolean emptyResult = true;
                statement =  connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                ResultSet resultSet = (statement).executeQuery(finalQuery);

                if (resultSet != null) {
                    resultSet.beforeFirst();
                    resultSet.last();
                    int size = resultSet.getRow();
                    System.out.println(size);
                    resultSet.beforeFirst();
                    countryPanel.setLayout(new GridLayout(size, 1));
                    countryPanel.removeAll();
                    countryPanel.revalidate();
                    countryPanel.repaint();
                }

                while (resultSet.next()) {
                    if (null != resultSet && null != resultSet.getString(1) && !resultSet.getString(1).isEmpty()) {
                        JCheckBox cb = new JCheckBox(resultSet.getString(1));
                        countryPanel.add(cb, gbc);
                        countryPanel.revalidate();
                        emptyResult = false;
                    }
                }
                if (emptyResult) {
                    countryPanel.removeAll();
                    countryPanel.revalidate();
                    countryPanel.repaint();
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        finalQuery = "";
    }


    private boolean validateGenre(){

        for (Component c : genrePanel.getComponents()) {
            if (c != null && (c instanceof JCheckBox) && ((JCheckBox) c).isSelected()) {

                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Please select at least one Genre for the search!");
        return false;
    }

    private ArrayList<String> getCountriesList() {
        ArrayList<String> newString = new ArrayList<>();
        int i = 0;
        for (Component c : countryPanel.getComponents()) {
            if (c != null && (c instanceof JCheckBox) && ((JCheckBox) c).isSelected()) {
                newString.add(((JCheckBox) c).getText());
                i++;
            }
        }

        return newString;
    }

    private void getCast(){

        boolean flag = validateGenre();
        if (flag){
            PreparedStatement statement;
            String query = "";
//            if (andOrComboBox1.getSelectedIndex()==0){
                query = andGenres();
//            }else
//            {
//                query = orGenres();
//            }

            query += andDate();

            query = andCountries(query);

            finalQuery = "";
            finalQuery = "SELECT DISTINCT MCA.actorName  FROM Movie_actors MCA WHERE MCA.movieID IN (" + query + ") ORDER BY MCA.actorName";
            String finalCastQuery = "SELECT DISTINCT MCA.movieID  FROM Movie_countries MCA WHERE MCA.movieID IN (" + query + ") ORDER BY MCA.actorName";

            System.out.println(finalCastQuery);

            try {
                if (query.isEmpty()) {
                    countryPanel.removeAll();
                    countryPanel.revalidate();
                    countryPanel.repaint();
                    return;
                }

                boolean emptyResult = true;
                statement = (PreparedStatement) connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(finalQuery);

                if (resultSet != null) {
                    resultSet.beforeFirst();
                    resultSet.last();
                    int size = resultSet.getRow();
                    System.out.println(size);
                    resultSet.beforeFirst();
                    castPanel.setLayout(new GridLayout(size, 1));
                    castPanel.removeAll();
                    castPanel.revalidate();
                    castPanel.repaint();
                }

                while (resultSet.next()) {
                    if (null != resultSet && null != resultSet.getString(1) && !resultSet.getString(1).isEmpty()) {
                        JCheckBox cb = new JCheckBox(resultSet.getString(1));
                        castPanel.add(cb, gbc);
//                    cb.addActionListener(countryActionListener);
                        castPanel.revalidate();
                        emptyResult = false;
                    }
                }
                finalQuery = "";

                finalQuery = "SELECT DISTINCT MD.directorName FROM Movie_directors MD WHERE MD.movieID IN (" + query + ") ORDER BY MD.directorName";
                try {
                    if (query.isEmpty()) {
                        countryPanel.removeAll();
                        countryPanel.revalidate();
                        countryPanel.repaint();
                        return;
                    }

                    boolean emptyResult1 = true;
                    statement = (PreparedStatement) connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                    ResultSet resultSet1 = ((java.sql.Statement) statement).executeQuery(finalQuery);


                    directorComboBox.removeAllItems();
                    directorComboBox.addItem("");
                    while (resultSet1.next()) {
                        if (null != resultSet1 && null != resultSet1.getString(1) && !resultSet1.getString(1).isEmpty()) {
//                            JCheckBox cb = new JCheckBox(resultSet1.getString(1));
                            String temp = resultSet1.getString(1);
                            directorComboBox.addItem(temp);
//                    cb.addActionListener(countryActionListener);
                            directorComboBox.revalidate();
                            emptyResult = false;
                        }
                    }
                }catch (SQLException e2){
                    e2.printStackTrace();
                }


            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
        finalQuery = "";

    }

    private String andDate() {
        String query = "";
        if (fromComboBox.getSelectedIndex() > 0 && toComboBox.getSelectedIndex() > 0) {
            int fromOffset = minimumYear + fromComboBox.getSelectedIndex() - 1;
            int toOffset = minimumYear + toComboBox.getSelectedIndex() - 1;
            query += " INTERSECT (SELECT id FROM movies WHERE year BETWEEN " + fromOffset + " AND " + toOffset + ")";
        }
        return query;
    }

    private void getCastForOr(){
        boolean flag = validateGenre();
        if (flag) {
            PreparedStatement statement;
            ArrayList<String> genreSelected = (ArrayList<String>) getGenres();
            String queryOr = "";

            System.out.println("I am here for cast");

//            if (andOrComboBox1.getSelectedIndex()==0) {
//                queryOr = andGenres();
//                queryOr = " IN " + queryOr+")";
//            }
//            else
                queryOr = orGenres();

            queryOr += " AND (";

            queryOr = orCountries(queryOr);

            finalQuery = "SELECT DISTINCT MCA.actorName FROM Movie_actors MCA, Movie_countries MC, Movie_genres MG WHERE (MC.movieID = MG.movieID AND MC.movieID = MCA.movieID AND ("+queryOr+ ")";

            finalQuery += andDate();
            System.out.println(finalQuery);
            try {
                boolean emptyResult = true;
                statement = (PreparedStatement) connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(finalQuery);

                if (resultSet != null) {
                    resultSet.beforeFirst();
                    resultSet.last();
                    int size = resultSet.getRow();
                    System.out.println(size);
                    resultSet.beforeFirst();
                    castPanel.setLayout(new GridLayout(size, 1));
                    castPanel.removeAll();
                    castPanel.revalidate();
                    castPanel.repaint();
                }
                while (resultSet.next()) {
                    if (null != resultSet && null != resultSet.getString(1) && !resultSet.getString(1).isEmpty()) {
                        JCheckBox cb = new JCheckBox(resultSet.getString(1));
                        castPanel.add(cb, gbc);
//                    cb.addActionListener(countryActionListenerForOr);
                        castPanel.revalidate();
                        emptyResult = false;
                    }
                }
                if (emptyResult) {
                    castPanel.removeAll();
                    castPanel.revalidate();
                    castPanel.repaint();
                }
                finalQuery = "";

                finalQuery = "SELECT DISTINCT MD.directorName FROM Movie_directors MD, Movie_countries MC, Movie_genres MG WHERE MC.movieID = MG.movieID AND MC.movieID = MD.movieID AND ("+queryOr+ " ORDER BY MD.directorName)";
                try {
                    boolean emptyResult1 = true;
                    statement = connection.prepareStatement(finalQuery);
                    ResultSet resultSet1 = (statement).executeQuery(finalQuery);

                    directorComboBox.removeAllItems();
                    directorComboBox.addItem("");
                    while (resultSet1.next()) {
                        if (null != resultSet1 && null != resultSet1.getString(1) && !resultSet1.getString(1).isEmpty()) {
//                            JCheckBox cb = new JCheckBox(resultSet1.getString(1));
//                            castPanel.add(cb, gbc);
                            directorComboBox.addItem(resultSet1.getString(1));
                            directorComboBox.revalidate();
                            emptyResult1 = false;
                        }
                    }
                    if (emptyResult1) {
                        castPanel.removeAll();
                        castPanel.revalidate();
                        castPanel.repaint();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        finalQuery = "";
    }

    private String orCountries(String queryOr) {

        ArrayList<String> countrySelected = getCountriesList();
        if (countrySelected.size()>0){
            for (int i = 0; i < countrySelected.size(); i++) {
                if (i > 0) {
                    queryOr += " OR ";

                }
                queryOr += "MC.country = '" + countrySelected.get(i) + "'";


            }

            queryOr += ")";
        }
        return queryOr;
    }

    private void getTags(){
        boolean flag = validateGenre();
        if (flag){
            PreparedStatement statement;
            String query = "";
//            if (andOrComboBox1.getSelectedIndex()==0){
                query = andGenres();
//            }else
//            {
//                query = orGenres();
//            }

            query += andDate();
//            if (andOrComboBox2.getSelectedIndex() == 0) {
                query = andCountries(query);
//            }else{
//                query = orCountries(query);
//            }

            query = andCast(query);

            query = andDirector(query);

            finalQuery = "SELECT DISTINCT T.value FROM tags T WHERE T.id IN (SELECT MT.tagID FROM movie_tags MT WHERE MT.movieID IN (" +query+ ")) ORDER BY T.value";

            System.out.println(finalQuery);
            try {
                boolean emptyResult1 = true;
                statement = connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                ResultSet resultSet1 = (statement).executeQuery(finalQuery);

                if (resultSet1 != null) {
                    resultSet1.beforeFirst();
                    resultSet1.last();
                    int size = resultSet1.getRow();
                    System.out.println(size);
                    resultSet1.beforeFirst();
                    tagPanel.setLayout(new GridLayout(size, 1));
                    tagPanel.removeAll();
                    tagPanel.revalidate();
                    tagPanel.repaint();
                }
                while (resultSet1.next()) {
                    if (null != resultSet1 && null != resultSet1.getString(1) && !resultSet1.getString(1).isEmpty()) {
                            JCheckBox cb = new JCheckBox(resultSet1.getString(1));
                            tagPanel.add(cb, gbc);
                        emptyResult1 = false;
                    }
                }
                if (emptyResult1) {
                    tagPanel.removeAll();
                    tagPanel.revalidate();
                    tagPanel.repaint();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        }

    private String andDirector(String query) {
        if (directorComboBox.getSelectedIndex()>0){
            query += " INTERSECT (";
            String temp =  directorComboBox.getSelectedItem().toString();
            query += "SELECT MD.movieID FROM movie_directors MD WHERE MD.directorName = '" +temp+ "')";
        }
        return query;
    }

    private String andCast(String query) {

        ArrayList<String> castSelected = getCastSelected();
        if (!castSelected.isEmpty()) {
            query += " INTERSECT (";
            for (int i = 0; i < castSelected.size(); i++) {
                if (i > 0) {
                    query += " AND MCA" + (i - 1) + ".movieID IN ";

                }
                query += "(SELECT MCA" + i + ".movieID FROM Movie_actors MCA" + i + " WHERE MCA" + i + ".actorName = '"
                        + castSelected.get(i) + "'";

            }
            for (int i = 0; i < castSelected.size(); i++) {
                query += ")";

            }

            query += ")";
        }
        return query;
    }

    private ArrayList<String> getCastSelected() {
        ArrayList<String> newString = new ArrayList<>();
        int i = 0;
        for (Component c : castPanel.getComponents()) {
            if (c != null && (c instanceof JCheckBox) && ((JCheckBox) c).isSelected()) {
                newString.add(((JCheckBox) c).getText());
                i++;
            }
        }

        return newString;
    }

    private void getTagsForOr(){

        boolean flag = validateGenre();
        if (flag) {
            PreparedStatement statement;
            String queryOr = "";

            System.out.println("I am here for tags");

//            if (andOrComboBox1.getSelectedIndex()==0) {
//                queryOr = andGenres();
//                queryOr = " IN " + queryOr+")";
//            }
//            else
            queryOr = orGenres();

            queryOr += " AND (";

            queryOr = orCountries(queryOr);

            queryOr += " AND (";

            queryOr = orCast(queryOr);

            queryOr = andDirector(queryOr);

            finalQuery = "SELECT DISTINCT T.value FROM tags T WHERE T.id IN (SELECT MT.tagID FROM movie_tags MT, Movie_actors MCA, Movie_countries MC, Movie_genres MG WHERE (MC.movieID = MG.movieID AND MC.movieID = MCA.movieID AND MC.movieID = MT.movieID AND ("+queryOr+ "))";

//            finalQuery = "SELECT DISTINCT MCA.actorName FROM Movie_actors MCA, Movie_countries MC, Movie_genres MG WHERE (MC.movieID = MG.movieID AND MC.movieID = MCA.movieID AND ("+queryOr+ ")";

            finalQuery += andDate();
            System.out.println(finalQuery);
            try {
                boolean emptyResult = true;
                statement = (PreparedStatement) connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(finalQuery);

                if (resultSet != null) {
                    resultSet.beforeFirst();
                    resultSet.last();
                    int size = resultSet.getRow();
                    System.out.println(size);
                    resultSet.beforeFirst();
                    tagPanel.setLayout(new GridLayout(size, 1));
                    tagPanel.removeAll();
                    tagPanel.revalidate();
                    tagPanel.repaint();
                }
                while (resultSet.next()) {
                    if (null != resultSet && null != resultSet.getString(1) && !resultSet.getString(1).isEmpty()) {
                        JCheckBox cb = new JCheckBox(resultSet.getString(1));
                        tagPanel.add(cb, gbc);
//                    cb.addActionListener(countryActionListenerForOr);
                        tagPanel.revalidate();
                        emptyResult = false;
                    }
                }
                if (emptyResult) {
                    tagPanel.removeAll();
                    tagPanel.revalidate();
                    tagPanel.repaint();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    private String orCast(String queryOr) {
        ArrayList<String> castSelected = getCastSelected();
        if (castSelected.size()>0){
            for (int i = 0; i < castSelected.size(); i++) {
                if (i > 0) {
                    queryOr += " OR ";

                }
                queryOr += "MCA.actorName = '" + castSelected.get(i) + "'";


            }

            queryOr += ")";
        }
        return queryOr;
    }

    private void generateMovieResults(){

        boolean flag = validateGenre();
        if (flag) {
            String query1 = "";
            if (andOrComboBox1.getSelectedIndex()==0){
                String query = "";
                query = andGenres();

//                query += andDate();
                query = andCountries(query);

                query = andCast(query);

                query = andDirector(query);
                query1 = query;

            }else{
                String queryOr = "";
                queryOr = orGenres();

                queryOr += " AND (";

                queryOr = orCountries(queryOr);

                queryOr += " AND (";

                queryOr = orCast(queryOr);

                queryOr = andDirector(queryOr);
                query1 = queryOr;
            }
            String dateQuery = andDate();

            if (!dateQuery.equals("")) {
                query1 += dateQuery;
                if (andOrComboBox1.getSelectedIndex()==0)
                finalQuery = "SELECT distinct M.id, M.title, M.year, MC.country, M.rtAudienceRating, M.rtAudienceNumRatings " +
                        "FROM movies M, Movie_actors MCA, Movie_countries MC, Movie_genres MG "
                        + "WHERE (M.id = MC.movieID AND MC.movieID = MG.movieID AND MC.movieID = MCA.movieID  AND ("
                        + "M.id IN (" + query1 + ")))";
                else
                    finalQuery = "SELECT distinct M.id, M.title, M.year, MC.country, M.rtAudienceRating, M.rtAudienceNumRatings " +
                            "FROM movies M, Movie_actors MCA, Movie_countries MC, Movie_genres MG "
                            + "WHERE (M.id = MC.movieID AND MC.movieID = MG.movieID AND MC.movieID = MCA.movieID  AND ("
                            + query1 + ")";
            }
            else
                if (andOrComboBox1.getSelectedIndex()==0)
                finalQuery = "SELECT distinct M.id, M.title, M.year, MC.country, M.rtAudienceRating, M.rtAudienceNumRatings "+
                        "FROM movies M, Movie_actors MCA, Movie_countries MC, Movie_genres MG "
                        +"WHERE (M.id = MC.movieID AND MC.movieID = MG.movieID AND MC.movieID = MCA.movieID AND ("
                        + "M.id IN (" + query1 + ")))";
                else
                    finalQuery = "SELECT distinct M.id, M.title, M.year, MC.country, M.rtAudienceRating, M.rtAudienceNumRatings "+
                            "FROM movies M, Movie_actors MCA, Movie_countries MC, Movie_genres MG "
                            +"WHERE (M.id = MC.movieID AND MC.movieID = MG.movieID AND MC.movieID = MCA.movieID AND ("
                            + query1 + ")";


            System.out.println(finalQuery);
            try {
                boolean emptyResult = true;
                PreparedStatement statement =  connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                ResultSet resultSet = (statement).executeQuery(finalQuery);

                if (resultSet != null) {
                    resultSet.beforeFirst();
                    resultSet.last();
                    int size = resultSet.getRow();
                    System.out.println(size);
                    resultSet.beforeFirst();
                    movieResultPanel.setLayout(new GridLayout(size, 1));
                    movieResultPanel.removeAll();
                    movieResultPanel.revalidate();
                    movieResultPanel.repaint();
                    queryAreaTextArea.setText(finalQuery);
                }
                String temp = "";
                while (resultSet.next()) {
                    if (null != resultSet && null != resultSet.getString(1) && !resultSet.getString(1).isEmpty()) {
                        temp = "|";
                        temp += resultSet.getString(1);
                        temp += ",";
                        temp += resultSet.getString(2);
                        temp += ",";
                        temp += resultSet.getString(3);
                        temp += ",";
                        temp += resultSet.getString(4);
                        temp += ",";
                        temp += resultSet.getString(5);
                        temp += ",";
                        temp += resultSet.getString(6);
                        temp += "|";

                        JCheckBox cb = new JCheckBox(temp);
                        movieResultPanel.add(cb, gbc);
//                    cb.addActionListener(countryActionListenerForOr);
                        movieResultPanel.revalidate();
                        emptyResult = false;
                    }
                }
                if (emptyResult) {
                    movieResultPanel.removeAll();
                    movieResultPanel.revalidate();
                    movieResultPanel.repaint();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

    }

    private void generateUserResults(){
        boolean flag = validateResults();
        if (flag){
            flag = validateTags();
            if (flag){
                String tagValue = "";
                ArrayList<String> results = getMovieSelected();
                ArrayList<String> tags = getTagsSelected();
                ArrayList<String> mID = new ArrayList<>();
                if (!tagTextArea.getText().equals(""))
                    tagValue = tagTextArea.getText();

                for (int i = 0; i< results.size(); i++){
                    String temp = results.get(i);
                    String[] splits = temp.split(",");
                    splits[0] = splits[0].replace("|","");
                    mID.add(splits[0]);
                }
                String IDquery = "";
                String tagquery = "";

                for (int i = 0; i < mID.size(); i++) {
                    if (i > 0) {
                        IDquery += " AND M" + (i - 1) + ".ID IN ";

                    }
                    IDquery += "(SELECT M" + i + ".ID FROM Movies M" + i + " WHERE M" + i + ".id = '"
                            + mID.get(i) + "'";

                }
                for (int i = 0; i < mID.size(); i++) {
                    IDquery += ")";

                }

                for (int i = 0; i < tags.size(); i++) {
                    if (i > 0) {
                        tagquery += " OR ";

                    }
                    tagquery += "MT.value = '" + tags.get(i) + "'";


                }

                tagquery += ")";

                if (!tagTextArea.getText().equals("")){
                    if (tagquery.equals("")) {
                        String operator = tagComboBox1.getSelectedItem().toString();
                        tagquery = "MT.value "+operator+" '"+tagTextArea.getText()+"'";
                    }else{
                        String operator = tagComboBox1.getSelectedItem().toString();
                        tagquery = " OR MT.value "+operator+" '"+tagTextArea.getText()+"'";
                    }
                }

                finalQuery = "SELECT U.userID FROM user_TaggedMovies U, tags T WHERE (U.tagID = T.id AND (U.movieID IN ("+IDquery+")) AND U.tagID IN (SELECT MT.id FROM tags MT WHERE "+tagquery+")";

                System.out.println(finalQuery);
                try {
                    boolean emptyResult = true;
                    PreparedStatement statement =  connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                    ResultSet resultSet = (statement).executeQuery(finalQuery);

                    if (resultSet != null) {
                        resultSet.beforeFirst();
                        resultSet.last();
                        int size = resultSet.getRow();
                        System.out.println(size);
                        resultSet.beforeFirst();
                        userResultPanel.setLayout(new GridLayout(size, 1));
                        userResultPanel.removeAll();
                        userResultPanel.revalidate();
                        userResultPanel.repaint();
                        queryAreaTextArea.setText(finalQuery);
                    }
                    String temp = "";
                    while (resultSet.next()) {
                        if (null != resultSet && null != resultSet.getString(1) && !resultSet.getString(1).isEmpty()) {
//                            temp = "|";
//                            temp += resultSet.getString(1);
//                            temp += ",";
//                            temp += resultSet.getString(2);
//                            temp += ",";
//                            temp += resultSet.getString(3);
//                            temp += ",";
//                            temp += resultSet.getString(4);
//                            temp += ",";
//                            temp += resultSet.getString(5);
//                            temp += ",";
//                            temp += resultSet.getString(6);
//                            temp += "|";

                            JCheckBox cb = new JCheckBox(resultSet.getString(1));
                            userResultPanel.add(cb, gbc);
//                    cb.addActionListener(countryActionListenerForOr);
                            userResultPanel.revalidate();
                            emptyResult = false;
                        }
                    }
                    if (emptyResult) {
                        userResultPanel.removeAll();
                        userResultPanel.revalidate();
                        userResultPanel.repaint();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            }

    }

    private ArrayList<String> getTagsSelected() {
        ArrayList<String> newString = new ArrayList<>();
        int i = 0;
        for (Component c : tagPanel.getComponents()) {
            if (c != null && (c instanceof JCheckBox) && ((JCheckBox) c).isSelected()) {
                newString.add(((JCheckBox) c).getText());
                i++;
            }
        }

        return newString;
    }

    private ArrayList<String> getMovieSelected() {
        ArrayList<String> newString = new ArrayList<>();
        int i = 0;
        for (Component c : movieResultPanel.getComponents()) {
            if (c != null && (c instanceof JCheckBox) && ((JCheckBox) c).isSelected()) {
                newString.add(((JCheckBox) c).getText());
                i++;
            }
        }

        return newString;
    }

    private boolean validateTags() {
        for (Component c : tagPanel.getComponents()) {
            if (c != null && (c instanceof JCheckBox) && ((JCheckBox) c).isSelected()) {

                return true;
            }
        }
        if (!tagTextArea.getText().equals(""))
            return true;
        JOptionPane.showMessageDialog(null, "Please select at least one tag for the search!");
        return false;
    }

    private boolean validateResults() {
        for (Component c : movieResultPanel.getComponents()) {
            if (c != null && (c instanceof JCheckBox) && ((JCheckBox) c).isSelected()) {

                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Please select at least one movie for the search!");
        return false;
    }
    }



