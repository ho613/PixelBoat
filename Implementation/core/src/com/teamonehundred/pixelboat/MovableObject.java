package com.teamonehundred.pixelboat;

public abstract class MovableObject extends GameObject {
    /* ################################### //
                   ATTRIBUTES
    // ################################### */

    float max_speed = 10;
    float speed = 0;
    float drag = .05f;  // amount speed is reduced by every frame naturally
    float acceleration = .2f;
    float rotation_speed = 2.f;

    /* ################################### //
                  CONSTRUCTORS
    // ################################### */

    MovableObject(int x, int y, int w, int h, String texture_path) {
        super(x, y,  w,  h, texture_path);
    }

    /* ################################### //
                    METHODS
    // ################################### */

    // turn left (1) or right (-1)
    public void turn(int amount) {
        sprite.rotate(amount*rotation_speed);
    }

    // move forwards x in whatever direction currently facing
    // return amount moved in y for camera scrolling
    private double move(float distance) {
        double dy = Math.cos((Math.toRadians(sprite.getRotation()))) * distance;
        double dx = Math.sin((Math.toRadians(sprite.getRotation()))) * distance;

        sprite.translate((float) (-dx), (float) dy);

        return dy;
    }

    public double updatePosition(){
        double ret = move(speed);
        speed -= speed - drag < 0 ? speed : drag;
        return ret;
    }

    public void accelerate(){
        speed += speed >= max_speed ? 0 : acceleration;
    }
}