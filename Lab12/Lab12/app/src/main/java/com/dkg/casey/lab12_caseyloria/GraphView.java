package com.dkg.casey.lab12_caseyloria;


//CASEY LORIA
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GraphView extends View {
    private Paint endPaint;
    private Paint linePaint;
    private PointF end0, end1;
    private Path myPath;
    private PointF[] myPoints;
    private PointF movingPoint;
    private float offX, offY;

    int endStrokeWidth;

    private GestureDetector myGestureDetector;

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);

        endStrokeWidth = 25;

        endPaint = new Paint();
        endPaint.setColor(Color.RED);
        endPaint.setStrokeWidth(endStrokeWidth);
        endPaint.setStrokeCap(Paint.Cap.ROUND);
        endPaint.setStyle(Paint.Style.STROKE);

        linePaint = new Paint();
        linePaint.setColor(Color.MAGENTA);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.STROKE);

        end0 = new PointF(100,50);
        end1 = new PointF(100, 250);

        myPath = new Path();

        myPoints = new PointF[]{end0,end1};
        movingPoint = new PointF();


        myGestureDetector = new GestureDetector(context, new gestureListener());


    }



    private class gestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            setEndStrokeWidth(50);
            float x = e.getX();
            float y = e.getY();

            Log.d("Double Tap", "Tapped at: (" + x + "," + y + ")");
            Toast.makeText(getContext(), "Double Tapped at (" + x + "," + y + ")", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public int getEndStrokeWidth() {
        return endStrokeWidth;
    }

    public void setEndStrokeWidth(int endStrokeWidth) {
        this.endStrokeWidth = endStrokeWidth;
        //invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawCircle(500,500,40,linePaint);
        endPaint.setStrokeWidth(endStrokeWidth);
      // canvas.drawPoint(end0.x,end0.y,endPaint);
       // canvas.drawPoint(end1.x,end1.y,endPaint);

       // canvas.drawLine(end0.x,end0.y,end1.x,end1.y,linePaint);

        canvas.drawPath(myPath, linePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                //myPath.moveTo(event.getX(), event.getY());
                movingPoint = findPoint(event.getX(),event.getY(),myPoints);

                if(movingPoint != null) {
                    offX = movingPoint.x - event.getX();
                    offY = movingPoint.y - event.getY();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                //myPath.lineTo(event.getX(), event.getY());
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

        return myGestureDetector.onTouchEvent(event);
    }

    public PointF findPoint(float x, float y, PointF[] points){
        for(int i=0; i<points.length;i++){
            if(Math.hypot(x - points[i].x, y-points[i].y)<25)
                return points[i];
        }
        return null;
    }
}
