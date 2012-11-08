/* Copyright 2012 Michael Haar

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.dngames.mobilewebcam;

import java.util.List;

import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;

public class NewCameraFunctions
{
	private static boolean mNewCameraAvailable; // front camera API level 9
	private static boolean mPictureSizesAvailable; // picture sizes API level 5
	
	/* establish whether the "new" class is available to us */
	static
	{
		try
		{
			NewCameraWrapper.checkAvailable();
			mNewCameraAvailable = true;
		}
		catch (Throwable t)
		{
			mNewCameraAvailable = false;
		}

		try
		{
			NewPictureSizesWrapper.checkAvailable();
			mPictureSizesAvailable = true;
		}
		catch (Throwable t)
		{
			mPictureSizesAvailable = false;
		}
	}

	public static List<Size> getSupportedPictureSizes(Camera.Parameters params)
	{
		if(mPictureSizesAvailable)
			return NewPictureSizesWrapper.getSupportedPictureSizes(params);

		return null;
	}

	public static void setPictureSize(Camera.Parameters params, int w, int h)
	{
		if(mPictureSizesAvailable)
			NewPictureSizesWrapper.setPictureSize(params, w, h);
	}
	
	public static int getNumberOfCameras()
	{
		if(mNewCameraAvailable)
			return NewCameraWrapper.getNumberOfCameras();
		
		return 1;
	}
	
	public static Camera openFrontCamera()
	{
		if(mNewCameraAvailable)
			return NewCameraWrapper.openFrontCamera();

		return null;
	}
	
	public static boolean isZoomSupported(Camera.Parameters params)
	{
		if(mNewCameraAvailable)
			return NewCameraWrapper.isZoomSupported(params);
		
		return false;
	}
	
	public static void setZoom(Camera.Parameters params, int zoom)
	{
		if(mNewCameraAvailable)
			NewCameraWrapper.setZoom(params, zoom);
	}

	public static List<String> getSupportedWhiteBalance(Camera.Parameters params)
	{
		if(mNewCameraAvailable)
			return NewPictureSizesWrapper.getSupportedWhiteBalance(params);
		
		return null;
	}
	
	public static void setWhiteBalance(Camera.Parameters params, String balance)
	{
		if(mNewCameraAvailable)
			NewPictureSizesWrapper.setWhiteBalance(params, balance);
	}
}