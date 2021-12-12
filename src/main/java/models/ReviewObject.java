package models;

import com.google.gson.Gson;

public class ReviewObject {
    private static final Gson gson = new Gson();

    private int id;
    private String name;
    private String review;

    public ReviewObject(){
    }

    public ReviewObject newReview(int id, String name, String review){
        ReviewObject rw = new ReviewObject();
        this.id = id;
        this.name = name;
        this.review = review;

        return rw;
    }


    public void setId( int id )
    {
        this.id = id;
    }
    public void setName( String name )
    {
        this.name = name;
    }
    public void setReview( String review )
    {
        this.review = review;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getReview()
    {
        return review;
    }
    public String toJson()
    {
        return gson.toJson( this );
    }
    public static ReviewObject fromJson(String json )
    {
        return gson.fromJson( json, ReviewObject.class );
    }
}
