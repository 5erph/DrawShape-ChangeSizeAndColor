package com.dkg.casey.lab12_caseyloria;


import android.app.Application;

public class RGBStats extends Application
{
private int red=0,green=255,blue=255;
private boolean shrink = false,expand=false, triangle=true, rectangle=false, circle = false;

public int GetRed()
{
    return red;
}
    public int GetBlue()
    {
        return blue;
    }
    public int GetGreen()
    {
        return green;
    }

    public void SetColors(int Red, int Green, int Blue)
    {
        this.red = Red;
        this.green=Green;
        this.blue=Blue;
    }


    public boolean GetShrink()
    {
        return shrink;
    }

    public boolean GetExpand()
    {
        return expand;
    }

    public void SetShrinkExpand(boolean Shrink, boolean Expand)
    {
        this.shrink = Shrink;
        this.expand=Expand;
    }

    public boolean GetTriangle() {
        return triangle;
    }

    public boolean GetRectangle()
    {
        return  rectangle;
    }

    public boolean GetCircle() {
        return circle;
    }

    public void SetShape(boolean Triangle,boolean Rectangle, boolean Circle)
    {
        this.triangle = Triangle;
        this.rectangle = Rectangle;
        this.circle = Circle;
    }

}
