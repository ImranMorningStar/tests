package com.thrus.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FileLoader {
    public Texture shipTex,th,btup,btrig,btlef,starTex;
    public String vert,frag;
    public FileLoader(){
        th = new Texture(Gdx.files.internal("thrust/f.png"));
        btup = new Texture(Gdx.files.internal("bt/up.png"));
        btlef = new Texture(Gdx.files.internal("bt/lef.png"));
        btrig = new Texture(Gdx.files.internal("bt/rig.png"));
        shipTex = new Texture(Gdx.files.internal("ship/rocket.png"));
        starTex = new Texture(Gdx.files.internal("bg/star2.png"));
        vert = Gdx.files.internal("shaders/vert.glsl").readString();
        frag = Gdx.files.internal("shaders/frag.glsl").readString();

    }

}
