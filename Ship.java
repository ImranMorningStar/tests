package com.thrus.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;



public class Ship {

    Sprite shipSprite;
    Redemption redemption;
    float w = Cnst.width;
    float h = Cnst.height;
    public float maxSpeed,accn,dccn,accTimer,rotSpeed,rad,dx,dy,x,y,spw=0,sph=0,pointRad;
    public  boolean up,down,left,right,isTouched;
    thrust th;
    newThrust nth;
    float thrustVel;

    public Ship(Redemption re){

       // maxSpeed = 25;
        accn = 1000;
        dccn = 200;
        rad = 3.1415f/2f;
        rotSpeed = 4;
        x = w/2;
        y=h/2;
        this.redemption = re;
        th=new thrust(redemption);
        nth=new newThrust();
        shipSprite = new Sprite(redemption.loader.shipTex);
        spw = shipSprite.getWidth()/2;
        sph = shipSprite.getHeight()/2;
        shipSprite.setScale(1/3f);

    }

    public void phy(float dt)
    {
        
        maxSpeed=1000f*pointRad;
        
        if(isTouched){
            
        dx+=MathUtils.cosDeg(shipSprite.getRotation()+90)*accn*dt;
        dy+=MathUtils.sinDeg(shipSprite.getRotation()+90)*accn*dt;
            thrustVel=pointRad*10;
            //10f+((float) Math.sqrt(dx*dx + dy*dy)/50f);
        }
        else{thrustVel=0;}
        //MathUtils.lerp(thrustVel,0,10*dt);}
        float vec = (float) Math.sqrt(dx*dx + dy*dy);
        if (vec>maxSpeed){
            dx= (dx/vec)*maxSpeed;
            dy= (dy/vec)*maxSpeed;
        }
       
            if (vec>0){
                dx-=(dx/vec)*dccn*dt;
                dy-=(dy/vec)*dccn*dt;
            }
        
        
        
        x+=dx*dt;
        y+=dy*dt;
        
        nth.setVel(thrustVel);
        nth.setPoints(x,y,shipSprite.getRotation());
        nth.update(1/60f);
        shipSprite.setPosition(x-spw,y-sph);
       // wrap();
        
    }
        
        
        
        
    
    
    
    public void move(float touchpadX, float touchpadY,boolean t) {
        isTouched=t;
        nth.moo(true);
       // x = x+(touchpadX * maxSpeed);
      //  y =( y+(touchpadY * maxSpeed) );
        
        pointRad=(float)Math.sqrt(Math.pow(touchpadX,2)+Math.pow(touchpadY,2));
        
        //nth.setVel((float)Math.sqrt(Math.pow(touchpadX,2)+Math.pow(touchpadY,2)));
        
        if (touchpadX != 0 && touchpadY != 0) {
            
            //nth.moo(true);
            float rotation = (float) Math.atan2(touchpadX, -touchpadY) * MathUtils.radiansToDegrees;
            shipSprite.setRotation(rotation-180);
        }
    }

    
    
    public float getX() {
        return x-spw;
    }

    public float getY() {
        return y-sph;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
    public  void drth(ShaderProgram s){
        
        th.createThrust(s);
    }
    
    public void drawthrust(ShaderProgram s){
        nth.drawThrust(s);
    }

    public void draw(SpriteBatch batch){


       shipSprite.draw(batch);
//        System.out.println(shipSprite.getRotation());
//        Gdx.app.log("ang",);

    }
    public void update(float dt){
      //  th.setX(x);
     //   th.setY(y);
//        if(left){
//            rad+=rotSpeed*dt;
//        } else if (right) {
//            rad -=rotSpeed*dt;
//        }
//        if(up){
//            nth.moo(true);
//            dx+= MathUtils.cos(rad)*accn*dt;
//            dy+= MathUtils.sin(rad)*accn*dt;
//            accTimer+=dt;
//            if (accTimer>0.1f){
//                accTimer=0f;
//            }
//        } else {
//            nth.moo(false);
//            accTimer=0f;
//        }
//        //deccn
//        float vec = (float) Math.sqrt(dx*dx + dy*dy);
//        if (vec>0){
//            dx-=(dx/vec)*dccn*dt;
//            dy-=(dy/vec)*dccn*dt;
//        }
//        if (vec>maxSpeed){
//            dx= (dx/vec)*maxSpeed;
//            dy= (dy/vec)*maxSpeed;
//        }
//        //setpos
//        x+=dx*dt;
//        y+=dy*dt;
       // shipSprite.setPosition(x-spw,y-sph);
       // shipSprite.setRotation((MathUtils.radiansToDegrees*rad)-90);
        
       // th.update(this.x,this.y,shipSprite.getRotation());
       
    }
    public float vx(){
        return dx;
    }
    public float vy(){
        return dy;
    }

    private void wrap() {
        if (x<0) x = w;
        if (x>w) x=0;
        if (y<0) y=h;
        if (y>h) y=0;
    }

}
