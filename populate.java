import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class populate {


    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String CONNECTION = "jdbc:oracle:thin:@localhost:1521/orcl.21.123.235";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    public static Connection dbConnection = null;

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public static void main(String[] args) {
        dbConnection = getDBConnection();

//        populateMovieTable();
//
//        populateTagsTable();
//
//        populateCountriesTable();
//
//        populateMovieGenresTable();
//
//        populateMovieLocationsTable();
//
//        populateMoviesTagsTable();
//
//        populateActorsTable();
//
//        populateDirectorsTable();
//
//        populateUserRatedMoviesTable();

        populateUserRatedMoviesTimeStampTable();

//        populateUserTaggedMoviesTable();

        populateUserTaggedMoviesTimeStampTable();
    }

    public static void dropTables() {
        try {
            System.out.println("DROPPING TABLES");
            String sql_statement = "";
            List<String> tables = Arrays.asList("movies", "movie_genres", "movie_locations", "movie_tags",
                    "movie_countries", "tags");

            for (String table : tables) {
                sql_statement = "DROP TABLE " + table;
                PreparedStatement ps = null;
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    System.out.println("dropping table " + table);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED to drop tables");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

            System.out.println("DROPPING TABLES COMPLETED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void populateCountriesTable() {
        BufferedReader br = null;
        FileReader fr = null;
        String FILENAME = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\movie_countries.dat";
        String id = "";
        String country = "";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            String sql_statement;
            PreparedStatement ps = null;
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");

                id = (delimiter.length > 0) ? delimiter[0] : "";
                country = (delimiter.length > 1) ? delimiter[1] : "";

                sql_statement = "INSERT INTO movie_countries " + "VALUES (" + "'" + id + "','" + country + "')";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE COUNTRIES");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO COUNTRIES");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    public static void populateMovieGenresTable() {
        BufferedReader br2 = null;
        FileReader fr2 = null;
        String FILENAME2 = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\movie_genres.dat";
        try {
            fr2 = new FileReader(FILENAME2);
            br2 = new BufferedReader(fr2);
            String sCurrentLine;
            String id2;
            String genre;
            String sql_statement;
            PreparedStatement ps = null;
            br2.readLine();
            while ((sCurrentLine = br2.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");
                if (delimiter.length == 2) {
                    id2 = delimiter[0];
                    genre = delimiter[1];
                    sql_statement = "INSERT INTO movie_genres " + "VALUES (" + "'" + id2 + "','" + genre + "')";
                    try {
                        ps = dbConnection.prepareStatement(sql_statement);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE GENRES");
                        e.printStackTrace();
                        return;
                    } finally {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                    System.out.println("COLUMN MISMATCH FOR GENRES TABLE");
                }
            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            try {
                if (br2 != null)
                    br2.close();

                if (fr2 != null)
                    fr2.close();
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO GENRES");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    public static void populateMovieLocationsTable() {
        BufferedReader br3 = null;
        FileReader fr3 = null;
        String FILENAME3 = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\movie_locations.dat";
        try {
            fr3 = new FileReader(FILENAME3);
            br3 = new BufferedReader(fr3);
            String sCurrentLine;
            String id3;
            String location1;
            String location2;
            String location3;
            String location4;
            String sql_statement;
            PreparedStatement ps = null;
            br3.readLine();
            while ((sCurrentLine = br3.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");
                id3 = (delimiter.length > 0) ? delimiter[0] : "";
                location1 = (delimiter.length > 1) ? delimiter[1] : "";
                location2 = (delimiter.length > 2) ? delimiter[2] : "";
                location3 = (delimiter.length > 3) ? delimiter[3] : "";
                location4 = (delimiter.length > 4) ? delimiter[4] : "";
                sql_statement = "INSERT INTO movie_locations " + "VALUES (?, ?, ?, ?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1, id3);
                    ps.setString(2, location1);
                    ps.setString(3, location2);
                    ps.setString(4, location3);
                    ps.setString(5, location4);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE LOCATIONS");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br3 != null)
                    br3.close();
                if (fr3 != null)
                    fr3.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("INSERTED DATA INTO LOCATIONS");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    public static void populateMoviesTagsTable() {
        BufferedReader br4 = null;
        FileReader fr4 = null;
        String FILENAME4 = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\movie_tags.dat";
        try {
            fr4 = new FileReader(FILENAME4);
            br4 = new BufferedReader(fr4);
            String sCurrentLine;
            String id4;
            String tag_id;
            int tag_weight;
            String sql_statement;
            PreparedStatement ps = null;
            br4.readLine();
            while ((sCurrentLine = br4.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");
                id4 = (delimiter.length > 0) ? delimiter[0] : "";
                tag_id = (delimiter.length > 1) ? delimiter[1] : "";
                tag_weight = (delimiter.length > 0) ? Integer.parseInt(delimiter[2]) : 0;
                sql_statement = "INSERT INTO movie_tags " + "VALUES (?, ?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1, id4);
                    ps.setString(2, tag_id);
                    ps.setInt(3, tag_weight);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE MOVIE TAGS");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br4 != null)
                    br4.close();
                if (fr4 != null)
                    fr4.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO MOVIE TAGS");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    public static void populateMovieTable() {
        BufferedReader br6 = null;
        FileReader fr6 = null;
        String FILENAME6 = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\movies.dat";
        try {
            fr6 = new FileReader(FILENAME6);
            br6 = new BufferedReader(fr6);
            String sCurrentLine;
            String id6;
            String title;
            String imdbID;
            String spanishTitle;
            String imdbPictureURL;
            int year;
            String rtID;
            float rtAllCriticsRating;
            float rtAllCriticsNumReviews;
            float rtAllCriticsNumFresh;
            float rtAllCriticsNumRotten;
            float rtAllCriticsScore;
            float rtTopCriticsRating;
            float rtTopCriticsNumReviews;
            float rtTopCriticsNumFresh;
            float rtTopCriticsNumRotten;
            float rtTopCriticsScore;
            float rtAudienceRating;
            float rtAudienceNumRatings;
            float rtAudienceScore;
            String rtPictureURL;
            String sql_statement;
            PreparedStatement ps = null;
            br6.readLine();
            while ((sCurrentLine = br6.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");
                if (delimiter.length == 21) {
                    id6 = delimiter[0];
                    title = delimiter[1];
                    imdbID = delimiter[2];
                    spanishTitle = delimiter[3];
                    imdbPictureURL = delimiter[4];
                    year = Integer.parseInt(delimiter[5]);
                    rtID = delimiter[6];
                    rtAllCriticsRating = delimiter[7].equals("\\N") ? 0 : Float.parseFloat(delimiter[7]);
                    rtAllCriticsNumReviews = delimiter[8].equals("\\N") ? 0 : Float.parseFloat(delimiter[8]);
                    rtAllCriticsNumFresh = delimiter[9].equals("\\N") ? 0 : Float.parseFloat(delimiter[9]);
                    rtAllCriticsNumRotten = delimiter[10].equals("\\N") ? 0 : Float.parseFloat(delimiter[10]);
                    rtAllCriticsScore = delimiter[11].equals("\\N") ? 0 : Float.parseFloat(delimiter[11]);
                    rtTopCriticsRating = delimiter[12].equals("\\N") ? 0 : Float.parseFloat(delimiter[12]);
                    rtTopCriticsNumReviews = delimiter[13].equals("\\N") ? 0 : Float.parseFloat(delimiter[13]);
                    rtTopCriticsNumFresh = delimiter[14].equals("\\N") ? 0 : Float.parseFloat(delimiter[14]);
                    rtTopCriticsNumRotten = delimiter[15].equals("\\N") ? 0 : Float.parseFloat(delimiter[15]);
                    rtTopCriticsScore = delimiter[16].equals("\\N") ? 0 : Float.parseFloat(delimiter[16]);
                    rtAudienceRating = delimiter[17].equals("\\N") ? 0 : Float.parseFloat(delimiter[17]);
                    rtAudienceNumRatings = delimiter[18].equals("\\N") ? 0 : Float.parseFloat(delimiter[18]);
                    rtAudienceScore = delimiter[19].equals("\\N") ? 0 : Float.parseFloat(delimiter[19]);
                    rtPictureURL = delimiter[20];
                    sql_statement = "INSERT INTO movies (ID, TITLE, IMDBID, SPANISHTITLE, IMDBPICTUREURL, YEAR, "
                            + "RTID, RTALLCRITICSRATING, RTALLCRITICSNUMREVIEWS, RTALLCRITICSNUMFRESH, RTALLCRITICSNUMROTTEN,"
                            + "RTALLCRITICSSCORE, RTTOPCRITICSRATING, RTTOPCRITICSNUMREVIEWS, RTTOPCRITICSNUMFRESH, "
                            + "RTTOPCRITICSNUMROTTEN, RTTOPCRITICSSCORE, RTAUDIENCERATING, RTAUDIENCENUMRATINGS, "
                            + "RTAUDIENCESCORE, RTPICTUREURL) "
//                    sql_statement = "INSERT INTO movies "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try {
                        ps = dbConnection.prepareStatement(sql_statement);
                        ps.setString(1, id6);
                        ps.setString(2, title);
                        ps.setString(3, imdbID);
                        ps.setString(4, spanishTitle);
                        ps.setString(5, imdbPictureURL);
                        ps.setInt(6, year);
                        ps.setString(7, rtID);
                        ps.setDouble(8, rtAllCriticsRating);
                        ps.setDouble(9, rtAllCriticsNumReviews);
                        ps.setDouble(10, rtAllCriticsNumFresh);
                        ps.setDouble(11, rtAllCriticsNumRotten);
                        ps.setDouble(12, rtAllCriticsScore);
                        ps.setDouble(13, rtTopCriticsRating);
                        ps.setDouble(14, rtTopCriticsNumReviews);
                        ps.setDouble(15, rtTopCriticsNumFresh);
                        ps.setDouble(16, rtTopCriticsNumRotten);
                        ps.setDouble(17, rtTopCriticsScore);
                        ps.setDouble(18, rtAudienceRating);
                        ps.setDouble(19, rtAudienceNumRatings);
                        ps.setDouble(20, rtAudienceScore);
                        ps.setString(21, rtPictureURL);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE MOVIES");
                        e.printStackTrace();
                        return;
                    } finally {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    System.out.println("COLUMN MISMATCH FOR MOVIES TABLE");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (br6 != null)
                    br6.close();
                if (fr6 != null)
                    fr6.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("INSERTED DATA INTO MOVIE");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    public static void populateTagsTable() {

        BufferedReader br5 = null;
        FileReader fr5 = null;
        String FILENAME5 = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\tags.dat";
        try {
            fr5 = new FileReader(FILENAME5);
            br5 = new BufferedReader(fr5);
            String sCurrentLine;
            String id5;
            String value;
            String sql_statement;
            PreparedStatement ps = null;
            br5.readLine();
            while ((sCurrentLine = br5.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");
                id5 = (delimiter.length > 0) ? delimiter[0] : "";
                value = (delimiter.length > 1) ? delimiter[1] : "";
                sql_statement = "INSERT INTO tags " + "VALUES (?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1, id5);
                    ps.setString(2, value);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE TAGS");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br5 != null)
                    br5.close();
                if (fr5 != null)
                    fr5.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("INSERTED DATA INTO TAGS");
            System.out.println("______________________________________________");

        }

    }

    public static void populateActorsTable() {
        BufferedReader br = null;
        FileReader fr = null;
        String FILENAME = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\movie_actors.dat";
        String movieID = "";
        String actorID = "";
        String actorName = "";
        int ranking;
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            String sql_statement;
            PreparedStatement ps = null;
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");

                movieID = (delimiter.length > 0) ? delimiter[0] : "";
                actorID = (delimiter.length > 1) ? delimiter[1] : "";
                actorName = (delimiter.length > 1) ? delimiter[2] : "";
                ranking = (delimiter.length > 1) ? Integer.parseInt(delimiter[3])  : 0;

                sql_statement = "INSERT INTO movie_actors " + "VALUES (?, ?, ?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1,movieID);
                    ps.setString(2,actorID);
                    ps.setString(3,actorName);
                    ps.setString(4,Integer.toString(ranking));
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE ACTORS");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO ACTORS");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    public static void populateDirectorsTable() {
        BufferedReader br = null;
        FileReader fr = null;
        String FILENAME = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\movie_directors.dat";
        String movieID = "";
        String directorID = "";
        String directorName = "";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            String sql_statement;
            PreparedStatement ps = null;
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");

                movieID = (delimiter.length > 0) ? delimiter[0] : "";
                directorID = (delimiter.length > 1) ? delimiter[1] : "";
                directorName = (delimiter.length > 1) ? delimiter[2] : "";

                sql_statement = "INSERT INTO movie_directors " + "VALUES (?, ?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1,movieID);
                    ps.setString(2,directorID);
                    ps.setString(3,directorName);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE DIRECTORS");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO DIRECTORS");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    public static void populateUserRatedMoviesTable() {
        BufferedReader br = null;
        FileReader fr = null;
        String FILENAME = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\user_ratedmovies.dat";
        String userID = "";
        String movieID = "";
        float rating;
        int date_day;
        int date_month;
        int date_year;
        int date_hour;
        int date_minute;
        int date_second;
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            String sql_statement;
            PreparedStatement ps = null;
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");

                userID = (delimiter.length > 0) ? delimiter[0] : "";
                movieID = (delimiter.length > 0) ? delimiter[1] : "";
                rating = (delimiter.length > 0) ? Float.parseFloat(delimiter[2]) : 0;
                date_day = (delimiter.length > 0) ? Integer.parseInt(delimiter[3]) : 0;
                date_month = (delimiter.length > 0) ? Integer.parseInt(delimiter[4]) : 0;
                date_year = (delimiter.length > 0) ? Integer.parseInt(delimiter[5]) : 0;
                date_hour = (delimiter.length > 0) ? Integer.parseInt(delimiter[6]) : 0;
                date_minute = (delimiter.length > 0) ? Integer.parseInt(delimiter[7]) : 0;
                date_second = (delimiter.length > 0) ? Integer.parseInt(delimiter[8]) : 0;

                sql_statement = "INSERT INTO user_ratedmovies " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1,userID);
                    ps.setString(2,movieID);
                    ps.setString(3,Float.toString(rating));
                    ps.setString(4,Integer.toString(date_day));
                    ps.setString(5,Integer.toString(date_month));
                    ps.setString(6,Integer.toString(date_year));
                    ps.setString(7,Integer.toString(date_hour));
                    ps.setString(8,Integer.toString(date_minute));
                    ps.setString(9,Integer.toString(date_second));
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE USER RATED MOVIES");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO USER RATED MOVIES");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    public static void populateUserRatedMoviesTimeStampTable(){
        BufferedReader br = null;
        FileReader fr = null;
        String FILENAME = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\user_ratedmovies-timestamps.dat";
        String userID = "";
        String movieID = "";
        float rating;
        String timeStamp = "";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            String sql_statement;
            PreparedStatement ps = null;
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");

                userID = (delimiter.length > 0) ? delimiter[0] : "";
                movieID = (delimiter.length > 0) ? delimiter[1] : "";
                rating = (delimiter.length > 0) ? Float.parseFloat(delimiter[2]) : 0;
                timeStamp = (delimiter.length > 0) ? delimiter[3] : "";

                sql_statement = "INSERT INTO user_ratedmovies_timestamp " + "VALUES (?, ?, ?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1,userID);
                    ps.setString(2,movieID);
                    ps.setString(3,Float.toString(rating));
                    ps.setString(4,timeStamp);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE USER RATED MOVIES WITH TIMESTAMP");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO USER RATED MOVIES WITH TIMESTAMP");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    public static void populateUserTaggedMoviesTable() {
        BufferedReader br = null;
        FileReader fr = null;
        String FILENAME = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\user_taggedmovies.dat";
        String userID = "";
        String movieID = "";
        String tagID = "";
        int date_day;
        int date_month;
        int date_year;
        int date_hour;
        int date_minute;
        int date_second;
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            String sql_statement;
            PreparedStatement ps = null;
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");

                userID = (delimiter.length > 0) ? delimiter[0] : "";
                movieID = (delimiter.length > 0) ? delimiter[1] : "";
                tagID = (delimiter.length > 0) ? delimiter[2] : "";
                date_day = (delimiter.length > 0) ? Integer.parseInt(delimiter[3]) : 0;
                date_month = (delimiter.length > 0) ? Integer.parseInt(delimiter[4]) : 0;
                date_year = (delimiter.length > 0) ? Integer.parseInt(delimiter[5]) : 0;
                date_hour = (delimiter.length > 0) ? Integer.parseInt(delimiter[6]) : 0;
                date_minute = (delimiter.length > 0) ? Integer.parseInt(delimiter[7]) : 0;
                date_second = (delimiter.length > 0) ? Integer.parseInt(delimiter[8]) : 0;

                sql_statement = "INSERT INTO user_taggedmovies " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1,userID);
                    ps.setString(2,movieID);
                    ps.setString(3,tagID);
                    ps.setString(4,Integer.toString(date_day));
                    ps.setString(5,Integer.toString(date_month));
                    ps.setString(6,Integer.toString(date_year));
                    ps.setString(7,Integer.toString(date_hour));
                    ps.setString(8,Integer.toString(date_minute));
                    ps.setString(9,Integer.toString(date_second));
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE USER TAGGED MOVIES");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO USER TAGGED MOVIES");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    public static void populateUserTaggedMoviesTimeStampTable(){
        BufferedReader br = null;
        FileReader fr = null;
        String FILENAME = "C:\\Users\\apoor\\Downloads\\DBMS\\HW3_DATA\\user_taggedmovies-timestamps.dat";
        String userID = "";
        String movieID = "";
        String tagID = "";
        float rating;
        String timeStamp = "";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            String sql_statement;
            PreparedStatement ps = null;
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String delimiter[] = sCurrentLine.split("\t");

                userID = (delimiter.length > 0) ? delimiter[0] : "";
                movieID = (delimiter.length > 0) ? delimiter[1] : "";
                tagID = (delimiter.length > 0) ? delimiter[2] : "";
                timeStamp = (delimiter.length > 0) ? delimiter[3] : "";

                sql_statement = "INSERT INTO user_taggedmovies_timestamp " + "VALUES (?, ?, ?, ?)";
                try {
                    ps = dbConnection.prepareStatement(sql_statement);
                    ps.setString(1,userID);
                    ps.setString(2,movieID);
                    ps.setString(3,tagID);
                    ps.setString(4,timeStamp);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("FAILED PREPAPREDSTATEMENT FOR TABLE USER TAGGED MOVIES WITH TIMESTAMP");
                    e.printStackTrace();
                    return;
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("INSERTED DATA INTO USER TAGGED MOVIES WITH TIMESTAMP");
        System.out.println("______________________________________________");

        // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    }
}


