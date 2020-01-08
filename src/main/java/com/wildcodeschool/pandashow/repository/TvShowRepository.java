package com.wildcodeschool.pandashow.repository;

import com.wildcodeschool.pandashow.entity.TvShow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TvShowRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/panda_show?serverTimezone=GMT";
    private final static String DB_USER = "panda";
    private final static String DB_PASSWORD = "Pandash0w!";

    public TvShow findShowById(Long id) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM tvshow WHERE id_show = ?;"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            String urlImage = resultSet.getString("image_url");
            String title = resultSet.getString("title");
            String pegi = resultSet.getString("pegi");
            int releaseYear = resultSet.getInt("release_year");
            String summary = resultSet.getString("summary");
            String casting = resultSet.getString("casting");
            String creator = resultSet.getString("creator");
            int season = resultSet.getInt("season");

            statement = connection.prepareStatement(
                    "SELECT * FROM season WHERE id_show = ?;"
            );
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            List<Long> seasonIdList = new ArrayList<>();

            while (resultSet.next()) {

                Long idSeason = resultSet.getLong("id_season");
                seasonIdList.add(idSeason);
            }

            return new TvShow(id, urlImage, title, pegi, releaseYear, summary, casting, creator, season, seasonIdList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TvShow> findAllTvShow() {

        List<TvShow> shows = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM tvshow;"
            );
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Long id = resultSet.getLong("id_show");
                String posterImg = resultSet.getString("image_url");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("pegi");
                int releaseYear = resultSet.getInt("release_year");
                String summary = resultSet.getString("summary");
                String casting = resultSet.getString("casting");
                String creator = resultSet.getString("creator");
                int season = resultSet.getInt("season");
                shows.add(new TvShow(id, posterImg, title, genre, releaseYear, summary, casting, creator, season));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shows;
    }

    public void deleteShowById(Long idUser, Long idShow) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM my_list WHERE (id_show = ?) and (id_user = ?);"
            );
            statement.setLong(1, idShow);
            statement.setLong(2, idUser);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to delete data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TvShow saveNewShow(Long idApi, String posterImg, String showImg, String title, String genre, Long releaseYear, String summary, String casting, String creator) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `tvshow` (id_api, poster_img, show_img, title, genre, release_year, summary, casting, creator)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            statement.setLong(1, idApi);
            statement.setString(2, posterImg);
            statement.setString(3, showImg);
            statement.setString(4, title);
            statement.setString(5, genre);
            statement.setLong(6, releaseYear);
            statement.setString(7, summary);
            statement.setString(8, casting);
            statement.setString(9, creator);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to delete data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
