package com.example.firefly.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gms {
    public String w;
    public String y;
    public String t;
    public String gd;
    public String bph;
    @SerializedName("g")
    public List<G> g;
}
