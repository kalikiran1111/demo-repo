package com.example.hw3;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Scanner;

//DON'T FORGET TO ADD the mysql jar file to your project. And delete mine.

public class BabyNamesPopulateDB {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        PreparedStatement preparedStatement;
        // 3306 is the port where my database is hosted and set the timezone to UTC to avoid timezone inconsistencies.
        // This config may or may not work with your setup.
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/babynames?serverTimezone=UTC", "root", "root1234");



		//declare variables
        int boyNum, girlNum;
        String boyName, girlName;
        Scanner data;

		//loop to read files
        for (int year = 2001; year <=2010; year++) {
            //go here to see how a single year of date is formatted
            //http://liveexample.pearsoncmg.com/data/babynamesranking2001.txt
            //there is a separate textfile for each year, 2001 thru 2010
            int count = 0;
            data = new Scanner(new URL("http://liveexample.pearsoncmg.com/data/babynamesranking" + year + ".txt")
                    .openStream());
            System.out.println(year + " has ");

            //loop to read process a single year and insert into db
            while (data.hasNextLine()){
                count++;
                String line = data.nextLine();
                String[] details = line.split("\t");

                boyName = details[1].trim();
                boyNum = Integer.parseInt(details[2].trim());
                girlName = details[3].trim();
                girlNum = Integer.parseInt(details[4].trim());
                System.out.println(boyName + " "+ boyNum + " " + girlName + " "+ girlNum );

                String queryString = "INSERT INTO babyname VALUES (?,?,?,?)";

                //add the prepared statement that will insert tuple into babyname
                // Boys
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setInt(1, year);
                preparedStatement.setString(2, boyName);
                preparedStatement.setString(3, "M");
                preparedStatement.setInt(4, boyNum);
                preparedStatement.executeUpdate();

                //add the prepared statement that will insert tuple into babyname
                //Girls
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setInt(1, year);
                preparedStatement.setString(2, girlName);
                preparedStatement.setString(3, "F");
                preparedStatement.setInt(4, girlNum);
                preparedStatement.executeUpdate();
            }

            System.out.println("count= " + count);
        }



    }

}
