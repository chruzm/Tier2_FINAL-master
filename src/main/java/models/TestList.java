package models;

import com.google.gson.Gson;

public class TestList  {
    private static final Gson gson = new Gson();

    private String testelement;
    private String listnumber;

    public TestList(String number, String element)
    {
        this.listnumber = number;
        this.testelement = element;
    }

    public void setTestelement( String phoneNo )
    {
        this.testelement = testelement;
    }
    public void setListnumber( String name )
    {
        this.listnumber = listnumber;
    }
    public String getTestelement()
    {
        return testelement;
    }
    public String getListnumber()
    {
        return listnumber;
    }
    public String toJson()
    {
        return gson.toJson( this );
    }
    public static TestList fromJson(String json )
    {
        return gson.fromJson( json, TestList.class );
    }
}
