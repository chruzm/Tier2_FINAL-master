package models;
import com.google.gson.Gson;

public class Test
{
    private static final Gson gson = new Gson();

    private String phoneNo;
    private String name;

    public Test()
    {

    }

    public void setPhoneNo( String phoneNo )
    {
        this.phoneNo = phoneNo;
    }
    public void setName( String name )
    {
        this.name = name;
    }
    public String getPhoneNo()
    {
        return phoneNo;
    }
    public String getName()
    {
        return name;
    }
    public String toJson()
    {
        return gson.toJson( this );
    }
    public static Test fromJson(String json )
    {
        return gson.fromJson( json, Test.class );
    }
}
