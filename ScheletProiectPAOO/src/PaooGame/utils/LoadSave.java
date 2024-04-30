package PaooGame.utils;

import PaooGame.entities.HealthBar;
import PaooGame.entities.Player;
import PaooGame.entities.Score;

import java.io.File;
import java.sql.*;

public class LoadSave {
    private static Connection c;

    public static void InitDataBase() {
        try {
            File file = new File("savegame.db");
            boolean fileExists = file.exists();

            // Connect to the database
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:savegame.db");
            c.setAutoCommit(true);

            if (!fileExists) {
                // If database doesn't exist, create table
                Statement stmt = c.createStatement();
                String sql = "CREATE TABLE LOADSAVE " +
                        "( ID INT PRIMARY KEY NOT NULL, " +
                        " XPOS INT NOT NULL, " +
                        " YPOS INT NOT NULL, " +
                        " SCORE INT, " +
                        " HEALTH INT )";
                stmt.execute(sql);
                stmt.close();

                InitElements();

                System.out.println("Database created");
            } else {
                System.out.println("Database exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void CloseConnection() {
        try {
            if (c != null)
                c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void InitElements() {
        try {
            Statement stmt = c.createStatement();

            String sql = "INSERT INTO LOADSAVE (ID, XPOS,YPOS,SCORE,HEALTH) " +
                    "VALUES (1,90,450,0,3)";
            stmt.executeUpdate(sql);

            stmt.close();
            System.out.println("Records created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void SaveGameState(Player p)
    {

        String sql1 = "UPDATE LOADSAVE set XPOS = ? where ID=1";
        String sql2 = "UPDATE LOADSAVE set YPOS = ? where ID=1";
        String sql3 = "UPDATE LOADSAVE set SCORE = ? where ID=1";
        String sql4 = "UPDATE LOADSAVE set HEALTH = ? where ID=1";


        PreparedStatement pstmt;

        try {
            pstmt = c.prepareStatement(sql1);
            pstmt.setInt(1, (int) p.getHitBox().x);
            pstmt.executeUpdate();

            pstmt = c.prepareStatement(sql2);
            pstmt.setInt(1, (int) p.getHitBox().y);
            pstmt.executeUpdate();

            pstmt = c.prepareStatement(sql3);
            pstmt.setInt(1, (int) Score.current_score);
            pstmt.executeUpdate();

            pstmt = c.prepareStatement(sql4);
            pstmt.setInt(1, (3 - HealthBar.counter));
            pstmt.executeUpdate();

            pstmt.close();
            System.out.println("XPOS updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Records created successfully");
    }

}
