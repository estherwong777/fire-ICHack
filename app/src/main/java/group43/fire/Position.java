package group43.fire;

import android.content.res.Resources;

/**
 * Created by xie on 2018/1/27.
 */

public class Position {

  private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  private int xCoord;
  private int yCoord;

  public Position(int x,int y){
    assert (x <= screenWidth && x >= 0 && y <= screenHeight && y>=0):"impossible input";
    this.xCoord = x;
    this.yCoord = y;
  }

  public Position(){
    this.xCoord = 0;
    this.yCoord = 0;
  }

  public int getWidth(){
    return screenWidth;
  }

  public int getHeight(){
    return screenHeight;
  }

  public int getX (){
    return xCoord;
  }

  public int getY(){
    return yCoord;
  }


}