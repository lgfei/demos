package com.lgfei.demo.ssh.verfication;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 生成验证码实现类
 * @author lgfei
 */
public class VerificationCode {
	
	private int digit;
	private String code;
	private BufferedImage image;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public VerificationCode(int digit){
		this.code = generationCode(digit);
		this.image = generateImage(this.getCode());
	}
	
	/**
	 * 生成验证码
	 * @param digit 验证码的位数
	 * @return
	 */
	private String generationCode(int digit){
		char[] letterArr = {
				'0','1','2','3','4','5','6','7','8','9',
				'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < digit; i++){
			int random = (int)(36*Math.random());
			s.append(letterArr[random]);
		}
		return s.toString();
	}
	
	/**
	 * 生成验证码图片
	 */
	private BufferedImage generateImage(String code){
		int width = 70;
		int height = 40;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//		WritableRaster raster = image.getRaster();
//		ColorModel model = image.getColorModel();
//		Color color = Color.WHITE;
//		int rgb = color.getRGB();
//		Object colorData = model.getDataElements(rgb, null);
//		
//		for(int i=0;i<width;i++){
//			for(int j=0;j<height;j++){
//				raster.setDataElements(i, j, colorData);
//			}
//		}
		
		char[] codeArr = code.toCharArray();
		
		Graphics2D pic = image.createGraphics();
		//填充背景色
		pic.setColor(new Color(255,255,255));//白色
		pic.fillRect(0, 0, width, height);
		//设置验证码样式
		pic.setFont(new Font("Serif",Font.ITALIC,28));
		pic.setColor(Color.BLACK);
		pic.drawString(String.valueOf(codeArr[0]), 20, 25);
		
		pic.setFont(new Font("Serif",Font.PLAIN,28));
		pic.setColor(Color.RED);
		pic.drawString(String.valueOf(codeArr[1]), 30, 35);
		
		pic.setFont(new Font("Serif",Font.ITALIC,28));
		pic.setColor(Color.GREEN);
		pic.drawString(String.valueOf(codeArr[2]), 40, 30);
		
		pic.setFont(new Font("Serif",Font.PLAIN,28));
		pic.setColor(Color.BLUE);
		pic.drawString(String.valueOf(codeArr[3]), 50, 28);
		//生成干扰线
		int xPoints[]={2,4,38,2,20,68,12};
		int yPoints[]={4,38,2,20,39,12,40};
		int nPoints = 6;
		pic.drawPolyline(xPoints, yPoints, nPoints);
		
		return image;
	}
}
