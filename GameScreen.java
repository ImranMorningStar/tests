package com.thrus.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.MathUtils;



public class GameScreen implements Screen {

    Redemption redemption;
    Ship ship;
    Hud hud;
    SpriteBatch batch;
    OrthographicCamera cam;
    Viewport view;
    float w = Cnst.width;
    float h = Cnst.height;

    ShaderProgram s;
    Mesh mesh;
    float[] ver = new  float[16];
    
    BackgroundManager man;

    //tets
    Texture tex;

    public GameScreen(Redemption redemption){
        this.redemption=redemption;

    }



    @Override
    public void show() {

        ship = new Ship(redemption);
      
        

        tex = new Texture(Gdx.files.internal("badlogic.jpg"));
        cam = new OrthographicCamera();
        view = new FitViewport(w,h,cam);
        cam.position.set(w/2,h/2,0);
        //cam.zoom=8;
        cam.update();
        mesh = new Mesh(true,4,0,
                new VertexAttribute(VertexAttributes.Usage.Position
        ,2,"a_position") ,
                new VertexAttribute(VertexAttributes.Usage.TextureCoordinates
                        ,2,"a_texCoord")
        );
        ver = new float[]{
                0 , 0 , 0 , 1 ,
                0 , 100 , 0 , 0 ,
                100 , 0 , 1 , 1 ,
                100 , 100 , 1 , 0

        };
        mesh.setVertices(ver);
        s = new ShaderProgram(redemption.loader.vert,redemption.loader.frag);

        batch = new SpriteBatch();
        man = new BackgroundManager(redemption);

        hud=new Hud(redemption,batch);
        Gdx.input.setInputProcessor(hud.getStage());



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        cam.update();
//        ship.update(delta);
        update(delta);
        
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA,GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glActiveTexture(GL30.GL_TEXTURE0);
        // Gdx.gl30.glActiveTexture(GL30.GL_TEXTURE0);
        redemption.loader.th.bind(0);
        s.begin();
        s.setUniformi("u_texture",0);
        s.setUniformMatrix("u_projTrans",cam.combined);
//        mesh.render(s,GL30.GL_TRIANGLE_STRIP);

        ship.drawthrust(s);
        // ship.drth(s);
        s.end();
        
        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        man.draw(batch);
//        batch.draw(tex,0,0,w,h);
        ship.draw(batch);
        batch.end();
        
        batch.setProjectionMatrix(hud.getStage().getCamera().combined); //set the spriteBatch to draw what our stageViewport sees
        hud.getStage().act(delta); //act the Hud
        hud.getStage().draw();
        
        

    }

    private void update(float dt) {
        ship.move(hud.touchpad.getKnobPercentX(),hud.touchpad.getKnobPercentY(),hud.touchpad.isTouched());
        ship.phy(dt);
       // handleinput();
       ship.setLeft(hud.left);
       ship.setRight(hud.right);
       ship.setUp(hud.up);
       // ship.update(dt);
        cam.position.x=MathUtils.lerp(cam.position.x,ship.x,1*dt);
        cam.position.y=MathUtils.lerp(cam.position.y,ship.y,2.5f*dt);
        
        //cam.position.set(ship.x,ship.y,0);
     //   cam.position.set(ship.getX()-((70f*ship.vx()*dt)*(w/h)),ship.getY()-((100f*ship.vy()*dt)*(h/w)),0);
//        cam.position.x-=(20*ship.vx()*dt);
//        cam.position.y-=(20*ship.vy()*dt);
        cam.update();
        man.manageBG((int)ship.getX(),(int)ship.getY());
        
    }
/*
    private void handleinput() {
        ship.setLeft(GameKeys.isDown(GameKeys.LEFT));
        ship.setRight(GameKeys.isDown(GameKeys.RIGHT));
        ship.setUp(GameKeys.isDown(GameKeys.UP));
//        ship.setUp(Gdx.input.isTouched());
    }
*/

    @Override
    public void resize(int width, int height) {
    view.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        man.dispose();
        s.dispose();

    }
}
