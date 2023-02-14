/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anan
 */
public class OrbitBoxStorage {
    public String id, orbit_id, content_type,index_no,title, description, link, thumbnail, views, accessibility, visibility, published_date, created_date;

    public OrbitBoxStorage(String id, String orbit_id, String content_type, String index_no, String title, String description, String link, String thumbnail, String views, String accessibility, String visibility, String published_date, String created_date) {
        this.id = id;
        this.orbit_id = orbit_id;
        this.content_type = content_type;
        this.index_no = index_no;
        this.title = title;
        this.description = description;
        this.link = link;
        this.thumbnail = thumbnail;
        this.views = views;
        this.accessibility = accessibility;
        this.visibility = visibility;
        this.published_date = published_date;
        this.created_date = created_date;
    }
    
}
