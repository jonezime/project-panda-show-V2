package com.wildcodeschool.pandashow.entity;

import java.util.List;

public class TvShow {

    private Long id;
    private Long idApi;
    private String posterImg;
    private String showImg;
    private String title;
    private String genre;
    private int releaseYear;
    private String summary;
    private String casting;
    private String creator;
    private int season;
    private List<Long> seasonIdList;

    public TvShow(Long id, String posterImg, String title, String genre, int releaseYear, String summary, String casting, String creator, int season, List<Long> seasonIdList) {
        this.id = id;
        this.posterImg = posterImg;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.summary = summary;
        this.casting = casting;
        this.creator = creator;
        this.season = season;
        this.seasonIdList = seasonIdList;
    }

    public TvShow(Long id, String posterImg, String title, String genre, int releaseYear, String summary, String casting, String creator, int season) {
        this.id = id;
        this.posterImg = posterImg;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.summary = summary;
        this.casting = casting;
        this.creator = creator;
        this.season = season;
    }

    public TvShow(Long id, Long idApi, String posterImg, String showImg, String title, String genre, int releaseYear, String summary) {
        this.id = id;
        this.idApi = idApi;
        this.posterImg = posterImg;
        this.showImg = showImg;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdApi() {
        return idApi;
    }

    public void setIdApi(Long idApi) {
        this.idApi = idApi;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public void setPosterImg(String posterImg) {
        this.posterImg = posterImg;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCasting() {
        return casting;
    }

    public void setCasting(String casting) {
        this.casting = casting;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public List<Long> getSeasonIdList() {
        return seasonIdList;
    }

    public void setSeasonIdList(List<Long> seasonIdList) {
        this.seasonIdList = seasonIdList;
    }
}
