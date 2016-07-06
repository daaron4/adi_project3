package com.companyname.john.project3;

/**
 * Created by daaron on 6/29/16.
 */
public class PersonModel {

    // ToDo: change some things back to URL's:

    private int id;
    private String name;
    private String title;
    private String skills;
    private String open;
    private String github;
    private String ga;
    private String linkedin;
    private String other;
    private String image;
    private String user_email;
    private String phone;
    private String url;

    public PersonModel(int id, String name, String title, String skills, String open, String github, String ga, String linkedin, String other, String image, String user_email, String phone, String url) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.skills = skills;
        this.open = open;
        this.github = github;
        this.ga = ga;
        this.linkedin = linkedin;
        this.other = other;
        this.image = image;
        this.user_email = user_email;
        this.phone = phone;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getGa() {
        return ga;
    }

    public void setGa(String ga) {
        this.ga = ga;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
