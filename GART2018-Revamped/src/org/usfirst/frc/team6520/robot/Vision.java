package org.usfirst.frc.team6520.robot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision {
	
	Thread m_visionThread;
	Spark left = new Spark(0);
	Spark right = new Spark(1);
	double centerX = 0;
	
	public void startVision(){
		m_visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(320, 240);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream
					= CameraServer.getInstance().putVideo("Vision", 320, 240);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();
			Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
			List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
			Mat hierarchy = new Mat();
			boolean ran = false;
			
			int RB = 0;
			int G = 140;
			
			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				
//				//user code
				Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HSV);
				Core.inRange(mat, new Scalar(RB, G, RB), new Scalar(255, 255, 255), mat);
				SmartDashboard.putNumber("RB Channels", RB);
				SmartDashboard.putNumber("G Channel", G);
//				Imgproc.erode(mat, mat, kernel);
				Imgproc.dilate(mat, mat, kernel);
//				Imgproc.dilate(mat, mat, kernel);
				
				Imgproc.findContours(mat, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
//				display = new Mat();
//				Imgproc.drawContours(mat, contours, -1, new Scalar(255, 0, 0), 1);
				for (int i = 0; i < contours.size(); i++){
					Rect boundRect = Imgproc.boundingRect(contours.get(i));
//					float ratio = (float) (Imgproc.contourArea(contours.get(i))/(boundRect.width*boundRect.height));
					if (Imgproc.contourArea(contours.get(i)) > 8000){
						
						centerX = boundRect.x + (boundRect.width / 2);
						double centerY = boundRect.y + (boundRect.height / 2);
						
//						Imgproc.drawContours(mat, contours, i, new Scalar(255, 0, 0), 1);
						Imgproc.circle(mat, new Point(centerX, centerY), 1, new Scalar (255,0,0));
//						System.out.println(centerX + ", " + centerY);
					}
				}
				

				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		m_visionThread.setDaemon(true);
		m_visionThread.start();
	}
}
