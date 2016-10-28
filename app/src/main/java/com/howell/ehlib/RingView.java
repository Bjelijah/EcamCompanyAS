package com.howell.ehlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.howell.ecamcompany.R;

public class RingView extends View {

	private final  Paint paint;
	private final Context context;
	
	public RingView(Context context) {
		
		// TODO Auto-generated constructor stub
		this(context, null);
	}

	public RingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.paint = new Paint();
		this.paint.setAntiAlias(true); //消除锯齿
		this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆 
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		int center = getWidth()/2;
		int innerCircle = dip2px(context, 83); //设置内圆半径
		int ringWidth = dip2px(context, 8); //设置圆环宽度
		
		//绘制内圆
//		this.paint.setARGB(155, 167, 190, 206);
//		this.paint.setStrokeWidth(2);
//		canvas.drawCircle(center,center, innerCircle, this.paint);
		Shader mShader =new SweepGradient(center, center, new int[] { getResources().getColor(R.color.end_blue)/*,getResources().getColor(R.color.center_blue)*/
				,getResources().getColor(R.color.start_blue) }, null);
//		= new LinearGradient(0, 0, center, center,  
//                new int[] { getResources().getColor(R.color.end_blue),getResources().getColor(R.color.center_blue)
//				,getResources().getColor(R.color.start_blue)}, null, Shader.TileMode.MIRROR); // 一个材质,打造出一个线性梯度沿著一条线。  
		//绘制圆环
		//this.paint.setARGB(255, 212 ,225, 233);
//		this.paint.setColor(getResources().getColor(R.color.end_blue));
		this.paint.setShader(mShader);  
		this.paint.setStrokeWidth(ringWidth);
		canvas.drawCircle(center,center, innerCircle+1+ringWidth/2, this.paint);
		
		//绘制外圆
//		this.paint.setARGB(155, 167, 190, 206);
//		this.paint.setStrokeWidth(2);
//		canvas.drawCircle(center,center, innerCircle+ringWidth, this.paint);

		
		super.onDraw(canvas);
	}
	
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}

