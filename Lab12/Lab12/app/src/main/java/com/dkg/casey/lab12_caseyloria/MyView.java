package com.dkg.casey.lab12_caseyloria;

//CASEY LORIA
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyView extends View {
    private Paint endPaint;
    private Paint linePaint;
    private PointF end0, end1;
    private Path myPath;
    private PointF[] myPoints;
    private PointF movingPoint;
    private float offX, offY;
    private RGBStats rgbStats = new RGBStats();
    private int red,green,blue;
    boolean shrink = false, expand = false;

    GestureDetector gestureDetector;
    private Paint myPaint;
    private int myColor;
    int radius;
    int endStrokeWidth;

    public boolean circle =false,triangle=true, rectangle=false;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // creating new gesture detector
        gestureDetector = new GestureDetector(context, new GestureListener());

        red = rgbStats.GetRed();
        green=rgbStats.GetGreen();
        blue=rgbStats.GetBlue();
        myPaint = new Paint();
        myPaint.setStyle(Paint.Style.STROKE);
        myColor = Color.YELLOW;
        myPaint.setColor(myColor);

        radius = 50;
        shrink = rgbStats.GetShrink();
        expand= rgbStats.GetExpand();

        endPaint = new Paint();
        endPaint.setColor(Color.RED);
        endPaint.setStrokeWidth(endStrokeWidth);
        endPaint.setStrokeCap(Paint.Cap.ROUND);
        endPaint.setStyle(Paint.Style.STROKE);

        linePaint = new Paint();
        linePaint.setColor(Color.rgb(red,green,blue));
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.STROKE);

        end0 = new PointF(300,50);
        end1 = new PointF(300, 250);

        myPath = new Path();

        myPoints = new PointF[]{end0,end1};
        movingPoint = new PointF();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public void setColor(int color)
    {
        color = myColor;
        myPaint.setColor(myColor);
        invalidate();
        postInvalidate();

    }

    public int getColor() {return myColor;}

    // skipping measure calculation and drawing

    // delegate the event to the gesture detector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                myPath.moveTo(event.getX(), event.getY());
                movingPoint = findPoint(event.getX(),event.getY(),myPoints);

                if(movingPoint != null) {
                    offX = movingPoint.x - event.getX();
                    offY = movingPoint.y - event.getY();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                myPath.lineTo(event.getX(), event.getY());
                if(movingPoint != null) {
                    movingPoint.x = event.getX() + offX;
                    movingPoint.y = event.getY() + offY;


                }
                break;
            case MotionEvent.ACTION_UP:
                //myPath.close();
                movingPoint = null;
                break;
        }

        invalidate();

        //return gestureDetector.onTouchEvent(event);
        return  false;
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();

            setRadius(radius += 5);
            Log.d("Double Tap", "Tapped at: (" + x + "," + y + radius + ")");
            Toast.makeText(getContext(), "Double Tapped at (" + x + "," + y + ")", Toast.LENGTH_SHORT).show();


            return true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        shrink = rgbStats.GetShrink();
        expand = rgbStats.GetExpand();
        triangle = rgbStats.GetTriangle();
        circle = rgbStats.GetCircle();
        rectangle = rgbStats.GetRectangle();
        myPaint.setColor(myColor);
        int cx=500,cy=500,radius=100,left=400,top=400,bottom=600,right=750;
        canvas.drawCircle(600,100,radius,myPaint);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        if(circle == true)
        canvas.drawCircle(cx,cy,radius,linePaint);

        if(triangle==true)
        {
                Path path = new Path();
                Point a = new Point(500, 500);
                Point b = new Point(600, 300);
                Point c = new Point(700, 500);

                float[] points = new float[6];

            if(shrink == false && expand == false) {
                points[0] = a.x;
                points[1] = a.y;
                points[2] = b.x;
                points[3] = b.y;
                points[4] = c.x;
                points[5] = c.y;


                //path.setFillType(Path.FillType.EVEN_ODD);
                path.moveTo(500, 500);
                path.lineTo(b.x, b.y);
                path.lineTo(c.x, c.y);
                path.lineTo(a.x, a.y);



            }
            if(shrink == true)
            {
                Toast.makeText(getContext(), "SHRINK!", Toast.LENGTH_SHORT).show();
                points[0] = a.x;
                points[1] = a.y;
                points[2] = b.x;
                points[3] = b.y;
                points[4] = c.x;
                points[5] = c.y;
            }

            if(expand == true)
            {
                Toast.makeText(getContext(), "EXPAND!", Toast.LENGTH_SHORT).show();
                points[0] = a.x;
                points[1] = a.y;
                points[2] = b.x;
                points[3] = b.y;
                points[4] = c.x;
                points[5] = c.y;
            }
            path.close();
            canvas.drawPath(path, linePaint);
        }

        if(rectangle==true)
            canvas.drawRect(left,top,right,bottom,linePaint);

        endPaint.setStrokeWidth(endStrokeWidth);
       // canvas.drawPoint(end0.x,end0.y,endPaint);
       // canvas.drawPoint(end1.x,end1.y,endPaint);

       // canvas.drawLine(end0.x,end0.y,end1.x,end1.y,linePaint);

        canvas.drawPath(myPath, linePaint);
    }

    public PointF findPoint(float x, float y, PointF[] points){
        for(int i=0; i<points.length;i++){
            if(Math.hypot(x - points[i].x, y-points[i].y)<25)
                return points[i];
        }
        return null;
    }
}
