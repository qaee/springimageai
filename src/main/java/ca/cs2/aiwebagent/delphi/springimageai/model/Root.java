package ca.cs2.aiwebagent.delphi.springimageai.model;


import lombok.Data;

@Data
public class Root{
    public String model;
    //public Date created_at;
    public Message message;
    public boolean done;
}

