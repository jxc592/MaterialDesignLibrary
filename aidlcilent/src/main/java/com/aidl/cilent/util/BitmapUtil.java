package com.aidl.cilent.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BitmapUtil {
	static Context mContext = null;

	public static void init(Context mCon) {
		mContext = mCon;
	}

	public static final Bitmap getLittleimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 50f;// 这里设置高度为800f
		float ww = 40f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 8;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return bitmap;// 压缩好比例大小后再进行质量压缩
	}
	
	public static final Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 50;
		while (baos.toByteArray().length / 1024 > 50) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 5;// 每次都减少10
		}
		image.recycle();
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	public static final Bitmap compressImageinto(Bitmap image, String file) {
		// FileOutputStream fileOutputStream=new FileOutputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 50;
		while (baos.toByteArray().length / 1024 > 50) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 5;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	public static final Bitmap getimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 840f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 3;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	public static final Bitmap comp(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());

		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * 压缩图片
	 * 
	 * @param bitmap
	 * @param newWidth
	 * 
	 */
	public static Bitmap ResizeBitmap(Bitmap bitmap, int newWidth) {
		Bitmap resizedBitmap = null;
		try {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			float temp = ((float) height) / ((float) width);
			int newHeight = (int) ((newWidth) * temp);
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;
			Matrix matrix = new Matrix(); // resize the bit map
			matrix.postScale(scaleWidth, scaleHeight);
			resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
					matrix, true);
			bitmap.recycle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return resizedBitmap;

	}

    /**

     * @param bitmap      原图
     * @param edgeLength  希望得到的正方形部分的边长
     * @return  缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength)
    {
        if(null == bitmap || edgeLength <= 0)
        {
            return  null;
        }

        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if(widthOrg > edgeLength && heightOrg > edgeLength)
        {
            //压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (int)(edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try{
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            }
            catch(Exception e){
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try{
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            }
            catch(Exception e){
                return null;
            }
        }

        return result;
    }

	
	public static final Bitmap getTargetimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 250f;// 这里设置高度为800f
		float ww = 300f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}
	/**
	 * 压缩图片想要的像素
	 * 
	 * @param bitmap
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static Bitmap ResizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float temp = ((float) height) / ((float) width);
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix(); // resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = null;
		try {
			resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
					matrix, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			BitmapUtil.recycleBitmap(bitmap);
		}
		return resizedBitmap;

	}

	/**
	 * 把图片从初始地址放入指定地址
	 * 
	 * @param fromFile
	 * @param toFile
	 * @param width
	 * @param height
	 * @param quality
	 */
	static Bitmap bitmap = null;
	static Bitmap bitmapResize = null;

	public static boolean transImage(String fromFile, String toFile, int width,
			int height, int quality) {
		try {
			System.gc();// 提醒系统及时回收
			bitmap = BitmapUtil.getBitmapFromFile(fromFile);
			if (bitmap == null) {
				// ToastUtil.showLongMessage("拍照出错请重新拍");
				return false;
			}
			bitmapResize = BitmapUtil.ResizeBitmap(bitmap, width, height);
			System.gc();// 提醒系统及时回收
			if (bitmapResize == null) {
				// ToastUtil.showLongMessage("拍照出错请重新拍");
				return false;
			}
			// save file
			File myCaptureFile = new File(toFile);
			FileOutputStream out = new FileOutputStream(myCaptureFile);
			if (bitmapResize.compress(CompressFormat.JPEG, quality, out)) {
				out.flush();
				out.close();
			}
			try {
				if (!bitmap.isRecycled()) {
					bitmap.recycle();// 记得释放资源，否则会内存溢出
					bitmap = null;
					System.gc(); // 提醒系统及时回收
				}
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
			try {
				if (!bitmapResize.isRecycled()) {
					bitmapResize.recycle();
					bitmapResize = null;
					System.gc(); // 提醒系统及时回收
				}
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 把图片按指定名称保存到指定地址
	 * 
	 * @param bitmap
	 * @param dir
	 * @param fileName
	 */
	public static void saveBitmap(Bitmap bitmap, String dir, String fileName) {
		File file = new File(dir, fileName);
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			if (bitmap.compress(CompressFormat.PNG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把图片转换成字节
	 * 
	 * @param bm
	 * @return
	 */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * 把图片保存到指定地址
	 * 
	 * @param bitmap
	 * @param filePath
	 */
	public static void saveBitmap(Bitmap bitmap, String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (file.exists()) {
			FileOutputStream out;
			try {
				out = new FileOutputStream(file);
				if (bitmap.compress(CompressFormat.JPEG, 100, out)) {
					out.flush();
					out.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// BitmapUtil.recycleBitmap(bitmap);
	}

	/**
	 * 根据路径与名称获取图片
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static Bitmap getBitmapFromFile(String dir, String fileName) {
		try {
			InputStream is = new FileInputStream(new File(dir, fileName));
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			options.inSampleSize = 1; // width，hight设为原来的十分一
			Bitmap btp = BitmapFactory.decodeStream(is, null, options);
			return btp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// 计算图片的缩放值
	public static int calculateInSampleSize(int reqWidth, int reqHeight) {
		final int height = 2522;
		final int width = 1944;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 根据路径获取图片
	 * 
	 * @param filePath
	 * @return
	 */
	public static Bitmap getBitmapFromFile(String filePath) {
		try {
			File mFile = new File(filePath);
			if (mFile.exists()) {
				InputStream is = new FileInputStream(new File(filePath));
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Config.RGB_565;
				options.inPurgeable = true;
				options.inInputShareable = true;
				options.inJustDecodeBounds = false;
				options.inSampleSize = 3; // width，hight设为原来的十分一
				Bitmap btp = BitmapFactory.decodeStream(is, null, options);
				return btp;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 读取sdcard卡目录下的图片
	 * 
	 * @param filePath
	 * @return
	 */
	public static Bitmap getBitmapFromSdcard(String filePath) {
		File mFile = new File(filePath);
		// if (mFile.exists()) {
		try {
			InputStream is = new FileInputStream(mFile);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			Bitmap btp = BitmapFactory.decodeStream(is, null, options);
			return btp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		return null;

	}

	/*
	 * 通过URI获取远程的Bitmap
	 */
	public static final Bitmap returnBitMap(String url) {

		URL myFileUrl = null;

		Bitmap bitmap = null;

		try {

			myFileUrl = new URL(url);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		}

		try {

			HttpURLConnection conn = (HttpURLConnection) myFileUrl

			.openConnection();

			conn.setDoInput(true);

			conn.connect();

			InputStream is = conn.getInputStream();

			bitmap = BitmapFactory.decodeStream(is);

			is.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return bitmap;

	}

	/*
	 * 通过URI获取远程加密过的Bitmap
	 * 
	 * public static final String returnSmmsBitMap(String url,int renyuanId) {
	 * 
	 * URL myFileUrl = null;
	 * 
	 * // Bitmap bitmap = null; String fileName =
	 * FileUtil.getPathForFile(renyuanId, 1).replace(".jpg", ".smms");
	 * 
	 * try {
	 * 
	 * myFileUrl = new URL(url);
	 * 
	 * } catch (MalformedURLException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * try {
	 * 
	 * HttpURLConnection conn = (HttpURLConnection) myFileUrl .openConnection();
	 * conn.setConnectTimeout(10000); conn.setReadTimeout(60000);
	 * conn.setDoInput(true); conn.connect(); InputStream is =
	 * conn.getInputStream();
	 * 
	 * //保存成文件 File file=new File(fileName);
	 * 
	 * if(!file.exists()) { MyLog.addMyLog("照片下载成功内容是："+fileName);
	 * file.createNewFile();
	 * 
	 * OutputStream os = new FileOutputStream(file);
	 * 
	 * int bytesRead = 0;
	 * 
	 * byte[] buffer = new byte[8192];
	 * 
	 * while ((bytesRead = is.read(buffer)) != -1) {
	 * 
	 * os.write(buffer, 0, bytesRead);
	 * 
	 * } os.close();
	 * 
	 * is.close(); }
	 * 
	 * 
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace(); MyLog.addMyLog("照片下载失败："+e.toString()); fileName =
	 * ""; }
	 * 
	 * return fileName;
	 * 
	 * }
	 */

	/**
	 * 通过URI获取远程的Drawable
	 * 
	 * @param url
	 * @return
	 */
	public static Drawable loadImageFromUrl(String url) {
		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Drawable d = Drawable.createFromStream(i, "src");
		return d;
	}

	/**
	 * 字节数组转换成String
	 * 
	 * @param b
	 * @return
	 */
	public static final String ChangeByteToString(byte[] b) {
		if (b == null) {
			return null;
		}
		String bString = new String(b);
		return bString;
	}

	/**
	 * 图片压缩
	 * 
	 * @param srcBmp
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap getScaleBitmap(Bitmap srcBmp, int width, int height) {
		Bitmap bitmap = null;
		try {
			bitmap = Bitmap.createScaledBitmap(srcBmp, width, height, false);
			// recycleBitmap(srcBmp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * bitmap转换成字节数据的方法
	 * 
	 * @param btm
	 * @return
	 */
	public final static byte[] BitmapChangeByte(Bitmap btm) {
		if (btm == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		btm.compress(CompressFormat.PNG, 100, baos);
		byte[] data2 = baos.toByteArray();
		return data2;
	}

	/**
	 * Bitmap类型转换为byte[]类型
	 * 
	 * @param compressFormat
	 *            (Bitmap.CompressFormat.JPEG)
	 * @param bitmap
	 * @return
	 */
	public byte[] getBitmapToByte(CompressFormat compressFormat, Bitmap bitmap) {
		// BLOB类型
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			// 将Bitmap压缩成PNG编码，质量为100%存储
			bitmap.compress(compressFormat, 100, os);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return os.toByteArray();
	}

	/**
	 * 将byte[]转换成bitmap
	 * 
	 * @param b
	 * @return
	 */
	public final static Bitmap Bytes2Bimap(byte[] b) {
		if (b == null) {
			return null;
		}
		if (b.length != 0) {
			InputStream is = new ByteArrayInputStream(b);
			Bitmap bmp = BitmapFactory.decodeStream(is);
			return bmp;
		} else {
			return null;
		}
	}

	/**
	 * byte[]类型转换为Bitmap类型
	 * 
	 * @param bytes
	 * @return
	 */
	public Bitmap getByteToBitmap(byte[] bytes) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 保存图片在本地
	 * 
	 * @param bitName
	 * @param mBitmap
	 * @throws IOException
	 */

	public void saveMyBitmap(String bitName, Bitmap mBitmap) throws IOException {
		File m = new File("/sdcard/upload/");
		if (!m.exists()) {
			m.mkdir();
		}
		File f = new File("/sdcard/upload/" + bitName + ".png");
		f.createNewFile();

		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 读取资源文件assets目录下图片转换为Bitmap
	 * 
	 * @param path
	 * @param context
	 * @return
	 */
	public static Bitmap getAssetsImageBitmap(String path, Context context) {
		AssetManager assetManager = context.getResources().getAssets();
		Bitmap bitmap = null;
		InputStream is = null;
		try {
			is = assetManager.open(path);
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 读取Sdcard目录下图片转换为Bitmap
	 * 
	 * @param path
	 * @param context
	 * @return
	 */
	static InputStream is = null;
	static Bitmap btp = null;

	public static Bitmap getSdcardImageBitmap(String path) {
		if (path == null || "".equals(path)) {
			return null;
		}

		try {
			is = new FileInputStream(new File(path));
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			options.inSampleSize = 1; // width，hight设为原来的十分一
			btp = BitmapFactory.decodeStream(is, null, options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return btp;
	}

	public static Bitmap getSdcardImageBitmap(String path, int size) {
		if (path == null || "".equals(path)) {
			return null;
		}

		try {
			is = new FileInputStream(new File(path));
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			options.inSampleSize = size; // width，hight设为原来的十分一
			btp = BitmapFactory.decodeStream(is, null, options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return btp;
	}

	/**
	 * 销毁bitmap，回收内存
	 * 
	 * @param mBitmap
	 */
	public static void recycleBitmap(Bitmap mBitmap) {
		if (mBitmap != null && !mBitmap.isRecycled()) {
			mBitmap.recycle();
			mBitmap = null;
			System.gc();
			System.runFinalization();
			System.gc();
		}
	}

	/**
	 * 销毁bitmap，回收内存
	 * 
	 * @param bmps
	 */
	public static void recycleBitmapArr(Bitmap[] bmps) {
		for (int i = 0; i < bmps.length; i++) {
			Bitmap mBitmap = bmps[i];
			if (mBitmap != null && !mBitmap.isRecycled()) {
				mBitmap.recycle();
				mBitmap = null;
				System.gc();
				System.runFinalization();
				System.gc();
			}
		}
	}

	/**
	 * 销毁bitmap，回收内存
	 * 
	 * @param bmps
	 */
	public static void recycleBitmapList(ArrayList<Bitmap> bmps) {
		if (bmps == null || bmps.size() == 0) {
			return;
		}
		for (int i = 0; i < bmps.size(); i++) {
			Bitmap mBitmap = bmps.get(i);
			if (mBitmap != null && !mBitmap.isRecycled()) {
				mBitmap.recycle();
				mBitmap = null;
				System.gc();
				System.runFinalization();
				System.gc();
			}
		}
	}

	/**
	 * 隐藏键盘
	 * 
	 * @param context
	 * @param v
	 */
	public static void HideSoftKey(Context context, View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext()
				.getSystemService(context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
		}
	}

	/**
	 * 得到带圆角的bitmap
	 * 
	 * @param bitmap
	 * @param roundPx
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, w, h);
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 得到带倒影的图片
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
				h / 2, matrix, false);
		Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);
		canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);
		return bitmapWithReflection;
	}

	/**
	 * drawable 转换成bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		// 取 drawable 的颜色格式
		Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
				: Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}

	/*
	 * 将多个Bitmap合并成一个图片。
	 * 
	 * @param int 将多个图合成多少列
	 * 
	 * @param Bitmap... 要合成的图片
	 * 
	 * @return
	 */
	public static Bitmap combineBitmaps(int columns, Bitmap... bitmaps) {
		if (columns <= 0 || bitmaps == null || bitmaps.length == 0) {
			throw new IllegalArgumentException(
					"Wrong parameters: columns must > 0 and bitmaps.length must > 0.");
		}
		int maxWidthPerImage = 40;
		int maxHeightPerImage = 40;
//		for (Bitmap b : bitmaps) {
//			maxWidthPerImage = maxWidthPerImage > b.getWidth() ? maxWidthPerImage
//					: b.getWidth();
//			maxHeightPerImage = maxHeightPerImage > b.getHeight() ? maxHeightPerImage
//					: b.getHeight();
//		}
		int rows = 0;
//		if (columns >= bitmaps.length) {
//			rows = 1;
//			columns = bitmaps.length;
//		} else {
////			rows = bitmaps.length % columns == 0 ? bitmaps.length / columns
////					: bitmaps.length / columns + 1;
//			rows = 2;
//		}
		
		rows = 2;
		Bitmap newBitmap = Bitmap.createBitmap(columns * maxWidthPerImage, rows
				* maxHeightPerImage, Config.RGB_565);

		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				int index = x * columns + y;
				if (index >= bitmaps.length)
					break;
				newBitmap = mixtureBitmap(newBitmap, bitmaps[index],
						new PointF(y * maxWidthPerImage, x * maxHeightPerImage));
			}
		}
		return newBitmap;
	}

	/**
	 * Mix two Bitmap as one.
	 * 
	 * @param first
	 * @param second
	 * @param fromPoint
	 *            where the second bitmap is painted.
	 * @return
	 */
	public static Bitmap mixtureBitmap(Bitmap first, Bitmap second,
			PointF fromPoint) {
		if (first == null || second == null || fromPoint == null) {
			return null;
		}
		Bitmap newBitmap = Bitmap.createBitmap(first.getWidth(),
				first.getHeight(), Config.ARGB_4444);
		Canvas cv = new Canvas(newBitmap);
		cv.drawBitmap(first, 0, 0, null);
		cv.drawBitmap(second, fromPoint.x, fromPoint.y, null);
		cv.save(Canvas.ALL_SAVE_FLAG);
		cv.restore();
		return newBitmap;
	}

	/**
	 * 资源文件变成bitmap
	 * 
	 * @param id
	 */
	public static final Bitmap resourceToBitmap(int id) {
		Resources res = mContext.getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, id);
		return bmp;
	}

	/**
	 * 2. * 以最省内存的方式读取本地资源的图片 3. * @param context 4. * @param resId 5. * @return
	 * 6.
	 */
	public static Bitmap readBitMap(int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = mContext.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}

	/**
	 * base64转bitmap
	 * 
	 * @param base64
	 * @return
	 */
	public static Bitmap base64ToBitmap(String base64) {
		byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	public static Bitmap base64toBitmap2(String string) {
		// 将字符串转换成Bitmap类型
		Bitmap bitmap = null;
		try {
			byte[] bitmapArray;
			bitmapArray = Base64.decode(string, Base64.DEFAULT);
			bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
					bitmapArray.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	/*
	 * 将多个Bitmap合并成一个图片。
     * 
     * @param int 将多个图合成多少列
     * @param Bitmap... 要合成的图片
     * @return
     */ 
	/*
	public static Bitmap combineBitmaps(int columns, Bitmap... bitmaps) { 
        if (columns <= 0 || bitmaps == null || bitmaps.length == 0) { 
            throw new IllegalArgumentException("Wrong parameters: columns must > 0 and bitmaps.length must > 0."); 
        } 
        int maxWidthPerImage = 0; 
        int maxHeightPerImage = 0; 
        for (Bitmap b : bitmaps) { 
            maxWidthPerImage = maxWidthPerImage > b.getWidth() ? maxWidthPerImage : b.getWidth(); 
            maxHeightPerImage = maxHeightPerImage > b.getHeight() ? maxHeightPerImage : b.getHeight(); 
        } 
        int rows = 0; 
        if (columns >= bitmaps.length) { 
            rows = 1; 
            columns = bitmaps.length; 
        } else { 
            rows = bitmaps.length % columns == 0 ? bitmaps.length / columns : bitmaps.length / columns + 1; 
        } 
        Bitmap newBitmap = Bitmap.createBitmap(columns * maxWidthPerImage, rows * maxHeightPerImage, Config.RGB_565); 
 
        for (int x = 0; x < rows; x++) { 
            for (int y = 0; y < columns; y++) { 
                int index = x * columns + y; 
                if (index >= bitmaps.length) 
                    break; 
                newBitmap = mixtureBitmap(newBitmap, bitmaps[index], new PointF(y * maxWidthPerImage, x * maxHeightPerImage)); 
            } 
        } 
        return newBitmap; 
    } 
    */
}
