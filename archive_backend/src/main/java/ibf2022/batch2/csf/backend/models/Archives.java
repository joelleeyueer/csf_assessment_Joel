package ibf2022.batch2.csf.backend.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Archives {
    
    private String bundleId;
    private LocalDate date;
    private String title;
    private String name;
    private String comments;
    private List<String> urls = new ArrayList<String>();

    public Archives() {
    }
    

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public List<String> getUrls() {
        return urls;
    }
    
    public void addImageUrl(String imageUrl) {
        this.urls.add(imageUrl);
    }

    

    public String getBundleId() {
        return bundleId;
    }



    public void setBundleId(String uuid) {
        this.bundleId = uuid;
    }



    public void setUrls(List<String> imageUrls) {
        this.urls = imageUrls;
    }





    public LocalDate getDate() {
        return date;
    }





    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Archives [bundleId=" + bundleId + ", date=" + date + ", title=" + title + ", name=" + name
                + ", comments=" + comments + "]";
    }

    

    
    
}
